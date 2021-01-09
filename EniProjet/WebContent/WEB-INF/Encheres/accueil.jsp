<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
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
<p>
<label for="recherchearticle">Filtres</label>
<input type="text" name="recherchearticle" id="recherchearticle"
placeholder="le nom de l'article contient" size= "40"/>
<br/>
<br/>
<label for="categorie">Catégorie : </label>
<select name="categorie" size="1">
<option value="all" selected="selected">Toutes</option>
<option value="Informatique">Informatique</option>
<option value="Ameublement">Ameublement</option>
<option value="Vetement">Vêtement</option>
<option value="SportLoisirs">Sport&Loisirs</option>
</select>
</p>

<fieldset>

<div class="choixAV">
<input checked type="radio" id="achat" name="choixAV" value="achat">
<label for="achat">Achats</label><br>
	<fieldset>
<input type="checkbox" id="choixA1" name="choixA1" value="enchereouverte">
<label for="achat">enchères ouverte</label><br>
<input type="checkbox" id="choixA2" name="choixA2" value="enchereencours">
<label for="achat">mes enchères en cours</label><br>
<input type="checkbox" id="choixA3" name="choixA3" value="enchereremporte">
<label for="achat">mes enchères remportés</label><br>
	</fieldset>
<input type="radio" id="vente" name="choixAV" value="vente">
<label for="vente">Mes ventes</label><br>
	<fieldset>
<input type="checkbox" id="choixV1" name="choixV1" value="venteouverte">
<label for="achat">mes ventes en cours</label><br>
<input type="checkbox" id="choixV2" name="choixV2" value="ventenondebute">
<label for="achat">ventes non debutées</label><br>
<input type="checkbox" id="choixV3" name="choixV3" value="ventetermine">
<label for="achat">ventes terminées</label><br>
	</fieldset>
</div>
</fieldset>

<br/>
<br/>
<input type="submit" value="RECHERCHER">
<br/>
<p><%=request.getAttribute("affichageListeEnchere") %> </p>
<br/>
<p>
	<ul>
		<c:forEach var="v" items="${listeArticles}">
		<jsp:useBean id="now" class="java.util.Date" />
<c:set var="currentDate" value="${v.dateFinEncheres}" />
<fmt:parseDate value="${currentDate}" var="finEnchere" pattern="yyyy-MM-dd" />

<c:if test="${now.time lt finEnchere.time}">
		
		
		
			<p>
			<li><a href="<%=request.getContextPath()%>/ServletEncherir?idarticle=${v.noArticle}">${v.nomArticle}</a></li>
			<li>${v.prixVente}</li>
			<li>${v.dateFinEncheres}</li>
			<li>
			
			<a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${v.utilisateur.pseudo}">${v.utilisateur.pseudo}</a>
			</li>
			</p> 
			</c:if>
				</c:forEach>
				
					</ul>	


</body>
</html>