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
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a href="/personalSpace">Personal space</a></li>
			<li><a href="/messaging">Messages</a></li>
			<li style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
	<c:if test = "${previous != null}">
		<a href="/detail?ref=${previous}">Back</a>
	</c:if>
	<c:if test = "${previous == null}">
		<a href="/personalSpace">Back</a>
	</c:if>
	<table class="tabOffers">
		<tr>
			<th>REF</th>
			<th>Name</th>
			<th>Location</th>
			<th>Description</th>
			<th>Equipments</th>
			<th>Services</th>
			<th>Constraints</th>
		</tr>
		<tr>
			<td>${offer.idOffer}</td>
			<td>${offer.name}</td>
			<td>${offer.location}</td>
			<td>${offer.description}</td>
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
	</table>
	<div class=slider-scroll>
		<c:forEach items="${images}" var="i" varStatus="loop">
			<img id="slide-scroll${loop.index+1}" src="data:image/jpg;base64,${i}">
		</c:forEach>
	</div>
	<ul class="ancre">
		<c:forEach var="i" begin="1" end="${images.size()}" step="1">
			<li><a href="#slide-scroll${i}">${i}</a></li>
		</c:forEach>
	</ul>
	<c:if test = "${offersProposal != null and offersProposal.size() != 0}">
		<form action="/respond">
			<!-- <input type="hidden" name="idOffer" value="${offer.idOffer}"/> -->
			<table class="tabOffers">
		    	<caption>Offers proposal</caption>
				<tr>
					<th>REF</th>
					<th>Name</th>
					<th>Location</th>
					<th>Description</th>
					<th>Owner</th>
				</tr>
				<c:forEach items="${offersProposal}" var="o">
					<tr>
						<td>${o.idOffer}</td>
						<td>${o.name}</td>
						<td>${o.location}</td>
						<td>${o.description}</td>
						<td><a href="/profil?ref=${o.idOffer}&name=${o.owner}">${o.owner}</a></td>
						<td><button type="submit" name="accept" value="${o.idOffer}">Accept</button></td>
						<td><button type="submit" name="refuse" value="${o.idOffer}">Refuse</button></td>
						<td><a href="/detail?ref=${o.idOffer}&previous=${offer.idOffer}">Detail</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</c:if>
	<c:if test = "${offersProposed != null and offersProposed.size() != 0}">	
		<table class="tabOffers">
	    	<caption>Offers proposed</caption>
			<tr>
				<th>REF</th>
				<th>Name</th>
				<th>Location</th>
				<th>Description</th>
				<th>Owner</th>
			</tr>
			<c:forEach items="${offersProposed}" var="o">
				<tr>
					<td>${o.idOffer}</td>
					<td>${o.name}</td>
					<td>${o.location}</td>
					<td>${o.description}</td>
					<td><a href="/profil?ref=${o.idOffer}&name=${o.owner}">${o.owner}</a></td>
					<td><a href="/detail?ref=${o.idOffer}">Detail</a></td>
				</tr>
			</c:forEach>
		</table>
    </c:if>
</body>
</html>