package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		PrintWriter out = response.getWriter();
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		boolean checkSiEmail = Pattern.matches("\\b[\\w.%+-]+@[a-zA-Z\\d.-]+\\.[A-Za-z]{2,4}\\b", pseudo) ;
		
		out.println("pseudo : "+pseudo);
		out.println("password : " +password);
		out.println("email : "+checkSiEmail);
		out.close();
		
		if(checkSiEmail==true)
		{
			try 
			{
				UtilisateurBo utilisateurRecupere = UtilisateurBll.getCourriel(pseudo);
				
				out.println("password en bdd : "+ utilisateurRecupere.getPassword());
				out.close();
				String pwdBdd= utilisateurRecupere.getPassword();
				boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
				
				if(VerifPassword==false) 
				{
					out.println("Le login et le mot de passe ne correspondent pas");
					out.close();
				}
				else
				{
					HttpSession session = request.getSession();

					RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/accueil.jsp");
					rd.forward(request, response);
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
				out.println("password en bdd"+utilisateurRecupere.getPassword());
				out.close();
				String pwdBdd = utilisateurRecupere.getPassword();
				boolean VerifPassword = Pattern.matches(password, pwdBdd) ;
				
				if(VerifPassword==false) 
				{
					out.println("Le login et le mot de passe ne correspondent pas");
					out.close();
				}
				else
				{
					HttpSession session = request.getSession();

					RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/accueil.jsp");
					rd.forward(request, response);
				}

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
