<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/accueil.css">
<title>ENI-Enchères</title>
</head>
<body>
<header>
		<%@ include file="/WEB-INF/Include/header.html" %>
		<%@include file="/WEB-INF/Include/logo.html" %>
</header>

<section class="row col-xs-offset-1 col-xs-10 col-xs-offset-1">
<header>
<h2>Liste des enchères</h2>
</header>
</section>
<br/>
<section class="row col-xs-offset-1 col-xs-10 col-xs-offset-1">
	<div class="row col-xs-offset-1 col-xs-6">

		<form method="post" action="<%=request.getContextPath()%>/Accueil">
                    
			<label for="recherchearticle">Filtres : </label>
			<br/>
			<c:set var = "recherchearticle" value ="${namerecherche}" />
			<input type="text" name="recherche" 
			placeholder="${recherchearticle}" size= "30"/>
			<br/>
			<br/>
			<div id=categorie>
			<c:set var = "test2" value ="${categorieselection}" />
			<label for="categorie">Catégorie : </label>
			<select name="categorie" size="1" >
			<option value="" selected="selected">Toutes</option>
				<c:forEach items="${listeCategorie}" var="categ" >
					<option value="${categ.noCategorie}" ${categ.noCategorie == test2 ? 'selected="selected"' : ''} >${categ.libelle}</option>     
				</c:forEach>
			</select>
			</div>
	</div>
	<div class="col-xs-3 col-xs-offset-2" id="rechercher">
		<input class="btn btn-success" type="submit" value="RECHERCHER">
	</div>
		</form>


</section>
<br/>
<br/>
<section class="col-xs-12 ">
<br/>
<br/>

	<c:forEach var="v" items="${listeArticles}">
		
		
			<jsp:useBean id="now" class="java.util.Date" />
			<c:set var="currentDate" value="${v.dateFinEncheres}" />
			<fmt:parseDate value="${currentDate}" var="finEnchere" pattern="yyyy-MM-dd" />
			<c:set var="currentDate" value="${v.dateDebutEncheres}" />
			<fmt:parseDate value="${currentDate}" var="debutEnchere" pattern="yyyy-MM-dd" />
			<c:set var = "theString" value ="${v.nomArticle}" />
			<c:set var = "catégorie" value ="${v.categorie.noCategorie}" />
			<c:set var = "test1" value ="${filtreNom}"/>
			<c:set var = "test2" value ="${categorieselection}" />
			<c:set var = "recherchearticle" value ="${filtreNom}" />

		
    	<c:if test="${(empty test1 || fn:containsIgnoreCase(theString,filtreNom))&&(empty test2 || (catégorie eq test2))}">	
    
    		<c:if test="${now.time lt finEnchere.time && now.time gt debutEnchere.time}">
			<div class="col-xs-4" id="cartes">
				<div class="col-xs-4 col-xs-offset-1">
					<img  src="<%=request.getContextPath()%>/Image/NoImage.png" alt="${v.nomArticle}" class="photo">
				</div>
				<div class="col-xs-7">
					<div class=labelproduit>
					<p><u>${v.nomArticle}</u></p></div>
					<p>Prix : ${v.prixVente} points</p>
					<p>Fin : ${v.dateFinEncheres}</p>
					<p>Vendeur : ${v.utilisateur.pseudo}</p></div>
				</div> 
			
			</c:if>
		</c:if>
	</c:forEach>
</div>
</section>	

<c:set var = "test1" value =""/>

</body>
</html>