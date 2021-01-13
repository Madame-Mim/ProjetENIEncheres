package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;


/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/connexion.jsp");
	rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jspCible =null;
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		boolean checkSiEmail = Pattern.matches("\\b[\\w.%+-]+@[a-zA-Z\\d.-]+\\.[A-Za-z]{2,4}\\b", pseudo) ;
		if(checkSiEmail==true)
		{
			try 
			{
				
				UtilisateurBo utilisateurRecupere = UtilisateurBll.getCourriel(pseudo);

				if(utilisateurRecupere==null)
				{
					jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
				}
				else
				{
					String pwdBdd= utilisateurRecupere.getPassword();
					boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
					int id =utilisateurRecupere.getId();

					if(VerifPassword==false) 
					{
						jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
					}
					else
					{
						HttpSession session = request.getSession();
						session.setAttribute("session", id);
						jspCible="ServletAccueil";
					}
				}
			}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		
		else 
		{
			try 
			{
				UtilisateurBo utilisateurRecupere = UtilisateurBll.getPseudo(pseudo);
				if(utilisateurRecupere==null)
				{
					jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
				}
				else
				{
					String pwdBdd = utilisateurRecupere.getPassword();
					boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
					int id =utilisateurRecupere.getId();
					if(VerifPassword==false||pseudo==null)
					{
						jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
					}
					else
					{
						HttpSession session = request.getSession();
						session.setAttribute("session", id);
						jspCible="ServletAccueil";					
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		RequestDispatcher rd= request.getRequestDispatcher(jspCible);
		rd.forward(request, response);
	}
}
