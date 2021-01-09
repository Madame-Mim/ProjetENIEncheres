package fr.eni.projetEni.servlet;

import java.io.IOException;

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
 * Servlet implementation class ServletVenteEmportee
 */
@WebServlet("/VenteTerminee")
public class ServletVenteEmportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("idarticle"));

			ArticleVenduBo article = ArticleVenduBll.getById(id);
			request.setAttribute("article", article);
			
			EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
			request.setAttribute("enchere", enchere);

		} catch (Exception e) {
			e.printStackTrace();
		}
        
    //le forward envoi l'affichage à la jsp
    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/Vente-emportee.jsp");
    rd.forward(request, response);
    }
//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(session.getAttribute("session").toString());
		try {
			UtilisateurBll utilisateurAmodifie = new UtilisateurBll();
			UtilisateurBo utilisateurACrediter = UtilisateurBll.get(id);
			int montant = Integer.parseInt(request.getParameter("credit"));
			int creditActuel = utilisateurACrediter.getCredit();
			int nouveauCredit = creditActuel+montant;
			utilisateurACrediter.setCredit(nouveauCredit);
			
			utilisateurAmodifie.update(utilisateurACrediter);
			
			int idArticle = Integer.parseInt(request.getParameter("idarticle"));

			ArticleVenduBll articleAModifie = new ArticleVenduBll();
			ArticleVenduBo articleRetire = ArticleVenduBll.getById(idArticle);
			articleRetire.setRetraitEffectue(true);
			System.out.println(articleRetire);
			articleAModifie.updateArticle(articleRetire);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
	//	UtilisateurBll.update(utilisateurACrediter);
	}

}
