package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.utils.MonLogger;

public class UtilisateurDal {

    private static final String INSERT = "INSERT INTO utilisateurs VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM utilisateurs";
    private static final String GET_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur=?";
    private static final String GET_BY_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo=?";
    private static final String GET_BY_COURRIEL = "SELECT * FROM utilisateurs WHERE email=?";
    private static final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, codePostal=?, ville=?, password=?, credit=?, administrateur=? WHERE no_utilisateur=?";
    private static final String DELETE = "DELETE utilisateurs WHERE no_utilisateur=?";

    private static Logger logger = MonLogger.getLogger("UtilisateurDal");

    public static void insert(UtilisateurBo utilisateur )
    {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(INSERT);
            requete.setString(1, utilisateur.getPseudo());
            requete.setString(2, utilisateur.getNom());
            requete.setString(3, utilisateur.getPrenom());
            requete.setString(4, utilisateur.getEmail());
            requete.setString(5, utilisateur.getTelephone());
            requete.setString(6, utilisateur.getRue());
            requete.setString(7, utilisateur.getCodePostal());
            requete.setString(8, utilisateur.getVille());
            requete.setString(9, utilisateur.getPassword());
            requete.setInt(10, utilisateur.getCredit());
            requete.setBoolean(11, utilisateur.isAdministrateur());

            requete.executeUpdate();

        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode insert(Voiture voiture) avec voiture ="+ utilisateur +"- erreur : "+ex.getMessage());
        }
    }

    public static List<UtilisateurBo>  get()
    {
        List<UtilisateurBo> listes = new ArrayList<>();
   try(Connection cnx = ConnectionProvider.getConnection())
    {
        PreparedStatement requete = cnx.prepareStatement(GET_ALL);
        ResultSet rs =  requete.executeQuery();

        while(rs.next())
        {
            UtilisateurBo utilisateur = new UtilisateurBo();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setPseudo(rs.getString("pseudo"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setTelephone(rs.getString("telephone"));
            utilisateur.setRue(rs.getString("rue"));
            utilisateur.setCodePostal(rs.getString("codePostal"));
            utilisateur.setVille(rs.getString("ville"));
            utilisateur.setPassword(rs.getString("password"));
            utilisateur.setCredit(rs.getInt("credit"));
            utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
            listes.add(utilisateur);
        }
    }
        catch (Exception ex)
    {
        logger.severe("Erreur dans la méthode get() - erreur : "+ex.getMessage());
    }
        return listes;
}

    public static UtilisateurBo get(int id)
    {
    	UtilisateurBo resultat=null;
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
            requete.setInt(1,id);
            ResultSet rs =  requete.executeQuery();

            if(rs.next()) {
                resultat = new UtilisateurBo();
                resultat.setId(rs.getInt("id"));
                resultat.setPseudo(rs.getString("pseudo"));
                resultat.setNom(rs.getString("nom"));
                resultat.setPrenom(rs.getString("prenom"));
                resultat.setEmail(rs.getString("email"));
                resultat.setTelephone(rs.getString("telephone"));
                resultat.setRue(rs.getString("rue"));
                resultat.setCodePostal(rs.getString("codePostal"));
                resultat.setVille(rs.getString("ville"));
                resultat.setPassword(rs.getString("password"));
                resultat.setCredit(rs.getInt("credit"));
                resultat.setAdministrateur(rs.getBoolean("administrateur"));
            }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode get(int id) avec id ="+ id +"- erreur : "+ex.getMessage());
        }
        return resultat;
    }
    public static UtilisateurBo getPseudo(String pseudo)
    {
    	UtilisateurBo resultat=null;
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_PSEUDO);
            requete.setString(1,pseudo);
            ResultSet rs =  requete.executeQuery();

            if(rs.next()) {
                resultat = new UtilisateurBo();
                resultat.setId(rs.getInt("id"));
                resultat.setPseudo(rs.getString("pseudo"));
                resultat.setNom(rs.getString("nom"));
                resultat.setPrenom(rs.getString("prenom"));
                resultat.setEmail(rs.getString("email"));
                resultat.setTelephone(rs.getString("telephone"));
                resultat.setRue(rs.getString("rue"));
                resultat.setCodePostal(rs.getString("codePostal"));
                resultat.setVille(rs.getString("ville"));
                resultat.setPassword(rs.getString("password"));
                resultat.setCredit(rs.getInt("credit"));
                resultat.setAdministrateur(rs.getBoolean("administrateur"));
            }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode get(int id) avec id ="+ pseudo +"- erreur : "+ex.getMessage());
        }
        return resultat;
    }
    public static UtilisateurBo getCourriel(String email)
    {
    	UtilisateurBo resultat=null;
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_COURRIEL);
            requete.setString(1,email);
            ResultSet rs =  requete.executeQuery();

            if(rs.next()) {
                resultat = new UtilisateurBo();
                resultat.setId(rs.getInt("id"));
                resultat.setPseudo(rs.getString("pseudo"));
                resultat.setNom(rs.getString("nom"));
                resultat.setPrenom(rs.getString("prenom"));
                resultat.setEmail(rs.getString("email"));
                resultat.setTelephone(rs.getString("telephone"));
                resultat.setRue(rs.getString("rue"));
                resultat.setCodePostal(rs.getString("codePostal"));
                resultat.setVille(rs.getString("ville"));
                resultat.setPassword(rs.getString("password"));
                resultat.setCredit(rs.getInt("credit"));
                resultat.setAdministrateur(rs.getBoolean("administrateur"));
            }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode get(int id) avec id ="+ email +"- erreur : "+ex.getMessage());
        }
        return resultat;
    }
    public static void update(UtilisateurBo utilisateur)
    {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
        	PreparedStatement requete = cnx.prepareStatement(UPDATE);
            requete.setString(1, utilisateur.getPseudo());
            requete.setString(2, utilisateur.getNom());
            requete.setString(3, utilisateur.getPrenom());
            requete.setString(4, utilisateur.getEmail());
            requete.setString(5, utilisateur.getTelephone());
            requete.setString(6, utilisateur.getRue());
            requete.setString(7, utilisateur.getCodePostal());
            requete.setString(8, utilisateur.getVille());
            requete.setString(9, utilisateur.getPassword());
            requete.setInt(10, utilisateur.getCredit());
            requete.setBoolean(11, utilisateur.isAdministrateur());

            requete.executeUpdate();

        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode update(Voiture voiture) avec voiture ="+ utilisateur +"- erreur : "+ex.getMessage());
        }
    }

    public static void delete(int id)
    {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(DELETE);
            requete.setInt(1, id);
            requete.executeUpdate();
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode delete(int id) avec id ="+ id + "- erreur : "+ex.getMessage());
        }
    }
}

