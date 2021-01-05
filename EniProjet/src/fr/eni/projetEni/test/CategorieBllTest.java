package fr.eni.projetEni.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Test;

import fr.eni.projetEni.bll.CategorieBll;
import fr.eni.projetEni.bll.UtilisateurBll;
import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.bo.UtilisateurBo;
import fr.eni.projetEni.dal.CategorieDal;

public class CategorieBllTest {

	
	 @Test
    void insert() throws Exception {

        //PREMIERE POSSIBILITE : comparaison de la taille
        /****************************************************/
		
		// Taille initiale
	       int sizeBeforeInsertion = CategorieBll.get().size();

        //Ajout d'un conducteur et récupération de son id
        CategorieBo libelle = new CategorieBo("Bureau");
        CategorieBll.insert(libelle);
        
	
        //La taille doit avoir été augmentée de 1 après l'insertion
        int sizeAfterInsertion = CategorieBll.get().size();

        assertEquals(sizeAfterInsertion, sizeBeforeInsertion + 1);
    }
	
	
	
}
