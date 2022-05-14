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
	<c:if test = "${previous != null}">
		<a href="/detail?ref=${previous}">Back</a>
	</c:if>
	<c:if test = "${previous == null}">
		<a href="/personalSpace">Back</a>
	</c:if>
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
	<c:if test = "${offersProposal != null and offersProposal.size() != 0}">
		<form action="/respond">
			<!-- <input type="hidden" name="idOffer" value="${offer.idOffer}"/> -->
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
						<td><button type="submit" name="accept" value="${o.idOffer}">Accept</button></td>
						<td><button type="submit" name="refuse" value="${o.idOffer}">Refuse</button></td>
						<td><a href="/detail?ref=${o.idOffer}&previous=${offer.idOffer}">Detail</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</c:if>
	<c:if test = "${offersProposed != null and offersProposed.size() != 0}">	
		<table class="tabOffersProposed">
	    	<caption>Offers proposed</caption>
			<tr>
				<th>REF</th>
				<th>Name</th>
				<th>Location</th>
				<th>Description</th>
			</tr>
			<c:forEach items="${offersProposed}" var="o">
				<tr>
					<td>${o.idOffer}</td>
					<td>${o.name}</td>
					<td>${o.location}</td>
					<td>${o.description}</td>
					<td><a href="/detail?ref=${o.idOffer}">Detail</a></td>
				</tr>
			</c:forEach>
		</table>
    </c:if>
</body>
</html>