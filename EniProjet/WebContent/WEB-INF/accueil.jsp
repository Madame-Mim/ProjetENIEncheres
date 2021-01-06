<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI-Encheres</title>
<a href="#">S'inscrire-Se connecter</a>
</head>
<body>
<h1> Liste des enchères</h1>
<p>
<label for="recherchearticle">Filtres</label>
<input type="text" name="recherchearticle" id="recherchearticle"
placeholder="le nom de l'article contient" size= "40"/>

<label for="categorie">Catégorie : </label>
<select name="categorie" size="1">
<option value="all" selected="selected">Toutes</option>
<option value="Informatique">Informatique</option>
<option value="Ameublement">Ameublement</option>
<option value="Vetement">Vêtement</option>
<option value="SportLoisirs">Sport&Loisirs</option>
</select>
</p>


</body>
</html>