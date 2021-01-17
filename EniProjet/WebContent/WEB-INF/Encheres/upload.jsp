<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/venteterminee.css">

<title>Article à vendre</title>
</head>
<body class="container-fluid col-xs-12">
  	<header>
		<%@ include file="/WEB-INF/Include/logo.html" %>	
	</header>
	<section class="row col-xs-offset-3 col-xs-6 col-xs-offset-3">
	
			<div class="col-xs-offset-1 col-xs-4"> Article :</div><div class="col-xs-6">${article.nomArticle} </div>
			<br><br>
			<div class="col-xs-offset-1 col-xs-4"> Description : </div><div class="col-xs-6"> ${article.description}</div>
			<br>
			
			<div class="col-xs-offset-1 col-xs-4"> Catégorie :</div>
				<div class="col-xs-6">
					${article.categorie.getLibelle()}
				</div>
			<br>
			<div class="clearfix"></div>
			<br>
			
			<div class="clearfix"></div>
			<br>
			<div class="col-xs-offset-1 col-xs-4">Mise à prix : </div> <div class="col-xs-6">${article.miseAPrix}</div>
			<br><br>
			<fmt:parseDate  value="${article.dateDebutEncheres}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
			<fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" var="debutDateEnchere" />
			<div class="col-xs-offset-1 col-xs-4">Début de l'enchère : </div><div class="col-xs-7"> ${debutDateEnchere}</div>
			
			<fmt:parseDate  value="${article.dateFinEncheres}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
			<fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" var="finDateEnchere" />	
			<div class="col-xs-offset-1 col-xs-4">Fin de l'enchère : </div><div class="col-xs-6">${finDateEnchere}
			<c:if test="${not empty erreur2}">
				<span class="text-danger erreur"> ${erreur2} </span>
			</c:if></div>
			<br><br><br>
			
			<div class="col-xs-offset-1 col-xs-10">
			<fieldset>
		      	<legend>Retrait</legend>
				<span class="col-xs-6">Rue : </span><span class="col-xs-6">${article.retrait.getRue()}</span>
				<br><br>
				<span class="col-xs-6">Code postal : </span> <span class="col-xs-6">${article.retrait.getCodePostal()}</span>
				<br><br>
				<span class="col-xs-6">Ville : </span><span class="col-xs-6"> ${article.retrait.getVille()}</span>
			</fieldset>
			</div>
		<div class="clearfix"></div>
        <br>
        <br>
    	<form method="post" action="Upload?id=<c:out value="${article.noArticle}"/>" enctype="multipart/form-data">
            <div class="col-xs-offset-1 col-xs-4"> photo de l'article : </div>
            <div class="col-xs-3"><label class="btn btn-success" for="upload">uploader</label> <input name="fichier" id="upload" type="file"> </div>
             <input class="col-xs-3 btn btn-success" type="submit" /><br><br>
            
       </form>

        </section>        
    
</body>
</html>