package Controller;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Exceptions.EmptyFieldException;
import Exceptions.LivreNotFoundException;
import Exceptions.NoDataFoundException;
import Model.Livre;
import Model.LivreModel;
import Model.Utilisateur;
import Views.LivreFrame;

public class LivreController {
	private LivreFrame view = new LivreFrame();
	private LivreModel model = new LivreModel("livre.csv");
	
	public LivreController () {
 
		view.getAjouteBtn().addActionListener(e->{
			try {
				ajouterClick();
			} catch (EmptyFieldException exep) {
				
			}
		});
		view.getSupprimerBtn().addActionListener(e->{
			try {
				supprimerClick();

			}catch (LivreNotFoundException excep) { 
				
			}
		});

		view.getTable().addMouseListener(new MouseAdapter()  {
			public void mouseClicked(MouseEvent e)  {
				if (e.getClickCount() == 2) {
					try {
						modifierClick();
					}catch (LivreNotFoundException excep) {
						
					} catch(EmptyFieldException excep) {
						
					}
				}
			}
		});
		view.getEffacerBtn().addActionListener(e->effacer());
		view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					search();
				}catch(Exception e1) {
					
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
				}catch(Exception e1) {
					
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
				}catch(Exception e1) {
					
				}
			}
		});
		
		view.getUserBtn().addActionListener(action->{
			view.setVisible(false);
			new UtilisateurController();
		});
		
		view.getEmpruntBtn().addActionListener(action->{
			view.setVisible(false);
			new EmpruntController();
		});
		 
	}
	
	void ajouterClick() throws EmptyFieldException {
		
		String titre = view.getTitreField().getText();
		String auteur = view.getAuteurField().getText();
		String date = view.getDateField().getText();
		String genre = (String)view.getGenresComboBox().getSelectedItem();
		if (!titre.equals("") && !auteur.equals("")&& !date.equals("") && !genre.equals("") ) {
		Livre livre = new Livre(titre, auteur,date ,genre);
			model.ajouterLivre(livre);
			effacer();
			JOptionPane.showMessageDialog(view, "Livre Ajouter Avec success");
			reloadBoos();
			
		} else {
			throw new EmptyFieldException();
		}
		
	} 
	
	public void supprimerClick() throws LivreNotFoundException {
		int row = view.getTable().getSelectedRow();
		if (row >= 0) {
			Livre livre = model.getLivres().get(row);
			System.out.println(livre.toString());
			System.out.println(row);
			model.supprimerLivre(livre.getId());
			JOptionPane.showMessageDialog(view, "Le livre " + livre.getTitre() + "a ete supprimer");
			reloadBoos();
		} else {
			JOptionPane.showMessageDialog(view, "No row Selected");
		}
	}
	
	
	public void modifierClick() throws EmptyFieldException , LivreNotFoundException{
		int row = view.getTable().getSelectedRow();
//		if (row >= 0) {
			view.getModifierBtn().setEnabled(true);
			Livre livre = model.getLivres().get(row);
			JTextField titreField = view.getTitreField();
			JTextField auteurField = view.getAuteurField();
			JTextField dateField = view.getDateField();
			JComboBox<String> genresComboBox = view.getGenresComboBox();
			titreField.setText(livre.getTitre());
			auteurField.setText(livre.getAuteur());
			dateField.setText(livre.getDatePublication());
			genresComboBox.setSelectedItem(livre.getGenre());
			view.getModifierBtn().addActionListener(e->{
			String titre = titreField.getText();
			String auteur = auteurField.getText();
			String date = dateField.getText();
			String genre = (String)genresComboBox.getSelectedItem();
			try {
				model.modifierLivre(livre, titre, auteur, date, genre);
				reloadBoos();
				effacer();
				JOptionPane.showMessageDialog(view, "La modification a ete fait !");
			} catch (LivreNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EmptyFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});

		
	}
	
	
	public void search() {
		String search = view.getSearchField().getText();
		List<Livre> livres =  model.search(search);
		if (livres != null) {
			filterTable(livres);			
		} else {
			view.getTableModel().setRowCount(0);
		}
	}
	
	
	public void effacer () {
		JTextField titreField = view.getTitreField();
		JTextField auteurField = view.getAuteurField();
		JTextField dateField = view.getDateField();
		JComboBox<String> genresComboBox = view.getGenresComboBox();
		titreField.setText("");
		auteurField.setText("");
		dateField.setText("");
		genresComboBox.setSelectedIndex(0);
		view.getModifierBtn().setEnabled(false);;
	}
	
	public void reloadBoos() {
		ArrayList<Livre> livres = model.getLivres();
		DefaultTableModel tableModel = view.getTableModel();
		tableModel.setRowCount(0);
		for (Livre livre : livres) {
			String[] row = {
					Integer.toString(livre.getId()),
					livre.getTitre(),
					livre.getAuteur(),
					livre.getDatePublication(),
					livre.getGenre(),
			};
			tableModel.addRow(row);
		}
	}
	
	public void filterTable(List<Livre> livres) {
		DefaultTableModel tableModel = view.getTableModel();
		tableModel.setRowCount(0);
		for (Livre livre : livres) {
			String[] row = {
					Integer.toString(livre.getId()),
					livre.getTitre(),
					livre.getAuteur(),
					livre.getDatePublication(),
					livre.getGenre(),
			};
			tableModel.addRow(row);
		}
		
	}
	
	public void setFrame(LivreFrame frame) {
		this.view = frame;
	}
	
	public LivreFrame getFrame() {
		return this.view;
	}
	
	
}
