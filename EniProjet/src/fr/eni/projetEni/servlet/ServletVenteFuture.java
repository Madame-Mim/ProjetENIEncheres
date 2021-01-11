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

			List<CategorieBo> listeCategorie = CategorieBll.getallM1();
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

		int id = Integer.parseInt(session.getAttribute("session").toString());
		int idarticle = Integer.parseInt(request.getParameter("idarticle"));

		
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
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");
			
			CategorieBo categorieVente = CategorieBll.get(categorie);
			RetraitBo retrait = RetraitBll.getRetrait(rue, codePostal, ville); //recuperation de l'adresse de retrait en bdd
			
			if(retrait == null) //si cette adresse n'existe pas alors :
			{
				try {
					RetraitBll nouvelleadresse = new RetraitBll();
					
					RetraitBo newPlace = new RetraitBo();
					
					newPlace.setRue(rue);
					newPlace.setCodePostal(codePostal);
					newPlace.setVille(ville);
					
					nouvelleadresse.insert(newPlace); //enregistrement de la nouvelle adresse
					
				
					UtilisateurBo utilisateur;
			
					utilisateur = UtilisateurBll.get(id);
					ArticleVenduBo article = new ArticleVenduBo();
				
					article.setNoArticle(idArticle);
					article.setNomArticle(nom);
					article.setDescription(description);
					article.setDateDebutEncheres(debutEnchere);
					article.setDateFinEncheres(finEnchere);
					article.setMiseAPrix(prixBase);
					article.setPrixVente(0);
					article.setCategorie(categorieVente);
					article.setUtilisateur(utilisateur);
					article.setRetrait(newPlace); //on set la nouvelle adresse enregistrée
					try {
						articleAModifie.updateArticle(article); //enregistrement de l'article
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} 
			else
			{
				UtilisateurBo utilisateur;
				
				try {
					utilisateur = UtilisateurBll.get(id);
					ArticleVenduBo article = new ArticleVenduBo();
					//Enregistrer d'abord le retrait si besoin	RetraitBo retrait = RetraitBll.get(no_retrait);

						article.setNoArticle(idArticle);
						article.setNomArticle(nom);
						article.setDescription(description);
						article.setDateDebutEncheres(debutEnchere);
						article.setDateFinEncheres(finEnchere);
						article.setMiseAPrix(prixBase);
						article.setPrixVente(0);
						article.setCategorie(categorieVente);
						article.setUtilisateur(utilisateur);
						article.setRetrait(retrait);
						try {
							articleAModifie.updateArticle(article);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
		}
		else if(request.getParameter("annulerModif")!=null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("annulerVente")!=null)
		{
				ArticleVenduBll articleBll = new ArticleVenduBll();
				ArticleVenduBo articleADelete;
				try {
					System.out.println(idarticle);
					articleADelete = ArticleVenduBll.getById(idarticle);
					System.out.println(articleADelete);
					articleBll.deleteArticle(idarticle);
					
					   RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
					   rd.forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		 //le forward envoi l'affichage à la jsp

		}
}
