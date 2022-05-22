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
		<form action="/addOffer/submit" method="post" enctype="multipart/form-data" class="offerForm">
			<fieldset>
				<legend>House info</legend>
				<table>
					<tr>
						<td><label>Name</label></td>
						<td><input type="text" name="offerName" value = "${offer.name}"/></td>
					</tr>
					<tr>
						<td><label>Location</label></td>
						<td><input type="text" name="offerLocation" value = "${offer.location}" /></td>
					</tr>
					<tr>
						<td><label>Duration</label></td>
						<td><input type="text" name="offerDuration" value = "${offer.duration}" /></td>
					</tr>
					<tr>
						<td><label>Description</label></td>
						<td><textarea name="offerDescription">${offer.description}</textarea></td>
					</tr>
					<tr>
						<td><label>Images</label></td>
						<td><input type="file" name="offerImages" accept="image/png, image/jpeg" multiple></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
			    <legend>Equipments</legend>
			
			    <div>
			      <input type="checkbox" name="equipments" value="shower" <c:if test="${fn:contains(checkList, 'shower')}">checked</c:if>>
				  <label>Shower</label>
			    </div>
			
			    <div>
			      <input type="checkbox" name="equipments" value="bath" <c:if test="${fn:contains(checkList, 'bath')}">checked</c:if>>
			      <label>Bath</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value="hoven" <c:if test="${fn:contains(checkList, 'hoven')}">checked</c:if>>
			      <label>Hoven</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value="microwave" <c:if test="${fn:contains(checkList, 'microWave')}">checked</c:if>>
			      <label>MicroWave</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="coffeeMachine" <c:if test="${fn:contains(checkList, 'coffeeMachine')}">checked</c:if>>
			      <label>Coffee machine</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="vacuum" <c:if test="${fn:contains(checkList, 'vacuum')}">checked</c:if>>
			      <label>Vacuum</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="TV" <c:if test="${fn:contains(checkList, 'TV')}">checked</c:if>>
			      <label>TV</label>
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
			      <label>Water plants</label>
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
			      <label>No smoking</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="noise" <c:if test="${fn:contains(checkList, 'noise')}">checked</c:if>>
			      <label>No noise after 11pm</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="pets" <c:if test="${fn:contains(checkList, 'pets')}">checked</c:if>>
			      <label>No pets</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="children" <c:if test="${fn:contains(checkList, 'children')}">checked</c:if>>
			      <label>No children</label>
			    </div>
			    
			    
			</fieldset>
			<input type="submit" name="action" value="save" />
		</form>
	</div>
</body>
</html>