<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Offer</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a href="/personalSpace">Personal space</a></li>
			<li><a href="/messaging">Messages</a></li>
			<li style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
	
	<table class="tabOffers">
		<tr>
			<th>REF</th>
			<th>Name</th>
			<th>Location</th>
			<th>Description</th>
			<th>Owner</th>
			<th>Equipments</th>
			<th>Services</th>
			<th>Constraints</th>
		</tr>
		<tr>
			<td>${offer.idOffer}</td>
			<td>${offer.name}</td>
			<td>${offer.location}</td>
			<td>${offer.description}</td>
			<td><a href="/profil?ref=${offer.idOffer}&name=${offer.owner}">${offer.owner}</a></td>
			<td><c:forEach items="${offer.equipments}" var="e">
				<p>${e}</p>
			</c:forEach></td>
			<td><c:forEach items="${offer.services}" var="s">
				<p>${s}</p>
			</c:forEach></td>
			<td><c:forEach items="${offer.constraints}" var="c">
				<p>${c}</p>
			</c:forEach></td>
		</tr>
		<tr>
			<td><a href="/trade?ref=${offer.idOffer}">Trade</a></td>
		</tr>
	</table>
	<c:forEach items="${images}" var="i">
		<img src="data:image/jpg;base64,${i}"/>
	</c:forEach>
</body>
</html>