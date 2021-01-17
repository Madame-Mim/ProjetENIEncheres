package fr.eni.projetEni.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/ServletAfficherProfil")
public class ServletAfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	if(request.getParameter("pseudo2").trim().equals("")) //si le paramètre en url n'a pas de valeur associé
		{
		HttpSession session = request.getSession(); // récupèration de la session
		int id1= Integer.parseInt(session.getAttribute("session").toString()); //recupération de l'attribut de session "session"
		try {
			UtilisateurBo utilisateur = UtilisateurBll.get(id1); //récupération de l'utilisateur correspondant à l'id passé en session
			request.setAttribute("utilisateur", utilisateur); //instanciation de l'utilisateur pour affichage jsp
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		}
	else //si le parmaetre pseudo a une valeur

	{
		try {
			UtilisateurBo utilisateur = UtilisateurBll.get(UtilisateurBll.getPseudo(request.getParameter("pseudo2")).getId()); // récupération de l'id correspondant à ce pseudo
			request.setAttribute("utilisateur", utilisateur); //instanciation de l'utilisateur pour affichage jsp
		} catch (Exception e) {
			e.printStackTrace();
		}}
		RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/afficherProfil.jsp");
        rd.forward(request, response);
		
		
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.getAttribute("session");
		
		
		int id= Integer.parseInt(session.getAttribute("session").toString());
	
		try {
			UtilisateurBo utilisateur = UtilisateurBll.get(id);
			request.setAttribute("utilisateur", utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/afficherProfil.jsp");
        rd.forward(request, response);
		
	}

}
