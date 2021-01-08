<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail vente</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/header.html" %>	
	</header>
	
	<section>
		<h1>Vente </h1>
		<c:if test="${article.prixVente ==0}" >
		Cette vente s'est achevée sans enchère.
		</c:if>
		<c:if test="${article.prixVente !=0}">
		 ${enchere.noUtilisateur.getPseudo()} a remporté l'enchère !
		 </c:if>
		 <br>
		${article.nomArticle}
		<br>
		Description : ${article.description}
		<br>
		<c:if test="${article.prixVente ==0}" >
		Personne n'a enchéri sur ce produit.
		</c:if>
		<c:if test="${article.prixVente !=0}">
		Meilleur offre ${article.prixVente} points par ${enchere.noUtilisateur.getPseudo()}
		</c:if>
		<br>
		Mise à prix : ${article.miseAPrix} points
		<br>
		Fin de l'enchère : ${article.dateFinEncheres}
		<br>
		Retrait : ${article.retrait.getRue()}
		<br>
		${article.retrait.getCodePostal()}	${article.retrait.getVille()}
		<br>
		Vendeur :	${article.utilisateur.getPseudo()}
		<br>
			
		<form action="<%=request.getContextPath()%>/VenteTerminee" method="post">
			<input type="submit" value="Retrait effectué">
		</form>
	</section>
	
</body>
</html>