package fr.eni.projetEni.dal;

public class RetraitDal {

	private static final String INSERT = "INSERT INTO RETRAITS VALUES (?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM RETRAITS WHERE no_categorie = ?";
    private static final String GET_ALL = "SELECT * FROM RETRAITS";
    private static final String UPDATE = "UPDATE RETRAITS SET nom = ?, vitesseMax = ? WHERE no_categorie = ?";
    private static final String DELETE = "DELETE RETRAITS WHERE no_categorie = ?";
}
