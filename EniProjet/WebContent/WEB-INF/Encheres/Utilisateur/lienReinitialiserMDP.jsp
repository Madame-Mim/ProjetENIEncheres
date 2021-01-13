<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/Include/logo.html" %>
<title>Insert title here</title>
</head>
<body>
	<p>Bonjour, suite à votre demande, cliquez sur le lien suivant pour modifier votre mot de passe:<a href="/EniProjet/ServletReinitialisationMotDePasse?idUtilisateur=${idUtilisateur}">Cliquez ici.</a></p>
<p>Votre adresse email et votre nouveau mot de passe vous permettront de vous connecter à tout moment à votre espace</p>
<p>L'équipes ENI-Encheres.</p>
<br>
<p>Merci de ne pas répondre sur cette adresse mail car nous serons dans l'impossibilité de lire votre message.</p>
	
</body>
</html>