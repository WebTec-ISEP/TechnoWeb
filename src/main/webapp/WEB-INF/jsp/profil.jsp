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
			}
		});
	</script>
</head>
<body>
	<a href="/offer?ref=${idOffer}">Back</a>
	<a href="/home">Home</a>
	<table>
		<tr>
			<th>REF</th>
			<th>Name</th>
		</tr>
		<tr>
			<td>${user.idUser}</td>
			<td>${user.name}</td>
		</tr>
	</table>
	<a href="/personalSpace" class="personalSpace">PersonalSpace</a>
	<a href="/messaging?user=${user.name}" class="contact">contact</a>
</body>
</html>