<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="admin_index">Admin Management</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <ul class="navbar-nav mr-auto">
    	<c:if test="${sessionScope.adminName!=null}">
    		<li class="nav-item active">
    			<a class="nav-link" href="admin_addflight">Add Flight</a>
      		</li>
    		<li class="nav-item active">
    			<a class="nav-link" href="allhistory">History</a>
      		</li>
      	</c:if>
    </ul>
    <c:if test="${sessionScope.adminName!=null}">
    	<button class="btn btn-outline-success btn-left my-2 my-sm-0" onclick="javascript:location.href='admin-logout'">Logout</button>
    	<button class="btn btn-outline-success my-2 my-sm-0">${sessionScope.adminName}</button>
   	</c:if>
  </div>
</nav>