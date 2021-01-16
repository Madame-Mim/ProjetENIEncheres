package fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.dal.UtilisateurDal;

public class UtilisateurBll {

    public static void insert(UtilisateurBo utilisateur )throws Exception {
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
    	if(!utilisateur.getEmail().matches("\\b[\\w.%+-]+@[a-zA-Z\\d.-]+\\.[A-Za-z]{2,4}\\b"))
        {
            throw new Exception("L'adresse courriel n'est pas dans un format valide");
        }
    	if(UtilisateurBll.getCourriel(utilisateur.getEmail().trim().toLowerCase())!=null)
        {
            throw new Exception("L'adresse courriel existe déjà");
        }
    	if(UtilisateurBll.getPseudo(utilisateur.getPseudo().trim().toLowerCase())!=null)
        {
            throw new Exception("Le pseudo existe déjà");
        }
    	
    	UtilisateurDal.insert(utilisateur);
    }
  }

    public static List<UtilisateurBo> get()throws Exception {
        {
        	return UtilisateurDal.get();
        }
       }
    
    public static List<UtilisateurBo> getAllM1()throws Exception {
        {
        	return UtilisateurDal.getAllM1();
        }
       }

    public static UtilisateurBo get(int id)throws Exception {
    {
    	return UtilisateurDal.get(id);
    }
   }
    
    public static UtilisateurBo getPseudo(String pseudo) throws Exception {
    {
    	return UtilisateurDal.getPseudo(pseudo);
    }
   }
    public static UtilisateurBo getCourriel(String email)throws Exception {
    {
    	return UtilisateurDal.getCourriel(email);
    }
   }
    public static void update(UtilisateurBo utilisateur) throws Exception
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
    	
    	UtilisateurDal.update(utilisateur);
    }
       
    public static void delete(int id)throws Exception {
        if(UtilisateurDal.get(id) ==null)
        {
            throw new Exception("Il semblerait que cet utilisateur n'existe pas ou ait déjà été supprimé de la base de données.");
        }
        UtilisateurDal.delete(id);
    }
}
