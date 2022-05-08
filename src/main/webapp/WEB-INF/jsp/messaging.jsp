<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Messaging</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div> 
		<a href="/personalSpace">back</a> 
	</div>
    
    <table class="tabmessage">
			<tr>
				<th>REF</th>
				<th>Content</th>
				<th>Sender</th>
				<th>Recipient</th>
				<th>Time</th>
			</tr>
			<c:forEach items="${messages}" var="message">
				<tr>
					<td>${message.idMessage}</td>
					<td>${message.content}</td>
					<td>${message.sender}</td>
					<td>${message.timeStamp}</td>
				</tr>
			</c:forEach>
	</table>
	
	<table class="tabcontacts">
				<tr>
					<th>name</th>
				</tr>
				<c:forEach items="${contacts}" var="name">
				<tr>
					<td>${name}</td>
				</tr>
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