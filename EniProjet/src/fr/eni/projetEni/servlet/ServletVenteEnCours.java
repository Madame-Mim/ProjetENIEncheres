package fr.eni.projetEni.servlet;

import java.io.IOException;
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
		/*  
        if (session.getAttribute("session") == null )
        {
           
            response.sendRedirect(request.getContextPath()+"/ServletAccueil");
        }
        else
        {
        	*/
		int numArticle = Integer.parseInt(request.getParameter("idarticle"));

			try {
			    HttpSession session = request.getSession();
			     int no_utilisateur = Integer.parseInt(session.getAttribute("session").toString());
				ArticleVenduBo article = ArticleVenduBll.getById(numArticle);
				request.setAttribute("article", article);
				UtilisateurBo utilisateur = UtilisateurBll.get(no_utilisateur);
				request.setAttribute("utilisateur", utilisateur);

				
				EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
				request.setAttribute("enchere", enchere);


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
        //le forward envoi l'affichage à la jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-en-cours.jsp");
        rd.forward(request, response);
        }
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			HttpSession session = request.getSession();

			int no_utilisateur = Integer.parseInt(session.getAttribute("session").toString());

			UtilisateurBll utilisateurAmodifie = new UtilisateurBll();
			
			int no_article=Integer.parseInt(request.getParameter("idarticle"));
			
			ArticleVenduBll articleAModifie = new ArticleVenduBll();
			ArticleVenduBo article = ArticleVenduBll.getById(no_article);
			
			LocalDate date = LocalDate.now();	
			int montant= Integer.parseInt(request.getParameter("enchere"));

			EnchereBo enchere = new EnchereBo();
			
			//Remboursement points de la meilleur enchere actuelle
			EnchereBo meilleurEnchere = EnchereBll.getMaxByIdArticle(no_article);
	
			UtilisateurBo utilisateurARembourse = enchere.getNoUtilisateur();
			if(utilisateurARembourse != null)
			{
			int creditActuell = utilisateurARembourse.getCredit();
			int nouveauCreditt = creditActuell+meilleurEnchere.getMontantEnchere();
			utilisateurARembourse.setCredit(nouveauCreditt);
			
			utilisateurAmodifie.update(utilisateurARembourse);
			}
			
			//Retrait des points du nouvel enchérisseur
			UtilisateurBo utilisateur = UtilisateurBll.get(no_utilisateur);

			int creditActuel = utilisateur.getCredit();
			int nouveauCredit = creditActuel-montant;
			utilisateur.setCredit(nouveauCredit);
			utilisateurAmodifie.update(utilisateur);

		//	insertion de la nouvelle enchère
			enchere.setDateEnchere(date);
			enchere.setMontantEnchere(montant);
			enchere.setNoArticle(article);
			enchere.setNoUtilisateur(utilisateur);
			EnchereBll.insert(enchere);

			// Modification du prix de vente dans la table article
			
			article.setPrixVente(montant);

			articleAModifie.updateArticle(article);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	
        //le forward envoi l'affichage à la jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Gestion-enchere/enchere-en-cours.jsp");
        rd.forward(request, response);
		
}
}
