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
import fr.eni.projetEni.bll.RetraitBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.RetraitBo;

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
      // HttpSession session = request.getSession();
		/*  
        if (session.getAttribute("session") == null )
        {
           
            response.sendRedirect(request.getContextPath()+"/ServletAccueil");
        }
        else
        {
        	*/
		//int id = Integer.parseInt(request.getParameter("id"));

			try {
				ArticleVenduBo article = ArticleVenduBll.getById(7);
				request.setAttribute("article", article);
				System.out.println(article);
				System.out.println(article.getRetrait());
				System.out.println(article.getUtilisateur());
				System.out.println(article.getCategorie());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
        //le forward envoi l'affichage à la jsp
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/detail-Vente.jsp");
        rd.forward(request, response);
        }
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVenduBo> listeArticles;
		try {
			listeArticles = ArticleVenduBll.getAll();
			for(ArticleVenduBo article : listeArticles)
	        {
	            //"affichageListeArticle" doit apparaître dans la jsp pour afficher la liste comme ceci
	            //<p><%=request.getAttribute("affichageListeArticle") %> </p>
	            request.setAttribute("affichageListeArticle", listeArticles);
	            System.out.println(article);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //le forward envoi l'affichage à la jsp
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/detail-Vente.jsp");
        rd.forward(request, response);
		}

}
