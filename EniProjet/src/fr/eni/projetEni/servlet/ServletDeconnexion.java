package fr.eni.projetEni.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String URL_REDIRECTION = "http://localhost:8080/EniProjet/ServletAccueil";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
    	request.getSession().invalidate();        
    	
        /* Redirection vers la page d'accueil */
        response.sendRedirect( URL_REDIRECTION );
    }
}
