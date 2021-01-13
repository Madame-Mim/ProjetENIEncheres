<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Panel administration</title>
</head>
<body class="container-fluide col-xs-12">
<%@include file="/WEB-INF/Include/header.html" %>

<section class="col-xs-offset-2 col-xs-4">
	<h3> Utilisateur :</h3>
	<br>
	<div class="clearfix"></div>
	<br>
	<form action="<%=request.getContextPath()%>/Administration" method="post">
		<select name="utilisateur">	
				<% request.getAttribute("utilisateurListe"); %>
				<c:forEach var="v" items="${utilisateurListe}">
			   		<option value="${v.id}">(${v.pseudo})   ${v.nom} ${v.prenom}</option>
				</c:forEach>
		</select>
		<br><br>
			<input name="desactiver" type="submit" value="désactiver le compte">
			<input name="supprimerUtilisateur" type="submit" value="supprimer le compte">
	</form>
</section>
<section class="col-xs-4">
	<h3> Catégorie :</h3>
	<br>
	<div class="clearfix"></div>
	<br>
		<form>
			
			<div>	
				<% request.getAttribute("categorieListe"); %>
					<c:forEach var="v" items="${categorieListe}">
						<form action="<%=request.getContextPath()%>/Categories" method="post">
			   				<input name="idcategorie" value="${v.noCategorie}" type="hidden">
			   				<input type="text" value="${v.libelle}"placeholder="${v.libelle}"><input name="Modifier" type="submit" value="modifier">	
			   				<input name="supprimerCategorie" type="submit" value="supprimer">				   				
			   			</form><br><br>
					</c:forEach>
					<br><br>
					<form action="<%=request.getContextPath()%>/Categories" method="post">
			   				<input type="text" placeholder="Libellé de la catégorie"> <input name="Ajouter" type="submit" value="ajouter une catégorie">			   				
			   			</form>
			</div>
		</form>
</section>
</body>
</html>