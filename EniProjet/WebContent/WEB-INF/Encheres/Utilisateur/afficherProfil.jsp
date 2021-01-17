<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/.css">
<title>Afficher profil</title>
</head>

<body class="container-fluid col-xs-12">
	<header>
		<%@include file="/WEB-INF/Include/logo.html" %>
	</header>
	<section class="col-xs-offset-3 col-xs-4">
			<div class="col-xs-offset-2 col-xs-4">Pseudo:	   </div><div class="col-xs-4">	${utilisateur.pseudo}</div>
			<div class="col-xs-offset-2 col-xs-4">Nom :		   </div><div class="col-xs-4">	${utilisateur.nom}</div>
			<div class="col-xs-offset-2 col-xs-4">Prénom :     </div><div class="col-xs-4">	${utilisateur.prenom}</div>
			<div class="col-xs-offset-2 col-xs-4"> Email :	   </div><div class="col-xs-4"> ${utilisateur.email}</div>
			<div class="col-xs-offset-2 col-xs-4">Telephone :  </div><div class="col-xs-4">	${utilisateur.telephone}</div>
			<div class="col-xs-offset-2 col-xs-4">Rue :		   </div><div class="col-xs-4">	${utilisateur.rue}</div>
			<div class="col-xs-offset-2 col-xs-4">Code Postal :</div><div class="col-xs-4">	${utilisateur.codePostal}</div>
			<div class="col-xs-offset-2 col-xs-4">Ville :	   </div><div class="col-xs-4">	${utilisateur.ville}</div>
			
				<div class="clearfix"></div>
				<br><br>
			<form action="<%=request.getContextPath()%>/ServletModifierMonProfil">
				<div class="col-xs-offset-2 col-xs-4"><input class="btn btn-success" class="submit" type="submit" value="Modifier"></div>
			</form>
				<div class="clearfix"></div>
				<br><br>
	</section>
</body>
</html>