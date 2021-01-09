<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/venteterminee.css">
<title>Vente terminée</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/header.html" %>	
	</header>
	
	<section class="row col-xs-offset-4 col-xs-5 col-xs-offset-4">
		
		<c:if test="${article.prixVente ==0}" >
		<h2> Cette vente s'est achevée sans enchère.</h2>
		</c:if>
		<c:if test="${article.prixVente !=0}">
		 <h2>${enchere.noUtilisateur.getPseudo()} a remporté l'enchère !</h2>
		 </c:if>
		 <br>

		<h3>${article.nomArticle}</h3>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Description : </div><div class=" col-xs-6">${article.description}</div>
		<br>
		<c:if test="${article.prixVente ==0}" >
		Personne n'a enchéri sur ce produit.
		</c:if>
		<c:if test="${article.prixVente !=0}">
		<div class="col-xs-offset-2 col-xs-4">Meilleur offre : </div><div class="col-xs-6">${article.prixVente} points par <a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2">${enchere.noUtilisateur.getPseudo()}</a></div>
		</c:if>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Mise à prix : </div> <div class="col-xs-6">${article.miseAPrix} points</div>
		<br>
		<c:set var="finDateEnchere" value="<%=new java.util.Date()%>" />
		<div class="col-xs-offset-2 col-xs-4">Fin de l'enchère : </div><div class="col-xs-6"><fmt:formatDate pattern="dd/MM/yyyy" value="${finDateEnchere}"/></div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Retrait : </div><div class="col-xs-6">${article.retrait.getRue()}</div>
		<br>
		<div class="col-xs-offset-6 col-xs-6">${article.retrait.getCodePostal()}	${article.retrait.getVille()}</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Vendeur :	</div><div class="col-xs-6">${article.utilisateur.getPseudo()}</div>
		
			<div class="clearfix"></div>
		
		<br><br><br>
		<c:if test="${article.retraitEffectue== false }">
		<form class="col-xs-offset-1 col-xs-5" action="<%=request.getContextPath()%>/VenteTerminee" method="post">
			<input class="submit" type="submit" value="Retrait effectué">
			<input name="credit" type="hidden" value="${article.prixVente}">
		</form>
		</c:if>
	</section>
	
</body>
</html>