<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			if(${connected}!=1){
				$('.contact').hide();
				$('.personalSpace').hide();
				$('.notLogged').hide();
				$('.linkButton').hide();
			} else {
				$('.logged').hide();
			}
		});
	</script>
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/offer?ref=${idOffer}">Back</a></li>
			<li><a href="/home">Home</a></li>
			<li class = "logged"><a href="/personalSpace">Personal space</a></li>
			<li><a class = "logged" href="/messaging">Messages</a></li>
			<li class = "logged" style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
	<a class="linkButton" href="/messaging?user=${user.name}" class="contact">contact</a>
	<h1>${user.name}</h1>
	<h1>${user.email}</h1>
	<table class="tabOffers">
			<tr>
				<th></th>
				<th>Name</th>
				<th>Location</th>
				<th>Duration</th>
			</tr>
			<c:forEach items="${offers}" var="o" varStatus="loop">
			<tr>
				<td width="384" height="216"><a href="/offer?ref=${o.idOffer}"><img src="data:image/jpg;base64,${images[loop.index]}" width="384" height="216"/></a></td>
				<td><a href="/offer?ref=${o.idOffer}"><div style="height:216px;width:100%">${o.name}</div></a></td>
				<td><a href="/offer?ref=${o.idOffer}"><div style="height:216px;width:100%">${o.location}</div></a></td>
				<td><a href="/offer?ref=${o.idOffer}"><div style="height:216px;width:100%">${o.begin} to ${o.end}</div></a></td>
			</tr>

			</c:forEach>
	</table>
</body>
</html>