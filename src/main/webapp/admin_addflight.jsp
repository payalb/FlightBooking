<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Add Flight Information</title>

  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <link href="css/navbar.css" rel="stylesheet">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  


</head>

<body>
	<jsp:include page="admin_navbar.jsp"></jsp:include>
	<main role="main" class="container">
	  <div class="col-md-12">
	    <h4 class="mb-3">Flight Information</h4>
	    <form class="needs-validation" action="addflight" method="post">
	
	      <div class="row">
	        <div class="col-md-6 mb-3">
	          <label for="deptCity">Departure City</label>
	          <input  class="form-control" id="deptCity" name="deptCity" placeholder="Departure City" required>
	        </div>

				<div class="col-md-6 mb-3">
	          <label for="arrCity">Arrival City</label>
	          <input  class="form-control" id="arrCity" name="arrCity" placeholder="Arrival City" required>
	        </div>
	      </div>
	
	      <div class="row">
	        <div class="col-md-3 mb-3">
	          <label for="deptDate">Departure Date</label>
	          <input type="date" class="form-control" id="deptDate" name="deptDate" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="deptTime">Departure Time</label>
	          <input type="time" class="form-control" id="deptTime" name="deptTime" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="arrDate">Arrival Date</label>
	          <input type="date" class="form-control" id="arrDate" name="arrDate" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="arrTime">Arrival Time</label>
	          <input type="time" class="form-control" id="arrTime" name="arrTime" required>
	        </div>
	      </div>
	      
	      <div class="row">
	      	<div class="col-md-3 mb-3">
	          <label for="firstPrice">First Class Price</label>
	          <input type="number" class="form-control" id="firstPrice" name="firstPrice" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="businessPrice">Business Class Price</label>
	          <input type="number" class="form-control" id="businessPrice" name="businessPrice" required>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="economyPrice">Economy Class price</label>
	          <input type="number" class="form-control" id="economyPrice" name="economyPrice" required>
	        </div>
	      </div>
	
	      <hr class="mb-4">
	
	      <h4 class="mb-3">Airplane</h4>
	      <div class="row">
	        <div class="col-md-3 mb-3">
	          <label for="country">Airplane</label>
	          <select class="custom-select d-block w-100" id="airplaneId" name="airplaneId" required>
	          		<option value="" selected="selected">Choose...</option>
	          </select>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="businessCap">Business-class Capacity</label>
	          <input type="text" class="form-control" id="businessCap" name="businessCap"
	          	 value="" readonly>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="firstCap">First-class Capacity</label>
	          <input type="text" class="form-control" id="firstCap" name="firstCap" value="" readonly>
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="economyCap">Economy-class Capacity</label>
	          <input type="text" class="form-control" id="economyCap" name="economyCap" value="" readonly>
	        </div>
	      </div>
	
	      <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
	    </form>
	  </div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="js/airplane_info.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</body>
</html>
