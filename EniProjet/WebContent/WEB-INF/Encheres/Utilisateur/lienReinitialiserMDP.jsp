<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Insert title here</title>
</head>
<body class="container-fluid col-xs-12">
	<header>
		<%@include file="/WEB-INF/Include/logo.html" %>
	</header>
	<div class="col-xs-offset-3 col-xs-6">Bonjour, suite à votre demande, cliquez sur le lien suivant pour modifier votre mot de passe : <a href="/EniProjet/ServletReinitialisationMotDePasse?idUtilisateur=${idUtilisateur}">Cliquez ici.</a>
		<br>Votre adresse email et votre nouveau mot de passe vous permettront de vous connecter à tout moment à votre espace
		<br>L'équipes ENI-Encheres.
		<br>
		<br>Merci de ne pas répondre sur cette adresse mail car nous serons dans l'impossibilité de lire votre message.
	</div>
	
</body>
</html>