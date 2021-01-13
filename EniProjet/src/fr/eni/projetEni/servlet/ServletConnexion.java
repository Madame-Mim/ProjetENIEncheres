package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		
				Cookie[] cookies = request.getCookies();
				 if ( cookies != null ) 
				 {
			         for ( Cookie cookie : cookies ) 
			         {
			             request.setAttribute(cookie.getName(),cookie.getValue());
			         }
				}
		 

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
					request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas");
					jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
				}
				else
				{
					String pwdBdd= utilisateurRecupere.getPassword();
					boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
					int id =utilisateurRecupere.getId();

					if(VerifPassword==false) 
					{
						request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas");
						jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
					}
					else
					{
						HttpSession session = request.getSession();
						session.setAttribute("session", id);
						jspCible="/Accueil";
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
					request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas");
					jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
				}
				else
				{
					String pwdBdd = utilisateurRecupere.getPassword();
					boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
					int id =utilisateurRecupere.getId();
					if(VerifPassword==false||pseudo==null)
					{
						request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas");
						jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
					}
					else
					{
						HttpSession session = request.getSession();
						session.setAttribute("session", id);
						jspCible="/Accueil";					
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		//modif à partir de là
		if(request.getParameter("souvenir")!=null)
		{
		int checkbox = Integer.parseInt(request.getParameter("souvenir"));	
		
				Cookie[] cookies = request.getCookies();
				Cookie cookiePseudo = new Cookie("Cpseudo", pseudo);
				cookiePseudo.setMaxAge(60*60*24*30);
				response.addCookie(cookiePseudo);
				
				Cookie cookiePassword = new Cookie("Cpassword", password);
				cookiePassword.setMaxAge(60*60*24*30);
				response.addCookie(cookiePassword);	
		}
		RequestDispatcher rd= request.getRequestDispatcher(jspCible);
		rd.forward(request, response);
		
	}//fin du dopost
}
