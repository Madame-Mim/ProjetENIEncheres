package fr.eni.projetEni.bo;

import java.time.LocalDate;

	public class ArticleVenduBo {

		private int noArticle;
		private String nomArticle;
		private String description;
		private LocalDate dateDebutEncheres;
		private LocalDate dateFinEncheres;
		private int miseAPrix;
		private int prixVente;
		private static UtilisateurBo utilisateur;
		private static CategorieBo categorie;
		private static RetraitBo retrait;
		private boolean retraitEffectue;
		
		/* constructeur */
		public ArticleVenduBo() {
			super();
		}
		/* fin constructeur */
		
		
		/**
		 * @param noArticle
		 * @param nomArticle
		 * @param description
		 * @param dateDebutEncheres
		 * @param dateFinEncheres
		 * @param miseAPrix
		 * @param prixVente
		 * @param retraitEffectue
		 */
		public ArticleVenduBo(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
				LocalDate dateFinEncheres, int miseAPrix, int prixVente,  UtilisateurBo utilisateur,
				CategorieBo categorie, RetraitBo retrait, boolean retraitEffectue) {
			super();
			this.noArticle = noArticle;
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			ArticleVenduBo.utilisateur = utilisateur;
			ArticleVenduBo.categorie = categorie;
			ArticleVenduBo.retrait = retrait;
			this.retraitEffectue = retraitEffectue;		
		}

		/**
		 * @param nomArticle
		 * @param description
		 * @param dateDebutEncheres
		 * @param dateFinEncheres
		 * @param miseAPrix
		 * @param prixVente
		 * @param etatVente
		 * @param utilisateur
		 * @param categorie
		 * @param retrait
		 */
		public ArticleVenduBo(String nomArticle, String description, LocalDate dateDebutEncheres,
				LocalDate dateFinEncheres, int miseAPrix, int prixVente, UtilisateurBo utilisateur,
				CategorieBo categorie, RetraitBo retrait, boolean retraitEffectue) {
			super();
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			ArticleVenduBo.utilisateur = utilisateur;
			ArticleVenduBo.categorie = categorie;
			ArticleVenduBo.retrait = retrait;
			this.retraitEffectue = retraitEffectue;
		}
		
		
		
		/**
		 * @param nomArticle
		 * @param description
		 * @param dateFinEncheres
		 * @param miseAPrix
		 * @param prixVente
		 * @param utilisateur
		 * @param categorie
		 * @param retrait
		 */
		public ArticleVenduBo(String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix,
				int prixVente, UtilisateurBo utilisateur, CategorieBo categorie, RetraitBo retrait, boolean retraitEffectue) {
			super();
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			ArticleVenduBo.utilisateur = utilisateur;
			ArticleVenduBo.categorie = categorie;
			ArticleVenduBo.retrait = retrait;
			this.retraitEffectue = retraitEffectue;
		}
		
		
		/**
		 * @param nomArticle
		 * @param description
		 * @param dateDebutEncheres
		 * @param dateFinEncheres
		 * @param miseAPrix
		 * @param prixVente
		 * @param categorie
		 * @param retrait
		 */
		public ArticleVenduBo(String nomArticle, String description, LocalDate dateDebutEncheres,
				LocalDate dateFinEncheres, int miseAPrix, int prixVente, CategorieBo categorie, RetraitBo retrait, boolean retraitEffectue) {
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			ArticleVenduBo.categorie = categorie;
			ArticleVenduBo.retrait = retrait;
			this.retraitEffectue = retraitEffectue;
		}
		
		

		/**
		 * @param nomArticle
		 * @param description
		 * @param dateDebutEncheres
		 * @param dateFinEncheres
		 * @param miseAPrix
		 * @param prixVente
		 * @param retrait
		 */
		public ArticleVenduBo(String nomArticle, String description, LocalDate dateDebutEncheres,
				LocalDate dateFinEncheres, int miseAPrix, int prixVente, RetraitBo retrait,  boolean retraitEffectue) {
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			ArticleVenduBo.retrait = retrait;
			this.retraitEffectue = retraitEffectue;
		}

		/* getters setters */
		public int getNoArticle() {
			return noArticle;
		}
		public void setNoArticle(int noArticle) {
			this.noArticle = noArticle;
		}
		public String getNomArticle() {
			return nomArticle;
		}
		public void setNomArticle(String nomArticle) {
			this.nomArticle = nomArticle;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public LocalDate getDateDebutEncheres() {
			return dateDebutEncheres;
		}
		public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
			this.dateDebutEncheres = dateDebutEncheres;
		}
		public LocalDate getDateFinEncheres() {
			return dateFinEncheres;
		}
		public void setDateFinEncheres(LocalDate dateFinEncheres) {
			this.dateFinEncheres = dateFinEncheres;
		}
		public int getMiseAPrix() {
			return miseAPrix;
		}
		public void setMiseAPrix(int miseAPrix) {
			this.miseAPrix = miseAPrix;
		}
		public int getPrixVente() {
			return prixVente;
		}
		public void setPrixVente(int prixVente) {
			this.prixVente = prixVente;
		}
	//	public String getEtatVente() {
	//		return etatVente;
	//	}
	//	public void setEtatVente(String etatVente) {
	//		this.etatVente = etatVente;
	//	}
		public UtilisateurBo getUtilisateur() {
			return utilisateur;
		}
		public void setUtilisateur(UtilisateurBo utilisateur) {
			ArticleVenduBo.utilisateur = utilisateur;
		}
		public CategorieBo getCategorie() {
			return categorie;
		}
		public void setCategorie(CategorieBo categorie) {
			ArticleVenduBo.categorie = categorie;
		}
		public RetraitBo getRetrait() {
			return retrait;
		}
		public void setRetrait(RetraitBo retrait) {
			ArticleVenduBo.retrait = retrait;
		}
		/* fin getters setters */


		/**
		 * @return the retraitEffectue
		 */
		public boolean isRetraitEffectue() {
			return retraitEffectue;
		}

		/**
		 * @param retraitEffectue the retraitEffectue to set
		 */
		public void setRetraitEffectue(boolean retraitEffectue) {
			this.retraitEffectue = retraitEffectue;
		}

		/* toString */
		@Override
		public String toString() {
			return "ArticleVenduBo [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description="
					+ description + ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres
					+ ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente /*+ ", etatVente=" + etatVente + "]"*/;
		}
		/* toString */
		
		
	}
	
