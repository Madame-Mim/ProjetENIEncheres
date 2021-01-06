package fr.eni.projetEni.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.dal.UtilisateurDal;

/**
 * Servlet implementation class ServletInscriptionUtilisateur
 */
@WebServlet("/ServletInscriptionUtilisateur")
public class ServletInscriptionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/inscription.jsp" ).forward( request, response );

	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtilisateurDal utilisateur = new UtilisateurDal();
		
		request.setAttribute("utilisateur", utilisateur);
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward( request, response );

		
	} 
}
