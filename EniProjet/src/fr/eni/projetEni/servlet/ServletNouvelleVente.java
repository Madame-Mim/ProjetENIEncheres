package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEni.bll.ArticleVenduBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.RetraitBo;
import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.dal.ArticleVenduDal;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletNouvelleVente - doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletNouvelleVente - doPost");
		
		String nomArticle = request.getParameter("nomArticle");
		System.out.println("nomArticle :" + nomArticle);
		
		String descritpionArticle = request.getParameter("descritpionArticle");
		System.out.println("descritpionArticle :" + descritpionArticle);
		
		
		Date debutEnchere;
		try {
			debutEnchere = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("debutEnchere"));
			System.out.println("debutEnchere :" + debutEnchere);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Date finEnchere;
		try {
			finEnchere = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("finEnchere"));
			System.out.println("finEnchere :" + finEnchere);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int miseAPrixArticle = Integer.parseInt(request.getParameter("miseAPrixArticle"));
		System.out.println("miseAPrixArticle :" + miseAPrixArticle);
		
		int prixVente = miseAPrixArticle;
		System.out.println("prixVente :" + miseAPrixArticle);
	
		//UtilisateurBo utilisateur = new UtilisateurBo("BigBoss", "Durand", "Jean", "Jean.Durand@gmail.com", 0102030405, "18 rue Emile Zola", 44000, "Nantes", "lemeilleur", 0, false);
		
		
	//créer catégorieBo
		String categorieArticle = request.getParameter("categorieArticle");
		System.out.println("CatégorieArticle :" + categorieArticle);
		CategorieBo categorie = new CategorieBo(categorieArticle);
		int noCategorie = categorie.getNoCategorie();
		System.out.println("noCategorie : " + noCategorie);
		
	//créer l'adresse de retrait
		String rueRetrait = request.getParameter("rueRetrait");
		int codePostalRetrait = Integer.parseInt(request.getParameter("codePostalRetrait"));
		String villeRetrait = request.getParameter("villeRetrait");
		RetraitBo adresseRetrait = new RetraitBo(rueRetrait, codePostalRetrait, villeRetrait);
		System.out.println(adresseRetrait);
		
		ArticleVenduBo essai = new ArticleVenduBo(nomArticle, descritpionArticle, debutEnchere, finEnchere, miseAPrixArticle, prixVente, adresseRetrait)
		System.out.println();		
		ArticleVenduBll ArticleVenduBll = new ArticleVenduBll();
		ArticleVenduBo nouvelleVente = ArticleVenduBll.insertArticle(nomArticle, descritpionArticle, debutEnchere, finEnchere, miseAPrixArticle, prixVente, adresseRetrait);
		
		
	}

}
