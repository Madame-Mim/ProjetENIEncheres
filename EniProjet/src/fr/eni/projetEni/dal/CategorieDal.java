package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEni.bo.CategorieBo;

public class CategorieDal {

	private static final String INSERT = "INSERT INTO CATEGORIES VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
    private static final String GET_ALL = "SELECT * FROM CATEGORIES";
    private static final String UPDATE = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    private static final String DELETE = "DELETE CATEGORIES WHERE no_categorie = ?";
     
    
    public static void insert(CategorieBo libelle)
    {
    	try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement requete = cnx.prepareStatement(INSERT);
			
			requete.setString(1, libelle.getLibelle());
			requete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public static CategorieBo get(int no_categorie)
    {
    	CategorieBo resultat = null;
    	Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
	    	ResultSet rs = requete.executeQuery();
	    	
	    	if(rs.next())
	    	{
	    		resultat = new CategorieBo();
	    		resultat.setNoCategorie(rs.getInt("no_categorie"));
	    		resultat.setLibelle(rs.getString("libelle"));
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return resultat;
    }
    
    public static List<CategorieBo>  get()
    {
    	List<CategorieBo>libelles = new ArrayList<CategorieBo>();
    	Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement requete = cnx.prepareStatement(GET_ALL);
			ResultSet rs = requete.executeQuery();
			
			while(rs.next())
			{
				CategorieBo libelle = new CategorieBo();
				libelle.setNoCategorie(rs.getInt("no_categorie"));
				libelle.setLibelle(rs.getString("libelle"));
				libelles.add(libelle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	return libelles;
    }
    
    public static void update(CategorieBo libelle)
    {
    	Connection cnx;
        try
        {
        	cnx = ConnectionProvider.getConnection();
            PreparedStatement requete = cnx.prepareStatement(UPDATE);
            requete.setInt(1, libelle.getNoCategorie());
            requete.setString(2,libelle.getLibelle());
            requete.executeUpdate();
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }

    public static void delete(int no_categorie)
    {
    	Connection cnx;
        try
        {
        	cnx = ConnectionProvider.getConnection();
            PreparedStatement requete = cnx.prepareStatement(DELETE);
            requete.setInt(1,no_categorie);
            requete.executeUpdate();
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }
}
