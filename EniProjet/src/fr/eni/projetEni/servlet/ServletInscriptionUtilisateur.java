package fr.eni.projetEni.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * @author edavi2020
 * Servlet implementation class ServletInscriptionUtilisateur
 */
@WebServlet("/ServletInscriptionUtilisateur")
public class ServletInscriptionUtilisateur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UtilisateurBll utilisateurCree = new UtilisateurBll();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/inscription.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//récupération des infos des champs
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motdepasse = request.getParameter("motdepasse");
		String confirmation = request.getParameter("confirmation");
		System.out.println(motdepasse+ ", " +confirmation);

		//instanciation d'un nouvel utilisateur
		UtilisateurBo utilisateur = new UtilisateurBo();
		
		//attribution des valeurs
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setPassword(motdepasse);

		//teste le mot de passe
		if (motdepasse.equals(confirmation)) 
		{
			//insertion de l'utilisateur dans la BDD
			try 
			{
				utilisateurCree.insert(utilisateur);
				utilisateur = UtilisateurBll.getPseudo(pseudo);

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			
			//instanciation d'une nouvelle session
			HttpSession session = request.getSession();
			
			//récupération de l'ID de l'utilisateur
			int id = utilisateur.getId();
			
			//attribution de l'ID dans la session
			session.setAttribute("session", id);

			//teste si l'utilisateur existe déjà
			if (id !=0)
			{
					this.getServletContext().getRequestDispatcher( "/Accueil" ).forward( request, response );
					
			} else {	
				
				//envoi d'un message d'erreur
				String message ="Echec de l'inscription. Réessayez.";
				request.setAttribute("erreur", message);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/inscription.jsp" ).forward( request, response );		
			}	
		}
	}		
}

		
	
	

