package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;


import com.util.DatabaseUtil;

public class From_cityDao extends DatabaseUtil{
	
	
	public ArrayList<String> getCityNames(String find){
		ArrayList<String> list = new ArrayList<String>();
		
		ResultSet set = null;
		String data;
		try {

		    Connection conn = DatabaseUtil.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("select * from flight");
			
			set = ps.executeQuery();
			while(set.next()) {
				 
				 data = set.getString("departure_city");
				 System.out.println(data);
				 list.add(data);
			}	
			
		} catch (Exception e) {
			
		}finally {
			try {
				if (set != null)set.close();
				
			} catch (Exception e) {
				
			}
		}
		
		return list;
	}
	public ArrayList<String> getArrCityNames(String find){
		ArrayList<String> list1 = new ArrayList<String>();
		
		ResultSet set = null;
		String data;
		try {

		    Connection conn = DatabaseUtil.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("select * from flight");
			
			set = ps.executeQuery();
			while(set.next()) {
				 
				 data = set.getString("arrival_city");
				 System.out.println(data);
				 list1.add(data);
			}	
			
		} catch (Exception e) {
			
		}finally {
			try {
				if (set != null)set.close();
				
			} catch (Exception e) {
				
			}
		}
		
		return list1;
	}


}
