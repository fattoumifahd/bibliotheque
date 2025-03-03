package Model;
import java.time.LocalDate;

public class Emprunt {
	private int codeEmprunt;
	private Utilisateur user;
	private Livre livre;
	private LocalDate dateEmprunt = LocalDate.now();
	private LocalDate dateRetourPrevue;
	private LocalDate dateRetourEffective;
	
	private boolean etatRetour;
	static int compteur = 0;
	
	
	public Emprunt(Utilisateur user, Livre livre ) {
		this.user = user;
		this.livre = livre;
		this.dateEmprunt = LocalDate.now();
		this.dateRetourPrevue = dateEmprunt.plusWeeks(2);
		this.etatRetour = false;
		compteur++;
		this.codeEmprunt = compteur;
	} 

	
	public Emprunt() {
		super();
		compteur++;
		this.codeEmprunt = compteur;
	}
	public LocalDate getDateRetourEffective() {
		return dateRetourEffective;
	}

	public void setDateRetourEffective(LocalDate dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}


	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}

	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
	
	
	public int getCodeEmprunt() {
		return codeEmprunt;
	}

	public void setCodeEmprunt(int codeEmprunt) {
		this.codeEmprunt = codeEmprunt;
	}

	public boolean isEtatRetour() {
		return etatRetour;
	}

	public void setEtatRetour(boolean etatRetour) {
		this.etatRetour = etatRetour;
	}
	@Override 
	public String toString() {
		return codeEmprunt+","+user.getId()+","+livre.getTitre()+","
				+dateEmprunt+","+dateRetourPrevue+","+((etatRetour==true) ? "retouner": "en cours")+","
				+dateRetourEffective +","+ user.getNom() + " " + user.getPrenom();
	}
	
	
	
	
}
