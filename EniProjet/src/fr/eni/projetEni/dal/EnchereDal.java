package fr.eni.projetEni.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.EnchereBo;
import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.utils.MonLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnchereDal {

	
	private static final String INSERT="INSERT INTO Encheres VALUES (?,?,?,?)";
    private static final String GET_BY_ID="SELECT * FROM Encheres WHERE no_enchere=?";
    private static final String GET_BY_UTILISATEUR="SELECT * FROM Encheres WHERE no_utilisateur=?";
    private static final String GET_BY_IDARTICLE="SELECT * FROM Encheres WHERE no_article=?";
    private static final String GET_MAX_BY_IDARTICLE="SELECT * FROM Encheres WHERE montant_enchere =(SELECT MAX(montant_enchere) FROM Encheres WHERE no_article=?)";
    private static final String GET_ALL="SELECT * FROM Encheres";
    private static final String UPDATE="UPDATE Encheres SET date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? WHERE no_enchere=?";
    private static final String DELETE="DELETE Encheres WHERE no_enchere=?";
    private static Logger logger = MonLogger.getLogger("EnchereDAL");
    
    
    public static void insert(EnchereBo enchere)
    {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(INSERT);
            requete.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
            requete.setInt(2, enchere.getMontantEnchere());
            requete.setObject(3, enchere.getNoArticle().getNoArticle());
            requete.setObject(4, enchere.getNoUtilisateur().getId());

            requete.executeUpdate();

        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode insert(EnchereBo enchere) avec enchere ="+ enchere +"- erreur : "+ex.getMessage());
        }
    }
    
    public static EnchereBo get(int noEnchere) {
    	EnchereBo resultat=null;

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
            requete.setInt(1,noEnchere);
            ResultSet rs = requete.executeQuery();

            if(rs.next()) {
               resultat = new EnchereBo();
               resultat.setNoEnchere(rs.getInt("no_enchere"));
               resultat.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
               resultat.setMontantEnchere(rs.getInt("montant_enchere"));
               UtilisateurBo utilisateur = UtilisateurDal.get(rs.getInt("id"));
               resultat.setNoUtilisateur(utilisateur);
               ArticleVenduBo article = ArticleVenduDal.getById(rs.getInt("id"));
               resultat.setNoArticle(article);             
                                         		
                           }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode get(int id) avec id ="+ noEnchere +"- erreur : "+ex.getMessage());
        }
        return resultat;
	}
    
    public static EnchereBo getByIdArticle(int noArticle)
    {
    	EnchereBo resultat=null;
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_IDARTICLE);
            requete.setInt(1,noArticle);
            ResultSet rs =  requete.executeQuery();

            while(rs.next()) {
                resultat = new EnchereBo();
            	 resultat.setNoEnchere(rs.getInt("no_enchere"));
                 resultat.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
                 resultat.setMontantEnchere(rs.getInt("montant_enchere"));
                 
                 ArticleVenduBo article = ArticleVenduDal.getById(rs.getInt("no_article"));
                 resultat.setNoArticle(article);      
                 UtilisateurBo utilisateur = UtilisateurDal.get(rs.getInt("no_utilisateur"));
                 resultat.setNoUtilisateur(utilisateur);
            }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode getByIdArticle(int noArticle) avec noArticle ="+ noArticle +"- erreur : "+ex.getMessage());
        }
        return resultat;
	}
    
    public static EnchereBo getMaxByIdArticle(int noArticle)
    {
    	EnchereBo resultat=null;
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_MAX_BY_IDARTICLE);
            requete.setInt(1,noArticle);
            ResultSet rs =  requete.executeQuery();

            while(rs.next()) {
                 resultat = new EnchereBo();
            	 resultat.setNoEnchere(rs.getInt("no_enchere"));
                 resultat.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
                 resultat.setMontantEnchere(rs.getInt("montant_enchere"));
                 
                 ArticleVenduBo article = ArticleVenduDal.getById(rs.getInt("no_article"));
                 resultat.setNoArticle(article);      
                 UtilisateurBo utilisateur = UtilisateurDal.get(rs.getInt("no_utilisateur"));
                 resultat.setNoUtilisateur(utilisateur);
            }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode getMaxByIdArticle(int noArticle) avec noArticle ="+ noArticle +"- erreur : "+ex.getMessage());
        }
        return resultat;
	}
    
    public static List<EnchereBo> getbyutilisateur(int no_Utilisateur)
    {
        List<EnchereBo> listes = new ArrayList<>();
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_UTILISATEUR);
            requete.setInt(1,no_Utilisateur);
            ResultSet rs = requete.executeQuery();

            while(rs.next())
            {
            	EnchereBo enchere= new EnchereBo();
            	enchere.setNoEnchere(rs.getInt("id"));
            	enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
            	enchere.setMontantEnchere(rs.getInt("montant_enchere"));
            	UtilisateurBo utilisateur = UtilisateurDal.get(rs.getInt("no_utilisateur"));
                enchere.setNoUtilisateur(utilisateur);
                ArticleVenduBo article = ArticleVenduDal.getById(rs.getInt("id"));
                enchere.setNoArticle(article);  
            	listes.add(enchere);
            	
            }
        }

        catch (Exception ex)
        {
            logger.severe("Erreur dans la methode get() - erreur :" + ex.getMessage());
        }
        return listes;
    }
    
    public static List<EnchereBo> get()
    {
        List<EnchereBo> listes = new ArrayList<>();
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_ALL);
            ResultSet rs = requete.executeQuery();

            while(rs.next())
            {
            	EnchereBo enchere= new EnchereBo();
            	enchere.setNoEnchere(rs.getInt("id"));
            	enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
            	enchere.setMontantEnchere(rs.getInt("montant_enchere"));
            	UtilisateurBo utilisateur = UtilisateurDal.get(rs.getInt("id"));
                enchere.setNoUtilisateur(utilisateur);
                ArticleVenduBo article = ArticleVenduDal.getById(rs.getInt("id"));
                enchere.setNoArticle(article);  
            	listes.add(enchere);
            	
            }
        }

        catch (Exception ex)
        {
            logger.severe("Erreur dans la methode get() - erreur :" + ex.getMessage());
        }
        return listes;
    }
    
    public static void update(EnchereBo enchere)
    {
    	 try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(UPDATE);
            requete.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
            requete.setInt(2,enchere.getMontantEnchere());
            requete.setInt(3,enchere.getNoArticle().getNoArticle());
            requete.setInt(4,enchere.getNoUtilisateur().getId());

            requete.executeUpdate();
        }
        catch(Exception ex)
        {
            logger.severe("Erreur dans la methode update(EnchereBo enchere) avec enchere =" + enchere + " erreur :" + ex.getMessage());
        }
    }
       
    public static void delete(int id)
    {
    	try(Connection cnx = ConnectionProvider.getConnection())
        {

            PreparedStatement requete = cnx.prepareStatement(DELETE);
            requete.setInt(1,id);
            requete.executeUpdate();

        }
        catch(Exception ex)
        {
            logger.severe("Erreur dans la methode delete(int id) avec enchere =" + id + " erreur :" + ex.getMessage());


        }
    
    
    }
}
