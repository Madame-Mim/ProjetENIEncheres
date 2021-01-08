package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.RetraitBo;

public class RetraitDal {

	private static final String INSERT = "INSERT INTO RETRAITS VALUES (?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM RETRAITS WHERE no_retrait = ?";
    private static final String GET_ALL = "SELECT * FROM RETRAITS";
    private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ? , ville = ? WHERE no_retrait = ?";
    private static final String DELETE = "DELETE RETRAITS WHERE no_retrait = ?";
    
    
    public static void insert(RetraitBo AdresseRetrait)
    {
    	try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement requete = cnx.prepareStatement(INSERT);
			
			requete.setString(1, AdresseRetrait.getRue());
			requete.setString(2, AdresseRetrait.getCodePostal());
			requete.setString(3, AdresseRetrait.getVille());
			requete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public static RetraitBo get(int noRetrait)
    {
    	RetraitBo resultat = null;
    	Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
			requete.setInt(1,noRetrait);
	    	ResultSet rs = requete.executeQuery();
	    	
	    	if(rs.next())
	    	{
	    		resultat = new RetraitBo();
	    		resultat.setNoRetrait(rs.getInt("no_retrait"));
	    		resultat.setRue(rs.getString("rue"));
	    		resultat.setCodePostal(rs.getString("code_postal"));
	    		resultat.setVille(rs.getString("ville"));
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return resultat;
    }
    
    
    public static List<RetraitBo>  get()
    {
    	List<RetraitBo>listeRetraits = new ArrayList<RetraitBo>();
    	Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement requete = cnx.prepareStatement(GET_ALL);
			ResultSet rs = requete.executeQuery();
			
			while(rs.next())
			{
				RetraitBo AdresseRetrait = new RetraitBo();
				AdresseRetrait.setNoRetrait(rs.getInt("no_retrait"));
				AdresseRetrait.setRue(rs.getString("rue"));
				AdresseRetrait.setCodePostal(rs.getString("code_postal"));
				AdresseRetrait.setVille(rs.getString("ville"));
				listeRetraits.add(AdresseRetrait);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	return listeRetraits;
    }
    
    public static void update(RetraitBo AdresseRetrait)
    {
    	Connection cnx;
        try
        {
        	cnx = ConnectionProvider.getConnection();
            PreparedStatement requete = cnx.prepareStatement(UPDATE);
            requete.setInt(1, AdresseRetrait.getNoRetrait());
            requete.setString(2,AdresseRetrait.getRue());
            requete.setString(3, AdresseRetrait.getCodePostal());
            requete.setString(4, AdresseRetrait.getVille());
            requete.executeUpdate();
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }

    public static void delete(int no_retrait)
    {
    	Connection cnx;
        try
        {
        	cnx = ConnectionProvider.getConnection();
            PreparedStatement requete = cnx.prepareStatement(DELETE);
            requete.setInt(1,no_retrait);
            requete.executeUpdate();
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }
}
