<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
       <!--    <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />     url ?????? -->
<title>ENI-Enchères</title>
</head>
<%@include file="/WEB-INF/Include/Logo.html" %>
<body>

	<header>
	</header>
        
    <h1>Mon profil</h1>
        
        <div>
            <form method="post" action="<c:url value="ServletInscriptionUtilisateur"/>">
                <fieldset>
                    <legend>Créer mon profil</legend>
                    
                    <label for="pseudo">Pseudo : <span class="requis"></span></label>
                    <input type="text" id="pseudo" name="pseudo" required="required" value="<c:out value=""/>" size="30" maxlength="30"  />
                    <br />
                    
                    <label for="nom">Nom : <span class="requis"></span></label>
                    <input type="text" id="nom" name="nom" required="required" value="<c:out value=""/>" size="30" maxlength="30" />
                    <br />
                    
                    <label for="prenom">Prénom : <span class="requis"></span></label>
                    <input type="text" id="prenom" name="prenom" required="required" value="<c:out value=""/>" size="30" maxlength="30" />
                    <br />
                    
                    <label for="email">Email :</label>
                    <input type="email" id="email" name="email" required="required" value="<c:out value=""/>" size="20" maxlength="60" />
                    <br />
                    
                    <label for="telephone">Numéro de téléphone :<span class="requis"></span></label>
                    <input type="text" id="telephone" name="telephone" required="required" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="rue">Rue :<span class="requis"></span></label>
                    <input type="text" id="rue" name="rue" required="required" value="" size="20" maxlength="120" />
                    <br />
                    
                    <label for="codePostal">Code postale :<span class="requis"></span></label>
                    <input type="text" id="codePostal" name="codePostal" required="required" value="" size="20" maxlength="20" />
                    <br />
                    
                                        
                    <label for="ville">Ville :<span class="requis"></span></label>
                    <input type="text" id="ville" name="ville" required="required" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="motdepasse">Mot de passe :<span class="requis"></span></label>
                	<input type="password" id="motdepasse" name="motdepasse" required="required" value="" size="20" maxlength="20" />
                	<br />

                	<label for="confirmation">Confirmation du mot de passe <span class="requis"></span></label>
                	<input type="password" id="confirmation" name="confirmation" required="required" value="" size="20" maxlength="20" />
                	<br />
                    
                </fieldset>
                <input type="submit" value="Valider"  />
                <a href="ServletAccueil" class="button">Annuler</a>
                <br /> 
            </form>
        </div>

