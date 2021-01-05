package fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.UtilisateurBO;
import fr.eni.projetEni.dal.UtilisateurDAL;

public class UtilisateurBLL {

    public static void insert(UtilisateurBO utilisateur )throws Exception {
    {
    	if(utilisateur.getPseudo().trim().equals("")||utilisateur.getNom().trim().equals("")||utilisateur.getPrenom().trim().equals("")||
    			utilisateur.getEmail().trim().equals("")||utilisateur.getTelephone().trim().equals("")||utilisateur.getRue().trim().equals("")|| 
    			utilisateur.getCodePostal().trim().equals("")||utilisateur.getVille().trim().equals("")||utilisateur.getPassword().trim().equals(""))
    	{
    		throw new Exception("L'ensemble des informations doivent être renseignées");
    	}
    	
    	if(!utilisateur.getPseudo().matches("^[a-zA-Z0-9]*$"))
        {
            throw new Exception("Le pseudo ne peut contenir que des lettres et des chiffres");
        }
    	
    	UtilisateurDAL.insert(utilisateur);
    }
  }

    public static List<UtilisateurBO> get()throws Exception {
    {
    	return UtilisateurDAL.get();
    }
   }

    public static UtilisateurBO get(int id)throws Exception {
    {
    	return UtilisateurDAL.get(id);
    }
   }
    
    public static UtilisateurBO getPseudo(String pseudo) throws Exception {
    {
    	return UtilisateurDAL.getPseudo(pseudo);
    }
   }
    public static UtilisateurBO getCourriel(String email)throws Exception {
    {
    	return UtilisateurDAL.getCourriel(email);
    }
   }
    public static void update(UtilisateurBO utilisateur) throws Exception{
    {
    	if(utilisateur.getPseudo().trim().equals("")||utilisateur.getNom().trim().equals("")||utilisateur.getPrenom().trim().equals("")||
    			utilisateur.getEmail().trim().equals("")||utilisateur.getTelephone().trim().equals("")||utilisateur.getRue().trim().equals("")|| 
    			utilisateur.getCodePostal().trim().equals("")||utilisateur.getVille().trim().equals("")||utilisateur.getPassword().trim().equals(""))
    	{
    		throw new Exception("L'ensemble des informations doivent être renseignées");
    	}
    	
    	if(!utilisateur.getPseudo().matches("^[a-zA-Z0-9]*$"))
        {
            throw new Exception("Le pseudo ne peut contenir que des lettres et des chiffres");
        }
    	
    	UtilisateurDAL.update(utilisateur);
    }
    }
    public static void delete(int id)throws Exception {
        if(UtilisateurDAL.get(id) ==null)
        {
            throw new Exception("Il semblerait que cet utilisateur n'existe pas ou ait déjà été supprimé de la base de données.");
        }
        UtilisateurDAL.delete(id);
    }
}
