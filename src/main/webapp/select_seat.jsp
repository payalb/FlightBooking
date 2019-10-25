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
#sect{
padding-bottom:15px;
}
.seatLine{
text-align:center;
}
#sect:nth-of-type(1){
background-color:rgba(152,250,0,0.1);

}
#sect:nth-of-type(2){
background-color:rgba(152,0,50,0.1);

}
#sect:nth-of-type(3){
background-color:rgba(0,152,50,0.1);


}
.available,.unavailable{
width:30px; height:30px;

}
form{text-align:center;}
#bookBtn{
width:300px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>

	<% 
	HashSet<String> availableSeats=(HashSet<String>)request.getAttribute("availableSeat");
	ArrayList<ArrayList<String>> layout=(ArrayList<ArrayList<String>>) request.getAttribute("layout");
	int[] rows=(int[])request.getAttribute("rows");
	String[] classes=new String[5];
	classes[0]="First Class";
	classes[1]="Business Class";
	classes[2]="Economy Class";
	int sec=1;
	%> <div id="sect"><h5><%=classes[0]%></h5> <% 
	for(int i=0;i<layout.size();i++){
		
		int row=0;
		%><p class="seatLine">  <% 
		for(int j=0;j<layout.get(i).size();j++){
			
			String code=layout.get(i).get(j);
			if(code.equals("AC")){				
				%><span class="AC"> <%=row %> </span><% 
				}
			if(code.equals("AL")){	
				
				%></div><p></p><div id="sect">
				<%if(sec==1||sec==2){ %>
				
				<h5> <%=classes[sec]%> </h5>
				
				
				<% 
				}
				sec++;
				}
			if(code.split("-").length==3){
				String seatId=code.split("-")[2];
				row=Integer.valueOf(seatId.substring(0, seatId.length()-1));
				if(availableSeats.contains(seatId)){
					%><img src="image/available.jpg" class="available" id="<%=code %>"><% 
				}else{
					%><img src="image/unavailable.jpg" class="unavailable"   id="<%=code %>"><% 
				}
			}
			
			
		} %>
		</p>
		<% 
	} 
	%>
	</div>
	<!--  <input type="hidden" name="title" value"title_value">-->
	<form action="seating"  method="post">
	
	<input type="hidden" name="seats" id="picked" value="">
	<button  class="btn btn-primary btn-lg " id="bookBtn" type="submit">Submit</button>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="js/book.js"></script>
	<script>
	$(function(){
		var set = new Set();
		var value="";
		$('.available').click(function(){
			$(this).attr("src","image/selected.jpg");
			set.add($(this).attr("id"));
			console.log(set.size);
			
			
		});
		
		$('.available').dblclick(function(){
			
			$(this).attr("src","image/available.jpg");
			if(set.has($(this).attr("id"))){
				set.delete($(this).attr("id"));
			}		
			console.log(set.size);
			//for(let key of set.keys()) {
				//  value=value+key;
				  //} 
			//$("#picked").val(value); 
			//$("#picked").val( $(this).attr("id")+";"+ $("#picked").val());
			
		});
		$('#bookBtn').click(function(){
			var value="";
			for(let key of set.keys()) {
				 value=value+key+"_";
				 } 
			$("#picked").val(value);
			
			
		});
		
		
	})
	
	</script>
</body>
</html>