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
	
	UtilisateurBo utilisateur = new UtilisateurBo("BigBoss", "Durand", "Jean", "Jean.Durand@gmail.com", "0102030405", "18 rue Emile Zola", "44000", "Nantes", "lemeilleur", 0, false);
	//Permet de récupérer l'id de l'utilisateur
		/*HttpSession session = request.getSession();
		session.setAttribute("session", 1);//a retirer juste là pour les test
		session.getAttribute("session");
		System.out.println(session.getAttribute("session"));
		int utilisateurAttendu = session.getAttribute("session");*/
		
	//Permet de récupérer le noCategorie	
		int noCategorie2 = Integer.parseInt(request.getParameter("categorieArticle"));
		System.out.println("nocategorie liste deroulante :" + noCategorie2);
		CategorieBo noCat = CategorieBll.get(noCategorie2);
		System.out.println("noCat : " + noCat);
		
	//Permet de récupérer l'idRetrait
			/*List<RetraitBo> listeRetraits = RetraitBll.get();
			String rueRetrait = request.getParameter("rueRetrait");
			int codePostalRetrait = Integer.parseInt(request.getParameter("codePostalRetrait"));
			String villeRetrait = request.getParameter("villeRetrait");
			
			for(RetraitBo retrait : listeRetraits)
			{
				if(retrait.getRue().equals(rueRetrait) & retrait.getCodePostal()==codePostalRetrait & retrait.getVille().equals(villeRetrait))
				{
					int noRetrait = retrait.getNoRetrait();
					System.out.println("Retrait numéro " + noRetrait);
				}
			}*/
		
	
		
		//Récupérer et le noRetrait et le noCategorie et l'insérer dans la method insert
		String rueRetrait2 = request.getParameter("rueRetrait");
		String codePostalRetrait2 = request.getParameter("codePostalRetrait");
		String villeRetrait2 = request.getParameter("villeRetrait");
		RetraitBo retraitMarche = new RetraitBo(rueRetrait2, codePostalRetrait2, villeRetrait2);
		int duretraitcherche = retraitMarche.getNoRetrait();
		RetraitBo fppfff = RetraitBll.get(duretraitcherche);
		System.out.println("enfin trouvé :" + fppfff);
		
		
		
		List<RetraitBo> listeRetraits = RetraitBll.get();
		String rueRetrait = request.getParameter("rueRetrait");
		String codePostalRetrait = request.getParameter("codePostalRetrait");
		String villeRetrait = request.getParameter("villeRetrait");
		
			for(RetraitBo retrait : listeRetraits)
			{
				if(retrait.getRue().equals(rueRetrait) & retrait.getCodePostal().equals(codePostalRetrait) & retrait.getVille().equals(villeRetrait))
				{
					int noRetrait = retrait.getNoRetrait();
					System.out.println("Retrait numéro " + noRetrait);
					RetraitBo retraitAttendu = new RetraitBo("1 rue du retrait","93000","Bobigny");
					 //retraitAttendu = RetraitBll.get(retraitAttendu.getNoRetrait());
					 System.out.println("retraitAttendu :" + retraitAttendu);
					
					ArticleVenduBll articleVenduBll = new ArticleVenduBll();
					ArticleVenduBo nouvelleVenteReussie = articleVenduBll.ajouter(nomArticle, descriptionArticle, debutEncherelocalDate, finEncherelocalDate, miseAPrixArticle, prixVente, utilisateur, noCat, retraitAttendu);
					System.out.println("samere :" + nouvelleVenteReussie);
					
				//Sert si je passe par le method insert de Emilie
					//ArticleVenduBo nouvelArticleVendu = new ArticleVenduBo(nomArticle, descriptionArticle, debutEncherelocalDate, finEncherelocalDate, miseAPrixArticle, prixVente, utilisateur, noCat, retraitAttendu);
					//ArticleVenduBo nouvelleVente = articleVenduBll.insertArticle(nouvelArticleVendu);;
				}
				
			}
		
	
	//insérer un article	
		/*ArticleVenduBll articleVenduBll = new ArticleVenduBll();
		ArticleVenduBo nouvelArticleVendu = new ArticleVenduBo(nomArticle, descriptionArticle, debutEncherelocalDate, finEncherelocalDate, miseAPrixArticle, prixVente, session.getAttribute("session"), noCategorie2, noRetrait);
		ArticleVenduBo nouvelleVente = articleVenduBll.insertArticle(nouvelArticleVendu);*/
	
				

	
}
}//ne pas toucher
