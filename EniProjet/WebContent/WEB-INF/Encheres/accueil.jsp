<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/accueil.css">
<title>ENI-Enchères</title>
</head>
<body>
<header>
		<%@ include file="/WEB-INF/Include/header.html" %>
		<%@include file="/WEB-INF/Include/logo.html" %>
</header>
<c:set var="vente" value="vente" />
<c:set var = "test1" value ="${filtreNom}"/>
<c:set var = "test2" value ="${categorieselection}" />
<c:set var ="vendeur" value ="${vendeur}" />
<c:set var ="choixAV" value ="${choixAV}" />
<c:set	var ="choixA1" value ="${choixA1}" />
<c:set	var ="choixA2" value ="${choixA2}" />
<c:set	var ="choixA3" value ="${choixA3}" />
<c:set	var ="choixV1" value ="${choixV1}" />
<c:set	var ="choixV2" value ="${choixV2}" />
<c:set	var ="choixV3" value ="${choixV3}" />
<c:if test="${empty choixA1 && empty choixA2 && empty choixA3}"> 
<c:if test="${empty choixV1 && empty choixV2 && empty choixV3}">
<c:set var ="choixA1" value ="A1" />	
</c:if>
</c:if>


<section class="row col-xs-offset-1 col-xs-10 col-xs-offset-1">
<header>
<h2>Liste des enchères</h2>	
</header>
</section>
<br/>
<br/>

<section class="col-xs-12">
	<div class=" col-xs-8">

		<form method="post" name="form1" action="<%=request.getContextPath()%>/Accueil">
           <div class="row col-xs-offset-1 col-xs-10 col-xs-offset-1">         
			<label for="recherchearticle">Filtres : </label>
			<br/>
			<c:set var = "recherchearticle" value ="${namerecherche}" />
			<input type="text" name="recherche" 
			placeholder="${recherchearticle}" size= "30"/>
			<br/>
			<br/>
			<div id=categorie>
			<c:set var = "test2" value ="${categorieselection}" />
			<label for="categorie">Catégorie : </label>
			<select name="categorie" size="1" >
			<option value="" selected="selected">Toutes</option>
				<c:forEach items="${listeCategorie}" var="categ" >
					<option value="${categ.noCategorie}" ${categ.noCategorie == test2 ? 'selected="selected"' : ''} >${categ.libelle}</option>     
				</c:forEach>
			</select>
			</div>
	      </div>
	      <br/>
	      <br/>
	      <br/>	
	  
		  <div class="col-xs-12"> 
				<div class="row col-xs-6">
			<input checked type="radio" id="achat" name="choixAV" value="achat" onclick="grisercheckbox();">
			<label for="achat">Achats</label>
			<br>				
			<input <c:if test="${!empty choixA1}">checked</c:if>  type="checkbox" id="choixA1" name="choixA1" value="enchereouverte"   > 
			<label for="choixA1">enchères ouverte</label><br>
			<input <c:if test="${!empty choixA2}">checked</c:if>  type="checkbox" id="choixA2" name="choixA2" value="enchereencours"  > 
			<label for="choixA2">mes enchères en cours</label><br>
			<input <c:if test="${!empty choixA3}">checked</c:if> type="checkbox" id="choixA3" name="choixA3" value="enchereremporte"  >
			<label for="choixA3">mes enchères remportés</label><br>
			    </div>
				<div class="row col-xs-6">
			<input <c:if test="${choixAV eq vente}">checked</c:if> type="radio" id="vente" name="choixAV" value="vente" onclick="grisercheckbox();">
			<label for="vente">Mes ventes</label>
			<br>
			<input <c:if test="${!empty choixV1}">checked</c:if> type="checkbox" id="choixV1" name="choixV1" value="venteouverte" disabled="disabled">
			<label for="choixV1">mes ventes en cours</label><br>
			<input <c:if test="${!empty choixV2}">checked</c:if> type="checkbox" id="choixV2" name="choixV2" value="ventenondebute" disabled="disabled">
			<label for="choixV2">ventes non debutées</label><br>
			<input <c:if test="${!empty choixV3}">checked</c:if> type="checkbox" id="choixV3" name="choixV3" value="ventetermine" disabled="disabled">
			<label for="choixV3">ventes terminées</label><br></div>
           </div>
		</div>
	<div class="col-xs-3 col-xs-offset-1" id="rechercher">
		<input class="btn btn-success" type="submit" value="RECHERCHER">
	</div>
		</form>
</section>		
<section class="col-xs-12 ">
<br/>
<br/>

<br/>
<br/>
</section>
<section class="col-xs-12 ">


	
	<c:forEach var="v" items="${listeArticles}">
				
			<jsp:useBean id="now" class="java.util.Date" />
			<c:set var="currentDate" value="${v.dateFinEncheres}" />
			<fmt:parseDate value="${currentDate}" var="finEnchere" pattern="yyyy-MM-dd" />
			<c:set var="currentDate" value="${v.dateDebutEncheres}" />
			<fmt:parseDate value="${currentDate}" var="debutEnchere" pattern="yyyy-MM-dd" />
			<c:set var = "theString" value ="${v.nomArticle}" />
			<c:set var = "catégorie" value ="${v.categorie.noCategorie}" />
			<c:set var = "vendeurtest" value ="${v.utilisateur.id}" />

		<c:if test="${(empty test1 || fn:containsIgnoreCase(theString,filtreNom))&&(empty test2 || (catégorie eq test2))}">	
    	
    	
    		<c:if test="${!empty choixA1}">
    		<c:if test="${now.time lt finEnchere.time && now.time gt debutEnchere.time}">
			<div class="col-xs-4" id="cartes">
			<div class="entetebloc">Enchère en cours</div>
			<div class="col-xs-4 ">
					<img  src="<%=request.getContextPath()%>/Image/destroyed.jpg" alt="${v.nomArticle}" class="photo">
				</div>
			<div class="col-xs-offset-1 col-xs-7">
						
					<div class=labelproduit>
					<p><a href="<%=request.getContextPath()%>/VenteEnCours?idarticle=${v.noArticle}">${v.nomArticle}</a></p></div>
					<p>${v.prixVente}</p>
					<p>${v.dateFinEncheres}</p>
					<p><a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${v.utilisateur.pseudo}">${v.utilisateur.pseudo}</a></p></div>
			</div> 
			</c:if>
			</c:if>
		
			<c:if test="${!empty choixV1}">
			<c:if test="${vendeurtest eq vendeur}">
			<c:if test="${now.time lt finEnchere.time && now.time gt debutEnchere.time}">
			<div class="col-xs-4" id="cartes">
			<div class="entetebloc">Ventes en cours</div>
				<br/>
				<div class="col-xs-4 ">
					<img  src="<%=request.getContextPath()%>/Image/destroyed.jpg" alt="${v.nomArticle}" class="photo">
				</div>
				<div class="col-xs-offset-1 col-xs-7">
						
					<div class=labelproduit>
					<p><a href="<%=request.getContextPath()%>/VenteEnCours?idarticle=${v.noArticle}">${v.nomArticle}</a></p></div>
					<p>${v.prixVente}</p>
					<p>${v.dateFinEncheres}</p>
					<p><a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${v.utilisateur.pseudo}">${v.utilisateur.pseudo}</a></p></div>
			</div> 
			</c:if>
			</c:if>
			</c:if>
			
		
		<c:if test="${vendeurtest eq vendeur}">
			<c:if test="${!empty choixV2}">
			<c:if test="${now.time lt debutEnchere.time}">
			<div class="col-xs-4" id="cartes">
			<div class="entetebloc">Ventes non débutées</div>
			<br/>
				<div class="col-xs-4 ">
					<img  src="<%=request.getContextPath()%>/Image/destroyed.jpg" alt="${v.nomArticle}" class="photo">
				</div>
				<div class="col-xs-offset-1 col-xs-7">
								
					<div class=labelproduit>
					<p><a href="<%=request.getContextPath()%>/VenteFuture?idarticle=${v.noArticle}">${v.nomArticle}</a></p></div>
					<p>${v.prixVente}</p>
					<p>${v.dateFinEncheres}</p>
					<p><a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${v.utilisateur.pseudo}">${v.utilisateur.pseudo}</a></p></div>
			</div> 
			</c:if>
			</c:if>
			
			<c:if test="${!empty choixV3}">
			<c:if test="${now.time gt finEnchere.time}">
			<div class="col-xs-4" id="cartes">
			<div class="entetebloc">Ventes terminées</div>
			<br/>
				<div class="col-xs-4 ">
					<img  src="<%=request.getContextPath()%>/Image/destroyed.jpg" alt="${v.nomArticle}" class="photo">
				</div>
				<div class="col-xs-offset-1 col-xs-7">
						
			
					<div class=labelproduit>
					<p><a href="<%=request.getContextPath()%>/VenteTerminee?idarticle=${v.noArticle.noArticle}">${v.nomArticle}</a></p></div>
					<p>${v.prixVente}</p>
					<p>${v.dateFinEncheres}</p>
					<p><a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${v.utilisateur.pseudo}">${v.utilisateur.pseudo}</a></p></div>
			</div> 
			</c:if>
			</c:if>
		</c:if>
</c:if>	
</c:forEach>
</section>
<section class="col-xs-12 ">

	<c:forEach var="t" items="${malisteEncheresmax}">

		<jsp:useBean id="now1" class="java.util.Date" />
		<c:set var="currentDate" value="${t.noArticle.dateFinEncheres}" />
		<fmt:parseDate value="${currentDate}" var="finEnchere" pattern="yyyy-MM-dd" />
		<c:set var="currentDate" value="${t.noArticle.dateDebutEncheres}" />
		<fmt:parseDate value="${currentDate}" var="debutEnchere" pattern="yyyy-MM-dd" />
		<c:set var = "theString" value ="${t.noArticle.nomArticle}" />
		<c:set var = "catégorie" value ="${t.noArticle.categorie.noCategorie}" />
		<c:set var = "meilleurEnchere" value ="${t.noArticle.prixVente}" />
		<c:set var = "maMeilleurEnchere" value ="${t.montantEnchere}" />

	<c:if test="${(empty test1 || fn:containsIgnoreCase(theString,filtreNom))&&(empty test2 || (catégorie eq test2))}">	


		<c:if test="${!empty choixA2}">
		<c:if test="${now1.time lt finEnchere.time && now1.time gt debutEnchere.time}">
		<div class="col-xs-4" id="cartes">
		<div class="entetebloc">Mes encheres en cours</div>
		
		<br/>
			<div class="col-xs-4 ">
					<img  src="<%=request.getContextPath()%>/Image/destroyed.jpg" alt="${v.nomArticle}" class="photo">
				</div>
			<div class="col-xs-offset-1 col-xs-7">
						
			
			<div class=labelproduit>
			<p><a href="<%=request.getContextPath()%>/VenteEnCours?idarticle=${t.noArticle.noArticle}">${t.noArticle.nomArticle}</a></p></div>
			<p>${t.noArticle.prixVente}</p>
			<p>${t.noArticle.dateFinEncheres}</p>
			<p><a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${t.noArticle.utilisateur.pseudo}">${t.noArticle.utilisateur.pseudo}</a></p></div> 
			</div> 
		
		
		</c:if>
		</c:if>	
		
		<c:if test="${!empty choixA3}">
		<c:if test="${now1.time gt finEnchere.time}">
		<c:if test="${meilleurEnchere eq maMeilleurEnchere}">
		<div class="col-xs-4" id="cartes">
		<div class="entetebloc">Mes enchères remportées</div>
			<br/>
			<div class="col-xs-4 ">
					<img  src="<%=request.getContextPath()%>/Image/destroyed.jpg" alt="${v.nomArticle}" class="photo">
				</div>
				<div class="col-xs-offset-1 col-xs-7">
						
			
			<div class=labelproduit>
			<p><a href="<%=request.getContextPath()%>/ServletEnchereRemportee?idarticle=${t.noArticle}">${t.noArticle.nomArticle}</a></p></div>
			<p>${t.noArticle.prixVente}</p>
			<p>${t.noArticle.dateFinEncheres}</p>
			<p><a href="<%=request.getContextPath()%>/ServletAfficherProfil?pseudo2=${t.noArticle.utilisateur.pseudo}">${t.noArticle.utilisateur.pseudo}</a></p></div> 	
		</div>
		</c:if>
		</c:if>	
		</c:if>
</c:if>
</c:forEach>
</section>					


</body>
<script type="text/javascript">  
function grisercheckbox(){
    	
    
        if(document.getElementById("achat").checked){
 
            document.getElementById('choixV1').disabled = 'disabled';
            document.getElementById('choixV2').disabled = 'disabled';
            document.getElementById('choixV3').disabled = 'disabled';
            document.getElementById('choixA1').disabled ='';
            document.getElementById('choixA2').disabled = '';
            document.getElementById('choixA3').disabled = '';
            document.getElementById('choixV1').checked = false;
            document.getElementById('choixV2').checked = false;
            document.getElementById('choixV3').checked = false;
            document.getElementById('choixA1').checked = true;
        }
 
        else if(document.getElementById("vente").checked){
 
        	 document.getElementById('choixV1').disabled ='' ;
             document.getElementById('choixV2').disabled ='';
             document.getElementById('choixV3').disabled ='';   
             document.getElementById('choixA1').disabled = 'disabled';
             document.getElementById('choixA2').disabled = 'disabled';
             document.getElementById('choixA3').disabled = 'disabled';
             document.getElementById('choixA1').checked = false;
             document.getElementById('choixA2').checked = false;
             document.getElementById('choixA3').checked = false;
             document.getElementById('choixV1').checked = true;
 
        }}
        </script>
</html>