<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<c:url value="/CSS/inscription.css"/>" /><title>ENI-Enchères</title>
</head>
<%@include file="/WEB-INF/Include/logo.html" %>
<body>

<header>
<%@ include file="/WEB-INF/Include/header.html" %>	
</header>

	<h1>Mon profil</h1>
	
        <div>
            <form method="post" action="<c:url value="/ServletModifierMonProfil"/>">
                <fieldset>
                    <legend>Modifier mon profil</legend>
                    
                    <div class="container-fluid">
		                    <div class="row">
			                    <div class="col-2">
			                    </div>
			                    <div class="col-4">
			                    
				                    <label for="pseudo">Pseudo : <span class="requis"></span></label>
				                    <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="30" maxlength="30"  />
				                    <br />
				                    
				                    <label for="prenom">Prénom : <span class="requis"></span></label>
				                    <input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom}"/>" size="30" maxlength="30" />
				                    <br />
				                    
				                    <label for="telephone">Numéro de téléphone :<span class="requis"></span></label>
				                    <input type="text" id="telephone" name="telephone" value="<c:out value="${utilisateur.telephone}"/>" size="20" maxlength="20" />
				                    <br />
				                    
				                    <label for="codePostal">Code postal :<span class="requis"></span></label>
				                    <input type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateur.codePostal}"/>" size="20" maxlength="20" />
				                    <br />
				                    
				                    <label for="motdepasseactuel">Mot de passe actuel :<span class="requis"></span></label>
				                	<input type="password" id="motdepasseactuel" name="motdepasseactuel" value="<c:out value="${utilisateur.password}"/>" size="20" maxlength="20" />
				                	<br />
				                    
				                    <label for="motdepasse">Nouveau mot de passe :<span class="requis"></span></label>
				                	<input type="password" id="motdepasse" name="motdepasse" value="<c:out value="${utilisateur.password}"/>" size="20" maxlength="20" />
				                	<br />
		                		</div>
		                	
		                    
		                    
		                    
			                    <div class="col-4">
			                    
				                    <label for="nom">Nom : <span class="requis"></span></label>
				                    <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="30" maxlength="30" />
				                    <br />                    
				                                        
				                    <label for="email">Email :</label>
				                    <input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
				                    <br />
				                                                            
				                    <label for="rue">Rue :<span class="requis"></span></label>
				                    <input type="text" id="rue" name="rue" value="<c:out value="${utilisateur.rue}"/>" size="20" maxlength="120" />
				                    <br />
				                                                            
				                    <label for="ville">Ville :<span class="requis"></span></label>
				                    <input type="text" id="ville" name="ville" value="<c:out value="${utilisateur.ville}"/>" size="20" maxlength="20" />
				                    <br />
				                    <br />
				          
				                	<label for="confirmation">Confirmation du mot de passe <span class="requis"></span></label>
				                	<input type="password" id="confirmation" name="confirmation" value="<c:out value="${utilisateur.password}"/>" size="20" maxlength="20" />
				                	<br />
			                	
			                	</div>
			                	<div class="col-2">
			                	</div>
		                	</div>
                	</div>
                    
                </fieldset> 
                
                <div class="container">
	                <div class="row">
		                <div class="col">
		                </div>
		                <div class="col">
		                </br></br>
		                <input class="btn btn-success" type="submit" value="Enregistrer">
		                </div>
		                <div class="col">
		                </br></br>
		                <a class="btn btn-success" href="ServletSupprimerMonProfil" role="button">Annuler</a> 
		                </div>
	                </div>
                </div>
                
            </form>
        </div>
</body>
</html>