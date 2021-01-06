package EniProjet.src.fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.ArticleVenduBo;


public class ArticleVenduBll {

	public static void insertArticle(ArticleVenduBo article )throws Exception {
	    {
	    	if(article.getNomArticle().trim().equals("")||article.getDescription().trim().equals("")||article.getDateDebutEncheres().trim().equals("")||
	    			article.getDateFinEncheres().trim().equals("")||article.getMiseAPrix()<=0||article.getPrixVente()<=0||article.getUtilisateur().equals("")||article.getCategorie().equals("")||article.getRetrait().equals("") )
	    	{
	    		throw new Exception("L'ensemble des informations doit être renseigné.");
	    	}
	    	
	    	ArticleVenduDal.insertArticle(article);
	    }
	  }

	    public static List<ArticleVenduBo> getAll()throws Exception {
	    {
	    	return ArticleVenduDal.getAll();
	    }
	   }

	    public static ArticleVenduBo getById(int id)throws Exception {
	    {
	    	return ArticleVenduDal.getById(id);
	    }
	   }
	    
	    public static ArticleVenduBo getByNom(String nom) throws Exception {
	    {
	    	return ArticleVenduDal.getByNom(nom);
	    }
	   }
	    public static ArticleVenduBo getByIdUtilisateur(int id)throws Exception {
	    {
	    	return ArticleVenduDal.getByIdUtilisateur(id);
	    }
	   }
	    public static void updateArticle(ArticleVenduBo article) throws Exception{
	    {
	    	if(article.getNomArticle().trim().equals("")||article.getDescription().trim().equals("")||article.getDateDebutEncheres().trim().equals("")||
	    			article.getDateFinEncheres().trim().equals("")||article.getMiseAPrix()<=0||article.getPrixVente()<=0||article.getUtilisateur().equals("")||article.getCategorie().equals("")||article.getRetrait().equals("") )
	    	{
	    		throw new Exception("L'ensemble des informations doit être renseigné.");
	    	}
	    	
	    	ArticleVenduDal.updateArticle(article);
	    }
	    }
	    public static void deleteArticle(int id)throws Exception {
	        if(UtilisateurDal.get(id)==null)
	        {
	            throw new Exception("Cet article n'existe pas ou a déjà été supprimé de la base de données.");
	        }
	        ArticleVenduDal.deleteArticle(id);
	    }

}
