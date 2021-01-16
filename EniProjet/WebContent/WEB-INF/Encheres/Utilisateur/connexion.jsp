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
	<c:if test="${not empty Cpseudo}">
		<c:set var="cookiepseudo" value="${Cpseudo}"> </c:set>
		<c:set var="checked" value="checked"> </c:set>		
	</c:if>
	<c:if test="${empty Cpseudo}">
		<c:set var="cookiepseudo" value=""> </c:set>
	</c:if>
		<c:if test="${not empty Cpassword}">
		<c:set var="cookiepassword" value="${Cpassword}"> </c:set>
	</c:if>
	<c:if test="${empty Cpassword}">
		<c:set var="cookiepassword" value=""> </c:set>
	</c:if>
	<section class="row col-xs-offset-4 col-xs-4 col-xs-offset-4">
		<form action="<%=request.getContextPath()%>/connexion" method="post">
			<label for="identifiant">Identifiant : </label>
			<input class="texte" type="text" name="pseudo" id="identifiant" size=30 value="${cookiepseudo}" required/>
			<br><br>
			<label for="password">Mot de passe : </label>
			<input class="texte" type="password" name="password" id="password"  size=30 value="${cookiepassword}" required/>
			<br><br>
			<c:if test="${not empty erreur}">
				<span class="text-danger erreur"> ${erreur} </span>
			<br><br>
			</c:if>
			<div class="clearfix"></div>
<br><br>
			<input class="submit btn btn-success" type="submit" value="Connexion">
			 
			 <div id="iteration2">
			 <input type="checkbox" name="souvenir" id=souvenir value="1" ${checked} >
				 Se souvenir de moi
				<br><br>
				<a href="/EniProjet/ServletMotDePasseOublie">Mot de passe oublié</a>
			</div>
		</form>
		
	<div class="clearfix"></div>
<br><br>
			<form action="<%=request.getContextPath()%>/ServletInscriptionUtilisateur">
				<input class="submit btn btn-success" type="submit" value="Créer un compte">
			</form>
			
	</section>
</body>
</html>