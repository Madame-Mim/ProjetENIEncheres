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
import fr.eni.projetEni.bll.CategorieBll;
import fr.eni.projetEni.bll.EnchereBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.EnchereBo;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * Servlet implementation class ServletAdministration
 */
@WebServlet("/Administration")
public class ServletAdministration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();

		List<UtilisateurBo> listeUtilisateur;
		try {
			listeUtilisateur = UtilisateurBll.get();
			request.setAttribute("utilisateurListe", listeUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<CategorieBo> listeCategorie = CategorieBll.getallM1();
		request.setAttribute("categorieListe", listeCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Administration/admin.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idUtilisateur = Integer.parseInt(request.getParameter("utilisateur"));
		String supprimerUser = request.getParameter("supprimerUtilisateur");
		String desactiverUser = request.getParameter("desactiver");
		String supprimerCategorie = request.getParameter("supprimerCategorie");

		String modifier = request.getParameter("Modifier");
		
		if(idUtilisateur!=0)
		{
			if(supprimerUser!=null)
			{
				//maj des encheres vers "utilisateur supprimé"
				
				EnchereBll enchereAmodifié= new EnchereBll();
				UtilisateurBo utilisateurAmodifie;
				try
				{
					utilisateurAmodifie = UtilisateurBll.get(idUtilisateur);
					List<EnchereBo> listeEnchere = EnchereBll.getbyutilisateur(idUtilisateur);
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
					utilisateurArticleAmodifie = UtilisateurBll.get(idUtilisateur);

					List<ArticleVenduBo> listeArticle = (List<ArticleVenduBo>) ArticleVenduBll.getByIdUtilisateur(idUtilisateur);
					System.out.println(listeArticle);

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
					UtilisateurBo utilisateur = UtilisateurBll.get(idUtilisateur);
					utilisateurASupprimer.delete(idUtilisateur);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Administration/admin.jsp");
					rd.forward(request, response);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else if(desactiverUser!=null)
			{
				
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Administration/admin.jsp");
				rd.forward(request, response);
			}
		}
	}

}
