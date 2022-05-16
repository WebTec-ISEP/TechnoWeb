<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Personal space</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a class="active" href="#personalSpace">Personal space</a></li>
			<li><a href="/messaging">Messages</a></li>
			<li style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
    
    <table class="tabOffers">
    	<caption>Vos Offres</caption>
		<tr>
			<th>REF</th>
			<th>Name</th>
		</tr>
		<c:forEach items="${offers}" var="o">
			<tr>
				<td>${o.idOffer}</td>
				<td>${o.name}</td>
				<td> <a onclick="return confirm('Please Confirm')" href="/delete?ref=${o.idOffer}&name=${name}"> Delete </a> </td>
				<td><a href="/edit?ref=${o.idOffer}">Edit</a></td>
				<td><a href="/detail?ref=${o.idOffer}">Detail</a></td>
			</tr>
		</c:forEach>
		<tr><td><a href="/addOffer">Add</a></td></tr>
	</table>
	
	<table class="tabAcceptedOffers">
    	<caption>Accepted Offers</caption>
		<tr>
			<th>REF</th>
			<th>Name</th>
		</tr>
		<c:forEach items="${acceptedOffers}" var="o">
			<tr>
				<td>${o.idOffer}</td>
				<td>${o.name}</td>
				<td><a href="/detail?ref=${o.idOffer}">Detail</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>