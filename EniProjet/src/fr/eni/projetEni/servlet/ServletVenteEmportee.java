package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ServletVenteEmportee
 */
@WebServlet("/VenteTerminee")
public class ServletVenteEmportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArticleVenduBo article = ArticleVenduBll.getById(7);
			request.setAttribute("article", article);
			
			EnchereBo enchere = EnchereBll.getByIdArticle(article.getNoArticle());
			request.setAttribute("enchere", enchere);

		} catch (Exception e) {
			e.printStackTrace();
		}
        
    //le forward envoi l'affichage à la jsp
    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Gestion-enchere/Vente-emportee.jsp");
    rd.forward(request, response);
    }
//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	//	int id = Integer.parseInt(session.getAttribute("session").toString());
		try {
			UtilisateurBo utilisateurACrediter = UtilisateurBll.get(4);
			System.out.println(utilisateurACrediter);

			int montant = Integer.parseInt(request.getParameter("credit"));
			System.out.println("montant : "+montant);

			int creditActuel = utilisateurACrediter.getCredit();
			System.out.println("credit actuel : "+creditActuel);

			int nouveauCredit = creditActuel+montant;
			System.out.println("nouveau credit : "+nouveauCredit);

			utilisateurACrediter.setCredit(montant);
			System.out.println(utilisateurACrediter);
			
			UtilisateurBll.update(utilisateurACrediter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	//	UtilisateurBll.update(utilisateurACrediter);
	}

}
