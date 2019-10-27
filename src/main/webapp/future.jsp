<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8" import="com.dto.BookingStatus"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Future Flight</title>
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
			<i class="fas fa-table"></i> Future Booking
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
				    <% if(request.getAttribute("message") != null) {%>
                        <div class="alert alert-success" role="alert">
                                ${message}
                        </div>
                    <%			request.setAttribute("message", null);
                       } %>
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
							<th>Status</th>
                            <th>Operation</th>
						</tr>
					</thead>
                    <tbody>
						<c:forEach items="${futureBooking}" var="future">
							<tr>
								<td>${future.key.getBookingId()}</td>
								<td>${future.key.getFlightId()}</td>
								<td>${future.value.getDepartureCity()}</td>
								<td>${future.value.getArrivalCity()}</td>
								<td>${future.value.getDepartureTime()}</td>
								<td>${future.value.getArrivalTime()}</td>
								<td>${future.key.getFlightClass()}</td>
								<td>${future.key.getSeatNumber()}</td>
								<td>${future.key.getStatus().toString()}</td>
								<td><a href="./bookingCancel?bookingId=${future.key.getBookingId()}">Cancel</a><br>
									<a href="./bookingEdit?bookingId=${future.key.getBookingId()}">️Edit️</a>
									<c:if test="${future.key.getSeatNumber() != null && !future.key.getSeatNumber().isEmpty() && future.key.getStatus() == 'RESERVED'}">
										<a class="btn btn-sm btn-success" href="./paymentComplete?bookingId=${future.key.getBookingId()}"> finish payment️</a>
									</c:if>
									</td>
							</tr>
						</c:forEach>
						<c:if test="${futureBooking==null || futureBooking.size()==0}">
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
