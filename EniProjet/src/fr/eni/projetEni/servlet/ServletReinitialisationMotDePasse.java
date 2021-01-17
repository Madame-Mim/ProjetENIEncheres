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
		int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur")); //récupération de l'id passé en url
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/ReinitialisationMotDePasse.jsp");
		rd.forward(request, response);
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
		String confirmationMotDePasse = request.getParameter("confirmationMotDePasse");
		int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
		
		
		List<UtilisateurBo> utilisateurs = null;
		try {
			utilisateurs = UtilisateurBll.get();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if(!(nouveauMotDePasse.equals(confirmationMotDePasse))) //si les mots de passe sont différents
		{
			request.setAttribute("erreur", "Les mots de passe ne correspondent pas"); //instanciation du message d'erreur pour affichage en jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/ReinitialisationMotDePasse.jsp"); //retour sur la page
			rd.forward(request, response); 
		}
		else //si les mots de passe correspondent
		{
			UtilisateurBo utilisateurMAJ;
			try {
				utilisateurMAJ = UtilisateurBll.get(idUtilisateur); //récupération de l'utilisateur
				
				for(UtilisateurBo utilisateur : utilisateurs ) //pour chacun
				if(utilisateur.getEmail().equals(utilisateurMAJ.getEmail()) & utilisateur.getId() == idUtilisateur) //si l'id et l'email correspondent à l'utilisateur
				{
					utilisateurMAJ.setPassword(nouveauMotDePasse); //instanciation du mot de passe
					UtilisateurBll.update(utilisateurMAJ); //update de l'utilisateur
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/connexion.jsp"); //retour sur la page de connexion
					rd.forward(request, response); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Sinon
			request.setAttribute("erreur", "Cet utilisateur n'existe pas, merci de recliquer sur le lien et de ne pas jouer avec l'url"); //message d'erreur
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/ReinitialisationMotDePasse.jsp"); //retour au formulaire
			rd.forward(request, response); 
		}
		
	}

}
