<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<main role="main" class="container">
<c:if test="${bookingList.size()!=null}">
<c:out value="${bookingList.size()}"/>
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> Flight Information
			</div>
			<div class="card-body">

				<c:if test="${bookingList.size()==0}">
					<div class="alert alert-warning" role="alert">Have not found
						booking records.</div>
				</c:if>
				<c:if test="${bookingList.size()>0}">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Flight ID</th>
									<th>Booking ID</th>
									<th>Seat Type</th>
									<th>Baggage</th>
									<th>Seat Number</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:set value="0" var="paymentAmount" />  
								<c:forEach items="${bookingList}" var="booking">
									<tr>
										<td>${booking.getFlightId()}</td>
										<td>${booking.getBookingId()}</td>
										<td>${booking.getFlightClass().toString()}</td>
										<td>${booking.getBaggage()}</td>
										<td>${booking.getSeatNumber()}</td>
										<td>${booking.getStatus().toString()}</td>
										<c:set value="${paymentAmount+1 }" var="paymentAmount" />
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<c:set var="paymentAmount" value="${paymentAmount}" scope="session"/>
					<c:set var="totalpayment" value="${param.total }" scope="session"/>
					
					your total amount will be: $  ${param.total}</br>
					<a href="payment" class="btn btn-success btn-block" >Make Payment!</a>
				</c:if>
			</div>
		</div>
	</c:if> </main>
	<jsp:include page="footer.jsp"></jsp:include>


</main>

</body>
</html>