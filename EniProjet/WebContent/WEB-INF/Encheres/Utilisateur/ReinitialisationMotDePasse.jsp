<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Réinitialisation du mot de passe</title>
</head>
<body>
	<h1>Réinitialisation du mot de passe</h1>
	<form method="post" action="/EniProjet/ServletReinitialisationMotDePasse">
		<label>Entrer nouveau mot de passe : </label><input type="text" name="nouveauMotDePasse" required>
		<br><br>
		<label>Confirmer nouveau mot de passe : </label><input type="text" name="confirmationMotDePasse" required>
		<br>
		<input type="submit" value="Valider">
	</form>

</body>
</html>