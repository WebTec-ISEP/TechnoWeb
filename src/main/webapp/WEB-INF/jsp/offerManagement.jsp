<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Offer Management</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<body>
	<div class="navigationBar">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a href="/personalSpace">Personal space</a></li>
			<li><a href="/messaging">Messages</a></li>
			<li style="float:right"><a href="/logout">log out</a></li>
		</ul>
	</div>
	<div>
		<a href="/personalSpace">Back</a>
		<form action="/addOffer/submit" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Name</td>
					<td><input type="text" name="offerName" value = "${offer.name}"/></td>
				</tr>
				<tr>
					<td>Location</td>
					<td><input type="text" name="offerLocation" value = "${offer.location}" /></td>
				</tr>
				<tr>
					<td>Duration</td>
					<td><input type="text" name="offerDuration" value = "${offer.duration}" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea name="offerDescription"/>${offer.description}</textarea></td>
				</tr>
				<tr>
					<td>Images</td>
					<td><input type="file" name="offerImages" accept="image/png, image/jpeg" multiple></td>
				</tr>
			</table>
			<fieldset>
			    <legend>Equipements</legend>
			
			    <div>
			      <input type="checkbox" name="equipments" value="shower" <c:if test="${fn:contains(checkList, 'shower')}">checked</c:if>>
				  <label>Douche</label>
			    </div>
			
			    <div>
			      <input type="checkbox" name="equipments" value="bath" <c:if test="${fn:contains(checkList, 'bath')}">checked</c:if>>
			      <label>Baignoire</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value="hoven" <c:if test="${fn:contains(checkList, 'hoven')}">checked</c:if>>
			      <label>Four</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value="microwave" <c:if test="${fn:contains(checkList, 'microWave')}">checked</c:if>>
			      <label>Micro-onde</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="coffeeMachine" <c:if test="${fn:contains(checkList, 'coffeeMachine')}">checked</c:if>>
			      <label>Machine à café</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="vacuum" <c:if test="${fn:contains(checkList, 'vacuum')}">checked</c:if>>
			      <label>Aspirateur</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="TV" <c:if test="${fn:contains(checkList, 'TV')}">checked</c:if>>
			      <label>Télévision</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="wifi" <c:if test="${fn:contains(checkList, 'wifi')}">checked</c:if>>
			      <label>Wifi</label>
			    </div>
			</fieldset>
			<fieldset>
			    <legend>Services</legend>
			
			    <div>
			      <input type="checkbox" name="services" value="water" <c:if test="${fn:contains(checkList, 'water')}">checked</c:if>>
			      <label>Arroser les plantes</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="services" value="pet" <c:if test="${fn:contains(checkList, 'pet')}">checked</c:if>>
			      <label>Keep pet</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="services" value="clean" <c:if test="${fn:contains(checkList, 'clean')}">checked</c:if>>
			      <label>Clean house</label>
			    </div>
			    
			</fieldset>
			<fieldset>
			    <legend>Constraints</legend>
			
			    <div>
			      <input type="checkbox" name="constraints" value="smoking" <c:if test="${fn:contains(checkList, 'smoking')}">checked</c:if>>
			      <label>Interdiction de fumer</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="noise" <c:if test="${fn:contains(checkList, 'noise')}">checked</c:if>>
			      <label>Pas de bruit après 23h</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="pets" <c:if test="${fn:contains(checkList, 'pets')}">checked</c:if>>
			      <label>Pas d'animaux</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="children" <c:if test="${fn:contains(checkList, 'children')}">checked</c:if>>
			      <label>Pas d'enfants</label>
			    </div>
			    
			    
			</fieldset>
			<input type="submit" name="action" value="save" />
		</form>
		<div> <a href="/personalSpace">back</a> </div>
	</div>
</body>
</html>