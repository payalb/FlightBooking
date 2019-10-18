<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="com.dto.Flight"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="author" content="">

<title>Flight Booking</title>

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
	<form class="needs-validation" action="flightquery" method="post">
		<div style="padding: 2rem 1rem; margin-bottom: 2rem; background-color: #e9ecef; border-radius: .3rem;">
			<h1>Hello travelers. Where would you like to go?</h1>
			<div class="row mt-4">
				<div class="col-md-3">
					<label for="from">From</label> <input type="text"
						class="form-control" id="from" name="from" placeholder="Departure"
						value="" data-validation="length" data-validation-length="min2"
						required>
				</div>
				<div class="col-md-3">
					<label for="to">To</label> <input type="text" class="form-control"
						id="to" name="to" placeholder="Destination" value=""
						data-validation="length" data-validation-length="min2" required>
				</div>
				<div class="col-md-3">
					<label for="to">Date</label> <input type="date"
						class="form-control" id="date" name="date" placeholder="Date"
						value="" required>
				</div>
				<div class="col-md-3 mt-2">
					<label></label>
					<button class="btn btn-primary btn-lg btn-block"
						style="padding: 1px 7px 2px;" type="submit">Search</button>
				</div>
			</div>
		</div>
	</form>
	<c:if test="${flightList.size()!=null}">
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> Flight Information
			</div>
			<div class="card-body">

				<c:if test="${flightList.size()==0}">
					<div class="alert alert-warning" role="alert">Have not found
						matched records.</div>
				</c:if>
				<c:if test="${flightList.size()>0}">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Flight ID</th>
									<th>Departure City</th>
									<th>Arrival City</th>
									<th>Departure Time</th>
									<th>Arrival Time</th>
									<c:if test="${sessionScope.passengerEmail!=null}">
										<th>Operation</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${flightList}" var="entry">
									<tr>
										<td>${entry.key.getFlightId()}</td>
										<td>${entry.key.getDepartureCity()}</td>
										<td>${entry.key.getArrivalCity()}</td>
										<td>${entry.key.getDepartureTime()}</td>
										<td>${entry.key.getArrivalTime()}</td>
										<c:if test="${sessionScope.passengerEmail!=null && entry.value}">
											<td><a href="flightinfo?flightId=${entry.key.getFlightId()}">Book</a></td>
										</c:if>
										<c:if test="${!entry.value}">
											<td>Sold Out</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</c:if> </main>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
