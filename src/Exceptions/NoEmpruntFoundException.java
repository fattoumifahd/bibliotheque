package Exceptions;

import javax.swing.JOptionPane;

public class NoEmpruntFoundException extends Exception {
	public NoEmpruntFoundException() {
		JOptionPane.showMessageDialog(null, "Aucune emprunt trouver!");
	}
	
	public NoEmpruntFoundException(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
}
