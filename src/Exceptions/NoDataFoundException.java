package Exceptions;

import javax.swing.JOptionPane;

public class NoDataFoundException extends Exception {
	public NoDataFoundException() {
		JOptionPane.showMessageDialog(null, "No Data Found", "Exception", JOptionPane.WARNING_MESSAGE, null);
	}
	public NoDataFoundException(String message) {
		JOptionPane.showMessageDialog(null, message, "Exception", JOptionPane.WARNING_MESSAGE, null);
	}
}
