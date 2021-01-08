package fr.eni.projetEni.bo;

import java.time.LocalDate;

public class EnchereBo {

	private int noEnchere;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private static ArticleVenduBo noArticle;
	private static UtilisateurBo noUtilisateur;
	/**
	 * @param noEnchere
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param noArticle
	 * @param noUtilisateur
	 */
	public EnchereBo(int noEnchere, LocalDate dateEnchere, int montantEnchere, ArticleVenduBo noArticle,
			UtilisateurBo noUtilisateur) {
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}
	
	/* constructeur */
	public EnchereBo() {
		super();
	}
	/* fin constructeur */
	

	/**
	 * @return the noEnchere
	 */
	public int getNoEnchere() {
		return noEnchere;
	}

	/**
	 * @param noEnchere the noEnchere to set
	 */
	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	/**
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the noArticle
	 */
	public ArticleVenduBo getNoArticle() {
		return noArticle;
	}

	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(ArticleVenduBo noArticle) {
		EnchereBo.noArticle = noArticle;
	}

	/**
	 * @return the noUtilisateur
	 */
	public UtilisateurBo getNoUtilisateur() {
		return noUtilisateur;
	}

	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(UtilisateurBo noUtilisateur) {
		EnchereBo.noUtilisateur = noUtilisateur;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EnchereBo [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere="
				+ montantEnchere + ", noArticle=" + noArticle + ", noUtilisateur=" + noUtilisateur + "]";
	}
	
	
}
