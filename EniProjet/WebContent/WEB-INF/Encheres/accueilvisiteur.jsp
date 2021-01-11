<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accueil visiteur</title>

</head>
<body>

<header>
		<%@ include file="/WEB-INF/Include/header.html" %>
</header>
	
	
<h1> Liste des enchères</h1>

<c:set var = "recherchearticle" value ="le nom de l'article contient" />
<form method="post" action="<%=request.getContextPath()%>/ServletAccueil">
<label for="recherchearticle">Filtres</label>
<input type="text" name="recherche" 
placeholder="${recherchearticle}" size= "10"/>
<br/>


<br/>
<label for="categorie">Catégorie : </label>
<select name="categorie" size="1">
<option value="" selected="selected">Toutes</option>
<option value="1">Informatique</option>
<option value="2">Ameublement</option>
<option value="3">Vêtement</option>
<option value="4">Sport et Loisirs</option>
</select>
<input type="submit" value="RECHERCHER">
</form>
<br/>
<br/>


<br/>
<br/>




	


	
	<ul>
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
			<p>
			<li>${v.nomArticle}</li>
			<li>${v.prixVente}</li>
			<li>${v.dateFinEncheres}</li>
			<li>${v.utilisateur.pseudo}	</li>
			</p> 
		</c:if>
			</c:if>
			

		
	
			
			
				</c:forEach>
				
					</ul>	

<c:set var = "test1" value =""/>
</body>
</html>