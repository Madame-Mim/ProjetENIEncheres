package fr.eni.projetEni.bo;

public class CategorieBo {

	private int noCategorie;
	private String libelle;
	
	public CategorieBo() {}
	
	
	/**
	 * @param libelle
	 */
	public CategorieBo(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * @param noCategorie
	 * @param libelle
	 * @param categorieArticle
	 */
	public CategorieBo(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "CategorieBo [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	
	
}