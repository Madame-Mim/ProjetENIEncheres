package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.RetraitBo;
import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.utils.MonLogger;

public class ArticleVenduDal {

    private static final String INSERT="INSERT INTO ARTICLES_VENDUS VALUES (?,?,?,?,?,?,?)";
    
    private static final String GET_BY_NOM="SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
    private static final String GET_ALL="SELECT * FROM ARTICLES_VENDUS";
    private static final String GET_BY_ID="SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
    private static final String GET_BY_ID_UTILISATEUR="SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
    
    
    /* get by idUtilisateur: select all from articlesVendu where  no_utilisateur = ? */
    /* where date_debut  */  
      /* %request.getparameter("recherche")% */ 
    
    
    private static final String UPDATE="UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE no_article = ?";
    private static final String DELETE="DELETE ARTICLES_VENDUS WHERE no_article = ?";
	
    private static Logger logger = MonLogger.getLogger("ArticleVenduDal");

    
    public static void insertArticle(ArticleVenduBo articleVendu) {
    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
            PreparedStatement rqt = cnx.prepareStatement(INSERT);

            rqt.setString(1, articleVendu.getNomArticle());
            rqt.setString(2, articleVendu.getDescription());
            rqt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
            rqt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
            rqt.setInt(5, articleVendu.getMiseAPrix());
            rqt.setInt(6, articleVendu.getPrixVente());
            rqt.setObject(7, articleVendu.getRetrait());

            rqt.executeUpdate();
            
		} catch (Exception ex) {
            logger.severe("Erreur dans la méthode insertArticle(ArticleVenduBo articleVendu) avec article ="+ articleVendu +"- erreur : "+ex.getMessage());
		}
    } /* fin insert */
    
    
    public static ArticleVenduBo getByNom(String nom) {
    	
        ArticleVenduBo resultat = null;
        
        UtilisateurDal utilisateur = new UtilisateurDal();


    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
    		
    		PreparedStatement rqt = cnx.prepareStatement(GET_BY_NOM);

            rqt.setString(1, nom);

            ResultSet rs = rqt.executeQuery();

            if (rs.next())
            {
                resultat = new ArticleVenduBo();
                resultat.setNomArticle(rs.getNString("nom_article"));
                resultat.setDescription(rs.getNString("description"));
                resultat.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                resultat.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                resultat.setMiseAPrix(rs.getInt("prix_initial"));
                resultat.setPrixVente(rs.getInt("prix_vente"));

                UtilisateurBo vendeur = utilisateur.getPseudo("pseudo");
                
                resultat.setUtilisateur(vendeur);
                
            }

        } 
    	catch (Exception ex ) 
    	{
            logger.severe("Erreur dans la méthode getByNom " + nom + "erreur : " + ex.getMessage());
        }
        return resultat;
    } // fin get by id
    
    
    public static List<ArticleVenduBo> getAll() {

        List<ArticleVenduBo> liste = new ArrayList<>();
        
        
        UtilisateurBo utilisateur = new UtilisateurBo();
        
        CategorieBo categorie = new CategorieBo();
        
        RetraitBo retrait = new RetraitBo();

        try ( Connection cnx = ConnectionProvider.getConnection() ) 
        {
            PreparedStatement rqt = cnx.prepareStatement(GET_ALL);

            ResultSet rs = rqt.executeQuery();

            while (rs.next())
            {
                ArticleVenduBo article = new ArticleVenduBo();
                article.setNoArticle(rs.getInt("no_article"));
                article.setNomArticle(rs.getNString("nom_article"));
                article.setDescription(rs.getNString("description"));
                article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                article.setMiseAPrix(rs.getInt("prix_initial"));
                article.setPrixVente(rs.getInt("prix_vente"));
                
                article.setUtilisateur(rs.getInt(utilisateur.getId()));
                
                article.setCategorie(rs.getNString(categorie.getLibelle()));
                
                article.setRetrait(rs.getObject(retrait.getLieuRetrait());

                
                liste.add(article);
            }

        } catch (Exception ex ) {
            logger.severe("erreur dans la méthode getAll " + "erreur : " + ex.getMessage());
        }
        return liste;
    }/* fin get all */


    public static ArticleVenduBo getById(int id) {
    	
        ArticleVenduBo resultat = null;
        
        UtilisateurDal utilisateur = new UtilisateurDal();
        
        RetraitDal retrait = new RetraitDal();


    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
    		
    		PreparedStatement rqt = cnx.prepareStatement(GET_BY_ID);

            rqt.setInt(1, id);

            ResultSet rs = rqt.executeQuery();

            if (rs.next())
            {
                resultat = new ArticleVenduBo();
                resultat.setNomArticle(rs.getNString("nom_article"));
                resultat.setDescription(rs.getNString("description"));
                resultat.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                resultat.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                resultat.setMiseAPrix(rs.getInt("prix_initial"));
                resultat.setPrixVente(rs.getInt("prix_vente"));

                UtilisateurBo vendeur = utilisateur.getPseudo("pseudo");
                
                resultat.setUtilisateur(vendeur);
                
                RetraitBo ret = retrait.getClass();
                
            }

        } 
    	catch (Exception ex ) 
    	{
            logger.severe("Erreur dans la méthode getById(int id) " + nom + "erreur : " + ex.getMessage());
        }
        return resultat;
    } // fin get by id
    
    
   public static ArticleVenduBo getByIdUtilisateur(int id) {
    	
        ArticleVenduBo resultat = null;
        
        UtilisateurDal utilisateur = new UtilisateurDal();
        
        RetraitDal retrait = new RetraitDal();


    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
    		
    		PreparedStatement rqt = cnx.prepareStatement(GET_BY_ID);

            rqt.setInt(1, utilisateur.get(id));

            ResultSet rs = rqt.executeQuery();

            if (rs.next())
            {
                resultat = new ArticleVenduBo();
                resultat.setNomArticle(rs.getNString("nom_article"));
                resultat.setDescription(rs.getNString("description"));
                resultat.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                resultat.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                resultat.setMiseAPrix(rs.getInt("prix_initial"));
                resultat.setPrixVente(rs.getInt("prix_vente"));

                UtilisateurBo vendeur = utilisateur.getPseudo("pseudo");
                
                resultat.setUtilisateur(vendeur);
                
                RetraitBo ret = retrait.getClass();
                
                resultat.setRetrait(retrait);
                
            }

        } 
    	catch (Exception ex ) 
    	{
            logger.severe("Erreur dans la méthode getById(int id) " + nom + "erreur : " + ex.getMessage());
        }
        return resultat;
    } // fin get by idUtilisateur
    
    
    public static void updateArticle(ArticleVenduBo articleVendu) {
        try ( Connection cnx = ConnectionProvider.getConnection() ) 
        {
        	PreparedStatement rqt = cnx.prepareStatement(UPDATE);

        	 rqt.setString(1, articleVendu.getNomArticle());
             rqt.setString(2, articleVendu.getDescription());
             rqt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
             rqt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
             rqt.setInt(5, articleVendu.getMiseAPrix());
             rqt.setInt(6, articleVendu.getPrixVente());
            rqt.executeUpdate();

        } catch (Exception ex ) {
            logger.severe("erreur dans la méthode updateArticle(ArticleVenduBo articleVendu) " + articleVendu + "erreur : " + ex.getMessage());
        }
    }


    public static void deleteArticle(int id) {
        try ( Connection cnx = ConnectionProvider.getConnection() ) {
            PreparedStatement rqt = cnx.prepareStatement(DELETE);

            rqt.setInt(1, id);
            rqt.executeUpdate();

        } catch (Exception ex ) {
            logger.severe("erreur dans la méthode  deleteArticle(int id) " + id + "erreur : " + ex.getMessage());
        }
    }
    
    
    
}
