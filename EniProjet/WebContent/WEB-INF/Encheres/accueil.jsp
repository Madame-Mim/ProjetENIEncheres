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
<title>ENI-Encheres</title>

</head>

<body>

<header>
		<%@ include file="/WEB-INF/Include/header.html" %>
</header>
<h1> Liste des enchères</h1>	


<c:set var = "recherchearticle" value ="${namerecherche}" />
<form method="post" action="<%=request.getContextPath()%>/ServletAccueil">
<label for="recherchearticle">Filtres</label>
<input type="text" name="recherche" 
placeholder="${recherchearticle}" size= "40"/>
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


<br/>
<br/>




<div class="choixAV">
<input checked type="radio" id="achat" name="choixAV" value="achat">
<label for="achat">Achats</label><br>

<input checked type="checkbox" id="choixA1" name="choixA1" value="enchereouverte"  > 
<label for="achat">enchères ouverte</label><br>
<input type="checkbox" id="choixA2" name="choixA2" value="enchereencours"  > 
<label for="achat">mes enchères en cours</label><br>
<input type="checkbox" id="choixA3" name="choixA3" value="enchereremporte"  >
<label for="achat">mes enchères remportés</label><br>

<input type="radio" id="vente" name="choixAV" value="vente">
<label for="vente">Mes ventes</label><br>

<input type="checkbox" id="choixV1" name="choixV1" value="venteouverte">
<label for="achat">mes ventes en cours</label><br>
<input type="checkbox" id="choixV2" name="choixV2" value="ventenondebute">
<label for="achat">ventes non debutées</label><br>
<input type="checkbox" id="choixV3" name="choixV3" value="ventetermine">
<label for="achat">ventes terminées</label><br>

</div>



<input type="submit" value="RECHERCHER">
</form>

<br/>
<!--
<p><%=request.getAttribute("listeArticles") %> </p>
vendeur
<p>${vendeur}</p>
ma liste enchere
<p><%=request.getAttribute("malisteEncheres") %> </p>
<br/>-->
<p>
	<ul>

<c:set var = "test1" value ="${filtreNom}"/>
<c:set var = "test2" value ="${categorieselection}" />
<c:set var = "recherchearticle" value ="${filtreNom}" />
<c:set var ="vendeur" value ="${vendeur}" />
<c:set var ="choixAV" value ="${choixAV}" />
<c:set	var ="choixA1" value ="${choixA1}" />
<c:set	var ="choixA2" value ="${choixA2}" />
<c:set	var ="choixA3" value ="${choixA3}" />
<c:set	var ="choixV1" value ="${choixV1}" />
<c:set	var ="choixV2" value ="${choixV2}" />
<c:set	var ="choixV3" value ="${choixV3}" />
	
	
<c:forEach var="v" items="${listeArticles}">
		
		
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="currentDate" value="${v.dateFinEncheres}" />
<fmt:parseDate value="${currentDate}" var="finEnchere" pattern="yyyy-MM-dd" />
<c:set var="currentDate" value="${v.dateDebutEncheres}" />
<fmt:parseDate value="${currentDate}" var="debutEnchere" pattern="yyyy-MM-dd" />
<c:set var = "theString" value ="${v.nomArticle}" />
<c:set var = "catégorie" value ="${v.categorie.noCategorie}" />
<c:set var = "vendeurtest" value ="${v.utilisateur.id}" />

<c:if test="${(empty test1 || fn:contains(theString,filtreNom))&&(empty test2 || (catégorie eq test2))}">	
    	
    	
    	<c:if test="${!empty choixA1}">
    	<c:if test="${now.time lt finEnchere.time && now.time gt debutEnchere.time}">
			<div class="bloc">
			<div class="entetebloc">Enchère en cours</div>
			<li>${v.nomArticle}</li>
			<li>${v.prixVente}</li>
			<li>${v.dateFinEncheres}</li>
			<li>${v.utilisateur.pseudo}	</li>
			</div> 
		</c:if>
		</c:if>
		
		<c:if test="${!empty choixV1}">
		<c:if test="${vendeurtest eq vendeur}">
		<c:if test="${now.time lt finEnchere.time && now.time gt debutEnchere.time}">
		<div class="bloc">
			<div class="entetebloc">Ventes en cours</div>
			<li>${v.nomArticle}</li>
			<li>${v.prixVente}</li>
			<li>${v.dateFinEncheres}</li>
			<li>${v.utilisateur.pseudo}	</li>
		
		</c:if>
		</c:if>
		</c:if>
			
		
	<c:if test="${vendeurtest eq vendeur}">
		<c:if test="${!empty choixV2}">
		<c:if test="${now.time lt debutEnchere.time}">
		<div class="bloc">
			<div class="entetebloc">Ventes non debutées</div>
			<li>${v.nomArticle}</li>
			<li>${v.prixVente}</li>
			<li>${v.dateFinEncheres}</li>
			<li>${v.utilisateur.pseudo}	</li>
		
		</c:if>
		</c:if>
		<c:if test="${!empty choixV3}">
		<c:if test="${now.time gt finEnchere.time}">
		<div class="bloc">
			<div class="entetebloc">Ventes non debutées</div>
			<li>${v.nomArticle}</li>
			<li>${v.prixVente}</li>
			<li>${v.dateFinEncheres}</li>
			<li>${v.utilisateur.pseudo}	</li>
		
		</c:if>
		</c:if>
	</c:if>
</c:if>	
</c:forEach>
<c:forEach var="t" items="${malisteEncheres}">

<jsp:useBean id="now1" class="java.util.Date" />
<c:set var="currentDate" value="${t.noArticle.dateFinEncheres}" />
<fmt:parseDate value="${currentDate}" var="finEnchere" pattern="yyyy-MM-dd" />
<c:set var="currentDate" value="${t.noArticle.dateDebutEncheres}" />
<fmt:parseDate value="${currentDate}" var="debutEnchere" pattern="yyyy-MM-dd" />
<c:set var = "theString" value ="${t.noArticle.nomArticle}" />
<c:set var = "catégorie" value ="${t.noArticle.categorie.noCategorie}" />
<c:set var = "meilleurEnchere" value ="${t.noArticle.prixVente}" />
<c:set var = "maMeilleurEnchere" value ="${t.montantEnchere}" />

<c:if test="${(empty test1 || fn:contains(theString,filtreNom))&&(empty test2 || (catégorie eq test2))}">	



		<c:if test="${!empty choixA}">
		<c:if test="${now1.time lt finEnchere.time && now1.time gt debutEnchere.time}">
		<div class="bloc">
			<div class="entetebloc">Enchere en cours</div>
			<li>${t.noArticle.nomArticle}</li>
			<li>${t.noArticle.prixVente}</li>
			<li>${t.noArticle.dateFinEncheres}</li>
			<li>${t.noArticle.utilisateur.pseudo}	</li>
			
		
		
		</c:if>
		</c:if>	
		
		<c:if test="${!empty choixA}">
		<c:if test="${now1.time gt finEnchere.time}">
		<c:if test="${meilleurEnchere eq maMeilleurEnchere}">
		<div class="bloc">
			<div class="entetebloc">Mes enchères remportées</div>
			<li>${t.noArticle.nomArticle}</li>
			<li>${t.noArticle.prixVente}</li>
			<li>${t.noArticle.dateFinEncheres}</li>
			<li>${t.noArticle.utilisateur.pseudo}	</li>
		
		</c:if>
		</c:if>	
		</c:if>
</c:if>
</c:forEach>						
</ul>	

</body>
</html>