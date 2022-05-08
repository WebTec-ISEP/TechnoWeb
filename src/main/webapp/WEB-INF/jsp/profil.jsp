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
	<a href="/offer?ref=${idOffer}">Back</a>
	<table>
		<tr>
			<th>REF</th>
			<th>Name</th>
		</tr>
		<tr>
			<td>${user.idUser}</td>
			<td>${user.name}</td>
		</tr>
	</table>
	<a href="/messaging">contact</a>
</body>
</html>