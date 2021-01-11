package fr.eni.projetEni.bll;

import java.util.List;

import fr.eni.projetEni.bo.RetraitBo;
import fr.eni.projetEni.dal.RetraitDal;

public class RetraitBll {
	
		 public static void insert(RetraitBo AdresseRetrait) throws Exception
	 {
		 if(AdresseRetrait.getRue().trim().equals("") || AdresseRetrait.getCodePostal().trim().equals("") || AdresseRetrait.getVille().trim().equals(""))
         {
			 throw new Exception("Addresse non conforme");
         }
		 RetraitDal.insert(AdresseRetrait);
	 }
	 
	 public static RetraitBo get(int no_retrait)
	 {
		 return RetraitDal.get(no_retrait);
	 }
	 
	 public static RetraitBo getRetrait(String rue, String codePostal, String ville)
	 {
		 return RetraitDal.getRetrait(rue, codePostal, ville);
	 }
	 
	public static List<RetraitBo>  get()
	{
		return RetraitDal.get();
	}
	
	public static void update(RetraitBo AdresseRetrait)
	{
		RetraitDal.update(AdresseRetrait);
	}
	
	public static void delete(int no_retrait) throws Exception
	{
		if(no_retrait < 0)
		{
			throw new Exception("Le numéro de retrait n'existe pas");
		}
		RetraitDal.delete(no_retrait);
	}
	    
}
