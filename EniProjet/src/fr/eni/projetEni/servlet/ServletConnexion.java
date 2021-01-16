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
		
				Cookie[] cookies = request.getCookies(); //Récupération des cookies existants
				 if ( cookies != null ) 
				 {
			         for ( Cookie cookie : cookies ) 
			         {
			             request.setAttribute(cookie.getName(),cookie.getValue()); //Mis en attribut des cookies pour affichage en jsp
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
		boolean checkSiEmail = Pattern.matches("\\b[\\w.%+-]+@[a-zA-Z\\d.-]+\\.[A-Za-z]{2,4}\\b", pseudo) ; //Vérification du format email de l'identifiant
		
		if(checkSiEmail==true) //si c'est un email alors : 
		{
			try 
			{
				
				UtilisateurBo utilisateurRecupere = UtilisateurBll.getCourriel(pseudo); //Je récupère l'utilisateur lié à ce courriel

				if(utilisateurRecupere==null ||utilisateurRecupere.getId()==1) //Si je n'ai rien récupéré ou si cela renvoie à l'id 1 (pré rempli en BDD comme "Pseudo supprimé") alors :
				{
					request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas"); //Attribution du message d'erreur pour affichage en jsp
					jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp"; //lien vers lequel sera renvoyé l'utilisateur
				}
				else // si un utilisateur a été récupéré alors :
				{
					String pwdBdd= utilisateurRecupere.getPassword(); //Je récupère le mot de passe de cet utilisateur
					boolean VerifPassword = Pattern.matches(password, pwdBdd) ; // Vérification de la correspondance entre les mots de passe
					int id =utilisateurRecupere.getId(); //Je récupère l'id de l'utilisateur

					if(VerifPassword==false) // Si les mots de passe ne correspondent pas
					{
						request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas"); //Attribution du message d'erreur pour affichage en jsp
						jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp"; //lien vers lequel sera renvoyé l'utilisateur
					}
					else
					{
						HttpSession session = request.getSession(); //création d'une session
						session.setAttribute("session", id); //envoie de l'id en session sous l'attribut "session"
						jspCible="/Accueil"; //lien vers lequel sera renvoyé l'utilisateur
					}
				}
			}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		
		else //si l'utilisateur n'a pas rentré une adresse email
		{
			try 
			{
				UtilisateurBo utilisateurRecupere = UtilisateurBll.getPseudo(pseudo); // Je récupère l'utilisateur par son pseudo
				if(utilisateurRecupere==null ||utilisateurRecupere.getId()==1) //Si je n'ai rien récupéré ou si cela renvoie à l'id 1 (pré rempli en BDD comme "Pseudo supprimé") alors :
				{
					request.setAttribute("erreur", "L'identifiant et le mot de passe ne correspondent pas");
					jspCible="/WEB-INF/Encheres/Utilisateur/connexion.jsp";
				}
				else
				{
					String pwdBdd = utilisateurRecupere.getPassword();
					boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
					int id =utilisateurRecupere.getId();
					
					if(VerifPassword==false||pseudo==null) //si le mot de passe est faux ou que le pseudo n'existe pas alors
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

		if(request.getParameter("souvenir")!=null) //si le paramètre a bien été envoyé (si "Se souvenir de moi" a été coché)
		{	
				Cookie[] cookies = request.getCookies(); //Ouverture du tableau des cookies
				Cookie cookiePseudo = new Cookie("Cpseudo", pseudo); //création d'un cookie pour stocker le pseudo
				cookiePseudo.setMaxAge(60*60*24*30); //attribution d'un délai d'utilisation en seconde (ici 30 jours de 24 heures de 60 minutes de 60 secondes), soit 1 mois
				response.addCookie(cookiePseudo); //ajout du cookie dans le tableau
				
				Cookie cookiePassword = new Cookie("Cpassword", password); //création d'un cookie pour stocker le mot de passe
				cookiePassword.setMaxAge(60*60*24*30);
				response.addCookie(cookiePassword);	
		}
		
				RequestDispatcher rd= request.getRequestDispatcher(jspCible); //renvoie vers le lien attribué précédement
				rd.forward(request, response);
		
	}//fin du dopost
}
