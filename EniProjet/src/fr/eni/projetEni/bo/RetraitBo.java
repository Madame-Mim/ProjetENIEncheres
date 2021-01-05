package fr.eni.projetEni.bo;

public class RetraitBo {

	private String rue;
	private int CodePostal;
	private String ville;
	private ArticleVenduBo lieuRetrait;
	private int NoRetrait;
	
	public RetraitBo() {}

	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param lieuRetrait
	 * @param noRetrait
	 */
	public RetraitBo(String rue, int codePostal, String ville, ArticleVenduBo lieuRetrait, int noRetrait) {
		this.rue = rue;
		CodePostal = codePostal;
		this.ville = ville;
		this.lieuRetrait = lieuRetrait;
		NoRetrait = noRetrait;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return CodePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		CodePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the lieuRetrait
	 */
	public ArticleVenduBo getLieuRetrait() {
		return lieuRetrait;
	}

	/**
	 * @param lieuRetrait the lieuRetrait to set
	 */
	public void setLieuRetrait(ArticleVenduBo lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	/**
	 * @return the noRetrait
	 */
	public int getNoRetrait() {
		return NoRetrait;
	}

	/**
	 * @param noRetrait the noRetrait to set
	 */
	public void setNoRetrait(int noRetrait) {
		NoRetrait = noRetrait;
	}

	@Override
	public String toString() {
		return "RetraitBo [rue=" + rue + ", CodePostal=" + CodePostal + ", ville=" + ville + ", lieuRetrait="
				+ lieuRetrait + ", NoRetrait=" + NoRetrait + "]";
	}
}