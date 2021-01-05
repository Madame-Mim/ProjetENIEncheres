package fr.eni.projetEni.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.projetEni.bo.UtilisateurBO;
import fr.eni.projetEni.dal.ConnectionProvider;
import fr.eni.projetEni.utils.MonLogger;

public class UtilisateurBLL {

	private static Logger logger = MonLogger.getLogger("UtilisateurBLL");

    public static void insert(UtilisateurBO utilisateur )throws Exception {
    {
    	if(utilisateur.getPseudo().trim().equals("")||utilisateur.getNom().trim().equals("")||utilisateur.getPrenom().trim().equals("")||
    			utilisateur.getEmail().trim().equals("")||utilisateur.getTelephone().trim().equals("")||utilisateur.getRue().trim().equals("")|| 
    			utilisateur.getCodePostal().trim().equals("")||utilisateur.getVille().trim().equals("")||utilisateur.getPassword().trim().equals(""))
    	{
    		throw new Exception("L'ensemble des informations doivent être renseignées");
    	}
    }

    public static List<UtilisateurBO>  get()
    {
    	if(!utilisateur.getPseudo().matches("^[a-zA-Z0-9]*$"))
        {
            throw new Exception("La plaque d'immatriculation doit être au format AAA111AAA");
        }
}

    public static UtilisateurBO get(int id)
    {
    	
    }
    public static UtilisateurBO getPseudo(String pseudo)
    {
    	
    }
    
    public static UtilisateurBO getCourriel(String email)
    {
    
    }
    public static void update(UtilisateurBO utilisateur)
    {
       
    }

    public static void delete(int id)
    {
       
    }
}
