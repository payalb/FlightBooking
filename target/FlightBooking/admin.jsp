<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Login</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="css/sign_in.css" rel="stylesheet">
<link href="css/navbar_footer.css" rel="stylesheet">
</head>

<body class="text-center">
	<form class="form-signin" action="admin-login" method="POST">
		<h1 class="h3 mb-3 font-weight-normal">ADMIN SIGN IN</h1>
		<c:if test="${param.errorMsg != null}">
			<div class="alert alert-warning" role="alert">${param.errorMsg}</div>
		</c:if>
		<label for="inputName" class="sr-only">User Name</label> <input
			type="text" id="inputName" name="username" class="form-control"
			placeholder="Admin Username" required autofocus> <label
			for="inputPassword" class="sr-only">Password</label> <input
			type="password" id="inputPassword" name="password"
			class="form-control" placeholder="Password" required>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
	</form>
</body>
</html>