package Views;

import java.awt.Component;

import javax.swing.*;

public class CustomOptionDialog extends JOptionPane {
	public static JLabel titreLabel = new JLabel("Titre");
	public static JLabel auteurLabel = new JLabel("Auteur");
	public static JLabel dateLabel = new JLabel("Date Publication");
	public static JLabel genreLabel = new JLabel("Genre");
	public static JTextField auteurField ;
	public static JTextField titreField;
	public static JTextField dateField ;
	public static String[] genres = {"","narratif", "théâtral", "poétique","argumentatif","épistolaire"};
	public static JComboBox<String> genresComboBox = new JComboBox<String>(genres);;
	public static JPanel p1 = new JPanel();
	public static JPanel p2 = new JPanel();
	public static JPanel p3 = new JPanel();
	public static JPanel p4 = new JPanel();
	public static JPanel container = new JPanel();
	
	public static int showCustomOptionDialog(Component parent, String title ,String[] options) {
		titreField = new JTextField(50);
		auteurField = new JTextField(50);
		dateField = new JTextField(50);
		p1.add(titreLabel);
		p1.add(titreField);
		p2.add(auteurLabel);
		p2.add(auteurField);
		p3.add(dateLabel);
		p3.add(dateField);
		p4.add(genreLabel);
		p4.add(genresComboBox);
		JComponent[] inputs = new JComponent[] {
				p1 , p2 ,p3, p4
		};
		return JOptionPane.showOptionDialog(parent, inputs, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
	}
	public static int  showCustomOptionDialog(Component parent, String title ,String[] options ,String[] data) {
		titreField = new JTextField(data[0] , 50);
		auteurField = new JTextField(data[1] , 50);
		dateField = new JTextField(data[2] , 50);
		genresComboBox.setSelectedItem(data[3]);
		p1.add(titreLabel);
		p1.add(titreField);
		p2.add(auteurLabel);
		p2.add(auteurField);
		p3.add(dateLabel);
		p3.add(dateField);
		p4.add(genreLabel);
		p4.add(genresComboBox);
		JComponent[] inputs = new JComponent[] {
				p1 , p2 ,p3, p4
		};
		return JOptionPane.showOptionDialog(parent, inputs, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
	}
//	int show
}
