<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/connexion.css">
<title>Afficher profil</title>
</head>
<%@include file="/WEB-INF/Include/logo.html" %>
<body>



<ul>
<li>Pseudo:		    ${utilisateur.pseudo}</li>
<li>Nom :			${utilisateur.nom}</li>
<li>Prénom :		${utilisateur.prenom}</li>
<li>Email :			${utilisateur.email}</li>
<li>Telephone :		${utilisateur.telephone}</li>
<li>Rue :			${utilisateur.rue}</li>
<li>Code Postal :	${utilisateur.codePostal}</li>
<li>Ville :			${utilisateur.ville}</li>
</ul>

<br><br>
			<form action="<%=request.getContextPath()%>/ServletModifierMonProfil">
				<input class="submit" type="submit" value="Modifier">
			</form>


</body>
</html>