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
			if(${connected}==1){
				$('.notLogged').hide();
			} else {
				$('.logged').hide();
			}
		});
	</script>
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a class="active" href="#home">Home</a></li>
			<li class = "logged"><a href="/personalSpace">Personal space</a></li>
			<li><a class = "logged" href="/messaging">Messages</a></li>
			<li class = "logged" style="float:right"><a href="/logout">log out</a></li>
			<li style="float:right">
				<form class ="notLogged" action="/personalSpace" method="post">
					<table>
						<tr>
							<td>Login</td>
							<td><input type="text" name="name" /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" /></td>
						</tr>
					</table>
					<input type="submit" name="action" value="login" id="login"/>
				</form>
				<a class = "notLogged" href="/register" id="register">sign up</a>
			</li>
		</ul>
	</div>
	
	<form class="searchBar" action="/home" method="post">
		<table>
			<tr>
				<td><input type="text" name="motCle" placeholder="search..."/></td>
				<td><input type="submit" name="action" value="search" /></td>
			</tr>
		</table>
	</form>
    
    <table class="tabOffers">
			<tr>
				<th></th>
				<th>Name</th>
				<th>Location</th>
			</tr>
			<c:forEach items="${offers}" var="o" varStatus="loop">
			<tr>
				<td width="384" height="216"><a href="/offer?ref=${o.idOffer}"><img src="data:image/jpg;base64,${images[loop.index]}" width="384" height="216"/></a></td>
				<td><a href="/offer?ref=${o.idOffer}"><div style="height:216px;width:100%">${o.name}</div></a></td>
				<td><a href="/offer?ref=${o.idOffer}"><div style="height:216px;width:100%">${o.location}</div></a></td>
			</tr>

			</c:forEach>
	</table>
</body>
</html>