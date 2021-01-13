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
<title>Enchère en cours</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
	
	<section class="row col-xs-offset-3 col-xs-6 col-xs-offset-3">
		<h2>Détail vente</h2>
		
		<h3>${article.nomArticle}</h3>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Description : </div><div class=" col-xs-6">${article.description}</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4"> Catégorie</div><div class=" col-xs-6"> ${article.categorie.getLibelle()}</div>
		<br>
		<c:if test="${article.prixVente ==0}" >
		<br><br>
		<div class="col-xs-offset-2 col-xs-10"><strong>Personne n'a encore enchéri sur ce produit.</strong></div>
		<br>
		</c:if>
		<c:if test="${article.prixVente !=0}">
		<div class="col-xs-offset-2 col-xs-4">Meilleur offre : </div><div class="col-xs-6">${article.prixVente} points par <a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2">${enchere.noUtilisateur.getPseudo()}</a></div>
		</c:if>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Mise à prix : </div> <div class="col-xs-6">${article.miseAPrix} points</div>
		<br>
		<fmt:parseDate  value="${article.dateFinEncheres}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
		<fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" var="finDateEnchere" />	
		<div class="col-xs-offset-2 col-xs-4">Fin de l'enchère : </div><div class="col-xs-6">${finDateEnchere}</div>
		<br>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Retrait : </div><div class="col-xs-6">${article.retrait.getRue()}</div>
		<br>
		<div class="col-xs-offset-6 col-xs-6">${article.retrait.getCodePostal()}	${article.retrait.getVille()}</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Vendeur :	</div><div class="col-xs-6">${article.utilisateur.getPseudo()}</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4">Ma proposition </div>
		 <c:if test="${article.miseAPrix>article.prixVente}" >
		 	<c:set var="proposition" value="${article.miseAPrix}" scope="page" />
		 </c:if>
			<c:if test="${article.miseAPrix<=article.prixVente}" >
			 <c:set var="proposition" value="${article.prixVente+1}" scope="page" />
		 </c:if>
		 
		 <c:choose>
			 <c:when test="${utilisateur.getCredit()<article.prixVente}" >
				 <c:set var="enabled" value="disabled" scope="page" />
				 <c:set var="message" value="Vous n'avez pas assez de crédit pour surenchérir !" scope="page" />
			 </c:when>
			 <c:when test="${article.utilisateur.getId()==sessionScope.session}" >
				 <c:set var="enabled" value="disabled" scope="page" />
				 <c:set var="message" value="Vous ne pouvez pas enchérir sur votre propre bien !" scope="page" />
			 </c:when>
			 <c:when test="${enchere.noUtilisateur.getId()==sessionScope.session}" >
				 <c:set var="enabled" value="disabled" scope="page" />
				 <c:set var="message" value="Vous êtes déjà le meilleur enchérisseur !" scope="page" />
			 </c:when>
			 <c:otherwise>
			 	<c:set var="message" value="Vous disposez de ${utilisateur.getCredit()} points pour enchérir" scope="page" />
			 </c:otherwise>
		 </c:choose>
		 
		<div class="col-xs-6">
		<form action="<%=request.getContextPath()%>/VenteEnCours?idarticle=${article.getNoArticle()}" method="post">
			<input  name="enchere" type="number" max="${article.utilisateur.getCredit()}" min="${proposition}" value="${proposition}" size=5 ${enabled}>
			<input  class="submitEncherir" type="submit" value="Enchérir" ${enabled}>
		</form>
		</div>
					<div class="clearfix"></div>
		
		<br><br>
		<span class="col-xs-offset-4 col-xs-6">${message} </span> 
		
	</section>
	
</body>
</html>