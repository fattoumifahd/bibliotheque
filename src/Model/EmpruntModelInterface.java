package Model;

import java.util.List;

import Exceptions.LivreNotDisponibleException;

public interface EmpruntModelInterface {
	public void emprunter(Utilisateur user, Livre livre) throws LivreNotDisponibleException;
	public void retour(Emprunt emprunt);
	public Emprunt chercherParId(int code);
	public List<Emprunt> chercher(String search);
	public void sauvgarderCSV();
	public void LireCSV() ;
	
	
}
