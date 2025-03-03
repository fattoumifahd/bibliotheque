package Exceptions;

import javax.swing.JOptionPane;

public class LivreNotDisponibleException extends Exception {
	public LivreNotDisponibleException() {
		JOptionPane.showMessageDialog(null, "ce livre est n'es pas disponible");
	}
	
	public LivreNotDisponibleException(String livreTitle) {
		JOptionPane.showMessageDialog(null, "Le livre " + livreTitle+ " est n'es pas disponible");
	}
}
