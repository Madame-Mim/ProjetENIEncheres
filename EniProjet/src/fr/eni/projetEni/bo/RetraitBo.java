package fr.eni.projetEni.bo;

public class RetraitBo {

	private String rue;
	private int CodePostal;
	private String ville;
	private RetraitBo lieuRetrait;
	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param lieuRetrait
	 */
	public RetraitBo(String rue, int codePostal, String ville, RetraitBo lieuRetrait) {
		this.rue = rue;
		CodePostal = codePostal;
		this.ville = ville;
		this.lieuRetrait = lieuRetrait;
	}
	
	
}
