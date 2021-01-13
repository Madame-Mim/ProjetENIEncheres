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

<div class="block">
<header>
<h2>Liste des enchères</h2>
</header>
</div>
<br/>
<div class="block">
	<div class="blockform">

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
	<div class="blockform" id="rechercher">
		<input class="btn btn-success" type="submit" value="RECHERCHER">
	</div>
		</form>
</div>


<br/>
<br/>
<div class="block">


<div class="dispocartes">
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

		
    	<c:if test="${(empty test1 || fn:contains(theString,filtreNom))&&(empty test2 || (catégorie eq test2))}">	
    
    		<c:if test="${now.time lt finEnchere.time && now.time gt debutEnchere.time}">
			<div class="cartes">
				<div id="colonnes">
					<div class="photo">
					<img  alt="dest" src="<%=request.getContextPath()%>/Image/destroyed.jpg" width="130" height=auto align="left" valign="center">
					</div>
				</div>
				<div id="colonnes">
					<div class=labelproduit>
					<p><u>${v.nomArticle}</u></p></div>
					<p>${v.prixVente}</p>
					<p>${v.dateFinEncheres}</p>
					<p>${v.utilisateur.pseudo}</p></div>
				</div> 
			
			</c:if>
		</c:if>
	</c:forEach>
</div>
	

<c:set var = "test1" value =""/>
</div>
</body>
</html>