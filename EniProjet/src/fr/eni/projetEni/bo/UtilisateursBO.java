package fr.eni.projetEni.bo;

public class UtilisateursBO {
private int id;
private String pseudo;
private String nom;
private String prenom;
private String email;
private String telephone;
private String rue;
private String codePostal;
private String ville;
private String password;
private int credit;
private boolean administrateur;

/**
 * constructeur vide
*/
public UtilisateursBO() {
	super();
}

/**
* copntructeur complet
 */
public UtilisateursBO(int id, String pseudo, String nom, String prenom, String email, String telephone, String rue,
		String codePostal, String ville, String password, int credit, boolean administrateur) {
	super();
	this.id = id;
	this.pseudo = pseudo;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.telephone = telephone;
	this.rue = rue;
	this.codePostal = codePostal;
	this.ville = ville;
	this.password = password;
	this.credit = credit;
	this.administrateur = administrateur;
}

/**
 constructeur sans id
 */
public UtilisateursBO(String pseudo, String nom, String prenom, String email, String telephone, String rue,
		String codePostal, String ville, String password, int credit, boolean administrateur) {
	super();
	this.pseudo = pseudo;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.telephone = telephone;
	this.rue = rue;
	this.codePostal = codePostal;
	this.ville = ville;
	this.password = password;
	this.credit = credit;
	this.administrateur = administrateur;
}

/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the pseudo
 */
public String getPseudo() {
	return pseudo;
}
/**
 * @param pseudo the pseudo to set
 */
public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}
/**
 * @return the nom
 */
public String getNom() {
	return nom;
}
/**
 * @param nom the nom to set
 */
public void setNom(String nom) {
	this.nom = nom;
}
/**
 * @return the prenom
 */
public String getPrenom() {
	return prenom;
}
/**
 * @param prenom the prenom to set
 */
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the telephone
 */
public String getTelephone() {
	return telephone;
}
/**
 * @param telephone the telephone to set
 */
public void setTelephone(String telephone) {
	this.telephone = telephone;
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
	this.codePostal = codePostal;
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
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return the credit
 */
public int getCredit() {
	return credit;
}
/**
 * @param credit the credit to set
 */
public void setCredit(int credit) {
	this.credit = credit;
}
/**
 * @return the administrateur
 */
public boolean isAdministrateur() {
	return administrateur;
}
/**
 * @param administrateur the administrateur to set
 */
public void setAdministrateur(boolean administrateur) {
	this.administrateur = administrateur;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "UtilisateursBO [id=" + id + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
			+ email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
			+ ", password=" + password + ", credit=" + credit + ", administrateur=" + administrateur + "]";
}


}
