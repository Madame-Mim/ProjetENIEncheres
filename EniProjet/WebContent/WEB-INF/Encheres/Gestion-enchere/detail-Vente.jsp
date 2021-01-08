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
		<h1>Détail vente</h1>
		
		${article.nomArticle}
		<br>
		Description : ${article.description}
		<br>
		Catégorie ${article.categorie.getLibelle()}
		<br>
		<c:if test="${article.prixVente ==0}" >
		Personne n'a encore enchéri sur votre produit.
		</c:if>
		<c:if test="${article.prixVente !=0}">
		Meilleur offre ${article.prixVente} points par ${enchere.utilisateur.getPseudo()}
		</c:if>
		<br>
		Mise à prix : ${article.miseAPrix}
		<br>
		Fin de l'enchère : ${article.dateFinEncheres}
		<br>
		Retrait : ${article.retrait.getRue()}
		<br>
		${article.retrait.getCodePostal()}	${article.retrait.getVille()}
		<br>
		Vendeur :	${article.utilisateur.getPseudo()}
		<br>
		Ma proposition 
		 <c:if test="${article.miseAPrix>article.prixVente}" >
		 <c:set var="proposition" value="${article.miseAPrix}" scope="page" />
		 </c:if>
		 <c:if test="${article.miseAPrix<=article.prixVente}" >
		 <c:set var="proposition" value="${article.prixVente+1}" scope="page" />
		 </c:if>
		<form action="<%=request.getContextPath()%>/encherir" method="post">
			<input type="number" c:out min="${proposition}" size=10>
			<input type="submit" value="Enchérir">
		</form>
		
	</section>
	
</body>
</html>