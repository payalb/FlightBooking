<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="css/navbar.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<main role="main" class="container">
	<div class="card mb-3">
		<div class="card-header">
			<i class="fas fa-table"></i> Edit your booking
		</div>
		<div class="card-body">
			<c:if test="${ sessionScope.book != null }">
				<p>Please select a class:</p>
				<a class="btn btn-secondary btn-lg btn-block" href="./reselectSeating?class=firstclass">First Class</a>
				<a class="btn btn-secondary btn-lg btn-block" href="./reselectSeating?class=businessclass">Business Class</a>
				<a class="btn btn-secondary btn-lg btn-block" href="./reselectSeating?class=economyclass">Economy Class</a>
			</c:if>
			<c:if test="${ sessionScope.book == null }">
				<div class="alert alert-warning" role="alert">
					Have not found matched records.
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>