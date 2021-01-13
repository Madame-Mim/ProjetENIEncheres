package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;

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
		
		int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
		System.out.println("idUtilisateur sur servletReinitialisatioMDP : " + idUtilisateur);
		
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
		int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
		System.out.println("idUtilisateur sur servletReinitialisatioMDP : " + idUtilisateur);
		
		
		List<UtilisateurBo> utilisateurs = null;
		try {
			utilisateurs = UtilisateurBll.get();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if(!(nouveauMotDePasse.equals(confirmationMotDePasse)))
		{
			System.out.println("Veuillez entrer deux mot de passe identiques");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/ReinitialisationMotDePasse.jsp");
			rd.forward(request, response); 
		}else
		{
			UtilisateurBo utilisateurMAJ;
			try {
				utilisateurMAJ = UtilisateurBll.get(idUtilisateur); 
				System.out.println("utilisateurMAJ :" + utilisateurMAJ);
				
				for(UtilisateurBo utilisateur : utilisateurs )
				if(utilisateur.getEmail().equals(utilisateurMAJ.getEmail()) & utilisateur.getId() == idUtilisateur)
				{
					utilisateurMAJ.setPassword(nouveauMotDePasse);
					UtilisateurBll.update(utilisateurMAJ);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("identique ok");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/connexion.jsp");
			rd.forward(request, response); 
		}
		
	}

}
