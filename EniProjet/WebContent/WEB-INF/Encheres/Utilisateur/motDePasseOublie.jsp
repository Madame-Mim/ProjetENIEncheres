<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/Include/logo.html" %>
<title>Réinitialiser mot de passe</title>
</head>
<body>
	<h1>Mot de passe oublié</h1>
	<form method="post" action="/EniProjet/ServletMotDePasseOublie">
	<label>Adresse Mail : </label><input type="text" name="adresseMail" required>
	<br>
	<input type="submit" value="Valider">
	</form>
</body>
</html>