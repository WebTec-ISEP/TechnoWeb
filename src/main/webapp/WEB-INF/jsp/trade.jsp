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
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a href="/personalSpace">Personal space</a></li>
			<li><a href="/messaging">Messages</a></li>
			<li style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
	<a href="/offer?ref=${offer.idOffer}">Back</a>
	<table class="tabOffers">
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
	<form action="/tradeProposal" method="post">
		<select name="selected">
			<c:forEach items="${offers}" var="o">
				<option value="${o.idOffer}">${o.name}</option><a href="/detail?ref=${o.idOffer}">Detail</a>
			</c:forEach>
		</select>
		<input type="hidden" name="idOffer" value="${offer.idOffer}" />
		<input type="submit" name="action" value="propose" />
	</form>
</body>
</html>