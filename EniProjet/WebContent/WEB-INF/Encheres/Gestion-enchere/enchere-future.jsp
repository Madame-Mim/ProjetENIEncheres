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

	
	<section class="row col-xs-offset-3 col-xs-6 col-xs-offset-3">
		<h3>Nouvelle vente</h3>
		<br>
		<div class="clearfix"></div>
		<br>
		<form action="<%=request.getContextPath()%>/VenteFuture?idarticle=${article.noArticle}" method="post">
			<div class="col-xs-offset-1 col-xs-4"> Article :</div><div class="col-xs-6"><input name="nom" type="text" value="${article.nomArticle}"  size=36></div>
			<br><br>
			<div class="col-xs-offset-1 col-xs-4"> Description : </div><div class="col-xs-6"> <textarea name="description" cols="35" rows="3">${article.description}</textarea> </div>
			<br>
			
			<div class="col-xs-offset-1 col-xs-4"> Catégorie :</div>
				<div class="col-xs-6">
				
					<select name="categorie">	
						<% request.getAttribute("categorieListe"); %>
						<c:forEach var="v" items="${categorieListe}">
							<c:if test="${article.categorie.getNoCategorie()==v.noCategorie}">
								<c:set var="selected" value="selected"></c:set>
							</c:if>
							<c:if test="${article.categorie.getNoCategorie()!=v.noCategorie}">
								<c:set var="selected" value=""></c:set>
							</c:if>
			   				<option value="${v.noCategorie}" ${selected} > ${v.libelle}</option>
						</c:forEach>
					</select>
				</div>
			<br>
			<div class="clearfix"></div>
			<br>
			
			<div class="col-xs-offset-1 col-xs-4"> photo de l'article : </div><div class="col-xs-6"><label class="submit" for="upload">uploader</label> <input id="upload" type="file"> </div>
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-4">Mise à prix : </div> <div class="col-xs-6"><input name="prix" type="number" value="${article.miseAPrix}" min=1 size=3></div>
			<br><br>
			<fmt:parseDate  value="${article.dateDebutEncheres}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
			<fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd" var="debutDateEnchere" />
			<div class="col-xs-offset-1 col-xs-4">Début de l'enchère : </div><div class="col-xs-7"><input name="dateDebut"min="${date}" type="date" value="${debutDateEnchere}"/>
			<c:if test="${not empty erreur}">
				<span class="text-danger erreur"> ${erreur} </span>
			</c:if></div>
			<br><br>
			<fmt:parseDate  value="${article.dateFinEncheres}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
			<fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd" var="finDateEnchere" />	
			<div class="col-xs-offset-1 col-xs-4">Fin de l'enchère : </div><div class="col-xs-6"><input name="dateFin" type="date" min="${date}" value="${finDateEnchere}"/>
			<c:if test="${not empty erreur2}">
				<span class="text-danger erreur"> ${erreur2} </span>
			</c:if></div>
			<br><br><br>
			
			<div class="col-xs-offset-1 col-xs-10">
			<fieldset>
		      	<legend>Retrait</legend>
				<span class="col-xs-6">Rue : </span><span class="col-xs-6"><input name="rue" type="text" value="${article.retrait.getRue()}"></span>
				<br><br>
				<span class="col-xs-6">Code postal : </span> <span class="col-xs-6"> <input name="codepostal" type="text" value="${article.retrait.getCodePostal()}" > </span>
				<br><br>
				<span class="col-xs-6">Ville : </span><span class="col-xs-6"> <input name="ville" type="text" value="${article.retrait.getVille()}" ></span>
			</fieldset>
			</div>
			<br>
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-3"><input class="submitCourt btn btn-success" type="submit" value ="enregistrer" name="enregistrer"></div>
			<div class="col-xs-4"><input class="submitCourt btn btn-success" type="submit" value="annuler" name="annulerModif"></div>
			<div class="col-xs-3"><input class="submitLong btn btn-success" type="submit" value="annuler la vente" name="annulerVente"></div>
		</form>
	</section>
	
</body>
</html>