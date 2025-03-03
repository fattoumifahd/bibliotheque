package Model;

import Exceptions.EmptyFieldException;
import Exceptions.UtilisateurNotFoundException;

public interface UtilisateurModelInterface {
	void ajouterUtilisateur(Utilisateur u);
	void ModifieUtilisateur(Utilisateur user, String nom , String prenom , String tele) throws EmptyFieldException;
	void supprimer(int id) throws UtilisateurNotFoundException;
	
	

}
