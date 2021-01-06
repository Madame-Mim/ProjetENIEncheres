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

import fr.eni.projetEni.bo.ArticleVenduBo;

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
		System.out.println("nomArticle" + nomArticle);
		String descritpionArticle = request.getParameter("descritpionArticle");
		System.out.println("descritpionArticle" + descritpionArticle);
		String CatégorieArticle = request.getParameter("CatégorieArticle");
		System.out.println("CatégorieArticle" + CatégorieArticle);
		int miseAPrixArticle = Integer.parseInt(request.getParameter("miseAPrixArticle"));
		System.out.println("miseAPrixArticle" + miseAPrixArticle);
		
		Date debutEnchere;
		try {
			debutEnchere = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("debutEnchere"));
			System.out.println("debutEnchere" + debutEnchere);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Date finEnchere;
		try {
			finEnchere = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("finEnchere"));
			System.out.println("finEnchere" + finEnchere);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		ArticleVenduBo nouvelleVente = new ArticleVenduBo(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, utilisateur, categorie, retrait)
		
		
	}

}
