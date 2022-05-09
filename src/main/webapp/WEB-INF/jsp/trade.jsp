<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Trade</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<a href="/offer?ref=${idOffer}">Back</a>
	<table>
		<tr>
			<th>REF</th>
			<th>Name</th>
			<th>Location</th>
			<th>Description</th>
			<th>Owner</th>
		</tr>
		<tr>
			<td>${offer.idOffer}</td>
			<td>${offer.name}</td>
			<td>${offer.location}</td>
			<td>${offer.description}</td>
			<td><a href="/profil?ref=${offer.idOffer}&name=${offer.owner}">${offer.owner}</a></td>
		</tr>
	</table>
	<select>
		<c:forEach items="${offers}" var="o">
			<option value="${o.idOffer}">${o.name}</option><a href="/detail?ref=${o.idOffer}">Detail</a>
		</c:forEach>
	</select>
</body>
</html>