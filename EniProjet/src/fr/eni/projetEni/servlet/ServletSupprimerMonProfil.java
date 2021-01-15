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

		HttpSession session = request.getSession();
		session.getAttribute("session");
		int id= Integer.parseInt(session.getAttribute("session").toString());
	
		EnchereBll enchereAmodifié= new EnchereBll();
		UtilisateurBo utilisateurAmodifie;
		try
		{
			utilisateurAmodifie = UtilisateurBll.get(id);
			List<EnchereBo> listeEnchere = EnchereBll.getbyutilisateur(id);
			for (int i = 0; i < listeEnchere.size(); i++) 
			{
				EnchereBo enchere = new EnchereBo();
				enchere.setNoUtilisateur(utilisateurAmodifie);
				enchereAmodifié.updateAll(enchere);
			}
		
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		//maj des articles vers "utilisateur supprimé"

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

					
		UtilisateurBll utilisateurASupprimer = new UtilisateurBll();
		try 
		{
			UtilisateurBo utilisateur = UtilisateurBll.get(id);
			utilisateurASupprimer.delete(id);
			
			session.invalidate();
			
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
