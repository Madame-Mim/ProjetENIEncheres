package fr.eni.projetEni.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.utils.MonLogger;
import fr.eni.projetEni.bll.UtilisateurBll;

class UtilisateurBllTest {

	private static Logger logger = MonLogger.getLogger("PersonneBllTest");

    @BeforeEach
    void setUp() {
        logger.info("setUp");
    }

    @AfterEach
    void tearDown() {
        List<UtilisateurBo> utilisateur = UtilisateurBll.get();
        for (utilisateur item : utilisateurs) {
            try {

            	UtilisateurBll.delete(item.getId());
            } catch (Exception e) {
                fail("erreur : " + e.getMessage());
            }
        }
    }

    @Test
    void insert() {
    	UtilisateurBo utilisateur = new UtilisateurBo();
    	utilisateur.setNom("Picasso");
    	utilisateur.setPrenom("Pablo");
        try {
        	UtilisateurBll.insert(utilisateur);
        } catch (Exception e) {
            fail("erreur : " + e.getMessage());
        }

        List<UtilisateurBo> utilisateur = UtilisateurBll.get();
        utilisateur dernierePersonne = utilisateurs.get(utilisateurs.size() - 1);

        assertEquals(dernierePersonne.getNom(), utilisateur.getNom());
        assertEquals(dernierePersonne.getPrenom(), utilisateur.getPrenom());

    }

    @Test
    void get() {
    	utilisateur p1 = new Utilisateur("Picassa", "Paola");
    	utilisateur p2 = new Utilisateur("Picassi", "Paoli");
    	utilisateur p3 = new Utilisateur("Picasse", "Paule");
        try {
        	UtilisateurBll.insert(p1);
        	UtilisateurBll.insert(p2);
        	UtilisateurBll.insert(p3);
        } catch (Exception e) {
            fail("erreur : " + e.getMessage());
        }


        List<Utilisateur> utilisateurs = UtilisateurBll.get();
        assertEquals(3, utilisateurs.size());


    }

    @Test
    void testGet() throws Exception {
        Personne p1 = new Personne("Picassa", "Paola");
        Personne p2 = new Personne("Picassi", "Paoli");
        Personne p3 = new Personne("Picasse", "Paule");

            UtilisateurBll.insert(p1);
            UtilisateurBll.insert(p2);
            UtilisateurBll.insert(p3);

        List<Personne> utilisateurs = UtilisateurBll.get();
        Personne personnearecup =utilisateurs.get(2);

        Personne personnerecuperee = UtilisateurBll.get(personnearecup.getId());
        assertEquals(personnearecup.getNom(), personnerecuperee.getNom());
        assertEquals(personnearecup.getPrenom(), personnerecuperee.getPrenom());
        assertEquals(personnearecup.getId(), personnerecuperee.getId());
    }

    @Test
    void update() throws Exception {

        Personne utilisateur = new Personne();
        utilisateur.setNom("Picasso");
        utilisateur.setPrenom("Pablo");

        UtilisateurBll.insert(utilisateur);
        List<Personne> utilisateurs = UtilisateurBll.get();
        Personne personneamodifier = utilisateurs.get(0);

        personneamodifier.setNom("Picassa");
        personneamodifier.setPrenom("Paola");

        UtilisateurBll.update(personneamodifier);
        Personne personnemodifiee = UtilisateurBll.get(personneamodifier.getId());

        assertEquals(personnemodifiee.getNom(), "Picassa");
        assertEquals(personnemodifiee.getPrenom(), "Paola");
    }


    @Test
    void delete() throws Exception {
        Personne utilisateur = new Personne();
        utilisateur.setNom("Picasso");
        utilisateur.setPrenom("Pablo");

        UtilisateurBll.insert(utilisateur);
        List<Personne> utilisateurs = UtilisateurBll.get();

        for (Personne item : utilisateurs) {
            UtilisateurBll.delete(item.getId());
        }
        utilisateurs = UtilisateurBll.get();
        assertEquals(0, utilisateurs.size());
    }
}
