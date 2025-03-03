package Exceptions;

import javax.swing.JOptionPane;

public class NoRowSelectedException extends Exception{
	public NoRowSelectedException() {
		JOptionPane.showMessageDialog(null, "No Row Selected", "Alert!", JOptionPane.ERROR_MESSAGE, null);
	}
	public NoRowSelectedException(String message) {
		JOptionPane.showMessageDialog(null, message, "Alert!", JOptionPane.ERROR_MESSAGE, null);
	}
}
