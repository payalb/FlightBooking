<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Information</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="css/navbar.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="admin_navbar.jsp"></jsp:include>

	<main role="main" class="container">
		<div class="jumbotron">
			<h1>Something went wrong. Please try again later.</h1>
		    <p class="lead">${param.exception}</p>
		</div>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>