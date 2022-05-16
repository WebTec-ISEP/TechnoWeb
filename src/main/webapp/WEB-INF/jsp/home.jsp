<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			if(${connected}==1){
				$('.login').hide();
				$('.register').hide();
			} else {
				$('.logout').hide();
				$('.personalSpace').hide();
			}
		});
	</script>
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/personalSpace" class = "personalSpace">Personal space</a></li>
			<li><a class="active" href="#home">Home</a></li>
			<li style="float:right"><a href="/logout" class = "logout">log out</a></li>
			<li style="float:right">
				<form action="/personalSpace" method="post">
					<table>
						<tr>
							<td>Login</td>
							<td><input type="text" name="name" /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" /></td>
						</tr>
					</table>
					<input type="submit" name="action" value="login" id="login"/>
				</form>
				<a href="/register" class = "register" id="register">sign up</a>
			</li>
		</ul>
	</div>
	<form action="/home" method="post">
			<table>
				<tr>
					<td><input type="text" name="motCle" placeholder="search..."/></td>
					<td><input type="submit" name="action" value="search" /></td>
				</tr>
			</table>
	</form>
    
    <table class="taboffers">
			<tr>
				<th>REF</th>
				<th>Name</th>
			</tr>
			<c:forEach items="${offers}" var="o">
			<tr>
				<td>${o.idOffer}</td>
				<td>${o.name}</td>
				<td><a href="/offer?ref=${o.idOffer}">Details</a></td>
			</tr>

			</c:forEach>
	</table>
</body>
</html>