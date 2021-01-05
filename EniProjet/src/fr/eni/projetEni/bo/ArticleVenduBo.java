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
		private String etatVente;
		private UtilisateurBo utilisateur;
		private CategorieBo categorie;
		private RetraitBo retrait;
		
		
		/* constructeur */
		public ArticleVenduBo() {
			super();
		}
		/* fin constructeur */
		
		
		/* getters setters */
		public int getNoArticle() {
			return noArticle;
		}
		/**
		 * @param noArticle
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
		public ArticleVenduBo(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
				LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, UtilisateurBo utilisateur,
				CategorieBo categorie, RetraitBo retrait) {
			super();
			this.noArticle = noArticle;
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			this.etatVente = etatVente;
			this.utilisateur = utilisateur;
			this.categorie = categorie;
			this.retrait = retrait;
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
				LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, UtilisateurBo utilisateur,
				CategorieBo categorie, RetraitBo retrait) {
			super();
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			this.etatVente = etatVente;
			this.utilisateur = utilisateur;
			this.categorie = categorie;
			this.retrait = retrait;
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
		public String getEtatVente() {
			return etatVente;
		}
		public void setEtatVente(String etatVente) {
			this.etatVente = etatVente;
		}
		public UtilisateurBo getUtilisateur() {
			return utilisateur;
		}
		public void setUtilisateur(UtilisateurBo utilisateur) {
			this.utilisateur = utilisateur;
		}
		public CategorieBo getCategorie() {
			return categorie;
		}
		public void setCategorie(CategorieBo categorie) {
			this.categorie = categorie;
		}
		public RetraitBo getRetrait() {
			return retrait;
		}
		public void setRetrait(RetraitBo retrait) {
			this.retrait = retrait;
		}
		/* fin getters setters */


		/* toString */
		@Override
		public String toString() {
			return "ArticleVenduBo [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description="
					+ description + ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres
					+ ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + "]";
		}
		/* toString */
		
		
	}
	
