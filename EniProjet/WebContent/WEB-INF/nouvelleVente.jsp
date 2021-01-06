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
	<img src=https://www.monchatestroi.fr/wp-content/uploads/2015/11/Chaton-blanc-coeur-10.jpg>
	<label>Article : </label><input type="text" name="nomArticle" required>
	<label>Decsription : </label><input type="text" name="descritpionArticle" required>
	<label>Catégorie</label><input type="select" name="CatégorieArticle" required>
	<label>Photo de l'article</label><input type="submit" value="UPLOADER">
	<label></label>
	<label></label>
	<label></label>
	<label></label>
	<label></label>
	<label></label>
	<label></label>
	<input type="submit" value="Enregister"
</form>
</body>
</html>