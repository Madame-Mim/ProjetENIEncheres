package fr.eni.projetEni.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
@WebServlet("/VenteEnCours")
public class ServletVenteEnCours extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("idarticle")==null || request.getParameter("idarticle")=="" || Integer.parseInt(request.getParameter("idarticle"))==0)// si idarticle n'existe pas ou est paramétré à 0
		{
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
		    rd.forward(request, response);
		}
		else
		{
       		int numArticle = Integer.parseInt(request.getParameter("idarticle"));

       		try
			{
			    HttpSession session = request.getSession();
			     int no_utilisateur = Integer.parseInt(session.getAttribute("session").toString());
			     
			    ArticleVenduBo article = ArticleVenduBll.getById(numArticle);
				request.setAttribute("article", article);
				if(article==null) //si l'id article n'existe pas
				{
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
				    rd.forward(request, response);
				}			
				else
				{				
					Timestamp debutEnchereTimestamp = Timestamp.valueOf(article.getDateDebutEncheres().atStartOfDay()); //Passage de la date de debut d'enchere de l'article au format timestamp
					long debutEnchereMillis = debutEnchereTimestamp.getTime(); // obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et cette date
					Timestamp finEnchereTimestamp = Timestamp.valueOf(article.getDateFinEncheres().atStartOfDay()); //Passage de la date de fin d'enchere de l'article au format timestamp
					long finEnchereMillis = finEnchereTimestamp.getTime(); // obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et cette date
					long now = System.currentTimeMillis(); //obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et maintenant
						
					if(finEnchereMillis < now || now < debutEnchereMillis) //si la date actuelle n'est pas comprise entre les dates de l'enchère
					{
						RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
					    rd.forward(request, response);
					}			
					else 
					{
						UtilisateurBo utilisateur = UtilisateurBll.get(no_utilisateur);
						request.setAttribute("utilisateur", utilisateur);
								
						EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
						request.setAttribute("enchere", enchere);

						File photo = new File("Image/"+numArticle+".jpg");
						File noPhoto = new File("Image/NoImage.png");
						
						if(photo.isFile())
						{
							request.setAttribute("image", photo);
						}
						else
						{
							request.setAttribute("image", noPhoto);
						}
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-en-cours.jsp");
						rd.forward(request, response);
					}
				}
			}
		
			catch (Exception e)
			{
						e.printStackTrace();
			}
		}
}
				

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			HttpSession session = request.getSession(); //récupération de la session

			int no_utilisateur = Integer.parseInt(session.getAttribute("session").toString()); //récupération de l'id passé en attribut de session (au format int de l'objet passé en String)

			UtilisateurBll utilisateurAmodifie = new UtilisateurBll(); //instanciation de l'utilisateurBll
			
			int no_article=Integer.parseInt(request.getParameter("idarticle")); //récupération de l'id de l'article passé en paramère d'url sous le nom "idarticle"
			
			ArticleVenduBll articleAModifie = new ArticleVenduBll(); //instanciation de l'article utilisateurBll
			ArticleVenduBo article = ArticleVenduBll.getById(no_article); //récupération de l'article par son id
			
			LocalDate date = LocalDate.now();	// récupération de la date du jour
			int montant= Integer.parseInt(request.getParameter("enchere")); //récupération du montant de l'enchère effectuée

			EnchereBo enchere = new EnchereBo(); //instanciation d'une nouvelle enchère.
			
			/*Remboursement points de la meilleur enchere actuelle */
			EnchereBo meilleurEnchere = EnchereBll.getMaxByIdArticle(no_article, no_article); //récupération de la meilleur enchère de l'article voulu
	
			UtilisateurBo utilisateurARembourse = meilleurEnchere.getNoUtilisateur(); // récupération du meilleur enchérisseur passé
			if(utilisateurARembourse != null) //s'il existe alors :
			{
			int creditActuell = utilisateurARembourse.getCredit(); //recuperation de son credit actuel
			int nouveauCreditt = creditActuell+meilleurEnchere.getMontantEnchere(); // Calcul du nouveau crédit
			utilisateurARembourse.setCredit(nouveauCreditt); //instanciation du nouveau crédit
			
			utilisateurAmodifie.update(utilisateurARembourse); //update du crédit de l'ancien meilleur enchérisseur
			}
			
			/* Retrait des points du nouvel enchérisseur */
			UtilisateurBo utilisateur = UtilisateurBll.get(no_utilisateur); //récupération de l'utilisateur qui vient de faire l'enchère

			int creditActuel = utilisateur.getCredit();  //recuperation de son credit actuel
			int nouveauCredit = creditActuel-montant; // Calcul du nouveau crédit
			utilisateur.setCredit(nouveauCredit); //instanciation du nouveau crédit
			utilisateurAmodifie.update(utilisateur); //update du crédit du nouvel enchérisseur

			/* insertion de la nouvelle enchère */
			enchere.setDateEnchere(date); //instanciation de la date d'enchère
			enchere.setMontantEnchere(montant); //instanciation du montant enchéri
			enchere.setNoArticle(article); //instanciation de l'article concerné
			enchere.setNoUtilisateur(utilisateur); //instanciation de l'utilisateur qui a fait l'enchère
			EnchereBll.insert(enchere); //Insertion en bdd

			// Modification du prix de vente dans la table article
			
			article.setPrixVente(montant); // instanciation du prix de vente actuel

			articleAModifie.updateArticle(article); // update du prix de l'article
			
			
			/* Rechargement des données pour affichage */
			int numArticle = Integer.parseInt(request.getParameter("idarticle"));

			ArticleVenduBo articleACharge = ArticleVenduBll.getById(numArticle);
			request.setAttribute("article", articleACharge);
			EnchereBo enchereACharge = EnchereBll.getByIdArticle(article.getNoArticle());
			request.setAttribute("enchere", enchereACharge);
			
			File photo = new File("Image/"+numArticle+".jpg");
			File noPhoto = new File("Image/NoImage.png");
			
			if(photo.isFile())
			{
				request.setAttribute("image", photo);
			}
			else
			{
				request.setAttribute("image", noPhoto);
			}
	        //le forward envoi l'affichage à la jsp
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-en-cours.jsp");
	        rd.forward(request, response);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		
	}
}
