package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		
		//Permet de récupérer la date de début d'enchère en String et de la convertir en LocalDate
		String debutEnchere = request.getParameter("debutEnchere");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate debutEncherelocalDate = LocalDate.parse(debutEnchere,formatter);
		System.out.println("Début enchère : " + debutEncherelocalDate);
		
		
		//Permet de récupérer la date de fin d'enchère en String et de la convertir en LocalDate
		String finEnchere = request.getParameter("finEnchere");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate finEncherelocalDate = LocalDate.parse(finEnchere,formatter);
		System.out.println("fin enchère : " + finEncherelocalDate);
		
				
		int miseAPrixArticle = Integer.parseInt(request.getParameter("miseAPrixArticle"));
		System.out.println("miseAPrixArticle :" + miseAPrixArticle);
		
		int prixVente = miseAPrixArticle;
		System.out.println("prixVente :" + miseAPrixArticle);
	
		//UtilisateurBo utilisateur = new UtilisateurBo("BigBoss", "Durand", "Jean", "Jean.Durand@gmail.com", 0102030405, "18 rue Emile Zola", 44000, "Nantes", "lemeilleur", 0, false);
		UtilisateurBo utilisateur = new UtilisateurBo();
		
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
		int noRetrait = adresseRetrait.getNoRetrait();
		System.out.println("affiche le numéro de retrait " + noRetrait);
		
		ArticleVenduBll ArticleVenduBll = new ArticleVenduBll();
		ArticleVenduBo nouvelleVente = ArticleVenduBll.ajouter(nomArticle, descritpionArticle, debutEncherelocalDate, finEncherelocalDate, miseAPrixArticle, prixVente, utilisateur, categorie,adresseRetrait);
		
	}

}
