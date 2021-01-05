package fr.eni.projetEni.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;
import fr.eni.projetEni.utils.MonLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.bll.UtilisateurBll;

class UtilisateurBllTest {

	private static Logger logger = MonLogger.getLogger("PersonneBllTest");

    @BeforeEach
    void setUp() {
        logger.info("setUp");
    }

    @AfterEach
    void tearDown() throws Exception {
        List<UtilisateurBo> utilisateurs = UtilisateurBll.get();
        for (UtilisateurBo item : utilisateurs) {
            try {

            	UtilisateurBll.delete(item.getId());
            } catch (Exception e) {
                fail("erreur : " + e.getMessage());
            }
        }
    }

    @Test
    void insert() throws Exception {
    	UtilisateurBo utilisateur = new UtilisateurBo();
    	utilisateur.setPseudo("hippieMan");
    	utilisateur.setNom("Pablo");
    	utilisateur.setPrenom("Picasso");
    	utilisateur.setEmail("Pablo.Picassiette@gmal.com");
    	utilisateur.setTelephone("0102030405");
    	utilisateur.setRue("18 rue Megalo");
    	utilisateur.setCodePostal("75002");
    	utilisateur.setVille("Paris");
    	utilisateur.setPassword("peintreDeOuf");
    	utilisateur.setCredit(120);
    	utilisateur.setAdministrateur(true);
        try {
        	UtilisateurBll.insert(utilisateur);
        } catch (Exception e) {
            fail("erreur : " + e.getMessage());
        }

        List<UtilisateurBo> utilisateurs = UtilisateurBll.get();
        UtilisateurBo dernierePersonne = utilisateurs.get(utilisateurs.size() - 1);

        assertEquals(dernierePersonne.getNom(), utilisateur.getNom());
        assertEquals(dernierePersonne.getPrenom(), utilisateur.getPrenom());

    }

    @Test
    void get() throws Exception {
    	UtilisateurBo p1 = new UtilisateurBo("monpseudo", "monnom", "monprenom", "monemail", "montelephone", "marue", "moncodepostal", "maville", "monpassword", 0, true);
    	UtilisateurBo p2 = new UtilisateurBo("tonpseudo", "tonnom", "tonprenom", "tonemail", "tontelephone", "tarue", "toncodepostal", "taville", "tonpassword", 0, false);
    	UtilisateurBo p3 = new UtilisateurBo("sonpseudo", "sonnom", "sonprenom", "sonemail", "sontelephone", "sarue", "soncodepostal", "saville", "sonpassword", 200, false);
        try {
        	UtilisateurBll.insert(p1);
        	UtilisateurBll.insert(p2);
        	UtilisateurBll.insert(p3);
        } catch (Exception e) {
            fail("erreur : " + e.getMessage());
        }


        List<UtilisateurBo> utilisateurs = UtilisateurBll.get();
        assertEquals(3, utilisateurs.size());


    }

    @Test
    void testGet() throws Exception {
    	UtilisateurBo p1 = new UtilisateurBo("monpseudo", "monnom", "monprenom", "monemail", "montelephone", "marue", "moncodepostal", "maville", "monpassword", 0, true);
    	UtilisateurBo p2 = new UtilisateurBo("tonpseudo", "tonnom", "tonprenom", "tonemail", "tontelephone", "tarue", "toncodepostal", "taville", "tonpassword", 0, false);
    	UtilisateurBo p3 = new UtilisateurBo("sonpseudo", "sonnom", "sonprenom", "sonemail", "sontelephone", "sarue", "soncodepostal", "saville", "sonpassword", 200, false);

            UtilisateurBll.insert(p1);
            UtilisateurBll.insert(p2);
            UtilisateurBll.insert(p3);

        List<UtilisateurBo> utilisateurs = UtilisateurBll.get();
        UtilisateurBo personnearecup =utilisateurs.get(2);

        UtilisateurBo personnerecuperee = UtilisateurBll.get(personnearecup.getId());
        assertEquals(personnearecup.getNom(), personnerecuperee.getNom());
        assertEquals(personnearecup.getPrenom(), personnerecuperee.getPrenom());
        assertEquals(personnearecup.getId(), personnerecuperee.getId());
    }

    @Test
    void update() throws Exception {

    	UtilisateurBo utilisateur = new UtilisateurBo();
    	utilisateur.setPseudo("hippieMan");
    	utilisateur.setNom("Pablo");
    	utilisateur.setPrenom("Picasso");
    	utilisateur.setEmail("Pablo.Picassiette@gmal.com");
    	utilisateur.setTelephone("0102030405");
    	utilisateur.setRue("18 rue Megalo");
    	utilisateur.setCodePostal("75002");
    	utilisateur.setVille("Paris");
    	utilisateur.setPassword("peintreDeOuf");
    	utilisateur.setCredit(120);
    	utilisateur.setAdministrateur(true);

        UtilisateurBll.insert(utilisateur);
        List<UtilisateurBo> utilisateurs = UtilisateurBll.get();
        UtilisateurBo personneamodifier = utilisateurs.get(0);

        personneamodifier.setNom("Picassa");
        personneamodifier.setPrenom("Paola");

        UtilisateurBll.update(personneamodifier);
        UtilisateurBo personnemodifiee = UtilisateurBll.get(personneamodifier.getId());

        assertEquals(personnemodifiee.getNom(), "Picassa");
        assertEquals(personnemodifiee.getPrenom(), "Paola");
    }


    @Test
    void delete() throws Exception {
    	UtilisateurBo utilisateur = new UtilisateurBo();
        utilisateur.setNom("Picasso");
        utilisateur.setPrenom("Pablo");

        UtilisateurBll.insert(utilisateur);
        List<UtilisateurBo> utilisateurs = UtilisateurBll.get();

        for (UtilisateurBo item : utilisateurs) {
            UtilisateurBll.delete(item.getId());
        }
        utilisateurs = UtilisateurBll.get();
        assertEquals(0, utilisateurs.size());
    }
}
