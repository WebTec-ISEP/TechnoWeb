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
			} else {
				setOption(selector,'${messages.keySet().toArray()[0]}');
			}
			$('.conv').hide();
			$('#'+selector.value).show();
			$('#recipient').val(selector.value);
			$(document).scrollTop($(document).height());
		});

		function updated(){
			var selector = document.getElementById("selectContact");
			$('#recipient').val(selector.value);
		    $('.conv').hide();
		    $('#'+selector.value).show();
		    $(document).scrollTop($(document).height());
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
    <div class="selectContactDiv">
    	<label>Contacts</label>
		<select name="item" id="selectContact" onchange="updated()" multiple>
			<c:forEach items="${messages.keySet()}" var="contact">
				<option>${contact}</option>
			</c:forEach>
		</select>
	</div>
	
	<% Map<String,List<Message>> messages = (Map<String,List<Message>>)request.getAttribute("messages"); %>
	<c:forEach items="${messages.keySet()}" var="contact">
		<div class="conv" id="${contact}">
		<% String contact = (String)pageContext.getAttribute("contact");
			List<Message> messagesList = messages.get(contact);
			for(Message message:messagesList){
				if(message.getSender().equals(session.getAttribute("name"))){%>
					<div class="send">
						<div><%out.print(message.getContent());%></div>
					</div>
				<%} else {%>
					<div class="received">
						<div><%out.print(message.getContent());%></div>
					</div>
				<%}
			}%>
		</div>
	</c:forEach>
	
	<form action="/messaging" method="post" name="sendMessage" class="sendMessage">
		<input type="text" name="message" placeholder="message..."/>
		<input type="hidden" name="recipient" id="recipient" value="no"/>
		<input type="submit" name="action" value="send"/>
	</form>
</body>
</html>