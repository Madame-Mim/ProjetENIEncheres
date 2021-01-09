package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.time.LocalDate;

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
		int id = Integer.parseInt(request.getParameter("idarticle"));

			try {
				ArticleVenduBo article = ArticleVenduBll.getById(id);
				request.setAttribute("article", article);
				
				
				EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
				request.setAttribute("enchere", enchere);


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
		try 
		{
			HttpSession session = request.getSession();
			int no_utilisateur = Integer.parseInt(session.getAttribute("session").toString());

			UtilisateurBo utilisateur = UtilisateurBll.get(no_utilisateur);
			
			int no_article=Integer.parseInt(request.getParameter("id"));
			ArticleVenduBo article = ArticleVenduBll.getById(no_article);
			
			LocalDate date = LocalDate.now();	
			int montant= Integer.parseInt(request.getParameter("enchere"));

			EnchereBo enchere = new EnchereBo();
			enchere.setDateEnchere(date);
			enchere.setMontantEnchere(montant);
			enchere.setNoArticle(article);
			enchere.setNoUtilisateur(utilisateur);

			EnchereBll.insert(enchere);
			System.out.println(article);
			article.setPrixVente(montant);
			System.out.println(article.getDateDebutEncheres());
			ArticleVenduBll.updateArticle(article);
			System.out.println(article);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	
        //le forward envoi l'affichage à la jsp
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/detail-Vente.jsp");
        rd.forward(request, response);
		
}
}
