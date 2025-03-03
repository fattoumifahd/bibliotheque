package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
//import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Model.Livre;
import Model.LivreModel;

public class LivreFrame extends JFrame {
	// navigation buttons 
	private JButton empruntBtn = new JButton("Gestion des emprunt");
	private JButton userBtn = new JButton("Gestion des Utilisateur");
	
	// buttons
	private JButton ajouteBtn = new JButton("Ajouter");
	private JButton effacerBtn = new JButton("Effacer");
	private JButton supprimerBtn = new JButton("Supprimer");
	private JButton modifierBtn = new JButton("Modifier");
	private JButton searchBtn = new JButton("rechercher");
	// Fields 
	private JTextField searchField = new JTextField();
	private JTextField titreField = new JTextField();
	private JTextField auteurField = new JTextField();
	private JFormattedTextField dateField = new JFormattedTextField("YYYY");
	private JComboBox<String> genresComboBox; 
	// labels 
	private JLabel searchLabel = new JLabel("chercher");
	private JLabel titreLabel = new JLabel("Titre");
	private JLabel auteurLabel = new JLabel("Auteur");
	private JLabel dateLabel = new JLabel("Anner de publication");
	private JLabel genreLabel = new JLabel("Genre");
	// table model	
	private DefaultTableModel tableModel;
	//table 
	private JTable table;
	JScrollPane scrollPane;
	private ListSelectionModel selectionModel;
	
	
	




	public LivreFrame () {
		setTitle("Livre Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800 , 500);
		ajouterComposent();
//		revalidate();  // Revalidate layout
//        repaint();
//		setSize(850 , 500);
		setLocationRelativeTo(null);
		setVisible(true);

//		pack();
		
		
	}



	public void ajouterComposent () {
//		dateField.setP
		this.modifierBtn.setEnabled(false);
		String [] genres = {"","narratif", "théâtral","poétique","argumentatif","épistolaire"};
		genresComboBox = new JComboBox<String>(genres);
		JPanel formContainer = new JPanel();
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
//		formContainer.setPreferredSize(new Dimension(320, 200));
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		
		titreField.setPreferredSize(new Dimension(150 , 30));
		auteurField.setPreferredSize(new Dimension(150 , 30));
		dateField.setPreferredSize(new Dimension(70 , 30));
		searchField.setPreferredSize(new Dimension(150 , 30));
		genresComboBox.setPreferredSize(new Dimension(150 , 30));
//		titreField.setSize(100, 50);
//		titreField.setSize(100, 50);
//		titreField.setSize(100, 50);
		// from panels
		JPanel titrePanel = new JPanel();
		titrePanel.setLayout(new FlowLayout());
		JPanel auteurPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel genrePanel = new JPanel();
		titrePanel.add(titreLabel);
		titrePanel.add(titreField);
		titrePanel.setMaximumSize(new Dimension( 300, 150));
		auteurPanel.add(auteurLabel);
		auteurPanel.add(auteurField);
		auteurPanel.setMaximumSize(new Dimension( 300, 150));
		datePanel.add(dateLabel);
		datePanel.add(dateField);
		datePanel.setMaximumSize(new Dimension( 300, 150));
		genrePanel.add(genreLabel);
		genrePanel.add(genresComboBox);
		genrePanel.setMaximumSize(new Dimension( 300, 150));
		buttonsPanel.add(ajouteBtn);
		buttonsPanel.add(modifierBtn);
		buttonsPanel.add(effacerBtn);
		buttonsPanel.setMaximumSize(new Dimension( 300, 150));
		formContainer.add(titrePanel);
		formContainer.add(auteurPanel);
		formContainer.add(datePanel);
		formContainer.add(genrePanel);
		formContainer.add(buttonsPanel);
		
//		JPanel wrapped = new JPanel();
//		wrapped.add(formContainer, BorderLayout.CENTER);
//		wrapped.add(buttonsPanel, BorderLayout.SOUTH);
		
//		container.add(p1);
//		container.add(p2);
//		container.add(p3);
//		container.add(p4);
//		container.add(p5);
		
		
		ArrayList<Livre> livres = new LivreModel("livre.csv").getLivres();
		String [][] rows = convertListToarray(livres);
		String [] cols = {"ID","Titre","Auteur","Date Publication","Genre"};
		tableModel = new DefaultTableModel(cols , 0) {
			@Override 
			public boolean isCellEditable(int row , int col) {
				return false ;
			}
		}; 
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(table);
//		scrollPane.setPreferredSize(new Dimension(400 , 350));
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		selectionModel = table.getSelectionModel();
		for (String[] row : rows) {
			tableModel.addRow(row);
		}
		
		JPanel tableContainer = new JPanel();
		JPanel searchContainer = new JPanel();
//		navigate 
		JPanel navigateContainer = new JPanel();
		navigateContainer.add(empruntBtn);
		navigateContainer.add(userBtn);
		// table 
		tableContainer.setLayout(new BoxLayout(tableContainer , BoxLayout.Y_AXIS));
		searchContainer.add(searchLabel); 
		searchContainer.add(searchField);
		
		searchContainer.setPreferredSize(new Dimension(400 ,70));
		navigateContainer.setBounds(10 , 10 , getWidth() , getHeight());
		tableContainer.add(navigateContainer);
		
		tableContainer.add(searchContainer );
		tableContainer.add(scrollPane );
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(supprimerBtn);
//		supprimerBtn.setPreferredSize(new Dimension(100,30));
		tableContainer.add(buttonPanel );
//		this.setLayout(new GridLayout(1,2,7,0));
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , tableContainer , formContainer);
//		pane.setDividerLocation(getWidth()/3);
		this.add(pane);
//		this.add(wrapped);
	}
	
	
	
	public JButton getEmpruntBtn() {
		return empruntBtn;
	}

	public void setEmpruntBtn(JButton empruntBtn) {
		this.empruntBtn = empruntBtn;
	}

	public JButton getUserBtn() {
		return userBtn;
	}

	public void setUserBtn(JButton userBtn) {
		this.userBtn = userBtn;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JButton getAjouteBtn() {
		return ajouteBtn;
	}

	public void setAjouteBtn(JButton ajouteBtn) {
		this.ajouteBtn = ajouteBtn;
	}

	public JButton getModifierBtn() {
		return modifierBtn;
	}

	public void setModifierBtn(JButton modifierBtn) {
		this.modifierBtn = modifierBtn;
	}

	public String [][] convertListToarray(ArrayList<Livre> l) {
		String [][] livres = new String[l.size()][];
		for (int i = 0; i < l.size(); i++) {
			livres[i] = l.get(i).toString().split(",");
			
		}
		return livres;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	public void setEffacerBtn(JButton effacerBtn) {
		this.effacerBtn = effacerBtn;
	}
	public JButton getEffacerBtn() {
		return effacerBtn;
	}


	public JButton getSupprimerBtn() {
		return supprimerBtn;
	}



	public void setSupprimerBtn(JButton supprimerBtn) {
		this.supprimerBtn = supprimerBtn;
	}



	public JTextField getTitreField() {
		return titreField;
	}



	public void setTitreField(JTextField titreField) {
		this.titreField = titreField;
	}



	public JTextField getAuteurField() {
		return auteurField;
	}



	public void setAuteurField(JTextField auteurField) {
		this.auteurField = auteurField;
	}



	public JLabel getTitreLabel() {
		return titreLabel;
	}



	public void setTitreLabel(JLabel titreLabel) {
		this.titreLabel = titreLabel;
	}



	public JLabel getAuteurLabel() {
		return auteurLabel;
	}



	public void setAuteurLabel(JLabel auteurLabel) {
		this.auteurLabel = auteurLabel;
	}



	public JLabel getDateLabel() {
		return dateLabel;
	}



	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}



	public JLabel getGenreLabel() {
		return genreLabel;
	}



	public void setGenreLabel(JLabel genreLabel) {
		this.genreLabel = genreLabel;
	}



	public JFormattedTextField getDateField() {
		return dateField;
	}



	public void setDateField(JFormattedTextField dateField) {
		this.dateField = dateField;
	}



	public JComboBox<String> getGenresComboBox() {
		return genresComboBox;
	}



	public void setGenresComboBox(JComboBox<String> genresComboBox) {
		this.genresComboBox = genresComboBox;
	}
	
	public ListSelectionModel getSelectionModel() {
		return selectionModel;
	}



	public void setSelectionModel(ListSelectionModel selectionModel) {
		this.selectionModel = selectionModel;
	}

	public JTable getTable() {
		return table;
	}

	
	public void setTable(JTable table) {
		this.table = table;
	}


}
