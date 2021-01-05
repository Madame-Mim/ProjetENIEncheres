package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import fr.eni.projetEni.bo.UtilisateursBO;
import fr.eni.projetEni.utils.MonLogger;

public class UtilisateursDAL {

    private static final String INSERT = "INSERT INTO utilisateurs VALUES(?,?,?)";
    private static final String GET_ALL = "SELECT * FROM utilisateurs";
    private static final String GET_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur=?";
    private static final String GET_BY_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo=?";
    private static final String GET_BY_COURRIEL = "SELECT * FROM utilisateurs WHERE courriel=?";
    private static final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, codePostal=?, ville=?, password=?, credit=?, administrateur=? WHERE no_utilisateur=?";
    private static final String DELETE = "DELETE utilisateurs WHERE no_utilisateur=?";

    private static Logger logger = MonLogger.getLogger("UtilisateursDAL");

    public static void insert(UtilisateursBO utilisateur )
    {
        try(Connection cnx = Utils.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(INSERT);
            requete.setString(1, utilisateur.getPseudo());
            requete.setString(2, utilisateur.getNom());
            requete.setObject(3, utilisateur.getPrenom());

            requete.executeUpdate();

        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode insert(Voiture voiture) avec voiture ="+ voiture +"- erreur : "+ex.getMessage());
        }
    }

    public static List<Voiture>  get()
    {
        List<Voiture> listes = new ArrayList<>();
   try(Connection cnx = Utils.getConnection())
    {
        PreparedStatement requete = cnx.prepareStatement(GET_ALL);
        ResultSet rs =  requete.executeQuery();

        while(rs.next())
        {
            Voiture voiture = new Voiture();
            voiture.setId(rs.getInt("idVoiture"));
            voiture.setNom(rs.getString("nom"));
            voiture.setPi(rs.getString("pi"));
            listes.add(voiture);
        }
    }
        catch (Exception ex)
    {
        logger.severe("Erreur dans la méthode get() - erreur : "+ex.getMessage());
    }
        return listes;
}

    public static Voiture get(int id)
    {
        Voiture resultat=null;
        try(Connection cnx = Utils.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
            requete.setInt(1,id);
            ResultSet rs =  requete.executeQuery();

            if(rs.next()) {
                resultat = new Voiture();
                resultat.setId(rs.getInt("idVoiture"));
                resultat.setNom(rs.getString("nom"));
                resultat.setPi(rs.getString("pi"));
                Personne conducteur = PersonneDAL.get(rs.getInt(id));
                resultat.setPersonne(conducteur);
            }
        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode get(int id) avec id ="+ id +"- erreur : "+ex.getMessage());
        }
        return resultat;
    }

    public static void update(Voiture voiture)
    {
        try(Connection cnx = Utils.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(UPDATE);
            requete.setString(1, voiture.getNom());
            requete.setString(2, voiture.getPi());
            requete.setObject(3, voiture.getPersonne());
            requete.setInt(4, voiture.getId());
            requete.executeUpdate();

        }
        catch (Exception ex)
        {
            logger.severe("Erreur dans la méthode update(Voiture voiture) avec voiture ="+ voiture +"- erreur : "+ex.getMessage());
        }
    }

    public static void delete(int id)
    {
        try(Connection cnx = Utils.getConnection())
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
}
