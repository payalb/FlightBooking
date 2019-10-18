<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Passenger Registration</title>
  <link
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
    crossorigin="anonymous">
  <link href="css/form_validation.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
  <div class="py-5 text-center">
    <h2>Passenger Registration</h2>
  </div>

  <div class="row">
    <div class="col-md-12">
      <form class="needs-validation" action="passenger-register" method="post">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstname">First name</label>
            <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Your First Name"
                   value="" data-validation="length" data-validation-length="min2" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastname">Last name</label>
            <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Your Last Name"
                   value="" data-validation="length" data-validation-length="min2" required>
          </div>
        </div>

        <div class="mb-3">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" required>
        </div>
		<div class="alert alert-warning" style="display: none;" role="alert" id="emailalert">Email address has been used.</div>

        <div class="mb-3">
          <label for="password">Password</label>
          <input type="password" class="form-control" id="password" name="pass_confirmation" placeholder="Password"
                 data-validation="length" data-validation-length="min5" required>
        </div>

        <div class="mb-3">
          <label for="passwordconfirm">Confirm Password</label>
          <input type="password" class="form-control" id="passwordconfirm" name="pass"
                 placeholder="Confirm Password" data-validation="confirmation" required>
        </div>

        <div class="d-block my-3">
          <label>Gender</label>
          <br>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="MALE" required>
            <label class="form-check-label" for="inlineRadio1">Male</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="FEMALE" required>
            <label class="form-check-label" for="inlineRadio2">Female</label>
          </div>
        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">Register</button>
      </form>
    </div>
  </div>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; Flight Booking</p>
    <ul class="list-inline">
      <li class="list-inline-item"><a href="#">Home</a></li>
    </ul>
  </footer>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.79/jquery.form-validator.min.js"></script>
<script src="js/register.js"></script>
</body>
</html>

