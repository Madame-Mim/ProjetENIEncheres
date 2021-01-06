package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEni.bll.ArticleVenduBll;
import fr.eni.projetEni.bo.ArticleVenduBo;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 try {
			ArticleVenduBo articlevendu = ArticleVenduBll.getById(7);
		out.println(request.getParameter(articlevendu.getNomArticle()));
			
		ArticleVenduBo article = null;
		article = new ArticleVenduBo(articlevendu.getNomArticle(),article.getDescription(),article.getDateFinEncheres(), article.getPrixVente(), article.getMiseAPrix(),article.getUtilisateur(),article.getCategorie(),article.getRetrait());
		request.setAttribute("article", article);
		 } catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 try {
			ArticleVenduBo articlevendu = ArticleVenduBll.getById(7);
		out.println(articlevendu.getNomArticle());
			
		ArticleVenduBo article = null;
		article = new ArticleVenduBo(articlevendu.getNomArticle(),article.getDescription(),article.getDateFinEncheres(), article.getPrixVente(), article.getMiseAPrix(),article.getUtilisateur(),article.getCategorie(),article.getRetrait());
		request.setAttribute("article", article);
		 } catch (Exception e) {
				e.printStackTrace();
			}
		}

}
