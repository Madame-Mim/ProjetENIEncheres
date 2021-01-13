package fr.eni.projetEni.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * Servlet implementation class ServletMotDePasseOublie
 */
@WebServlet("/ServletMotDePasseOublie")
public class ServletMotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletMotDePasseOublie - doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/motDePasseOublie.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletMotDePasseOublie - doPost");
		
			String adresseMail = request.getParameter("adresseMail");
			System.out.println("adresseMail :" + adresseMail);
		try {
			List<UtilisateurBo> listeUtilisateurs = UtilisateurBll.get();
			
			for(UtilisateurBo utilisateur : listeUtilisateurs)
			{
				if(utilisateur.getEmail().equals(adresseMail))
				{
					int idUtilisateur = utilisateur.getId();
					System.out.println("idUtilisateur : " + idUtilisateur);
					request.setAttribute("idUtilisateur", idUtilisateur);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Utilisateur/lienReinitialiserMDP.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}//fin de doPost

}//fin de la Servlet
