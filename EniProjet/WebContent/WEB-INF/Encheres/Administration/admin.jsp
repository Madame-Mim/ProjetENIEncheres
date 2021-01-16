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
	<header>
		<%@include file="/WEB-INF/Include/header.html" %>
	</header>
<div class="clearfix"></div>
<br><br>
	<section class="row col-xs-offset-2 col-xs-4">
		<form action="<%=request.getContextPath()%>/Administration" method="post">
			<fieldset>
				<legend> Gestion des utilisateurs</legend>
					<select name="utilisateur">	
							<% request.getAttribute("utilisateurListe"); %>
							<c:forEach var="v" items="${utilisateurListe}">
						   		<option value="${v.id}">(${v.pseudo})   ${v.nom} ${v.prenom}</option>
							</c:forEach>
					</select>
			
					<input class="btn btn-success" name="desactiver" type="submit" value="désactiver le compte" disabled>
					<input class="btn btn-success" name="supprimerUtilisateur" type="submit" value="supprimer le compte">
			</fieldset>
		</form>	
	</section>
	<section class="col-xs-offset-1 col-xs-4">
			<form>
				<fieldset>
			<legend>Gestion des catégories</legend>
				<div>	
					<% request.getAttribute("categorieListe"); %>
						<c:forEach var="v" items="${categorieListe}">
							<form action="<%=request.getContextPath()%>/Categories" method="post">
				   				<input name="idcategorie" value="${v.noCategorie}" type="hidden">
				   				<input type="text" value="${v.libelle}" placeholder="${v.libelle}"> <input class="btn btn-success" name="Modifier" type="submit" value="modifier" disabled>	
				   				<input class="btn btn-success" name="supprimerCategorie" type="submit" value="supprimer" disabled>				   				
				   			</form><br><br>
						</c:forEach>
						<br><br>
						<form action="<%=request.getContextPath()%>/Categories" method="post">
				   				<input type="text" placeholder="Libellé de la catégorie"> <input class="btn btn-success" name="Ajouter" type="submit" value="ajouter une catégorie" disabled>	
				   	</fieldset>		   				
				   		</form>
				</div>
			</form>
	</section>
</body>
</html>