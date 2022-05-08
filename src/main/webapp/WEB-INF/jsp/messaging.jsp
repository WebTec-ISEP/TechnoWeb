<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="org.techweb.entities.Message"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Messaging</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$('.conv').hide();
		});
		function updated(element){
		    var idx=element.selectedIndex;
		    var val=element.options[idx].value;
		    var content=element.options[idx].innerHTML;
		    $('.conv').hide();
		    $('#'+content).show();
		}
	</script>
</head>
<body>
	<div> 
		<a href="/personalSpace">back</a> 
	</div>
    
	<select name="item" class="selectContact" onchange="updated(this)">
		<c:forEach items="${messages.keySet()}" var="contact">
			<option>${contact}</option>
		</c:forEach>
	</select>
	
	<table class="tabMessage">
		<% Map<String,List<Message>> messages = (Map<String,List<Message>>)request.getAttribute("messages"); %>
		<c:forEach items="${messages.keySet()}" var="contact">
			<div class="conv" id="${contact}">${contact}<br>
			<% 
			 	String contact = (String)pageContext.getAttribute("contact");
				List<Message> messagesList = messages.get(contact);
				for(Message message:messagesList){%>
				<p>
					<%out.print(message.getSender()+" : "+message.getContent());%>
				</p>
				<%}
			%>
			</div>
		</c:forEach>
	</table>
	
	<form action="/messaging" method="post">
			<table>
				<tr>
					<td><input type="text" name="message" placeholder="message..."/></td>
					<td><input type="submit" name="action" value="send" /></td>
				</tr>
			</table>
	</form>
</body>
</html>