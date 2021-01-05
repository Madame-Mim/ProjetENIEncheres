package fr.eni.projetEni.test;

import org.junit.Test;

public class CategorieBllTest {

	@Test
    void insert() throws Exception {

        //PREMIERE POSSIBILITE : comparaison de la taille
        /****************************************************/

        // Taille initiale
       int sizeBeforeInsertion = conducteurDAO.selectAll().size();

        //Ajout d'un conducteur et récupération de son id
        Conducteur conducteur = new Conducteur("GOYER", "Germain");
        Conducteur recuperationInsertion = conducteurDAO.insert(conducteur);

        //La taille doit avoir été augmentée de 1 après l'insertion
        int sizeAfterInsertion = conducteurDAO.selectAll().size();

        assertEquals(sizeAfterInsertion, sizeBeforeInsertion + 1);

       //DEUXIEME POSSIBILITE: Comparaison du conducteur insérer et celui récupéré (on voit si on a bien les même données
        /****************************************************/

        Conducteur conducteurAttendu = new Conducteur("GOYER", "Germain");
        Conducteur recuperationConducteur = conducteurDAO.insert(conducteurAttendu);
        Conducteur conducteurRecupere = conducteurDAO.selectById(recuperationConducteur.getId());

        assertEquals(conducteurAttendu, conducteurRecupere);
    }
}
