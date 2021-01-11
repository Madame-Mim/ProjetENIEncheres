package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.ArticleVenduBll;
import fr.eni.projetEni.bo.ArticleVenduBo;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get");

				
				
		List<ArticleVenduBo> listeArticles = new ArrayList<>();
		try {
			listeArticles= ArticleVenduBll.getAll();
			
	        request.setAttribute("listeArticles", listeArticles);
	       
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();

		if(session.getAttribute("session")==null){
			
		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueilvisiteur.jsp");
	        rd.forward(request, response);}
		else
		{
			
			
			  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
		        rd.forward(request, response);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("do post");
			
		
		
		String filtreNom = request.getParameter("recherche");
		String categorieselection = request.getParameter("categorie");
		
		
		
		List<ArticleVenduBo> listeArticles = new ArrayList<>();
		try {
			listeArticles= ArticleVenduBll.getAll();
		
	        request.setAttribute("listeArticles", listeArticles);
	        request.setAttribute("filtreNom", filtreNom);  
	        request.setAttribute("categorieselection", categorieselection);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();

		if(session.getAttribute("session")==null){
			
		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueilvisiteur.jsp");
	        rd.forward(request, response);}
		else
		{
			
			
			  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
		        rd.forward(request, response);}
		
	}

		
		 // RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
	       // rd.forward(request, response);
	

}
