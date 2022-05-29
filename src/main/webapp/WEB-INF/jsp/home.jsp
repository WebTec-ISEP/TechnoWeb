<%@ page language="java" contentType="text/html ; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.1/jquery.min.js"></script>
<script>
		$(document).ready(function(){
			if(${connected}==1){
				$('.notLogged').hide();
			} else {
				$('.logged').hide();
			}
			
			if(${admin}==0){
				$('.delete').hide();
			}
		});
	</script>
</head>
<body>
	<div class="navigationBar">
		<ul>
			<li><a class="active" href="#home">Home</a></li>
			<li class="logged"><a href="/personalSpace">Personal space</a></li>
			<li><a class="logged" href="/messaging">Messages</a></li>
			<li class="logged" style="float: right"><a href="/logout">log
					out</a></li>
			<li style="float: right">
				<form class="notLogged" action="/personalSpace" method="post">
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
					<input type="submit" name="action" value="login" id="login" />
				</form> <a class="notLogged" href="/register" id="register">sign up</a>
			</li>
		</ul>
	</div>
	<div class="advancedSearch">
		<form class="searchBar" action="/home" method="post">
			<table>
				<tr>
					<td><fieldset>
						<legend>Equipments</legend>
							<div>
								<input type="checkbox" name="equipments" value="shower">
								<label>Shower</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="bath">
								<label>Bath</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="hoven">
								<label>Hoven</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="microwave">
								<label>MicroWave</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="coffeeMachine">
								<label>Coffee machine</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="vacuum">
								<label>Vacuum</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="TV"> 
								<label>TV</label>
							</div>
		
							<div>
								<input type="checkbox" name="equipments" value="wifi">
								<label>Wifi</label>
							</div>
						</fieldset></td>
		
						<td><fieldset>
							<legend>Services</legend>
									
							<div>
								<input type="checkbox" name="services" value="water"> 
								<label>Water plants</label>
							</div>
		
							<div>
								<input type="checkbox" name="services" value="pet"> 
								<label>Keep pet</label>
							</div>
		
							<div>
								<input type="checkbox" name="services" value="clean"> 
								<label>Clean house</label>
							</div>
		
						</fieldset></td>
						
						<td><fieldset>
							<legend>Constraints</legend>
		
							<div>
								<input type="checkbox" name="constraints" value="smoking">
								<label>No smoking</label>
							</div>
		
							<div>
								<input type="checkbox" name="constraints" value="noise">
								<label>No noise after 11pm</label>
							</div>
		
							<div>
								<input type="checkbox" name="constraints" value="pets">
								<label>No pets</label>
							</div>
		
							<div>
								<input type="checkbox" name="constraints" value="children">
								<label>No children</label>
							</div>
		
						</fieldset></td>
					</tr>
					<tr>
						<td colspan="3"><input type="text" name="motCle" placeholder="search..." />
						<input type="submit" name="action" value="search" /></td>
					</tr>
			</table>
		</form>
	</div>
	<table class="tabOffers">
		<tr>
			<th></th>
			<th>Name</th>
			<th>Location</th>
			<th>Duration</th>
			<th></th>
		</tr>
		<c:forEach items="${offers}" var="o" varStatus="loop">
			<tr>
				<td width="384" height="216"><a href="/offer?ref=${o.idOffer}"><img src="data:image/jpg;base64,${images[loop.index]}" width="384" height="216" /></a></td>
				<c:forEach items="${houses}" var="h">
					<c:if test = "${h.idHouse == o.houseId}">
						<td><a href="/offer?ref=${o.idOffer}"><div style="height: 216px; width: 100%">${h.name}</div></a></td>
						<td><a href="/offer?ref=${o.idOffer}"><div style="height: 216px; width: 100%">${h.location}</div></a></td>
					</c:if>
				</c:forEach>
				<td><a href="/offer?ref=${o.idOffer}"><div style="height: 216px; width: 100%">${o.begin} to ${o.end}</div></a></td>
				<td><a class="delete" href="/delete?ref=${o.idOffer}">delete</a></td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>