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
			var selector = document.getElementById("selectContact");
			if('${select}'!=""){
				setOption(selector,'${select}');
			}
			$('.conv').hide();
			$('#'+selector.value).show();
			$('#recipient').val(selector.value);
		});

		function updated(){
			var selector = document.getElementById("selectContact");
			$('#recipient').val(selector.value);
		    $('.conv').hide();
		    $('#'+selector.value).show();
		}
		
		function setOption(selectElement, value) {
		    return [...selectElement.options].some((option, index) => {
		        if (option.value == value) {
		            selectElement.selectedIndex = index;
		            return true;
		        }
		    });
		}
	</script>
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a href="/personalSpace">Personal space</a></li>
			<li><a class="active" href="#messaging">Messages</a></li>
			<li style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
    
	<select name="item" id="selectContact" onchange="updated()">
		<c:forEach items="${messages.keySet()}" var="contact">
			<option>${contact}</option>
		</c:forEach>
	</select>
	
	<table class="tabMessage">
		<% Map<String,List<Message>> messages = (Map<String,List<Message>>)request.getAttribute("messages"); %>
		<c:forEach items="${messages.keySet()}" var="contact">
			<div class="conv" id="${contact}">${contact}<br>
			<% String contact = (String)pageContext.getAttribute("contact");
				List<Message> messagesList = messages.get(contact);
				for(Message message:messagesList){%>
				<p>
					<%out.print(message.getSender()+" : "+message.getContent());%>
				</p>
				<%}%>
			</div>
		</c:forEach>
	</table>
	
	<form action="/messaging" method="post" name="sendMessage">
			<table>
				<tr>
					<td><input type="text" name="message" placeholder="message..."/></td>
					<td><input type="hidden" name="recipient" id="recipient" value="no"/></td>
					<td><input type="submit" name="action" value="send"/></td>
				</tr>
			</table>
	</form>
</body>
</html>