package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import Model.Utilisateur;
import Model.UtilisateurModel;

public class UtilisateurFrame extends JFrame {
	
	//navigate 
	private JButton empruntBtn = new JButton("Gestion des emprunt");
	private JButton livreBtn = new JButton("Gestion des Livre");
	
	//buttons
	private JButton ajouterBtn = new JButton("Ajouter Utilisateur");
	private JButton supprimerBtn = new JButton("Supprimer");
	private JButton modifierBtn = new JButton("Modifier");
	private JButton effacerBtn = new JButton("Effacer");
	private JButton searchBtn = new JButton("rechercher");
//	labels
	JLabel nomLabel = new JLabel("Nom");
	JLabel prenomLabel = new JLabel("Prenom");
	JLabel teleLabel= new JLabel("Telephone");
	JLabel searchLabel= new JLabel("recherche");
	
	// fields 
	JTextField nomField = new JTextField(); 
	JTextField prenomField = new JTextField();
	JTextField teleField = new JTextField();
	JTextField searchField = new JTextField();
	
	// table
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane scrollPane ;
	ListSelectionModel modelSelection;
	
	public UtilisateurFrame() {
		setTitle("Gestion des Utilisateur");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800 , 500);
		this.ajouterComponents();
		setLocationRelativeTo(null);
		setVisible(true);
//		pack();
//		setResizable(false);
		
	}
	

	

	public void ajouterComponents() {
		modifierBtn.setEnabled(false);
		// form
		JPanel formContainer = new JPanel();
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel , BoxLayout.X_AXIS));
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		nomField.setPreferredSize(new Dimension(150 , 30));
		prenomField.setPreferredSize(new Dimension(150 , 30));
		teleField.setPreferredSize(new Dimension(150 , 30));
		searchField.setPreferredSize(new Dimension(150 , 30));		
		JPanel prenomPanel = new JPanel();
		JPanel nomPanel = new JPanel();
		JPanel telePanel = new JPanel();
		prenomPanel.add(prenomLabel);
		prenomPanel.add(prenomField);
		prenomPanel.setMaximumSize(new Dimension( 300, 150));
		nomPanel.add(nomLabel);
		nomPanel.add(nomField);
		nomPanel.setMaximumSize(new Dimension( 300, 150));
		telePanel.add(teleLabel);
		telePanel.add(teleField);
		telePanel.setMaximumSize(new Dimension( 300, 150));
		buttonsPanel.add(ajouterBtn);
		buttonsPanel.add(modifierBtn);
		buttonsPanel.add(effacerBtn);
		buttonsPanel.setMaximumSize(new Dimension( 300, 150));
		formContainer.add(prenomPanel);
		formContainer.add(nomPanel);
		formContainer.add(telePanel);
		formContainer.add(buttonsPanel);
		ArrayList<Utilisateur> utilisateurs = new UtilisateurModel("Utilisateur.csv").getUtilisateurs();
		String[][] tableRows = convertListToarray(utilisateurs);
		String[] tableColumns = {"ID", "NOM", "PRENOM", "Telephone"}; 
		tableModel = new DefaultTableModel(tableColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(table);
		table.setBorder(BorderFactory.createLineBorder(Color.black));
//		scrollPane.setPreferredSize(new Dimension(400 , 300));
		for(String[] row : tableRows) {
			tableModel.addRow(row);
		}
		
		JPanel navigateContainer = new JPanel();
		navigateContainer.add(empruntBtn);
		navigateContainer.add(livreBtn);
		
		
		JPanel tableContainer = new JPanel();
		tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS));
		JPanel searchContainer = new JPanel();
		
		searchContainer.add(searchLabel);
		searchContainer.add(searchField);
		
		tableContainer.add(navigateContainer);
		
//		searchContainer.add(searchBtn);
		tableContainer.add(searchContainer , BorderLayout.NORTH);
		tableContainer.add(scrollPane , BorderLayout.CENTER);
		JPanel supprimerPanel = new JPanel();
		supprimerPanel.add(supprimerBtn);
		tableContainer.add(supprimerPanel , BorderLayout.SOUTH);
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableContainer , formContainer);
//		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
//		pane.setDividerLocation(700); 
		this.add(pane);
//		this.setLayout(new BoxLayout(pane, MAXIMIZED_BOTH);
		
		
		
		
	}

	
	public JButton getEmpruntBtn() {
		return empruntBtn;
	}


	public void setEmpruntBtn(JButton empruntBtn) {
		this.empruntBtn = empruntBtn;
	}


	public JButton getLivreBtn() {
		return livreBtn;
	}


	public void setLivretBtn(JButton livretBtn) {
		this.livreBtn = livretBtn;
	}

	public JButton getEffacerBtn() {
		return effacerBtn;
	}


	public void setEffacerBtn(JButton effacerBtn) {
		this.effacerBtn = effacerBtn;
	}


	public JLabel getNomLabel() {
		return nomLabel;
	}


	public void setNomLabel(JLabel nomLabel) {
		this.nomLabel = nomLabel;
	}


	public JLabel getPrenomLabel() {
		return prenomLabel;
	}


	public void setPrenomLabel(JLabel prenomLabel) {
		this.prenomLabel = prenomLabel;
	}


	public JLabel getTeleLabel() {
		return teleLabel;
	}


	public void setTeleLabel(JLabel teleLabel) {
		this.teleLabel = teleLabel;
	}


	public JTextField getNomField() {
		return nomField;
	}


	public void setNomField(JTextField nomField) {
		this.nomField = nomField;
	}


	public JTextField getPrenomField() {
		return prenomField;
	}


	public void setPrenomField(JTextField prenomField) {
		this.prenomField = prenomField;
	}


	public JTextField getTeleField() {
		return teleField;
	}


	public void setTeleField(JTextField teleField) {
		this.teleField = teleField;
	}


	public DefaultTableModel getTableModel() {
		return tableModel;
	}


	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public ListSelectionModel getModelSelection() {
		return modelSelection;
	}


	public void setModelSelection(ListSelectionModel modelSelection) {
		this.modelSelection = modelSelection;
	}


	public void setModifierBtn(JButton modifierBtn) {
		this.modifierBtn = modifierBtn;
	}


	public JButton getAjouterBtn() {
		return ajouterBtn;
	}

	public void setAjouterBtn(JButton ajouterBtn) {
		this.ajouterBtn = ajouterBtn;
	}

	public JButton getSupprimerBtn() {
		return supprimerBtn;
	}

	public void setSupprimerBtn(JButton supprimerBtn) {
		this.supprimerBtn = supprimerBtn;
	}

	public JButton getModifierBtn() {
		return modifierBtn;
	}

	public void setModiferBtn(JButton modiferBtn) {
		this.modifierBtn = modiferBtn;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}


	public void setSearchBtn(JButton searchBtn) {
		this.searchBtn = searchBtn;
	}


	public JTextField getSearchField() {
		return searchField;
	}


	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}

	public String [][] convertListToarray(ArrayList<Utilisateur> u) {
		String [][] livres = new String[u.size()][];
		for (int i = 0; i < u.size(); i++) {
			livres[i] = u.get(i).toString().split(",");
			
		}
		return livres;
	}
	

}
