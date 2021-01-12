package fr.eni.projetEni.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEni.bll.ArticleVenduBll;
import fr.eni.projetEni.bll.EnchereBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.EnchereBo;

/**
 * Servlet implementation class ServletEnchereRemportee
 */
@WebServlet("/ServletEnchereRemportee")
public class ServletEnchereRemportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletEnchereRemportee - doGet");
		
		try {
			int id = Integer.parseInt(request.getParameter("idarticle"));

			ArticleVenduBo article = ArticleVenduBll.getById(id);
			request.setAttribute("article", article);
			
			EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
			request.setAttribute("enchere", enchere);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchereRemportee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletEnchereRemportee - do Post");
	}

}
