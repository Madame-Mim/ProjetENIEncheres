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
<title>Vente future</title>
</head>
	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
<body>

	
	<section class="row col-xs-offset-4 col-xs-5 col-xs-offset-4">
		<h3>Nouvelle vente</h3>
		<br>
		<form action="<%=request.getContextPath()%>/VenteFuture?idarticle=${article.noArticle}" method="post">
		<div class="col-xs-offset-2 col-xs-4"> Article :</div><div class="col-xs-6"><input name="nom" type="text" value="${article.nomArticle}"></div>
		<br>
		<div class="col-xs-offset-2 col-xs-4"> Description : </div><div class="col-xs-6"> <textarea name="description">${article.description}</textarea> </div>
		<br>
		
		<div class="col-xs-offset-2 col-xs-4"> Catégorie :</div><div class="col-xs-6">	
			<select name="categorie">	
				<% request.getAttribute("categorieListe"); %>
				<c:forEach var="v" items="${categorieListe}">
	   				<option value="${v.noCategorie}">${v.libelle}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-xs-offset-2 col-xs-4"> photo de l'article : </div><div class="col-xs-6"> <input type="file"> </div>
		
		<div class="col-xs-offset-2 col-xs-4">Mise à prix : </div> <div class="col-xs-6"><input name="prix" type="number" value="${article.miseAPrix}" size=3></div>
		<br>
		<c:set var="debutDateEnchere" value="<%=new java.util.Date()%>" />
		<div class="col-xs-offset-2 col-xs-4">Début de l'enchère : </div><div class="col-xs-6"><input name="dateDebut" type="date" value="${debutDateEnchere}"/></div>
		<br>
		<c:set var="finDateEnchere" value="<%=new java.util.Date()%>" />
		<div class="col-xs-offset-2 col-xs-4">Fin de l'enchère : </div><div class="col-xs-6"><input name="dateFin" type="date" value="${finDateEnchere}"/></div>
		<br><br><br>
		
		<div class="col-xs-offset-2 col-xs-10">
		<fieldset>
	      	<legend>Retrait</legend>
			<span class="col-xs-6">Rue : </span><span class="col-xs-6"><input type="text" value="${article.retrait.getRue()}"></span>
			<span class="col-xs-6">Code postal : </span> <span class="col-xs-6"> <input type="text" value="${article.retrait.getCodePostal()}" > </span>
			<span class="col-xs-6">Ville : </span><span class="col-xs-6"> <input type="text" value="${article.retrait.getVille()}" ></span>
		</fieldset>
		</div>
		<br>
		<div class="col-xs-offset-2 col-xs-4"><input type="submit" value = "enregistrer" name="enregistrer"></div>
		<div class="col-xs-3"><input type="submit" value = "annuler" name="annulerModif"></div>
		<div class="col-xs-3"><input type="submit" value = "annuler la vente" name="annulerVente"></div>
	</form>
	</section>
	
</body>
</html>