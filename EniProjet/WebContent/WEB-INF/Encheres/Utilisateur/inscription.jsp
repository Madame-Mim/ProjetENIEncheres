<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<c:url value="/CSS/inscription.css"/>" />
<title>ENI-Enchères</title>
</head>
<%@include file="/WEB-INF/Include/logo.html" %>
<body>

	<header>
	</header>
        
    <h1>Mon profil</h1>
        
        <div>
            <form method="post" action="<c:url value="ServletInscriptionUtilisateur"/>">
                <fieldset>
                    <legend>Créer mon profil</legend>
                    
                    <div class="droite">
                    <label for="pseudo">Pseudo :</label>
                    <input type="text" id="pseudo" name="pseudo" required="required" value="" size="20" maxlength="30"  />
                    </div><br />
                    
                    <div class="gauche">
                    <label for="nom">Nom :</label>
                    <input class="text-end" type="text" id="nom" name="nom" required="required" value="" size="20" maxlength="30" />
                    </div><br />
                    
                    <label for="prenom">Prénom :</label>
                    <input type="text" id="prenom" name="prenom" required="required"  value="" size="20" maxlength="30" />
                    <br />
                    
                    <label for="email">Email :</label>
                    <input type="email" id="email" name="email" required="required" value="" size="20" maxlength="60" />
                    <br />
                    
                    <label for="telephone">Numéro de téléphone :</label>
                    <input type="text" id="telephone" name="telephone" required="required" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="rue">Rue :</label>
                    <input type="text" id="rue" name="rue" required="required" value="" size="20" maxlength="120" />
                    <br />
                    
                    <label for="codePostal">Code postale :</label>
                    <input type="text" id="codePostal" name="codePostal" required="required" value="" size="20" maxlength="20" />
                    <br />
                    
                                        
                    <label for="ville">Ville :</label>
                    <input type="text" id="ville" name="ville" required="required" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="motdepasse">Mot de passe :</label>
                	<input type="password" id="motdepasse" name="motdepasse" required="required" value="" size="20" maxlength="20" />
                	<br />

                	<label for="confirmation">Confirmation du mot de passe :</label>
                	<input type="password" id="confirmation" name="confirmation" required="required" value="" size="20" maxlength="20" />
                	<br />
                    
                </fieldset>
                <input class="btn btn-success" type="submit" value="Valider">
                <!--    <input type="submit" value="Valider"  />  -->
                <a class="btn btn-success" href="ServletAccueil" role="button">Annuler</a>
                <!-- <a href="ServletAccueil" class="button">Annuler</a>  -->
                <br /> 
            </form>
        </div>

