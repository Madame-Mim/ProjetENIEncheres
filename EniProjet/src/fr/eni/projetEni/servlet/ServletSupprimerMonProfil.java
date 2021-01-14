package fr.eni.projetEni.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * Servlet implementation class ServletSupprimerMonProfil
 */
@WebServlet("/ServletSupprimerMonProfil")
public class ServletSupprimerMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurBll utilisateurCree = new UtilisateurBll();

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute("session");
		int id= Integer.parseInt(session.getAttribute("session").toString());
	
		try {
			UtilisateurBo utilisateur = UtilisateurBll.get(id);
			request.setAttribute("utilisateur", utilisateur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			utilisateurCree.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//session.invalidate();
		
        this.getServletContext().getRequestDispatcher( "/Accueil" ).forward( request, response );
	} 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
