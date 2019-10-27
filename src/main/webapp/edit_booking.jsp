<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="css/navbar.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<main role="main" class="container">
	<div class="card mb-3">
		<div class="card-header">
			<i class="fas fa-table"></i> Edit your booking
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>Booking ID</th>
							<th>Flight ID</th>
							<th>Departure City</th>
							<th>Arrival City</th>
							<th>Departure Time</th>
							<th>Arrival Time</th>
							<th>Seat Class</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${sessionScope.book.getBookingId()}</td>
							<td>${sessionScope.book.getFlightId()}</td>
							<td>${sessionScope.flight.getDepartureCity()}</td>
							<td>${sessionScope.flight.getArrivalCity()}</td>
							<td>${sessionScope.flight.getDepartureTime()}</td>
							<td>${sessionScope.flight.getArrivalTime()}</td>
							<td>${sessionScope.book.getFlightClass()}</td>
							<td>${sessionScope.book.getStatus().toString()}</td>
						</tr>
						<c:if test="${sessionScope.book == null || sessionScope.flight == null }">
							<div class="alert alert-warning" role="alert">
								Have not found matched records.
							</div>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<c:if test="${book != null && flight != null }">
			<p>Please make a selection:</p>
			<a class="btn btn-secondary btn-lg btn-block" href="./bookingCancel?bookingId=${sessionScope.book.getBookingId()}">Cancel Booking</a>
			<a class="btn btn-secondary btn-lg btn-block" href="./seatOption">Edit Seats</a>
		</c:if>
	</div>
</body>
</html>