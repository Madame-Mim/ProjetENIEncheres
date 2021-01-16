package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.ArticleVenduBll;
import fr.eni.projetEni.bll.EnchereBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.EnchereBo;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * @author edavi2020
 * Servlet implementation class ServletSupprimerMonProfil
 */
@WebServlet("/ServletSupprimerMonProfil")
public class ServletSupprimerMonProfil extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UtilisateurBll utilisateurCree = new UtilisateurBll();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(); //récupération de la session
		session.getAttribute("session");
		int id= Integer.parseInt(session.getAttribute("session").toString()); // récupération de l'id de l'utilisateur connecté (passage au format int de l'objet passé en String)
	
		EnchereBll enchereAmodifié= new EnchereBll();
		UtilisateurBo utilisateurAmodifie;
		try
		{
			utilisateurAmodifie = UtilisateurBll.get(id); //récupération de l'utilisateur connecté
			List<EnchereBo> listeEnchere = EnchereBll.getbyutilisateur(id); //récupération de toutes les enchères faites par l'utilisateur
			for (int i = 0; i < listeEnchere.size(); i++) // pour chacune d'entre elles
			{
				EnchereBo enchere = new EnchereBo(); //instanciation d'une nouvelle enchère
				enchere.setNoUtilisateur(utilisateurAmodifie); //instanciation de l'utilisateur ayant fait l'enchère a modifié
				enchereAmodifié.updateAll(enchere); //appel à la fonction updateAll (qui met toutes les enchères sur l'utilisateur 1 : "Pseudo supprimé")
			}
		
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		/* update des articles vers "pseudo supprimé" */

		ArticleVenduBll articleAmodifié= new ArticleVenduBll();
		UtilisateurBo utilisateurArticleAmodifie;
		
		try
		{
			utilisateurArticleAmodifie = UtilisateurBll.get(id);

			List<ArticleVenduBo> listeArticle = (List<ArticleVenduBo>) ArticleVenduBll.getByIdUtilisateur(id);

			for (int i = 0; i < listeArticle.size(); i++) 
			{
				ArticleVenduBo article = new ArticleVenduBo();
				article.setUtilisateur(utilisateurArticleAmodifie);
				articleAmodifié.updateAll(article);
			}
		
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}

		/* updSuppression de l'utilisateur connecté */
			
		UtilisateurBll utilisateurASupprimer = new UtilisateurBll();
		try 
		{
			UtilisateurBo utilisateur = UtilisateurBll.get(id);
			utilisateurASupprimer.delete(id);
			
			session.invalidate(); //destruction de la session
			
			response.sendRedirect( "http://localhost:8080/EniProjet/Accueil" );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
