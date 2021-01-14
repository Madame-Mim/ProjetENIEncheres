package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("session", 1);//a retirer juste là pour les test
		int idUtilisateur = Integer.parseInt(session.getAttribute("session").toString());
		UtilisateurBo utilisateur = new UtilisateurBo();
		try {
			utilisateur = UtilisateurBll.get(idUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String rueRetraitUtilisateur = utilisateur.getRue();
		request.setAttribute("rueRetraitUtilisateur", rueRetraitUtilisateur);
		String cpRetraitUtilisateur = utilisateur.getCodePostal();
		request.setAttribute("cpRetraitUtilisateur", cpRetraitUtilisateur);
		String villeRetraitUtilisateur = utilisateur.getVille();
		request.setAttribute("villeRetraitUtilisateur", villeRetraitUtilisateur);
		
		List<CategorieBo> listeCategorie = CategorieBll.get();
		request.setAttribute("categorieListe", listeCategorie);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/nouvelleVente.jsp");
		rd.forward(request, response);
		
	}	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle = request.getParameter("nomArticle");
		
		String descriptionArticle = request.getParameter("descriptionArticle");
		
	//Permet de récupérer la date de début d'enchère en String et de la convertir en LocalDate
		String debutEnchere = request.getParameter("debutEnchere");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate debutEncherelocalDate = LocalDate.parse(debutEnchere,formatter);
		
		
	//Permet de récupérer la date de fin d'enchère en String et de la convertir en LocalDate
		String finEnchere = request.getParameter("finEnchere");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate finEncherelocalDate = LocalDate.parse(finEnchere,formatter2);
				
		int miseAPrixArticle = Integer.parseInt(request.getParameter("miseAPrixArticle"));
		
		int prixVente = miseAPrixArticle;
	
	//Permet de récupérer l'id de l'utilisateur
		HttpSession session = request.getSession();
		session.setAttribute("session", 1);//a retirer juste là pour les test
		int idUtilisateur = Integer.parseInt(session.getAttribute("session").toString());
		UtilisateurBo utilisateur = new UtilisateurBo();
		try {
			utilisateur = UtilisateurBll.get(idUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//Permet de récupérer le noCategorie	
		int noCategorie = Integer.parseInt(request.getParameter("categorie"));
		CategorieBo CategorieNouvelArticle = CategorieBll.get(noCategorie);
		
		
		boolean retraitEffectue = false;
		
	
		
		//Récupérer et le noRetrait et le noCategorie et l'insérer dans la method insert
		String rueRetrait = request.getParameter("rueRetrait");
		String codePostalRetrait = request.getParameter("codePostalRetrait");
		String villeRetrait = request.getParameter("villeRetrait");

		
		RetraitBo retrait = RetraitBll.getRetrait(rueRetrait, codePostalRetrait, villeRetrait); //recuperation de l'adresse de retrait en bdd
		
		if(retrait == null) //si cette adresse n'existe pas alors :
		{
			try {
				RetraitBll nouvelleAdresse = new RetraitBll();
				
				RetraitBo newRetrait = new RetraitBo();
				
				newRetrait.setRue(rueRetrait);
				newRetrait.setCodePostal(codePostalRetrait);
				newRetrait.setVille(villeRetrait);
				
				nouvelleAdresse.insert(newRetrait); //enregistrement de la nouvelle adresse
				
			
				
				//permet d'insérer une nouvelle vente
				ArticleVenduBll articleVenduBll = new ArticleVenduBll();
				ArticleVenduBo nouvelArticleVendu = new ArticleVenduBo();
				nouvelArticleVendu.setNomArticle(nomArticle);
				nouvelArticleVendu.setDescription(descriptionArticle);
				nouvelArticleVendu.setDateDebutEncheres(debutEncherelocalDate);
				nouvelArticleVendu.setDateFinEncheres(finEncherelocalDate);
				nouvelArticleVendu.setMiseAPrix(miseAPrixArticle);
				nouvelArticleVendu.setPrixVente(prixVente);
				nouvelArticleVendu.setRetraitEffectue(retraitEffectue);
				nouvelArticleVendu.setUtilisateur(utilisateur);
				nouvelArticleVendu.setCategorie(CategorieNouvelArticle);
				nouvelArticleVendu.setRetrait(newRetrait);

				try {
					articleVenduBll.insertArticle(nouvelArticleVendu);
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			

	} 
		else
		{
			
			try {
				ArticleVenduBo article = new ArticleVenduBo();
				//Enregistrer d'abord le retrait si besoin	RetraitBo retrait = RetraitBll.get(no_retrait);

				//permet d'insérer une nouvelle vente
				ArticleVenduBll articleVenduBll = new ArticleVenduBll();
				ArticleVenduBo nouvelArticleVendu = new ArticleVenduBo();
				nouvelArticleVendu.setNomArticle(nomArticle);
				nouvelArticleVendu.setDescription(descriptionArticle);
				nouvelArticleVendu.setDateDebutEncheres(debutEncherelocalDate);
				nouvelArticleVendu.setDateFinEncheres(finEncherelocalDate);
				nouvelArticleVendu.setMiseAPrix(miseAPrixArticle);
				nouvelArticleVendu.setPrixVente(prixVente);
				nouvelArticleVendu.setRetraitEffectue(retraitEffectue);
				nouvelArticleVendu.setUtilisateur(utilisateur);
				nouvelArticleVendu.setCategorie(CategorieNouvelArticle);
				nouvelArticleVendu.setRetrait(retrait);

				try {
					articleVenduBll.insertArticle(nouvelArticleVendu);
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}catch (Exception e) {
				e.printStackTrace();
			} 
			
	}

	}
	
}//ne pas toucher
