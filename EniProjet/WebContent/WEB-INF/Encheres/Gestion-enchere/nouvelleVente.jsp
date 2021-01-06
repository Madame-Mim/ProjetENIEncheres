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
		<img src=https://www.monchatestroi.fr/wp-content/uploads/2015/11/Chaton-blanc-coeur-10.jpg>
	</div>
	<br>
	<div>
		<label>Article : </label><input type="text" name="nomArticle" required>
	</div>
	<br>
	<div>
		<label>Decsription : </label><input type="text" name="descritpionArticle" required>
	</div>
	<div>
		<label>Catégorie</label><input type="select" name="CatégorieArticle" required>
	</div>
	<div>
		<label>Photo de l'article</label><input type="submit" value="UPLOADER">
	</div>
	<div>
		<label>Mise à prix : </label><input type="text" required>
	</div>
	<div>
		<label>Début de l'enchère : </label><input type="date" name="debutEnchere" required>
	</div>
	<div>
		<label>Fin de l'enchère : </label><input type="date" name="finEnchere" required>
	</div>
	<div>
		<label>Rue : </label><input type="text" name="rueRetrait" required>
	</div>
	
	<div>
		<label>Retrait</label>
		<div>
		<label>Code postal : </label><input type="text" name="codePostalRetrait" required>
		<label>Ville: </label><input type="text" name="villeRetrait" required>
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