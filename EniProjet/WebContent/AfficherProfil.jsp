<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères</title>
</head>
<body>

	<h1>Mon profil</h1>
	
        <div>
            <form method="post" action="<c:url value="/Inscription"/>">
                <fieldset>
                    <legend>Mon profil</legend>
                    
                    <label for="pseudo">Pseudo : <span class="requis"></span></label>
                    <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="30" maxlength="30"  />
                    <span class="erreur">${form.erreurs['pseudo']}</span>
                    <br />
                    
                    <label for="nom">Nom : <span class="requis"></span></label>
                    <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['nom']}</span>
                    <br />
                    
                    <label for="prenom">Prénom : <span class="requis"></span></label>
                    <input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['prenom']}</span>
                    <br />
                    
                    <label for="email">Email :</label>
                    <input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                    <span class="erreur">${form.erreurs['email']}</span>
                    <br />
                    
                    <label for="telephone">Numéro de téléphone :<span class="requis"></span></label>
                    <input type="text" id="telephone" name="telephone" value="" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['telephone']}</span>
                    <br />
                    
                    <label for="rue">Rue :<span class="requis"></span></label>
                    <input type="text" id="rue" name="rue" value="" size="20" maxlength="120" />
                    <span class="erreur">${form.erreurs['rue']}</span>
                    <br />
                    
                    <label for="codePostal">Code postale :<span class="requis"></span></label>
                    <input type="text" id="codePostal" name="codePostal" value="" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['codePostal']}</span>
                    <br />
                    
                                        
                    <label for="ville">Ville :<span class="requis"></span></label>
                    <input type="text" id="ville" name="ville" value="" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['ville']}</span>
                    <br />
                    
                    <label for="ville">Ville :<span class="requis"></span></label>
                    <input type="text" id="ville" name="ville" value="" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['ville']}</span>
                    <br />
                                        
                    <label for="motdepasseactuel">Mot de passe actuel :<span class="requis"></span></label>
                	<input type="password" id="motdepasseactuel" name="motdepasseactuel" value="" size="20" maxlength="20" />
                	<span class="erreur">${form.erreurs['motdepasseactuel']}</span>
                	<br />
                    
                    <label for="motdepasse">Mot de passe :<span class="requis"></span></label>
                	<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                	<span class="erreur">${form.erreurs['motdepasse']}</span>
                	<br />

                	<label for="confirmation">Confirmation du mot de passe <span class="requis"></span></label>
                	<input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                	<span class="erreur">${form.erreurs['confirmation']}</span>
                	<br />
                	
                	<label for="ville">Ville :<span class="requis"></span></label>
                    <input type="text" id="ville" name="ville" value="" size="20" maxlength="20" />
                    
                </fieldset>
                <p class="info">${ form.resultat }</p>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>


</body>
</html>