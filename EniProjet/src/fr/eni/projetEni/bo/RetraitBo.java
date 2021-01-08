package fr.eni.projetEni.bo;

public class RetraitBo {

	private String rue;
	private String codePostal;
	private String ville;
	private int noRetrait;
	
	public RetraitBo() {}

	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public RetraitBo(String rue, String codePostal, String ville) {
		this.rue = rue;
		codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param lieuRetrait
	 * @param noRetrait
	 */
	public RetraitBo(String rue, String codePostal, String ville, int noRetrait) {
		this.rue = rue;
		codePostal = codePostal;
		this.ville = ville;
		noRetrait = noRetrait;
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
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		codePostal = codePostal;
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
	 * @return the noRetrait
	 */
	public int getNoRetrait() {
		return noRetrait;
	}

	/**
	 * @param noRetrait the noRetrait to set
	 */
	public void setNoRetrait(int noRetrait) {
		noRetrait = noRetrait;
	}

	@Override
	public String toString() {
		return "RetraitBo [rue=" + rue + ", CodePostal=" + codePostal + ", ville=" + ville + ", NoRetrait=" + noRetrait + "]";
	}
}