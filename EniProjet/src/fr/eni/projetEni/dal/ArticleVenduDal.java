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
 
	private static final String INSERT="INSERT INTO ARTICLES_VENDUS VALUES (?,?,?,?,?,?,?,?,?,?)";
    
    private static final String GET_BY_NOM="SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
    private static final String GET_ALL="SELECT * FROM ARTICLES_VENDUS";
    private static final String GET_BY_ID="SELECT * FROM ARTICLES_VENDUS WHERE no_article= ?";
    private static final String GET_BY_ID_UTILISATEUR="SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
    
    
    /* get by idUtilisateur: select all from articlesVendu where  no_utilisateur = ? */
    /* where date_debut  */  
      /* %request.getparameter("recherche")% */ 
    
    
    private static final String UPDATE="UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, retrait_effectue=? WHERE no_article = ?";
    private static final String UPDATE_ALL="UPDATE ARTICLES_VENDUS SET no_utilisateur=1, no_retrait=1 WHERE no_utilisateur = ?";
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
            rqt.setBoolean(7, articleVendu.isRetraitEffectue());
            rqt.setInt(8, articleVendu.getUtilisateur().getId());
            rqt.setInt(9, articleVendu.getCategorie().getNoCategorie());
            rqt.setInt(10, articleVendu.getRetrait().getNoRetrait());


            rqt.executeUpdate();
            
		} catch (Exception ex) {
			ex.printStackTrace();
            //logger.severe("Erreur dans la méthode insertArticle(ArticleVenduBo articleVendu) avec article ="+ articleVendu +"- erreur : "+ex.getMessage());
		}
    } /* fin insert */
    
    
    public static ArticleVenduBo getByNom(String nom) {
    	
        ArticleVenduBo resultat = null;
        
    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
    		
    		PreparedStatement rqt = cnx.prepareStatement(GET_BY_NOM);

            rqt.setString(1, nom);

            ResultSet rs = rqt.executeQuery();

           while(rs.next())
            {
                resultat = new ArticleVenduBo();
                resultat.setNomArticle(rs.getString("nom_article"));
                resultat.setDescription(rs.getString("description"));
                resultat.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                resultat.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                resultat.setMiseAPrix(rs.getInt("prix_initial"));
                resultat.setPrixVente(rs.getInt("prix_vente"));
                resultat.setRetraitEffectue(rs.getBoolean("retrait_effectue"));

                UtilisateurBo vendeur = UtilisateurDal.getPseudo(rs.getString("Pseudo"));
                
                resultat.setUtilisateur(vendeur);
                
            }

        } 
    	catch (Exception ex ) 
    	{
            logger.severe("Erreur dans la méthode getByNom " + nom + "erreur : " + ex.getMessage());
        }
        return resultat;
    } // fin get by nom
    
    
    public static List<ArticleVenduBo> getAll() {

        List<ArticleVenduBo> liste = new ArrayList<>();
              
       
        try ( Connection cnx = ConnectionProvider.getConnection() ) 
        {
            PreparedStatement rqt = cnx.prepareStatement(GET_ALL);

            ResultSet rs = rqt.executeQuery();

            while (rs.next())
            {
                ArticleVenduBo article = new ArticleVenduBo();
                article.setNoArticle(rs.getInt("no_article"));
                article.setNomArticle(rs.getString("nom_article"));
                article.setDescription(rs.getString("description"));
                article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                article.setMiseAPrix(rs.getInt("prix_initial"));
                article.setPrixVente(rs.getInt("prix_vente"));
                article.setRetraitEffectue(rs.getBoolean("retrait_effectue"));

                UtilisateurBo utilisateur = UtilisateurDal.get(rs.getInt("no_utilisateur"));
                article.setUtilisateur(utilisateur);
                
                CategorieBo categorie = CategorieDal.get(rs.getInt("no_categorie"));
                article.setCategorie(categorie);
                
                RetraitBo retrait = RetraitDal.get(rs.getInt("no_retrait"));
                article.setRetrait(retrait);
  
                liste.add(article);
            }

        } catch (Exception ex ) {
           ex.printStackTrace();
        }
        return liste;
    }/* fin get all */


    public static ArticleVenduBo getById(int id) {
    	
        ArticleVenduBo resultat = null;
             
    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
    		
    		PreparedStatement rqt = cnx.prepareStatement(GET_BY_ID);

            rqt.setInt(1, id);

            ResultSet rs = rqt.executeQuery();

            while(rs.next())
            {
                resultat = new ArticleVenduBo();
                resultat.setNoArticle(rs.getInt("no_article"));
                resultat.setNomArticle(rs.getString("nom_article"));
                resultat.setDescription(rs.getString("description"));
                resultat.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                resultat.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                resultat.setMiseAPrix(rs.getInt("prix_initial"));
                resultat.setPrixVente(rs.getInt("prix_vente"));
                resultat.setRetraitEffectue(rs.getBoolean("retrait_effectue"));

                UtilisateurBo vendeur = UtilisateurDal.get(rs.getInt("no_utilisateur"));
                resultat.setUtilisateur(vendeur);
                
                CategorieBo categorie = CategorieDal.get(rs.getInt("no_categorie"));
                resultat.setCategorie(categorie);
                
                RetraitBo retrait = RetraitDal.get(rs.getInt("no_retrait"));
                resultat.setRetrait(retrait);


            }

        } 
    	catch (Exception ex ) 
    	{
            logger.severe("Erreur dans la méthode getById(int id) " + id + "erreur : " + ex.getMessage());
        }
        return resultat;
    } // fin get by id
    
    
   public static List<ArticleVenduBo> getByIdUtilisateur(int id) {
    	
	   List<ArticleVenduBo> listes = new ArrayList<>();

    	try ( Connection cnx = ConnectionProvider.getConnection() ) {
    		
    		PreparedStatement rqt = cnx.prepareStatement(GET_BY_ID_UTILISATEUR);

            rqt.setInt(1, id);

            ResultSet rs = rqt.executeQuery();

            while(rs.next())
            {
            	ArticleVenduBo resultat = new ArticleVenduBo();
                resultat.setNoArticle(rs.getInt("no_article"));
                resultat.setNomArticle(rs.getString("nom_article"));
                resultat.setDescription(rs.getString("description"));
                resultat.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                resultat.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                resultat.setMiseAPrix(rs.getInt("prix_initial"));
                resultat.setPrixVente(rs.getInt("prix_vente"));
                resultat.setRetraitEffectue(rs.getBoolean("retrait_effectue"));

                UtilisateurBo vendeur = UtilisateurDal.get(rs.getInt("no_utilisateur"));
                resultat.setUtilisateur(vendeur);
                
                RetraitBo retrait = RetraitDal.get(rs.getInt("no_retrait"));
                resultat.setRetrait(retrait);
                listes.add(resultat);

            }

        } 
    	catch (Exception ex ) 
    	{
            logger.severe("Erreur dans la méthode getByIdUtilisateur(int id) " + id + "erreur : " + ex.getMessage());
        }
        return listes;
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
             rqt.setBoolean(7, articleVendu.isRetraitEffectue());
       	 	 rqt.setInt(8, articleVendu.getNoArticle());

             rqt.executeUpdate();

        } catch (Exception ex ) {
           logger.severe("erreur dans la méthode updateArticle(ArticleVenduBo articleVendu) " + articleVendu + "erreur : " + ex.getMessage());
        }
    }/* fin update */

    public static void updateAll(ArticleVenduBo articleVendu) {
        try ( Connection cnx = ConnectionProvider.getConnection() ) 
        {
        	PreparedStatement rqt = cnx.prepareStatement(UPDATE_ALL);
      	 	 rqt.setInt(1, articleVendu.getUtilisateur().getId());
   
             rqt.executeUpdate();

        } catch (Exception ex ) {
           //logger.severe("erreur dans la méthode updateArticle(ArticleVenduBo articleVendu) " + articleVendu + "erreur : " + ex.getMessage());
        	ex.printStackTrace();
        }
    }/* fin update */
    public static void deleteArticle(int id) {
        try ( Connection cnx = ConnectionProvider.getConnection() ) {
            PreparedStatement rqt = cnx.prepareStatement(DELETE);

            rqt.setInt(1, id);
            rqt.executeUpdate();

        } catch (Exception ex ) {
            logger.severe("erreur dans la méthode  deleteArticle(int id) " + id + "erreur : " + ex.getMessage());
        }
    }/* fin delete */
    
    
    
}
