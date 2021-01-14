<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
    <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form method="post" action="Upload" enctype="multipart/form-data">
        <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
        
        <input type="submit" /></br></br>
        
        <c:out value="${ description }"></c:out></br>
        
          <img src="http://localhost:8080/<%=request.getContextPath()%>/Image/<c:out value="${ nomFichier }"></c:out>"/>
          
       <!--Si fichier uploadé sur serveur :   <img src="http://localhost:8080/uploaded/<c:out value="${ nomFichier }"></c:out>"/>   -->
        
    </form>
    
</body>
</html>