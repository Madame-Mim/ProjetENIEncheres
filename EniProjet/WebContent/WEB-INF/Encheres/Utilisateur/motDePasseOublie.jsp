<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%@include file="/WEB-INF/Include/logo.html" %>
<title>Réinitialiser mot de passe</title>
</head>
<body>
	<div class="col-xs-offset-3">
		<h1 >Mot de passe oublié</h1>
	</div>
	<div class="clearfix"></div>
	<br>
	<form method="post" action="/EniProjet/ServletMotDePasseOublie">
		<div class="col-xs-offset-3 col-xs-4">
			<label>Adresse Mail : </label> <input type="text" name="adresseMail" size=20 required>
			
		<c:if test="${not empty erreur}">
				<br>
				<span class="text-danger erreur"> ${erreur} </span>
		</c:if>
		
		<div class="clearfix"></div>
		<br>
			
			<input class="btn btn-success" type="submit" value="Valider">
		</div>
	</form>
</body>
</html>