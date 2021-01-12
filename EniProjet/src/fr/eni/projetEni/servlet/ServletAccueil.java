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
import fr.eni.projetEni.bll.CategorieBll;
import fr.eni.projetEni.bll.EnchereBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
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
		String namerecherche= "le nom de l'article contient";
		List<CategorieBo> listeCategorie = new ArrayList<>();
		if (session.getAttribute("session") == null ) 
		{
			try {
				listeArticles= ArticleVenduBll.getAll();
				listeCategorie= CategorieBll.get();
				request.setAttribute("listeCategorie", listeCategorie);
				 request.setAttribute("listeArticles", listeArticles);
				 request.setAttribute("namerecherche", namerecherche);
				 request.setAttribute("namerecherche", namerecherche);
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
	        request.setAttribute("namerecherche", namerecherche);
            
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
		String namerecherche= "le nom de l'article contient";
		List<CategorieBo> listeCategorie = new ArrayList<>();
		if (session.getAttribute("session") == null ) 
		{
			try {
				listeCategorie= CategorieBll.get();
				request.setAttribute("listeCategorie", listeCategorie);
				listeArticles= ArticleVenduBll.getAll();
				 request.setAttribute("listeArticles", listeArticles);
				 request.setAttribute("filtreNom", filtreNom);  
			     request.setAttribute("categorieselection", categorieselection);
			     if (filtreNom != null ) 
					{namerecherche=filtreNom;}
			     request.setAttribute("namerecherche", namerecherche);
			     
	            
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); }
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueilvisiteur.jsp");
	        rd.forward(request, response);
	       
			}
	
		else 
		{
			int vendeur= Integer.parseInt(session.getAttribute("session").toString());	
			
				        
		
			String choixAV = request.getParameter("choixAV");
			
			String choixA1 = request.getParameter("choixA1");
			String choixA2 = request.getParameter("choixA2");
			String choixA3 = request.getParameter("choixA3");
			String choixV1 = request.getParameter("choixV1");
			String choixV2 = request.getParameter("choixV2");
			String choixV3 = request.getParameter("choixV3");
			
		List<EnchereBo> malisteEncheres = new ArrayList<>();
		
		
		try {
			listeArticles= ArticleVenduBll.getAll();
			malisteEncheres=EnchereBll.getbyutilisateur(vendeur);
			request.setAttribute("malisteEncheres", malisteEncheres);
	        request.setAttribute("listeArticles", listeArticles);
	        request.setAttribute("filtreNom", filtreNom);  
	        request.setAttribute("categorieselection", categorieselection);
	        request.setAttribute("vendeur", vendeur);
	        request.setAttribute("choixAV", choixAV);
	        
	        request.setAttribute("choixA1", choixA1);
	        request.setAttribute("choixA2", choixA2);
	        request.setAttribute("choixA3", choixA3);
	        request.setAttribute("choixV1", choixV1);
	        request.setAttribute("choixV2", choixV2);
	        request.setAttribute("choixV3", choixV3);
	        request.setAttribute("namerecherche", namerecherche);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
		rd.forward(request, response);}
		
	}
		
			
		

}
