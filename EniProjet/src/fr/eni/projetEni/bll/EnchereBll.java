package fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.EnchereBo;
import fr.eni.projetEni.dal.EnchereDal;

public class EnchereBll
{
   

    public static void insert(EnchereBo enchere) throws Exception
    {
        if(enchere.getDateEnchere()==null||enchere.getMontantEnchere()==0)
        {
            throw new Exception("données incorrectes");
        }
      
        EnchereDal.insert(enchere);


    }
    public static EnchereBo get(int id) throws Exception
    {
        if(id<=0)
        {
            throw new Exception("Id incorrect");
        }
        return EnchereDal.get(id);

    }
    
    public static EnchereBo getByIdArticle(int noArticle) throws Exception
    {
        if(noArticle<=0)
        {
            throw new Exception("Id incorrect");
        }
        return EnchereDal.getByIdArticle(noArticle);

    }
    
    public static EnchereBo getMaxByIdArticle(int noArticle) throws Exception
    {
        if(noArticle<=0)
        {
            throw new Exception("Id incorrect");
        }
        return EnchereDal.getMaxByIdArticle(noArticle);

    }
    
    public static List<EnchereBo> get()
    {
        return EnchereDal.get();

    }
    
    public static List<EnchereBo> getbyutilisateur(int no_Utilisateur) throws Exception
    {
    	if(no_Utilisateur<=0)
        {
            throw new Exception("Vendeur incorrect");
        }
        return EnchereDal.getbyutilisateur(no_Utilisateur);

    }
    public static void update(EnchereBo enchere) throws Exception
    {
    	if(enchere.getDateEnchere()==null||enchere.getMontantEnchere()==0)
        {
            throw new Exception("données incorrectes");
        }
    	
        EnchereDal.update(enchere);
    }   
    
    public static void updateAll(EnchereBo enchere) throws Exception
    {
    	    	
        EnchereDal.updateAll(enchere);
    }   
    
    public static void delete(int id) throws Exception
    {
        if(id<=0)
        {
            throw new Exception("Id incorrect");
        }
        EnchereDal.delete(id);


    }

}
