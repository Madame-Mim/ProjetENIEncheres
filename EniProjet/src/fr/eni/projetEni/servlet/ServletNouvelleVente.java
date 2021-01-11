package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import fr.eni.projetEni.dal.ArticleVenduDal;
import fr.eni.projetEni.dal.RetraitDal;
 
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
		//System.out.println("nomArticle :" + nomArticle);
		
		String descriptionArticle = request.getParameter("descriptionArticle");
		//System.out.println("descriptionArticle :" + descriptionArticle);
		
	//Permet de récupérer la date de début d'enchère en String et de la convertir en LocalDate
		String debutEnchere = request.getParameter("debutEnchere");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate debutEncherelocalDate = LocalDate.parse(debutEnchere,formatter);
		//System.out.println("Début enchère : " + debutEncherelocalDate);
		
		
	//Permet de récupérer la date de fin d'enchère en String et de la convertir en LocalDate
		String finEnchere = request.getParameter("finEnchere");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate finEncherelocalDate = LocalDate.parse(finEnchere,formatter);
		//System.out.println("fin enchère : " + finEncherelocalDate);
		
				
		int miseAPrixArticle = Integer.parseInt(request.getParameter("miseAPrixArticle"));
		//System.out.println("miseAPrixArticle :" + miseAPrixArticle);
		
		int prixVente = miseAPrixArticle;
		//System.out.println("prixVente :" + miseAPrixArticle);
	
	//Permet de récupérer l'id de l'utilisateur
		HttpSession session = request.getSession();
		session.setAttribute("session", 1);//a retirer juste là pour les test
		int idUtilisateur = Integer.parseInt(session.getAttribute("session").toString());
		//System.out.println("id utilisateur : " + session.getAttribute("session"));
		UtilisateurBo utilisateur = new UtilisateurBo();
		try {
			utilisateur = UtilisateurBll.get(idUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//Permet de récupérer le noCategorie	
		int noCategorie2 = Integer.parseInt(request.getParameter("categorieArticle"));
		//System.out.println("nocategorie liste deroulante :" + noCategorie2);
		CategorieBo noCat = CategorieBll.get(noCategorie2);
		//System.out.println("noCat : " + noCat);
		
		
		boolean retraitEffectue = false;
		
	
		
		//Récupérer et le noRetrait et le noCategorie et l'insérer dans la method insert
		
		
		List<RetraitBo> listeRetraits = RetraitBll.get();
		String rueRetrait = request.getParameter("rueRetrait");
		String codePostalRetrait = request.getParameter("codePostalRetrait");
		String villeRetrait = request.getParameter("villeRetrait");
		
		
		/*for(RetraitBo retrait : listeRetraits)
		{
			
			if((retrait.getRue().equals(rueRetrait) & retrait.getCodePostal().equals(codePostalRetrait) & retrait.getVille().equals(villeRetrait)))
			{
				int noRetrait = retrait.getNoRetrait();
				System.out.println("Retrait numéro " + noRetrait);
				RetraitBo RetraitParDefaut = RetraitBll.get(noRetrait);
				System.out.println("RetraitParDefaut " + RetraitParDefaut);
				
				//permet d'insérer une nouvelle vente
				ArticleVenduBll articleVenduBll = new ArticleVenduBll();
				ArticleVenduBo nouvelArticleVendu = new ArticleVenduBo();
				nouvelArticleVendu.setNomArticle(nomArticle);
				nouvelArticleVendu.setDescription(descriptionArticle);
				nouvelArticleVendu.setDateDebutEncheres(debutEncherelocalDate);
				nouvelArticleVendu.setDateFinEncheres(debutEncherelocalDate);
				nouvelArticleVendu.setMiseAPrix(miseAPrixArticle);
				nouvelArticleVendu.setPrixVente(prixVente);
				nouvelArticleVendu.setRetraitEffectue(retraitEffectue);
				nouvelArticleVendu.setUtilisateur(utilisateur);
				nouvelArticleVendu.setCategorie(noCat);
				nouvelArticleVendu.setRetrait(RetraitParDefaut);
				System.out.println(nouvelArticleVendu);

				try {
					articleVenduBll.insertArticle(nouvelArticleVendu);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				
			}else {
				System.out.println("pas bon");
			}
			
		
	
			
		
		if(!(retrait.getRue().equals(rueRetrait) & retrait.getCodePostal().equals(codePostalRetrait) & retrait.getVille().equals(villeRetrait)))
		{
		//crée un nouveau retrait
				RetraitBo nouvelleAdresseRetrait = new RetraitBo();
				nouvelleAdresseRetrait.setRue(rueRetrait);
				nouvelleAdresseRetrait.setCodePostal(codePostalRetrait);
				nouvelleAdresseRetrait.setVille(villeRetrait);
				System.out.println("nouvelleAdresseRetrait : " + nouvelleAdresseRetrait);
				try {
					RetraitBll.insert(nouvelleAdresseRetrait);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
			
				
		}  
			*/
		
		
		RetraitBo retraitEssai1000= new RetraitBo();
		retraitEssai1000.setRue(rueRetrait);
		retraitEssai1000.setCodePostal(codePostalRetrait);
		retraitEssai1000.setVille(villeRetrait);
		try {
			RetraitBll.getRetrait(retraitEssai1000);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
			
			
				
				
			
		
			
			
			
			
		
		
	
	
				

	
	}
}//ne pas toucher
