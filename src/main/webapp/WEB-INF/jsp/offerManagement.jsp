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
<body>
	<div>
		<a href="/personalSpace">Back</a>
		<form action="/addOffer/submit" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Name</td>
					<td><input type="text" name="offerName"/></td>
				</tr>
				<tr>
					<td>Location</td>
					<td><input type="text" name="offerLocation" /></td>
				</tr>
				<tr>
					<td>Duration</td>
					<td><input type="text" name="offerDuration" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea name="offerDescription" /></textarea></td>
				</tr>
				<tr>
					<td>Images</td>
					<td><input type="file" name="offerImages" accept="image/png, image/jpeg" multiple></td>
				</tr>
			</table>
			<fieldset>
			    <legend>Equipements</legend>
			
			    <div>
			      <input type="checkbox" name="equipments" value="shower">
			      <label>Douche</label>
			    </div>
			
			    <div>
			      <input type="checkbox" name="equipments" value="bath">
			      <label>Baignoire</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value="hoven">
			      <label>Four</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value="microwave">
			      <label>Micro-onde</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="coffeeMachine">
			      <label>Machine à café</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="vacuum">
			      <label>Aspirateur</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="TV">
			      <label>Télévision</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="equipments" value ="wifi">
			      <label>Wifi</label>
			    </div>
			</fieldset>
			<fieldset>
			    <legend>Services</legend>
			
			    <div>
			      <input type="checkbox" name="services" value="water">
			      <label>Arroser les plantes</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="services" value="pet">
			      <label>Keep pet</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="services" value="clean">
			      <label>Clean house</label>
			    </div>
			    
			</fieldset>
			<fieldset>
			    <legend>Constraints</legend>
			
			    <div>
			      <input type="checkbox" name="constraints" value="smoking">
			      <label>Interdiction de fumer</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="noise">
			      <label>Pas de bruit après 23h</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="pets">
			      <label>Pas d'animaux</label>
			    </div>
			    
			    <div>
			      <input type="checkbox" name="constraints" value="children">
			      <label>Pas d'enfants</label>
			    </div>
			    
			    
			</fieldset>
			<input type="submit" name="action" value="save" />
		</form>
		<div> <a href="/personalSpace">back</a> </div>
	</div>
	
</body>
</html>