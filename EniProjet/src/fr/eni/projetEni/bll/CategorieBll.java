package fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.CategorieBo;
import fr.eni.projetEni.dal.CategorieDal;

public class CategorieBll {

	
	 public static void insert(CategorieBo libelle)
	 {
		 
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
		 
	 }
	 
	 public static void delete(int no_categorie)
	 {
		 
	 }
}
