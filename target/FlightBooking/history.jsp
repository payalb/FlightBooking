<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
	import="com.dto.Booking, com.dto.Flight"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="author" content="">
<title>Booking History</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="css/navbar.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<main role="main" class="container">
	<div class="card mb-3">
		<div class="card-header">
			<i class="fas fa-table"></i> Booking History
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
							<th>Seat No.</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookingHistory}" var="history">
							<tr>
								<td>${history.key.getBookingId()}</td>
								<td>${history.key.getFlightId()}</td>
								<td>${history.value.getDepartureCity()}</td>
								<td>${history.value.getArrivalCity()}</td>
								<td>${history.value.getDepartureTime()}</td>
								<td>${history.value.getArrivalTime()}</td>
								<td>${history.key.getFlightClass()}</td>
								<td>${history.key.getSeatNumber()}</td>
							</tr>
						</c:forEach>
						<c:if test="${bookingHistory==null}">
							<div class="alert alert-warning" role="alert">
								Have not found matched records.
							</div>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>