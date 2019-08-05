<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Ticket</title>
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
	  <div class="col-md-12">
	    <h4 class="mb-3">Booking Ticket</h4>
	    <form class="needs-validation" action="booking" method="post">
	
	      <div class="row">
          	<input type="hidden" id="flightId" name="flightId" value="${seat.getFlightId()}">
          	<input type="hidden" id="oldVersion" name="oldVersion" value="${seat.getVersion()}">
	        <div class="col-md-2 mb-3" id="tickets">
	          <label for="business">Business (${seat.getBusinessLeft()} left)</label>
	          <input type="hidden" id="businessLeft" name="businessLeft" value="${seat.getBusinessLeft()}">
	          <input type="number" class="form-control ticket-group" id="business" name="business" placeholder="No. of Tickets">
	        </div>
	        <div class="col-md-2 mb-3">
	          <label for="busiBaggage">Baggage</label>
	          <select class="custom-select d-block w-100" id="busiBaggage" name="busiBaggage" required>
	            <option value="1" selected>1</option>
	            <option value="2">2</option>
	            <option value="0">No Baggage</option>
	          </select>
	        </div>
	        <div class="col-md-2 mb-3">
	          <label for="firstClass">First Class (${seat.getFirstLeft()} left)</label>
	          <input type="hidden" id="firstLeft" name="firstLeft" value="${seat.getFirstLeft()}">
	          <input type="number" class="form-control ticket-group" id="firstClass" name="firstClass" placeholder="No. of Tickets">
	        </div>
	        <div class="col-md-2 mb-3">
	          <label for="firstBaggage">Baggage</label>
	          <select class="custom-select d-block w-100" id="firstBaggage" name="firstBaggage" required>
	            <option value="1" selected>1</option>
	            <option value="2">2</option>
	            <option value="0">No Baggage</option>
	          </select>
	        </div>
	        <div class="col-md-2 mb-3">
	          <label for="economy">Economy (${seat.getEconomyLeft()} left)</label>
	          <input type="hidden" id="economyLeft" name="economyLeft" value="${seat.getEconomyLeft()}">
	          <input type="number" class="form-control ticket-group" id="economy" name="economy" placeholder="No. of Tickets">
	        </div>
	        <div class="col-md-2 mb-3">
	          <label for="econoBaggage">Baggage</label>
	          <select class="custom-select d-block w-100" id="econoBaggage" name="econoBaggage" required>
	            <option value="1" selected>1</option>
	            <option value="2">2</option>
	            <option value="0">No Baggage</option>
	          </select>
	        </div>
	      </div>
	
	      <hr class="mb-4">
	
	      <h4 class="mb-3">Flight Information</h4>
	      <div class="row">
	        <div class="col-md-6 mb-3">
	          <label for="deptCity">Departure City</label>
	          <input type="text" class="form-control" id="deptCity" name="deptCity" 
	          	value="${flight.getDepartureCity()}" readonly>
	        </div>
	        <div class="col-md-6 mb-3">
	          <label for="arrCity">Arrival City</label>
	          <input type="text" class="form-control" id="arrCity" name="arrCity" 
	          	value="${flight.getArrivalCity()}" readonly>
	        </div>
	      </div>
	
	      <div class="row">
	        <div class="col-md-3 mb-3">
	          <label for="deptDate">Departure Date</label>
	          <input type="date" class="form-control" id="deptDate" name="deptDate" 
	          	value="${flight.getDepartureTime().toLocalDate()}" readonly>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="deptTime">Departure Time</label>
	          <input type="time" class="form-control" id="deptTime" name="deptTime" 
	          	value="${flight.getDepartureTime().toLocalTime()}" readonly>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="arrDate">Arrival Date</label>
	          <input type="date" class="form-control" id="arrDate" name="arrDate" 
	          	value="${flight.getArrivalTime().toLocalDate()}" readonly>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="arrTime">Arrival Time</label>
	          <input type="time" class="form-control" id="arrTime" name="arrTime" 
	          	value="${flight.getArrivalTime().toLocalTime()}" readonly>
	        </div>
	      </div>
	
	      <button class="btn btn-primary btn-lg btn-block" id="bookBtn" type="submit">Submit</button>
	    </form>
	  </div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="js/book.js"></script>
</body>
</html>