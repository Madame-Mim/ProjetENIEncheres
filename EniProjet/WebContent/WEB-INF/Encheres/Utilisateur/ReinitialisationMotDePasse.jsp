<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/nouvelleVente.css">
<%@include file="/WEB-INF/Include/logo.html" %>
<title>Réinitialisation du mot de passe</title>
</head>
<body>
	<div class="col-xs-offset-3">
		<h1>Réinitialisation du mot de passe</h1>
	</div>
	<form method="post" action="/EniProjet/ServletReinitialisationMotDePasse">
		<div class="clearfix"></div>
		<br>
		<div class="col-xs-offset-3">
			<label>Entrer nouveau mot de passe : </label><input type="password" name="nouveauMotDePasse" required>
			<br><br>
			<label>Confirmer nouveau mot de passe : </label><input type="password" name="confirmationMotDePasse" required>
			<br>
			<input type="hidden" name="idUtilisateur" value="<%= request.getParameter("idUtilisateur") %>"> 
			<div class="clearfix"></div>
			<br>
			<input class="btn btn-success" type="submit" value="Valider">
		</div>
	</form>

</body>
</html>