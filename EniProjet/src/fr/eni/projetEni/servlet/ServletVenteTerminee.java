package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
public class ServletVenteTerminee extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			HttpSession session = request.getSession();

			if(request.getParameter("idarticle")==null || request.getParameter("idarticle")=="" || Integer.parseInt(request.getParameter("idarticle"))==0)// si idarticle n'existe pas ou est paramétré à 0
			{
				RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
			    rd.forward(request, response);
			}
			else
			{
				int id = Integer.parseInt(request.getParameter("idarticle"));
				
				ArticleVenduBo article = ArticleVenduBll.getById(id); //récupération de l'article passé en paramètre d'url
				request.setAttribute("article", article);
				
				if(article==null ||article.getUtilisateur().getId() != Integer.parseInt(session.getAttribute("session").toString()))//Si l'utilisateur n'est pas le vendeur ou si l'article n'existe pas
				{
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
				    rd.forward(request, response);
				}
				else
				{
					EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
					request.setAttribute("enchere", enchere);	
					
					Timestamp timestamp = Timestamp.valueOf(article.getDateFinEncheres().atStartOfDay()); //Passage de la date de fin d'enchere de l'article au format timestamp
					long finEnchereMillis = timestamp.getTime(); // obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et cette date
					System.currentTimeMillis(); //obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et maintenant
				
					if(finEnchereMillis > System.currentTimeMillis()) //si d'avantages de millisecondes se seront écoulées à la date de fin d'enchère qu'aujourd'hui, la date n'est pas passée
					{
						RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
					    rd.forward(request, response);
					}
					else
					{
						RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/enchere-terminee.jsp");
						rd.forward(request, response);
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}							
}
//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(session.getAttribute("session").toString());

		try
		{
			UtilisateurBll utilisateurAmodifie = new UtilisateurBll();
			UtilisateurBo utilisateurACrediter = UtilisateurBll.get(id); // récupération de l'utilisateur connecté
			int montant = Integer.parseInt(request.getParameter("credit")); // récupération du montant du prix de vente obtenu de l'enchère
			int creditActuel = utilisateurACrediter.getCredit(); // récupération du nombre de crédit possédé par l'utilisateur
			int nouveauCredit = creditActuel+montant; // Calcul du crédit devant être en possession du vendeur
			utilisateurACrediter.setCredit(nouveauCredit); //instanciation de la donnée à modifier
			
			utilisateurAmodifie.update(utilisateurACrediter); //mise à jour de la donnée
			
			int idArticle = Integer.parseInt(request.getParameter("idarticle")); //récupération de l'id article

			ArticleVenduBll articleAModifie = new ArticleVenduBll(); 
			ArticleVenduBo articleRetire = ArticleVenduBll.getById(idArticle);  //récupération de l'article
			articleRetire.setRetraitEffectue(true); //instanciation de la donnée à modifier

			articleAModifie.updateArticle(articleRetire); //enregistrement du retrait dans la table
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			//Récupération des données nécessaires à l'affichage de la page (comme dans le do get)
			int id2 = Integer.parseInt(request.getParameter("idarticle"));
			ArticleVenduBo article;

			article = ArticleVenduBll.getById(id2);
			EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
			request.setAttribute("enchere", enchere);
			request.setAttribute("article", article);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/enchere-terminee.jsp");
	    rd.forward(request, response);
		}
}
