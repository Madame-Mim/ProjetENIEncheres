package fr.eni.projetEni.bo;

import java.util.Date;

	public class ArticleVenduBo {

		private int noArticle;
		private String nomArticle;
		private String description;
		private Date dateDebutEncheres;
		private Date dateFinEncheres;
		private int miseAPrix;
		private int prixVente;
		private String etatVente;
		private Utilisateur utilisateur;
		private Categorie categorie;
		private Retrait retrait;
		
		
		/* constructeur */
		public ArticleVenduBo() {
			super();
		}
		/* fin constructeur */
		
		
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
		public Date getDateDebutEncheres() {
			return dateDebutEncheres;
		}
		public void setDateDebutEncheres(Date dateDebutEncheres) {
			this.dateDebutEncheres = dateDebutEncheres;
		}
		public Date getDateFinEncheres() {
			return dateFinEncheres;
		}
		public void setDateFinEncheres(Date dateFinEncheres) {
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
		public Utilisateur getUtilisateur() {
			return utilisateur;
		}
		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}
		public Categorie getCategorie() {
			return categorie;
		}
		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}
		public Retrait getRetrait() {
			return retrait;
		}
		public void setRetrait(Retrait retrait) {
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
	