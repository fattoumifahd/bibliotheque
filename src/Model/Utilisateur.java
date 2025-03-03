package Model;

public class Utilisateur {
	
	
	
	private int id ;
	private String nom;
	private String prenom;
	private String telephone;
	private static int compteur ;
	
	public Utilisateur(String nom, String prenom, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		++compteur ;
		this.id = compteur;
//		compteur++;
	}
	
	
	public Utilisateur ( ) {
		super();
		++compteur;
		this.id = compteur; 
//		compteur++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setTelephone(String telephone ) {
		this.telephone = telephone;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public static int getCompteur() {
		return compteur;
	}
	

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public static void setCompteur(int compteur) {
		Utilisateur.compteur = compteur;
	}
	
	
	@Override
	public String toString() {
		return id + "," + nom + "," + prenom + "," + telephone;
	}

	
	
}
