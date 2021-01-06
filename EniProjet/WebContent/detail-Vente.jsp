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
		
		${article.nom}
		<br>
		Description : ${article.description}
		<br>
		Catégorie ${article.categorie}
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
		Ma proposition 
		<form action="<%=request.getContextPath()%>/encherir" method="post">
			<input type="number" min=${article.Prix}+1 size=10>
			<input type="submit" value="Enchérir">
		</form>
		
		
	</section>
	
</body>
</html>