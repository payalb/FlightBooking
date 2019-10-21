<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
	import="com.dto.Booking, com.dto.Flight"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Edit Flight</title>

  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <link href="css/navbar.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="admin_navbar.jsp"></jsp:include>
	<main role="main" class="container">
		<div class="col-md-12">
	    <h4 class="mb-3">Flight Information</h4>
	    <form class="needs-validation" action="admineditflight" method="post">
	
	      <div class="row">
	        <div class="col-md-6 mb-3">
	          <label for="deptCity">Departure City</label>
	          <input type="hidden" id="flightId" name="flightId" value="${flight.getFlightId()}">
	          <input type="text" class="form-control" id="deptCity" name="deptCity" placeholder="Departure City"
	                 data-validation="length" data-validation-length="min2" value="${flight.getDepartureCity()}" required>
	        </div>
	        <div class="col-md-6 mb-3">
	          <label for="arrCity">Arrival City</label>
	          <input type="text" class="form-control" id="arrCity" name="arrCity" placeholder="Arrival City"
	                 data-validation="length" data-validation-length="min2" value="${flight.getArrivalCity()}" required>
	        </div>
	      </div>
	
	      <div class="row">
	        <div class="col-md-3 mb-3">
	          <label for="deptDate">Departure Date</label>
	          <input type="date" class="form-control" id="deptDate" name="deptDate" value="${flight.getDepartureTime().toLocalDate()}" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="deptTime">Departure Time</label>
	          <input type="time" class="form-control" id="deptTime" name="deptTime" value="${flight.getDepartureTime().toLocalTime()}" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="arrDate">Arrival Date</label>
	          <input type="date" class="form-control" id="arrDate" name="arrDate" value="${flight.getArrivalTime().toLocalDate()}" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="arrTime">Arrival Time</label>
	          <input type="time" class="form-control" id="arrTime" name="arrTime" value="${flight.getArrivalTime().toLocalTime()}" required>
	        </div>
	      </div>
	
	      <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
	    </form>
	  </div>
	</main>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="js/edit_flight.js"></script>
</body>
</html>