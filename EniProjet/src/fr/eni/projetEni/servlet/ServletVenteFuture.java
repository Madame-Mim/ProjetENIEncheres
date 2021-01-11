package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import fr.eni.projetEni.bll.RetraitBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.EnchereBo;
import fr.eni.projetEni.bo.RetraitBo;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * Servlet implementation class ServletVenteEmportee
 */
@WebServlet("/VenteFuture")
public class ServletVenteFuture extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("idarticle"));

			ArticleVenduBo article = ArticleVenduBll.getById(id);
			request.setAttribute("article", article);

			List<CategorieBo> listeCategorie = CategorieBll.get();
			request.setAttribute("categorieListe", listeCategorie);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    //le forward envoi l'affichage à la jsp
    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp");
    rd.forward(request, response);
    }
//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	     session.setAttribute("session", 5);

		int id = Integer.parseInt(session.getAttribute("session").toString());
		
		if(request.getParameter("enregistrer")!=null)
		{
			ArticleVenduBll articleAModifie = new ArticleVenduBll();
			
			int idArticle = Integer.parseInt(request.getParameter("idarticle"));
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			String debut = request.getParameter("dateDebut");
			LocalDate debutEnchere = LocalDate.parse(debut);
			String fin = request.getParameter("dateFin");
			LocalDate finEnchere = LocalDate.parse(fin);

			int prixBase = Integer.parseInt(request.getParameter("prix"));
			int categorie =Integer.parseInt(request.getParameter("categorie"));
			
			CategorieBo categorieVente = CategorieBll.get(categorie);
		//pourquoi try catch ?	UtilisateurBo utilisateur = UtilisateurBll.get(id);
			ArticleVenduBo article = new ArticleVenduBo();
		//Enregistrer d'abord le retrait si besoin	RetraitBo retrait = RetraitBll.get(no_retrait);

			article.setNoArticle(idArticle);
			article.setNomArticle(nom);
			article.setDescription(description);
			article.setDateDebutEncheres(debutEnchere);
			article.setDateFinEncheres(finEnchere);
			article.setMiseAPrix(prixBase);
			article.setCategorie(categorieVente);
		//	article.setUtilisateur(utilisateur);
			try {
				articleAModifie.updateArticle(article);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(request.getParameter("annulerModif")!=null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp?idarticle=id");
			rd.forward(request, response);
		}
		else if(request.getParameter("annulerVente")!=null)
		{
			System.out.println("delete les données");
		}
		
		 //le forward envoi l'affichage à la jsp
	   // RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/enchere-future?idarticle=id.jsp");
	  //  rd.forward(request, response);
		}
}
