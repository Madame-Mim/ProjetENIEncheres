package fr.eni.projetEni.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.ArticleVenduBll;
import fr.eni.projetEni.bll.EnchereBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.EnchereBo;

/**
 * Servlet implementation class ServletEnchereRemportee
 */
@WebServlet("/ServletEnchereRemportee")
public class ServletEnchereRemportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletEnchereRemportee - doGet");
		
		try {
			HttpSession session = request.getSession(); //récupération de la session

			if(request.getParameter("idarticle")==null || request.getParameter("idarticle")=="" || Integer.parseInt(request.getParameter("idarticle"))==0)// si idarticle n'existe pas ou est paramétré à 0
			{
				RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
			    rd.forward(request, response);
			}
			else
			{
				int id = Integer.parseInt(request.getParameter("idarticle"));
	
				ArticleVenduBo article = ArticleVenduBll.getById(id);
				request.setAttribute("article", article);
				
				if(article==null)//Si l'utilisateur n'est pas le vendeur ou si l'article n'existe pas
				{
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
				    rd.forward(request, response);
				}
				
				else 
				{
					EnchereBo enchere = EnchereBll.getMaxByIdArticle(id,id);
					request.setAttribute("enchere", enchere);

					if(enchere.getNoUtilisateur().getId() != Integer.parseInt(session.getAttribute("session").toString()))// si l'utilisateur n'est pas l'acquéreur
					{
						RequestDispatcher rd = request.getRequestDispatcher("/Accueil"); // je renvoie vers l'accueil
					    rd.forward(request, response);
					}
					else
					{
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
							File photoArticle = new File("C://Users//nicol.DESKTOP-CD7QT37//git//projetENI//EniProjet//WebContent//Image//"+id+".jpg");
							String photo = "Image/"+id+".jpg";
							File noPhotoArticle = new File("C://Users//nicol.DESKTOP-CD7QT37//git//projetENI//EniProjet//WebContent//Image//NoImage.png"); 
							String noPhoto = "Image/NoImage.png";

							if(photoArticle.isFile())
							{
								request.setAttribute("image", photo);
							}
							else
							{
								request.setAttribute("image", noPhoto);
							}
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchereRemportee.jsp");
							rd.forward(request, response);
						}
					}
				}
			}
		}
			catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletEnchereRemportee - do Post");
	}

}
