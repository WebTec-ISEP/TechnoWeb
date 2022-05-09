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
					<td><input type="text" name="offerName" /></td>
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
				<tr>
					<td><input type="submit" name="action" value="save" /></td>
				</tr>
			</table>
		</form>
		<div> <a href="/personalSpace">back</a> </div>
	</div>
</body>
</html>