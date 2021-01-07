<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enchère terminée</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/header.html" %>	
	</header>
	
	<section>
		<h1>${article.utilisateur} a remporté l'enchère</h1>
		
		${article.nom}
		<br>
		Description : ${article.description}
		<br>
		Meilleur offre ${article.offre} points par ${article.utilisateur}
		<br>
		Mise à prix : ${article.prixDepart}
		<br>
		Fin de l'enchère : ${article.finEnchere}
		<br>
		Retrait : ${article.adresseRetrait}
		<br>
		Vendeur : ${article.nomVendeur}		
		<br>
		 
		<form action="<%=request.getContextPath()%>/confirmationRetrait" method="post">
			<input type="hidden" name="credit" value="${article.offre}">
			<input type="submit" value="retrait effectué">
		</form>
		<p><%=request.getAttribute("affichageListeArticle") %></p>
		
	</section>
	
</body>
</html>