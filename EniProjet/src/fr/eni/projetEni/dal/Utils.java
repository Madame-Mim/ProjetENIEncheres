package fr.eni.projetEni.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ENCHERES","utilisateur_BDD","Pa$$w0rd");
    }
}
