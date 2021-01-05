package fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.dal.CategorieDal;

public class CategorieBll {

	
	 public static void insert(CategorieBo libelle) throws Exception
	 {
		 if(libelle.getLibelle().trim().equals(""))
         {
			 throw new Exception("Libelle incorrect");
         }
		 CategorieDal.insert(libelle);
	 }
	 
	 
	 public static CategorieBo get(int no_categorie)
	 {
		 return CategorieDal.get(no_categorie);
	 }
	 
	 
	 public static List<CategorieBo>  get()
	 {
		 return CategorieDal.get();
	 }
	 
	 
	 public static void update(CategorieBo libelle)
	 {
		 CategorieDal.update(libelle);
	 }
	 
	 
	 public static void delete(int no_categorie) throws Exception
	 {
		 if(no_categorie <0)
		 {
			 throw new Exception("Catégorie inexistante");
		 }
		 CategorieDal.delete(no_categorie);
	 }
}
