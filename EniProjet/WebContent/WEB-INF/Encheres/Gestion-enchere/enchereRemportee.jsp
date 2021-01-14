<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/venteRemportee.css">
<title>Vente Remportée</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
	
	<section class="row col-xs-offset-3 col-xs-6 col-xs-offset-3">
		
		 <div class="title"><h3>Vous avez remporté la vente</h3></div>
		 <br>

		<h3>${article.nomArticle}</h3>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Description : </div><div class=" col-xs-6">${article.description}</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Meilleur offre : </div><div class="col-xs-6">${article.prixVente} points</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Mise à prix : </div> <div class="col-xs-6">${article.miseAPrix} points</div>
		<br>
		<fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" var="finDateEnchere" />	
		<div class="col-xs-offset-2 col-xs-4">Retrait : </div><div class="col-xs-6">${article.retrait.getRue()}</div>
		<br>
		<div class="col-xs-offset-6 col-xs-6">${article.retrait.getCodePostal()}	${article.retrait.getVille()}</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Vendeur :	</div><div class="col-xs-6">${article.utilisateur.getPseudo()}</div>
		<br>
		<div>Tel :</div><div class="col-xs-6">${article.utilisateur.getTelephone()}</div>
		
			<div class="clearfix"></div>
		
		<br><br><br>
		<div class="back"><a href="/EniProjet/Accueil">Retour Accueil</a> </div>
	</section>
	
</body>
</html>