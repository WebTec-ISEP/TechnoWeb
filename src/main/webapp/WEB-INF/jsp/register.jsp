<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div>
		<form action="/register" method="post">
			<table>
				<tr>
					<td>Login</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Verify password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="action" value="sign up" /></td>
				</tr>
			</table>
		</form>
		<div> <a href="/home">home</a> </div>
	</div>
</body>
</html>