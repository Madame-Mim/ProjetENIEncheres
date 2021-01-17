package fr.eni.projetEni.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
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
				int id = Integer.parseInt(request.getParameter("idarticle")); //recupere l'id de l'article

				ArticleVenduBo article = ArticleVenduBll.getById(id); // recupère les infos de l'article
				request.setAttribute("article", article); //met l'article en attribut pour l'affichage en jsp
				
				if(article==null || article.getUtilisateur().getId() != Integer.parseInt(session.getAttribute("session").toString()))//Si l'article n'existe pas ou si l'utilisateur n'est pas le vendeur 
				{
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
				    rd.forward(request, response);
				}
				else
				{
					List<CategorieBo> listeCategorie = CategorieBll.get(); // recupère les valeurs en BDD de la table catégorie
					request.setAttribute("categorieListe", listeCategorie);
					
					Timestamp timestamp = Timestamp.valueOf(article.getDateDebutEncheres().atStartOfDay()); //Passage de la date de fin d'enchere de l'article au format timestamp
					long debutEnchereMillis = timestamp.getTime(); // obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et cette date
					long now = System.currentTimeMillis(); //obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et maintenant
					Date date =new Date(now);
					if(debutEnchereMillis <= now) //si l'enchère à déjà commencé
					{
						RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
					    rd.forward(request, response);
					}
					else //j'ai passé tous les barrages
					{
						File photo = new File("Image/"+id+".jpg");
						File noPhoto = new File("Image/NoImage.png"); 

						if(photo.isFile())
						{
							request.setAttribute("image", photo);
						}
						else
						{
							request.setAttribute("image", noPhoto);
						}
						System.out.println(photo.isFile());
						System.out.println(request.getAttribute("image"));
						RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp"); // j'affiche la page voulue
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
		
		int id = Integer.parseInt(session.getAttribute("session").toString()); // récupération de l'id de l'utilisateur connecté
		
		int idarticle = Integer.parseInt(request.getParameter("idarticle")); // récupération de l'id de l'article
		
		if(request.getParameter("enregistrer")!=null) //Si l'enregistrement de l'update est demandé
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
			
			Timestamp debutEnchereTimestamp = Timestamp.valueOf(debutEnchere.atStartOfDay()); //Passage de la date de debut d'enchere de l'article au format timestamp
			long debutEnchereMillis = debutEnchereTimestamp.getTime(); // obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et cette date			
			Timestamp finEnchereTimestamp = Timestamp.valueOf(finEnchere.atStartOfDay()); //Passage de la date de fin d'enchere de l'article au format timestamp
			long finEnchereMillis = finEnchereTimestamp.getTime(); // obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et cette date		
			long now = System.currentTimeMillis(); //obtention du nombre de millisecondes écoulées entre le 1er janvier 1970 et maintenant

			if(debutEnchereMillis < now||finEnchereMillis < now || debutEnchereMillis >= finEnchereMillis) // si les dates d'encheres ont lieu avant la date actuelle ou si le debut à lieu avant la fin
			{ 
				//récupération des valeurs nécessaires à l'affichage jsp (comme pour le do get, sinon les valeurs ne sont pas connues)
				ArticleVenduBo article;
				try 
				{	
					article = ArticleVenduBll.getById(idarticle);
					request.setAttribute("article", article);

					List<CategorieBo> listeCategorie = CategorieBll.get();
					request.setAttribute("categorieListe", listeCategorie);	
					
					/* messages d'erreurs à afficher sur la jsp afin d'aider l'utilisateur */
					request.setAttribute("erreur", "la date doit au moins être à demain"); 
					request.setAttribute("erreur2", "la date doit être après celle de début d'enchère");
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				File photo = new File("Image/"+idarticle+".jpg");
				File noPhoto = new File("Image/NoImage.png"); 

				if(photo.isFile())
				{
					request.setAttribute("image", photo);
				}
				else
				{
					request.setAttribute("image", noPhoto);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp");
				rd.forward(request, response);
			}
			else if(retrait == null) //si l'adresse de retrait n'existe pas alors je l'insère :
				{
					try 
					{
						RetraitBll nouvelleadresse = new RetraitBll();
						
						RetraitBo newPlace = new RetraitBo();
						
						newPlace.setRue(rue);
						newPlace.setCodePostal(codePostal);
						newPlace.setVille(ville);
						
						nouvelleadresse.insert(newPlace); //enregistrement de la nouvelle adresse
						RetraitBo nouvelleAdresseRetrait = RetraitBll.getRetrait(newPlace.getRue(), newPlace.getCodePostal(), newPlace.getVille()); //récupération de l'adresse enregistrée
						
						UtilisateurBo utilisateur = UtilisateurBll.get(id); //je récupère le vendeur
									
						//J'instancie l'article avec les valeurs à y insérer
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
						article.setRetrait(nouvelleAdresseRetrait); //on set la nouvelle adresse enregistrée
						try
						{
							articleAModifie.updateArticle(article); //enregistrement de l'article
							
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					ArticleVenduBo article;
					try 
					{
						article = ArticleVenduBll.getById(idarticle);
						request.setAttribute("article", article);
	
						List<CategorieBo> listeCategorie = CategorieBll.get();
						request.setAttribute("categorieListe", listeCategorie);				
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp");//l'article est enregistré, j'affiche la page modifiée
					rd.forward(request, response);
				} 
				else 	//Si cette adresse de retrait existe déjà: 
				{
					UtilisateurBo utilisateur;
					
					try
					{
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
							article.setRetrait(retrait);
							
							try 
							{
								articleAModifie.updateArticle(article); //je modifie l'article sans insérer de nouvelles adresses de retrait
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					
					ArticleVenduBo article;
					try 
					{
						article = ArticleVenduBll.getById(idarticle);
						request.setAttribute("article", article);
	
						List<CategorieBo> listeCategorie = CategorieBll.get();
						request.setAttribute("categorieListe", listeCategorie);				
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
						File photo = new File("Image/"+idarticle+".jpg");
						File noPhoto = new File("Image/NoImage.png"); 
	
						if(photo.isFile())
						{
							request.setAttribute("image", photo);
						}
						else
						{
							request.setAttribute("image", noPhoto);
						}	
					
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp");
						rd.forward(request, response);
				}
				
			}
			else if(request.getParameter("annulerModif")!=null) //Si l'annulation de la modification est demandée
			{				
				try 
				{
					ArticleVenduBo article = ArticleVenduBll.getById(idarticle);
					request.setAttribute("article", article);
		
					List<CategorieBo> listeCategorie = CategorieBll.get();
					request.setAttribute("categorieListe", listeCategorie);
					
					File photo = new File("Image/"+idarticle+".jpg");
					File noPhoto = new File("Image/NoImage.png"); 

					if(photo.isFile())
					{
						request.setAttribute("image", photo);
					}
					else
					{
						request.setAttribute("image", noPhoto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-future.jsp");// je renvoie sur la page sans n'en rien faire
				rd.forward(request, response);
			}
			else if(request.getParameter("annulerVente")!=null) //Si l'annulation de la vente est demandée
			{
					ArticleVenduBll articleBll = new ArticleVenduBll();
					ArticleVenduBo articleADelete;
					try {
						articleADelete = ArticleVenduBll.getById(idarticle); //je récupère l'article
						articleBll.deleteArticle(idarticle);// et je le supprime
						
						
						   RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
						   rd.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
			}		
	}
}