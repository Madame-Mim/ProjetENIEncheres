<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
	<section>
		<div class=title><h1>Nouvelle vente</h1></div>
	
		<form method="post" action="/EniProjet/ServletNouvelleVente">
			
			
			<div>
				<label>Article : </label><input type="text" name="nomArticle" required>
			</div>
			<br>
			<div>
				<label>Decsription : </label><input type="text" name="descriptionArticle" required>
			</div>
			<br>
			<div>
				<label>Catégorie </label><select name="categorieArticle" required>
				<option value=2>Informatique</option>
				<option value=3>Ameublement</option>
				<option value=4>Vêtement</option>
				<option value=5>Sport&Loisirs</option>
				</select>
			</div>
			<br>
			<div>
				<label>Photo de l'article </label><input type="button" value="UPLOADER">
			</div>
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
				
			<div>
				<label>Retrait</label>
				<div>
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
				<input type="submit" value="Annuler">
			</div>
		</form>
	</section>	
</body>
</html>