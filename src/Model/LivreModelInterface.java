package Model;

import java.util.EmptyStackException;

import Exceptions.EmptyFieldException;
import Exceptions.LivreNotFoundException;

public interface LivreModelInterface {
	void ajouterLivre(Livre livre);
	void modifierLivre(Livre Livre ,String titre, String auteur , String date, String genre) throws LivreNotFoundException, EmptyFieldException;
	void supprimerLivre(int id) throws LivreNotFoundException;
	Livre rechercheParTitre(String titre);
	Livre rechercheParId(int id);
}
