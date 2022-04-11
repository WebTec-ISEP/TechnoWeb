<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div>
		<form action="/personalSpace" method="post">
			<table>
				<tr>
					<td>Login</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="psw" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="action" value="login" /></td>
				</tr>
			</table>
		</form>
		<div> <a href="/register">sign up</a> </div>
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