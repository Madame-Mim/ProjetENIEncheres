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

		UtilisateurBo utilisateur = new UtilisateurBo();
		
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setPassword(motdepasse);

		
		if (motdepasse.equals(confirmation)) 
		{
				try 
				{
					utilisateurCree.insert(utilisateur);
					utilisateur = UtilisateurBll.getPseudo(pseudo);
					

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				HttpSession session = request.getSession();
				
				int id = utilisateur.getId();
				session.setAttribute("session", id);

				System.out.println(id);
				System.out.println(session.getAttribute("session"));
				if (id !=0)
				{

					this.getServletContext().getRequestDispatcher( "/Accueil" ).forward( request, response );
				}
		else 
		{	
			String message ="Echec de l'inscription. Réessayez.";
			request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/inscription.jsp" ).forward( request, response );		
		}	
		}
	}		
}

		
	
	

