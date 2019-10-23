<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
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
<style>
.AC{ 
width:40px; 
border:1px; 
display:inline-block;
text-align:center;
}

.seatLine{
text-align:center;
}
.available,.unavailable{
width:30px; height:30px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>

	<c:forEach items="${bookingList}" var="booking">
		<tr>
			<td>${booking.getFlightId()}</td>
			<td>${booking.getBookingId()}</td>
			<td>${booking.getFlightClass().toString()}</td>
			<td>${booking.getBaggage()}</td>
			<td>${booking.getSeatNumber()}</td>
			<td>${booking.getStatus().toString()}</td>
			<c:set value="${paymentAmount+1 }" var="paymentAmount" />
		</tr>
	</c:forEach>
	<% 
	HashSet<String> availableSeats=(HashSet<String>)session.getAttribute("availableSeat");
	ArrayList<ArrayList<String>> layout=(ArrayList<ArrayList<String>>) session.getAttribute("layout");
	for(int i=0;i<layout.size();i++){
		int row=0;
		%><p class="seatLine"><% 
		for(int j=0;j<layout.get(i).size();j++){
			
			String code=layout.get(i).get(j);
			if(code.equals("AC")){				
				%><span class="AC"> <%=row %> </span><% 
				}
			if(code.split("-").length==3){
				String seatId=code.split("-")[2];
				row=Integer.valueOf(seatId.substring(0, seatId.length()-1));
				if(availableSeats.contains(seatId)){
					%><img src="image/available.jpg" class="available" id="<%=code %>"> </span><% 
				}else{
					%><img src="image/unavailable.jpg" class="unavailable"   id="<%=code %>"> </span><% 
				}
			}
			
			
		} 
		out.println("</p>");
	} 
	%>
	<!--  <input type="hidden" name="title" value"title_value">-->
	<from action="seating" method="post">
	<input type="text" name="seats" id="picked" value="">
	</from>
	<jsp:include page="footer.jsp"></jsp:include>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="js/book.js"></script>
	<script>
	$(function(){
		$('.available').click(function(){
			$(this).css("border:2px solid blue;");
			$("#picked").val( $(this).attr("id")+";"+ $("#picked").val());
			
		});
		
	})
	</script>
</body>
</html>