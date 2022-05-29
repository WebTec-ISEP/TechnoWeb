<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>House Management</title>
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
		<form action="/house/add" method="post" enctype="multipart/form-data" class="offerForm">
			<fieldset>
				<legend>House info</legend>
				<table>
					<tr>
						<td><label>Name</label></td>
						<td><input type="text" name="houseName" value = "${house.name}"/></td>
					</tr>
					<tr>
						<td><label>Location</label></td>
						<td><input type="text" name="houseLocation" value = "${house.location}" /></td>
					</tr>
					<tr>
						<td><label>Description</label></td>
						<td><textarea name="houseDescription">${house.description}</textarea></td>
					</tr>
					<tr>
						<td><label>Images</label></td>
						<td><input type="file" name="houseImages" accept="image/png, image/jpeg" multiple></td>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="houseId" value="${house.idHouse}" />
			<input type="submit" name="action" value="save" />
		</form>
	</div>
</body>
</html>