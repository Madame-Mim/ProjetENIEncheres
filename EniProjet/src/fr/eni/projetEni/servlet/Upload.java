package fr.eni.projetEni.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author edavi2020
 * Servlet implementation class Upload
 */
//@WebServlet("/Upload")
public class Upload extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 public static final int TAILLE_TAMPON = 10240;
	 public static final String CHEMIN_FICHIERS = "C:\\Users\\nicol.DESKTOP-CD7QT37\\git\\projetENI\\EniProjet\\WebContent\\Image\\"; 
	 //Si vers serveur :
	 //public static final String CHEMIN_FICHIERS = "C:\\apache-tomcat-8.5.60\\webapps\\uploaded\\";    
	  
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	        this.getServletContext().getRequestDispatcher("/WEB-INF/Encheres/upload.jsp").forward(request, response);
	    }

	 public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		 
	        // On récupère le champ description 
	        String description = request.getParameter("description");
	        request.setAttribute("description", description );

	        // On récupère le champ du fichier
	        Part part = request.getPart("fichier");
	            
	        // On vérifie qu'on a bien reçu un fichier
	        String nomFichier = "1.jpg";

	        // Si on a bien un fichier
	        if (nomFichier != null && !nomFichier.isEmpty()) {
	            String nomChamp = part.getName();

	            // On écrit définitivement le fichier dans le dossier
	            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

	            request.setAttribute(nomChamp, nomFichier);
	            
	            //On attribue le fichier à la requête
	            request.setAttribute("nomFichier", nomFichier);
	            
	        }
	        
	        this.getServletContext().getRequestDispatcher("/WEB-INF/Encheres/upload.jsp").forward(request, response);
	    }

	 	//méthode pour écrire le fichier dans le dossier
	    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
	        BufferedInputStream entree = null;
	        BufferedOutputStream sortie = null;
	        try {
	            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
	            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

	            byte[] tampon = new byte[TAILLE_TAMPON];
	            int longueur;
	            while ((longueur = entree.read(tampon)) > 0) {
	                sortie.write(tampon, 0, longueur);
	            }
	        } finally {
	            try {
	                sortie.close();
	            } catch (IOException ignore) { 
	            }
	            try {
	                entree.close();
	            } catch (IOException ignore) {
	            }
	        }
	    }
	    
	    //méthode pour vérifier qu'on reçoit un fichier
	    private static String getNomFichier( Part part ) {
	        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	            if ( contentDisposition.trim().startsWith( "filename" ) ) {
	                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	            }
	        }
	        return null;
	    }   
	}

