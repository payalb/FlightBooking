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
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> Flight Information
			</div>
			<div class="card-body">
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
							<tr>
								<td>${sessionScope.book.getFlightId()}</td>
								<td>${sessionScope.book.getBookingId()}</td>
								<td>${sessionScope.book.getFlightClass().toString()}</td>
								<td>${sessionScope.book.getBaggage()}</td>
								<td>${sessionScope.book.getSeatNumber()}</td>
								<td>${sessionScope.book.getStatus().toString()}</td>
							</tr>
						</tbody>
					</table>
				</div>
				your total amount will be:         $ ${sessionScope.money}<br>
				<a href="re_payment" class="btn btn-success btn-block" >Make Payment!</a>
			</div>
		</div>
	<jsp:include page="footer.jsp"></jsp:include>
</main>

</body>
</html>