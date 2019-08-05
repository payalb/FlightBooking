<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Passenger Profile</title>
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
	    <h4 class="mb-3">Passenger Profile</h4>
	    <form class="needs-validation" action="update-profile" method="post">
	
	      <div class="mb-3">
	        <label for="email">Email</label>
	        <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com"
	        	 value="${profile.getEmail()}" readonly>
	      </div>
	
	      <div class="row">
	        <div class="col-md-6 mb-3">
	          <label for="firstName">First name</label>
	          <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name" 
	          	value="${profile.getFirstName()}" data-validation="length" data-validation-length="min2" required>
	        </div>
	        <div class="col-md-6 mb-3">
	          <label for="lastName">Last name</label>
	          <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" 
	          	value="${profile.getLastName()}" data-validation="length" data-validation-length="min2" required>
	        </div>
	      </div>
	
	      <div class="d-block my-3">
	        <label>Gender</label>
	        <br>
	        <div class="form-check form-check-inline">
	          <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="MALE"
	          	 ${profile.getGender().toString()=='MALE'?'checked':''} required>
	          <label class="form-check-label" for="inlineRadio1">Male</label>
	        </div>
	        <div class="form-check form-check-inline">
	          <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="FEMALE"
	          	${profile.getGender().toString()=='FEMALE'?'checked':''}>
	          <label class="form-check-label" for="inlineRadio2">Female</label>
	        </div>
	      </div>
	
	      <div class="mb-3">
	        <label for="ssn">SSN</label>
	        <input type="text" class="form-control" id="ssn" name="ssn" placeholder="SSN number" 
	        	value="${profile.getSsn()}" data-validation="length" data-validation-length="9">
	      </div>
	
	      <div class="mb-3">
	        <label for="age">Age</label>
	        <input type="number" class="form-control" id="age" name="age" placeholder="Age" value="${profile.getAge()}">
	      </div>
	
	      <div class="mb-3">
	        <label for="address">Address</label>
	        <input type="text" class="form-control" id="address" name="address" placeholder="Your address" 
	        	value="${profile.getStreet()}">
	      </div>
	
	      <div class="mb-3">
	        <label for="aptNumber">Apartment Number</label>
	        <input type="text" class="form-control" id="aptNumber" name="aptNumber" placeholder="Apartment or suite" 
	        	value="${profile.getApartmentNumber()}">
	      </div>
	
	      <div class="row">
	        <div class="col-md-5 mb-3">
	          <label for="city">City</label>
	          <input type="text" class="form-control" id="city" name="city" placeholder="City" value="${profile.getCity()}">
	        </div>
	        <div class="col-md-4 mb-3">
	          <label for="state">State</label>
	          <input type="text" class="form-control" id="state" name="state" placeholder="State" value="${profile.getState()}">
	        </div>
	        <div class="col-md-3 mb-3">
	          <label for="zip">Zip</label>
	          <input type="number" class="form-control" id="zip" name="zip" placeholder="Zip Code" value="${profile.getZip()}">
	        </div>
	      </div>
	
	      <div class="mb-3">
	        <label for="telHome">Home Telephone</label>
	        <input type="text" class="form-control" id="telHome" name="telHome" placeholder="Home telephone number" 
	        	value="${profile.getTelHome()}">
	      </div>
	
	      <div class="mb-3">
	        <label for="telOffice">Office Telephone</label>
	        <input type="text" class="form-control" id="telOffice" name="telOffice" placeholder="Office telephone number"
	        	value="${profile.getTelOffice()}">
	      </div>
	
	      <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
	    </form>
	  </div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.79/jquery.form-validator.min.js"></script>
	<script>
	  $.validate({
	    modules : 'date, security'
	  });
	</script>
</body>
</html>