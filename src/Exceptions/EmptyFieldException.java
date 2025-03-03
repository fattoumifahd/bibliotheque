package Exceptions;

import javax.swing.JOptionPane;

public class EmptyFieldException extends Exception {
	public EmptyFieldException() {
		JOptionPane.showMessageDialog(null, "Tout les champte sont obligatoire !", getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	}
	
	public EmptyFieldException(String message) {
		JOptionPane.showMessageDialog(null, message, getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	}
}
