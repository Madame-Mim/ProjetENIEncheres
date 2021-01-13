package fr.eni.projetEni.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.UtilisateurBo;

/**
 * Servlet Filter implementation class FilterNoCache
 */
@WebFilter("/FilterAdmin")
public class FilterAdmin implements Filter {
    public static final String Accueil = "/Accueil";
@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
/* Récupération de la session depuis la requête */
HttpSession session = request.getSession();
int id = Integer.parseInt(session.getAttribute("session").toString());
UtilisateurBo utilisateur;
		try {
			utilisateur = UtilisateurBll.get(id);
		
		
			if(session.getAttribute("session")==null || utilisateur.isAdministrateur()==false)
			{
			    response.sendRedirect(request.getContextPath() + Accueil);
			} 
			else
			{
				
				chain.doFilter(req, res);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
