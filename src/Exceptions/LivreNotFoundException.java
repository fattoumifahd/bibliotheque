package Exceptions;

import javax.swing.JOptionPane;

public class LivreNotFoundException extends Exception {
	public LivreNotFoundException(String titre) {
		JOptionPane.showMessageDialog(null, "Aucune livre exists par ce titre : " + titre +"." , "Error" , JOptionPane.ERROR_MESSAGE); 
		System.out.println("Aucune livre exists par ce titre : " + titre+".");
	}
	
	public LivreNotFoundException() {
		JOptionPane.showMessageDialog(null, "Aucune livre trouver" , "" , JOptionPane.WARNING_MESSAGE);
		System.err.println("Aucune livre trouver");
	}
}
