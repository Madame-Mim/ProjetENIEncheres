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
	<div></div><img src=https://www.monchatestroi.fr/wp-content/uploads/2015/11/Chaton-blanc-coeur-10.jpg>
	<br><br>
	<label>Article : </label><input type="text" name="nomArticle" required>
	<br><br>
	<label>Decsription : </label><input type="text" name="descritpionArticle" required>
	<br><br>
	<label>Catégorie</label><input type="select" name="CatégorieArticle" required>
	<br><br>
	<label>Photo de l'article</label><input type="submit" value="UPLOADER">
	<br><br>
	<label>Mise à prix : </label><input type="text" required>
	<br><br>
	<label>Début de l'enchère : </label><input type="date" name="debutEnchere" required>
	<br><br>
	<label>Fin de l'enchère : </label><input type="date" name="finEnchere" required>
	<br><br>
	<label>Retrait</label>
	<br><br>
	<label>Rue : </label><input type="text" name="rueRetrait" required>
	<br><br>
	<label>Code postal : </label><input type="text" name="codePostalRetrait" required>
	<br><br>
	<label>Ville: </label><input type="text" name="villeRetrait" required>
	<br><br>
	<input type="submit" value="Enregister">
	<input type="submit" value="Annuler">
</form>
</body>
</html>