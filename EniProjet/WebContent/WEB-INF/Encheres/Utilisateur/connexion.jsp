<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/connexion.css">
<title>Se connecter</title>
</head>
<%@include file="/WEB-INF/Include/logo.html" %>
<body class="container-fluid col-xs-12">

	<div class="clearfix"></div>
<br><br>

	<section class="row col-xs-offset-4 col-xs-4 col-xs-offset-4">
	
		<form action="<%=request.getContextPath()%>/connexion" method="post">
			<label for="identifiant">Identifiant : </label>
			<input class="texte" type="text" name="pseudo" id="identifiant" size=30/>
			<br><br>
			<label for="password">Mot de passe : </label>
			<input class="texte" type="password" name="password" id="password"  size=30>
			<br><br>
			<input class="submit" type="submit" value="Connexion">
			 
			 <div id="iteration2">
			 <input type="checkbox" name="souvenir" id=souvenir value="1">
				 Se souvenir de moi
				<br>
				<a href="/EniProjet/ServletMotDePasseOublie">Mot de passe oublié</a>
			</div>
		</form>
		
	<div class="clearfix"></div>
<br><br>
			<form action="<%=request.getContextPath()%>/ServletInscriptionUtilisateur">
				<input class="submit" type="submit" value="Créer un compte">
			</form>
			
	</section>
</body>
</html>