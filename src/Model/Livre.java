package Model;

import java.util.Date;

public class Livre {
	private int id ;
	private String titre;
	private String auteur;
	private String datePublication;
	private String genre;
	private boolean disponible = true;
	public static int compteur = 1;
	
	

	public Livre() {
		super();
		this.id = compteur;
		compteur++;
	}
	
	public Livre(String titre , String auteur , String datePublication, String genre) {
		this.titre = titre;
		this.auteur = auteur;
		this.datePublication = datePublication;
		this.genre = genre;
		this.id = compteur;
		compteur++;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(String datePublication) {
		this.datePublication = datePublication;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	@Override
	public String toString() {
		return id +","+ titre+ "," + auteur + "," + datePublication + "," + genre + "," +((disponible == true) ? "disponible" : "reserver");
	}

}
