<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="index.jsp">Flight Booking</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <ul class="navbar-nav mr-auto">
    	<c:if test="${sessionScope.passengerEmail!=null}">
    		<li class="nav-item active">
    			<a class="nav-link" href="passenger-history">History</a>
      		</li>
      	</c:if>
    </ul>
    <c:if test="${sessionScope.passengerEmail==null}">
    	<button class="btn btn-outline-success btn-left my-2 my-sm-0" onclick="javascript:location.href='login'">Login</button>
    	<button class="btn btn-outline-success my-2 my-sm-0" onclick="javascript:location.href='register'">Register</button>
    </c:if>
    <c:if test="${sessionScope.passengerEmail!=null}">
    	<button class="btn btn-outline-success btn-left my-2 my-sm-0" onclick="javascript:location.href='logout'">Logout</button>
    	<button class="btn btn-outline-success my-2 my-sm-0" onclick="javascript:location.href='profileinfo'">${sessionScope.passengerEmail}</button>
    </c:if>
  </div>
</nav>