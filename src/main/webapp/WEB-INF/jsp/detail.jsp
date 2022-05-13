<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Detail</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<table>
		<tr>
			<th>REF</th>
			<th>Name</th>
			<th>Location</th>
			<th>Description</th>
		</tr>
		<tr>
			<td>${offer.idOffer}</td>
			<td>${offer.name}</td>
			<td>${offer.location}</td>
			<td>${offer.description}</td>
		</tr>
	</table>
	
	<table class="tabOffersProposal">
    	<caption>Offers proposal</caption>
		<tr>
			<th>REF</th>
			<th>Name</th>
			<th>Location</th>
			<th>Description</th>
		</tr>
		<c:forEach items="${offersProposal}" var="o">
		<tr>
			<td>${o.idOffer}</td>
			<td>${o.name}</td>
			<td>${o.location}</td>
			<td>${o.description}</td>
			<td><a href="/detail?ref=${o.idOffer}">Detail</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>