<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../CSS/connexion.css">
<title>Se connecter</title>
</head>
<body class="container-fluid col-xs-12">

	<div class="clearfix"></div>
<br><br>

	<section class="row col-xs-offset-4 col-xs-4 col-xs-offset-4">
	
		<form action="<%=request.getContextPath()%>/connexion" method="post">
			<label for="identifiant">Identifiant : </label>
			<input type="text" name="pseudo" id="identifiant"/>
			<br>
			<label for="password">Mot de passe : </label>
			<input type="password" name="password" id="password">
			<br>
			<input type="submit" value="Connexion">
			<input type="checkbox" name="souvenir" id=souvenir> 
			<label for="souvenir">Se souvenir de moi</label>
			<br>
			<a href="#">Mot de passe oublié</a>
		</form>
		
	<div class="clearfix"></div>
<br><br>
			<a href="#">Créer un compte</a>
			
	</section>
</body>
</html>