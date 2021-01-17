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
 * Servlet implementation class ServletMotDePasseOublie
 */
@WebServlet("/ServletMotDePasseOublie")
public class ServletMotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/motDePasseOublie.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String adresseMail = request.getParameter("adresseMail");
		try {
			List<UtilisateurBo> listeUtilisateurs = UtilisateurBll.get(); //Récupération de la liste des utilisateurs
			
			for(UtilisateurBo utilisateur : listeUtilisateurs) // Pour chacun d'entre eux
			{
				if(utilisateur.getEmail().toLowerCase().equals(adresseMail.toLowerCase())) //Si une adresse mail correspond
				{
					int idUtilisateur = utilisateur.getId(); // récupération de l'id correspondant
					request.setAttribute("idUtilisateur", idUtilisateur); //initialisation de cet id
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/lienReinitialiserMDP.jsp"); //j'amène sur le lien de réinitialisation
					rd.forward(request, response);
				}
			}
			//Si aucun résultat n'a été retourné au sein de la liste :
			request.setAttribute("erreur", "Cette adresse courriel est inconnue"); //initialisation d'un message d'erreur pour affichage en jsp

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/motDePasseOublie.jsp"); // retour sur le mail
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}//fin de doPost

}//fin de la Servlet
