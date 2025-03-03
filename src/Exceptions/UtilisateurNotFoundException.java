package Exceptions;

import javax.swing.JOptionPane;

public class UtilisateurNotFoundException extends Exception {
	public UtilisateurNotFoundException(int id) {
		// TODO Auto-generated constructor stub
		JOptionPane.showMessageDialog(null,"Utilisateur avec l'id = " +id+ "n'existe pas !" );
		System.err.println("Utilisateur avec l'id = " +id+ "n'existe pas !");
	}
	
	public UtilisateurNotFoundException(String message) {
		
		JOptionPane.showMessageDialog(null, message );
	}
	
}
