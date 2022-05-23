<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Offer</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			if(${connected}==1){
				$('.notLogged').hide();
			} else {
				$('.logged').hide();
				$('.linkButton').hide();
			}
		});
	</script>
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li class = "logged"><a href="/personalSpace">Personal space</a></li>
			<li><a class = "logged" href="/messaging">Messages</a></li>
			<li class = "logged" style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
	
	<table class="tabOffers">
		<tr>
			<th>Name</th>
			<th>Location</th>
			<th>Description</th>
			<th>Owner</th>
			<th>Equipments</th>
			<th>Services</th>
			<th>Constraints</th>
		</tr>
		<tr>
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
	<a class="linkButton" href="/trade?ref=${offer.idOffer}">Trade</a>
</body>
</html>