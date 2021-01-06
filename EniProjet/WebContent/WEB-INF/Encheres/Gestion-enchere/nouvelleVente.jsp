<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
<h1>Nouvelle vente</h1>

<form method="post" action="/EniProjet/ServletNouvelleVente">
	
	
	<div>
		<label>Article : </label><input type="text" name="nomArticle" required>
	</div>
	<br>
	<div>
		<label>Decsription : </label><input type="text" name="descritpionArticle" required>
	</div>
	<br>
	<div>
		<label>Catégorie </label><input type="select" name="CatégorieArticle" required>
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
</body>
</html>