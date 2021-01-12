package fr.eni.projetEni.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletReinitialisationMotDePasse
 */
@WebServlet("/ServletReinitialisationMotDePasse")
public class ServletReinitialisationMotDePasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletReinitialisationMotDePasse - doGet");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/ReinitialisationMotDePasse.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletReinitialisationMotDePasse - doPost");
		
		String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
		System.out.println("nouveauMotDePasse :" + nouveauMotDePasse);
		String confirmationMotDePasse = request.getParameter("confirmationMotDePasse");
		System.out.println("confirmationMotDePasse :" + confirmationMotDePasse);
		
		if(nouveauMotDePasse != confirmationMotDePasse)
		{
			System.out.println("Veuillez entrer deux mot de passe identiques");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/ReinitialisationMotDePasse.jsp");
			rd.forward(request, response); 
		}else
		{
			System.out.println("identique ok");
			RequestDispatcher rd1 = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/connexion.jsp");
			rd1.forward(request, response); 
		}
		 
		
	}

}
