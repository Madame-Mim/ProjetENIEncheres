package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import fr.eni.projetEni.bo.ArticleVenduBo;
import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.utils.MonLogger;

public class ArticleVenduDal {

    private static final String INSERT="INSERT INTO ARTICLES_VENDUS VALUES (?,?,?,?,?,?,?)";
    
    private static final String GET_BY_NOM="SELECT * FROM ARTICLES_VENDUS WHERE nom_article = ?";
    private static final String GET_ALL="SELECT * FROM ARTICLES_VENDUS";
    
    /* get by idUtilisateur: select all from articlesVendu where  no_utilisateur = ? */
    /* where date_debut  */
    
    
    private static final String UPDATE="UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE Id = ?";
    private static final String DELETE="DELETE ARTICLES_VENDUS WHERE nom_article = ?";
	
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
            rqt.setObject(7, articleVendu.getRetrait());                  /* ????????? */

            rqt.executeUpdate();
            
		} catch (Exception ex) {
            logger.severe("Erreur dans la méthode insertArticle(ArticleVenduBo articleVendu) avec article ="+ articleVendu +"- erreur : "+ex.getMessage());
		}
    } /* fin insert */
    
    
    public static ArticleVenduBo get(String nom) {
    	
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
            logger.severe("Erreur dans la méthode get(int id) " + nom + "erreur : " + ex.getMessage());
        }
        return resultat;
    } // fin get by id
    
}
