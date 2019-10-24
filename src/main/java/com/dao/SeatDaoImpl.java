package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.dto.FlightClass;
import com.dto.Seat;
import com.dto.SeatLocation;
import com.dto.SeatStatus;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.DatabaseUtil;
import com.util.EnumUtil;

/**
 * @author Chenghao Cai
 * @fileName SeatDaoImpl.java
 * @date Oct 20, 2019
 */

public class SeatDaoImpl implements SeatDao {
	public final static int COLFIRST = 5;
	public final static int COLBUSINESS = 5;
	public final static int COLECONOMY = 10;

	@Override
	public Seat getSeatById(String seatId, int flightId) throws DatabaseException, FileException {
		ResultSet set = null;
		Seat seat = null;

		String sql = "select seat_id, flight_id, class,  seat_status, seat_location "
				+ "from seat where seat_id = ? and flight_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, seatId);
			ps.setInt(2, flightId);
			set = ps.executeQuery();

			if (set.next()) {
				seat = new Seat(seatId, flightId, EnumUtil.stringToFlightClass(set.getString("class")),
						EnumUtil.stringToSeatStatus(set.getString("seat_status")),
						EnumUtil.stringToSeatLocation(set.getString("seat_location")));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get seat information: " + e.getMessage());
		}
		return seat;
	}

	@Override
	public int alterSeatStatus(Seat seat) throws DatabaseException, FileException, InputException {
		int row = 0;
		String sql = "update seat set seat_status = ? where seat_id = ? and flight_id = ? and seat_status = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			if (seat.getSeatStatus() == SeatStatus.AVAILABLE) {
				ps.setString(1, SeatStatus.UNAVAILABLE.toString());
			} else if (seat.getSeatStatus() == SeatStatus.UNAVAILABLE) {
				ps.setString(1, SeatStatus.AVAILABLE.toString());
			}
			ps.setString(2, seat.getSeatId());
			ps.setInt(3, seat.getFlightId());
			ps.setString(4, seat.getSeatStatus().toString());
			row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to update seat information.");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to update seat information: " + e.getMessage());
		}
		return row;
	}

	@Override
	public int addSeats(int flightId, int firstCap, int businessCap, int economyCap)
			throws DatabaseException, FileException {
		int row = 0;
		int[] cap = { firstCap, businessCap, economyCap };
		int[] rows = { (int) Math.ceil((double)firstCap / COLFIRST), 
				(int) Math.ceil((double)businessCap / COLBUSINESS),
				(int) Math.ceil((double)economyCap / COLECONOMY) };
		int[] cols = { COLFIRST, COLBUSINESS, COLECONOMY };
		try (Connection conn = DatabaseUtil.getConnection();) {
			int r = 1;
			for (int i = 0; i < rows.length; i++) { // 3 class
				int count = 0;
				for (int j = 0; j < rows[i]; j++) { // rows
					for (int k = 0; k < cols[i]; k++) { // columns
						if (count < cap[i]) {
							char column = (char) ('A' + k);

							String seatId = r + String.valueOf(column);
							FlightClass flightClass = FlightClass.ECONOMYCLASS;
							SeatLocation seatLocation = SeatLocation.OTHERS;
							switch (i) {
							case 0:
								flightClass = FlightClass.FIRSTCLASS;
								if (column == 'A' || column == 'E') {
									seatLocation = SeatLocation.WINDOW;
								}
								if (column == 'B' || column == 'C' || column == 'D') {
									seatLocation = SeatLocation.AISLE;
								}
								break;
							case 1:
								flightClass = FlightClass.BUSINESSCLASS;
								if (column == 'A' || column == 'E') {
									seatLocation = SeatLocation.WINDOW;
								}
								if (column == 'B' || column == 'C' || column == 'D') {
									seatLocation = SeatLocation.AISLE;
								}
								break;
							default:
								flightClass = FlightClass.ECONOMYCLASS;
								if (column == 'A' || column == 'J') {
									seatLocation = SeatLocation.WINDOW;
								} else if (column == 'C' || column == 'D' || column == 'G' || column == 'H') {
									seatLocation = SeatLocation.AISLE;
								}
								break;
							}

							Seat seat = new Seat(seatId, flightId, flightClass, SeatStatus.AVAILABLE, seatLocation);
							String sql = "insert into seat(seat_id, flight_id, class, seat_status,seat_location) "
									+ "values (?, ?, ?, ?, ?)";
							try (PreparedStatement ps = conn.prepareStatement(sql);) {
								ps.setString(1, seat.getSeatId());
								ps.setInt(2, seat.getFlightId());
								ps.setString(3, seat.getFlightClass().toString());
								ps.setString(4, seat.getSeatStatus().toString());
								ps.setString(5, seat.getSeatLocation().toString());
								row = ps.executeUpdate();

								if (row == 0) {
									throw new DatabaseException("Unable to insert seat information.");
								}

							} catch (SQLException e) {
								throw new DatabaseException("Unable to insert seat information: " + e.getMessage());
							}
							count++;
						}
					}
					r++;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to insert seat information: " + e.getMessage());
		}
		return row;
	}

	@Override
	public int deleteFlightSeats(int flightId) throws DatabaseException, FileException, InputException {
		int row = 0;
		String sql = "delete from seat where flight_id = ? ";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, flightId);
			row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to update seat information.");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to update seat information: " + e.getMessage());
		}
		return row;
	}
	@Override
	public int[] getRowsForClasses(int flightId, int firstCap, int businessCap, int economyCap) {
		int[] rows = { (int) Math.ceil((double)firstCap / COLFIRST), 
				(int) Math.ceil((double)businessCap / COLBUSINESS),
				(int) Math.ceil((double)economyCap / COLECONOMY) };
		return rows;
	}
	@Override
	public ArrayList<ArrayList<String>> getSeatLayout(int flightId, int firstCap, int businessCap, int economyCap) {
		ArrayList<ArrayList<String>> layout= new ArrayList<>();
		
		
		// F/B/E-W/A/O-11A   A
		int[] cap = { firstCap, businessCap, economyCap };
		int[] rows = { (int) Math.ceil((double)firstCap / COLFIRST), 
				(int) Math.ceil((double)businessCap / COLBUSINESS),
				(int) Math.ceil((double)economyCap / COLECONOMY) };
		int[] cols = { COLFIRST, COLBUSINESS, COLECONOMY };
		int r = 1;
		for (int i = 0; i < rows.length; i++) { // 3 class
			int count = 0;
			for (int j = 0; j < rows[i]; j++) { // rows
				ArrayList<String> rowL=new ArrayList<>();
				for (int k = 0; k < cols[i]; k++) { // columns
					if (count < cap[i]) {
						char column = (char) ('A' + k);

						String seatId = r + String.valueOf(column);
						String cl="";
						String location="O";
						
						switch (i) {
						case 0:
							cl="F";
							if (column == 'A' || column == 'E') {
								location = "W";
							}
							if (column == 'B' || column == 'C' || column == 'D') {
								location = "A";
							}
							if (column == 'C' || column == 'D') {
								rowL.add("AC");  //aisle column
							}
							break;
						case 1:
							cl="B";
							if (column == 'A' || column == 'E') {
								location = "W";
							}
							if (column == 'B' || column == 'C' || column == 'D') {
								location = "A";
							}
							if (column == 'C' || column == 'D') {
								rowL.add("AC"); 
							}
							break;
						default:
							cl="E";
							if (column == 'A' || column == 'J') {
								location = "W";
							} else if (column == 'C' || column == 'D' || column == 'G' || column == 'H') {
								location = "A";
							}
							if (column == 'D' || column == 'H') {
								rowL.add("AC"); 
							}
							break;
						}
						String code=cl+"-"+location+"-"+seatId;
						rowL.add(code);
						
					}
					count++;
				}
				layout.add(rowL);				
				r++;
			}
			ArrayList<String> aline=new ArrayList<>();				
			layout.add(aline);	
			aline.add("AL"); //seperator lane for classes
		}
		
		return layout;
	}

	
	@Override
	public HashSet<String> getAvailableSeats(int flightId) throws DatabaseException, FileException, InputException {
		HashSet<String> seats=new HashSet<>();
		ResultSet set = null;
		//Seat seat = null;

		String sql = "select seat_id from seat where flight_id = ? and seat_status='AVAILABLE'";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, flightId);
			set = ps.executeQuery();

			while (set.next()) {
				/*seat = new Seat(set.getString("seat_id"), flightId, 
						EnumUtil.stringToFlightClass(set.getString("class")),
						EnumUtil.stringToSeatStatus(set.getString("seat_status")),
						EnumUtil.stringToSeatLocation(set.getString("seat_location")));*/
				seats.add(set.getString("seat_id"));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get seat information: " + e.getMessage());
		}
		return seats;
	}
	
	
	  public static void main(String[] args) throws DatabaseException,
	  	FileException, InputException {
	  //System.out.println(System.getProperty("java.class.path"));
		  SeatDaoImpl impl=new SeatDaoImpl(); 
	//	 for(ArrayList<String> l:impl.getSeatLayout(1, 12, 13, 26)) {
	//		 for(String s: l) {
	//			 System.out.print(s+" ");
	//		 }
	//		 System.out.println();
	//	 }
		 // System.out.println(impl.getAvailableSeats(1).size());
		//  System.out.println(impl.addSeats(1, 12, 13, 26));
	  Seat seat=impl.getSeatById("6B", 1);
		 
	  System.out.println(impl.alterSeatStatus(seat));
	  //System.out.println(impl.getSeatById("6B", 1).toString());
	 //System.out.println(impl.deleteFlightSeats(1)); 
	 
	  }




}
