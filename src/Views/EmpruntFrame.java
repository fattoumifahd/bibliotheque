package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Model.Emprunt;
import Model.EmpruntModel;

public class EmpruntFrame extends JFrame {

	private JButton userBtn = new JButton("Gestion des Utilisateur");
	private JButton livreBtn = new JButton("Gestion des Livre");

	private JComboBox<String> filtercomboBox;
	JLabel teleLabel = new JLabel("Telephone");
	JLabel livreLabel = new JLabel("Livre");
	
	
	JTextField teleField = new JTextField(18);
	JTextField livreField = new JTextField(20);
	
	private String[] columns = {"Code", "Livre", "Emprunteur(s)", "Date du pret" ,"Date du prevue", "Etat" , "Date Retourn effective"};
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPanel;
	private JLabel searchLabel = new JLabel("chercher");
	private JTextField searchField = new JTextField();
	private JButton ajouter = new JButton("Ajouter");
	private JButton retourn = new JButton("Retourner");
	
	
	public EmpruntFrame() {
		setTitle("Gestion des Emprunts");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800 , 500);
		addComponents();
		setLocationRelativeTo(null); 
		setVisible(true);
	}
	
	
	public void addComponents() {

		tableModel = new DefaultTableModel(columns , 0) {;
		@Override
		public boolean isCellEditable(int row , int col) {
			return false;
		}
		};
		
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String [] filter = {"En cours" , "Retourne" , "Tout"};
		filtercomboBox = new JComboBox<String>(filter);

		JPanel navigateContainer = new JPanel();
		navigateContainer.add(userBtn);
		navigateContainer.add(livreBtn);
		navigateContainer.setMaximumSize(new Dimension(350 , 70));
		navigateContainer.setBounds(0, 0, WIDTH, HEIGHT);
		JPanel buttonsContainer = new JPanel();

		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		retourn.setEnabled(false);
		buttonsContainer.add(ajouter);
		buttonsContainer.add(retourn);

		searchField.setHorizontalAlignment(JTextField.CENTER);
		JPanel searchWrapper = new JPanel();
		searchWrapper.setLayout(new BoxLayout(searchWrapper, BoxLayout.X_AXIS));
		searchWrapper.add(searchLabel);
		searchWrapper.add(Box.createHorizontalStrut(10));
		searchWrapper.add(searchField);
		searchWrapper.add(Box.createHorizontalStrut(50));
		searchWrapper.add(new JLabel("Filtre"));
		searchWrapper.add(Box.createHorizontalStrut(10));
		searchWrapper.add(filtercomboBox);
		searchWrapper.setPreferredSize(new Dimension(500 , 40));
		searchWrapper.setMaximumSize(new Dimension(500 , 40));
		
		scrollPanel = new JScrollPane(table);
		scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel tableContainer = new JPanel();
		tableContainer.setLayout(new BoxLayout(tableContainer , BoxLayout.Y_AXIS));
		
		tableContainer.add(navigateContainer);
		tableContainer.add(Box.createVerticalStrut(30));
		tableContainer.add(searchWrapper);
		tableContainer.add(Box.createVerticalStrut(30));
		tableContainer.add(scrollPanel);
		tableContainer.add(buttonsContainer);
		
		
		this.add(tableContainer);
	}

	
	public JComboBox<String> getFiltercomboBox() {
		return filtercomboBox;
	}


	public void setFiltercomboBox(JComboBox<String> filtercomboBox) {
		this.filtercomboBox = filtercomboBox;
	}


	public JButton getUserBtn() {
		return userBtn;
	}


	public void setUserBtn(JButton userBtn) {
		this.userBtn = userBtn;
	}


	public JButton getLivreBtn() {
		return livreBtn;
	}


	public void setLivreBtn(JButton livreBtn) {
		this.livreBtn = livreBtn;
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


	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}


	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}


	public JLabel getSearchLabel() {
		return searchLabel;
	}


	public void setSearchLabel(JLabel searchLabel) {
		this.searchLabel = searchLabel;
	}


	public JTextField getSearchField() {
		return searchField;
	}


	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}




	public JButton getAjouter() {
		return ajouter;
	}


	public void setAjouter(JButton ajouter) {
		this.ajouter = ajouter;
	}


	public JButton getRetourn() {
		return retourn;
	}


	public void setRetourn(JButton retourn) {
		this.retourn = retourn;
	}
}



















