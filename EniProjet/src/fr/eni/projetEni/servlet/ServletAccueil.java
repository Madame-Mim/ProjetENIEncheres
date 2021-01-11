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
import fr.eni.projetEni.bll.EnchereBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.EnchereBo;
import fr.eni.projetEni.bo.UtilisateurBo;

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
		
		HttpSession session = request.getSession();
		List<ArticleVenduBo> listeArticles = new ArrayList<>();
		
		if (session.getAttribute("session") == null ) 
		{
			try {
				listeArticles= ArticleVenduBll.getAll();
				 request.setAttribute("listeArticles", listeArticles);
		       
	            
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
				
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueilvisiteur.jsp");
	        rd.forward(request, response);
	        }
			
	
		else 
		{
			int vendeur= Integer.parseInt(session.getAttribute("session").toString());
			System.out.println(vendeur);
		List<EnchereBo> malisteEncheres = new ArrayList<>();
		
		
		try {
			listeArticles= ArticleVenduBll.getAll();		
			malisteEncheres=EnchereBll.getbyutilisateur(vendeur);
			request.setAttribute("malisteEncheres", malisteEncheres);
	        request.setAttribute("listeArticles", listeArticles);
	        request.setAttribute("vendeur", vendeur);
	       
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
		rd.forward(request, response);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("do post");
		HttpSession session = request.getSession();
		List<ArticleVenduBo> listeArticles = new ArrayList<>();
		String filtreNom = request.getParameter("recherche");
		String categorieselection = request.getParameter("categorie");
		
		if (session.getAttribute("session") == null ) 
		{
			try {
				listeArticles= ArticleVenduBll.getAll();
				 request.setAttribute("listeArticles", listeArticles);
				 request.setAttribute("filtreNom", filtreNom);  
			        request.setAttribute("categorieselection", categorieselection);
		       
	            
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); }
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueilvisiteur.jsp");
	        rd.forward(request, response);
	       
			}
	
		else 
		{
			int vendeur= Integer.parseInt(session.getAttribute("session").toString());	
			
		List<EnchereBo> malisteEncheres = new ArrayList<>();
		
		
		try {
			listeArticles= ArticleVenduBll.getAll();
			malisteEncheres=EnchereBll.getbyutilisateur(vendeur);
			request.setAttribute("malisteEncheres", malisteEncheres);
	        request.setAttribute("listeArticles", listeArticles);
	        request.setAttribute("filtreNom", filtreNom);  
	        request.setAttribute("categorieselection", categorieselection);
	        request.setAttribute("vendeur", vendeur);
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
		rd.forward(request, response);}
		
	}
		
			
		

}
