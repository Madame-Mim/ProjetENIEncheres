package fr.eni.projetEni.bo;

public class CategorieBo {

	private int noCategorie;
	private String libelle;
	private CategorieBo categorieArticle;
	
	public CategorieBo() {}
	
	/**
	 * @param noCategorie
	 * @param libelle
	 * @param categorieArticle
	 */
	public CategorieBo(int noCategorie, String libelle, CategorieBo categorieArticle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.categorieArticle = categorieArticle;
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
	/**
	 * @return the categorieArticle
	 */
	public CategorieBo getCategorieArticle() {
		return categorieArticle;
	}
	/**
	 * @param categorieArticle the categorieArticle to set
	 */
	public void setCategorieArticle(CategorieBo categorieArticle) {
		this.categorieArticle = categorieArticle;
	}
	@Override
	public String toString() {
		return "CategorieBo [noCategorie=" + noCategorie + ", libelle=" + libelle + ", categorieArticle="
				+ categorieArticle + "]";
	}
	
	
}