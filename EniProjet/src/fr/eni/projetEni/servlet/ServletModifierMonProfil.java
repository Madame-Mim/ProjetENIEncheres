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
import fr.eni.projetEni.dal.UtilisateurDal;

/**
 *  * @author edavi2020
 * Servlet implementation class ServletModifierMonProfil
 */
@WebServlet("/ServletModifierMonProfil")
public class ServletModifierMonProfil extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UtilisateurBll utilisateurCree = new UtilisateurBll();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//instanciation d'une nouvelle session pour récupérer l'ID de l'utilisateur
		HttpSession session = request.getSession();
		session.getAttribute("session");
		int id= Integer.parseInt(session.getAttribute("session").toString());
	
		try {
			//instanciation d'un nouvel utilisateur pour récuperer l'ID
			UtilisateurBo utilisateur = UtilisateurBll.get(id);
			//attribution de l'ID dans la requête
			request.setAttribute("utilisateur", utilisateur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Encheres/Utilisateur/modifierMonProfil.jsp" ).forward( request, response );
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//int credit = Integer.parseInt(session.getAttribute("credit").toString());
		
		//instanciation d'une nouvelle session pour récupérer l'ID de l'utilisateur
		HttpSession session = request.getSession();
		//attribution de l'ID dans la requête
		session.getAttribute("session");
		int id= Integer.parseInt(session.getAttribute("session").toString());
	
		try {
			//instanciation d'un nouvel utilisateur pour récuperer l'ID
			UtilisateurBo utilisateur = UtilisateurBll.get(id);
			//attribution de l'utilisateur dans la requête
			request.setAttribute("utilisateur", utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//récupération des valeurs des champs
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
		boolean admin = false;
		
		//instanciation d'un nouvel utilisateur
		UtilisateurBo utilisateur = new UtilisateurBo();
		
		//attribution des nouvelles valeurs à l'utilisateur
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setPassword(motdepasse);
		//utilisateur.setCredit(credit);
		utilisateur.setAdministrateur(admin);
		utilisateur.setId(id);


		//teste le mot de passe
		if (motdepasse.equals(confirmation)) {
				try {
					//modification de l'utilisateur dans la BDD
					utilisateurCree.update(utilisateur);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/afficherProfil.jsp").forward( request, response );
	
		}
	}
}

