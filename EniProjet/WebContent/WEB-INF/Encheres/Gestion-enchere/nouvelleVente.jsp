<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/nouvelleVente.css">
<title>Nouvelle vente</title>
</head>
<body class="container-fluid col-xs-12">
	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
	<section class="col-xs-offset-3 col-xs-6">
		<div class="col-xs-12"><h1>Nouvelle vente</h1></div>
	
		<form method="post" action="/EniProjet/ServletNouvelleVente">
			
			<div class="col-xs-offset-1 col-xs-4"">
				<label>Article : </label></div>
			<div class="col-xs-6">
				<input type="text" name="nomArticle" size="37" required>
			</div>
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-4"">
				<label>Description : </label>
			</div>
			<div class="col-xs-6">
			<textarea name="descriptionArticle" required cols="35" rows="5"></textarea>
			</div>
			<br>
			<br>
			<div class="col-xs-offset-1 col-xs-4"> Catégorie :</div>
				<div class="col-xs-6">	
					<select name="categorie">	
						<% request.getAttribute("categorieListe"); %>
						<c:forEach var="v" items="${categorieListe}">
			   				<option value="${v.noCategorie}">${v.libelle}</option>
						</c:forEach>
					</select>
				</div>
			<br>
			<div class="clearfix"></div>
			<br>
			
			<div class="col-xs-offset-1 col-xs-4"> Photo de l'article : </div>
			<div class="col-xs-6"><label class="upload" for="upload">uploader</label> <input id="upload" type="file"> </div>
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-4">
				<label>Mise à prix : </label>
			</div>
			<div class="col-xs-6">	
				<input type="number" name="miseAPrixArticle" min="5" max="1000" step="5" required>
			</div>
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-4">
				<label>Début de l'enchère : </label>
			</div>
			<div class="col-xs-6">	
				<input type="date" name="debutEnchere" required>
			</div>
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-4">
				<label>Fin de l'enchère : </label>
			</div>
			<div class="col-xs-6">	
				<input type="date" name="finEnchere" required>
			</div>
			<div class="clearfix"></div>
			<br><br>
				
			<div class="col-xs-offset-1 col-xs-10">
				<fieldset>
				<legend>Retrait</legend>
								
					<span class="col-xs-offset-1 col-xs-4">
						<label>Rue : </label>
					</span>
					<span class="col-xs-6">
						<input type="text" name="rueRetrait" value="<%= request.getAttribute("rueRetraitUtilisateur")%>">
					</span>
					<div class="clearfix"></div>
					<br>
					<span class="col-xs-offset-1 col-xs-4">
						<label>Code postal : </label>
					</span>
					<span class="col-xs-6">
						<input type="text" name="codePostalRetrait" value="<%= request.getAttribute("cpRetraitUtilisateur")%>">
					</span>
					<div class="clearfix"></div>
					<br>
					<span class="col-xs-offset-1 col-xs-4">
						<label>Ville: </label>
					</span>
					<span class="col-xs-6">
						<input type="text" name="villeRetrait" value="<%= request.getAttribute("villeRetraitUtilisateur")%>">
					</span>
				</fieldset>
			</div>
			<div class="clearfix"></div>
			<br><br>
			<div class="col-xs-offset-1 col-xs-4">
				<input type="submit" value="Enregister">
				<a href="/EniProjet/Accueil">Annuler</a>
			</div>
			
		</form>
	</section>	
	<div class="clearfix"></div>
			<br><br><br><br>
</body>
</html>