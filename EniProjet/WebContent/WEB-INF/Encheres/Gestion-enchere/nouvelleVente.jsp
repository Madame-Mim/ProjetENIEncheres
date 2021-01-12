<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/nouvelleVente.css">
<title>Nouvelle vente</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
	<section>
		<div class=title><h1>Nouvelle vente</h1></div>
	
		<form method="post" action="/EniProjet/ServletNouvelleVente" enctype="multipart/form-data">
			
			
			<div>
				<label>Article : </label><input type="text" name="nomArticle" required>
			</div>
			<br>
			<div>
				<label>Decsription : </label><input type="textarea" name="descriptionArticle" required>
			</div>
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
			
			<div class="col-xs-offset-1 col-xs-4"> photo de l'article : </div><div class="col-xs-6"><label class="submit" for="upload">uploader</label> <input id="upload" type="file"> </div>
			<br>
			<div>
				<label>Mise à prix : </label><input type="number" name="miseAPrixArticle" min="5" max="1000" step="5" required>
			</div>
			<br>
			<div>
				<label>Début de l'enchère : </label><input type="date" name="debutEnchere" required>
			</div>
			<br>
			<div>
				<label>Fin de l'enchère : </label><input type="date" name="finEnchere" required>
			</div>
			<br>
				
			<div class="retrait">
				<label>Retrait</label>
			
				<div class="detailRetrait">
				<label>Rue : </label><input type="text" name="rueRetrait">
				<br>
				<label>Code postal : </label><input type="text" name="codePostalRetrait">
				<br>
				<label>Ville: </label><input type="text" name="villeRetrait">
				</div>
			</div>
			
			<br>
			<div>
				<input type="submit" value="Enregister">
				<a href="/EniProjet/ServletAccueil">Annuler</a>
			</div>
		</form>
	</section>	
</body>
</html>