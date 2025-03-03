package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Exceptions.EmptyFieldException;
import Exceptions.NoDataFoundException;
import Exceptions.NoRowSelectedException;
import Exceptions.UtilisateurNotFoundException;
import Model.Utilisateur;
import Model.UtilisateurModel;
import Views.UtilisateurFrame;

public class UtilisateurController {
	private UtilisateurModel model = new UtilisateurModel("Utilisateur.csv");
	private UtilisateurFrame view = new UtilisateurFrame();
	private ImageIcon successIcon = new ImageIcon("success-icon.png");
	
	public UtilisateurController () {
		view.getAjouterBtn().addActionListener(
				e->{
					try {
						ajouterClick();					
					}catch(EmptyFieldException excep) {
						excep.printStackTrace();
					}
				});
		view.getSupprimerBtn().addActionListener(
				e->{
					try {
						supprimerClick();
					} catch (UtilisateurNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoRowSelectedException e2) {
						e2.printStackTrace();
					}
				}
				);
		view.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						modifierClick();
					} catch (UtilisateurNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (EmptyFieldException e2) {
						e2.printStackTrace();
					}
				}
			}
			
		});
		 
		view.getEffacerBtn().addActionListener(e->effacer());
		
		
//		view.getSearchBtn().addActionListener(e->{
//			try {
//				searchClick();
//			} catch(Exception e1) {
//				e1.printStackTrace();
//			}
//		});
		view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println( "remove "+view.getSearchField().getText());
				try {					
					search();
				}catch (Exception excep) {
					
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println( "insert "+view.getSearchField().getText());
				try {					
					search();
				}catch (Exception excep) {
					
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println( "change "+view.getSearchField().getText());
				try {					
					search();
				}catch (Exception excep) {
					
				}
				
			}
		});
		
		view.getLivreBtn().addActionListener(e->{
			view.setVisible(false);
			new LivreController();
		});
		
		view.getEmpruntBtn().addActionListener(e->{
			view.setVisible(false);
			new EmpruntController();
		});
	}
	
	public void ajouterClick() throws EmptyFieldException {
		String nom = view.getNomField().getText();
		String prenom = view.getPrenomField().getText();
		String tele = view.getTeleField().getText();
		if (!nom.equals("") && !prenom.equals("") && !tele.equals("")) {
			Utilisateur user = new Utilisateur(nom,prenom , tele);
			model.ajouterUtilisateur(user);
			JOptionPane.showOptionDialog(view, "Utilisateur a ete ajouter !", "NEW USER",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE,
					successIcon, null, tele);
			reReadUsers();
			effacer();
		} else {
			throw new EmptyFieldException();
		}
		
		
	}
	
	public void supprimerClick() throws UtilisateurNotFoundException , NoRowSelectedException {
		int row = view.getTable().getSelectedRow();
		if (!(row >= 0)) {
			throw new NoRowSelectedException();
		} else {
			model.supprimer(model.getUtilisateurs().get(row).getId());
			reReadUsers();
		}
	}
	
	public void modifierClick() throws UtilisateurNotFoundException , EmptyFieldException {
		int row = view.getTable().getSelectedRow();
		JButton modifierBtn = view.getModifierBtn();
		if (row >= 0) {
			modifierBtn.setEnabled(true);
			Utilisateur user = model.getUtilisateurs().get(row);
			JTextField nom = view.getNomField();
			JTextField prenom = view.getPrenomField();
			JTextField tele =  view.getTeleField();
			nom.setText(user.getNom());
			prenom.setText(user.getPrenom());
			tele.setText(user.getTelephone());
			modifierBtn.addActionListener(e->{
				try {
					model.ModifieUtilisateur(user, nom.getText() , prenom.getText() , tele.getText());
					reReadUsers();
					effacer();
				} catch (EmptyFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}					
			});
		}

	}
	
	public void search () {
		String search = view.getSearchField().getText();
		List<Utilisateur> user = model.search(search);
		filiterTable(user);
		
	}
	
	public UtilisateurFrame getView() {
		return view;
	}

	public void setView(UtilisateurFrame view) {
		this.view = view;
	}

//	public void searchClick()  throws EmptyFieldException , NoDataFoundException {
//		String search = view.getSearchField().getText();
//		if (search.equals("")) {
//			reReadUsers();
//		} else {			
//			List<Utilisateur> u = model.rechercher(search);
//			if (!(u ==null)) {
//				DefaultTableModel tableModel = view.getTableModel();
////				ArrayList<Utilisateur> rows = model.getUtilisateurs();
//				tableModel.setRowCount(0);
//				for (Utilisateur user : u) {
//					user.toString();
//					String[] row = {
//							Integer.toString(user.getId()),
//							user.getNom(),
//							user.getPrenom(),
//							user.getTelephone(),
//					};
//					tableModel.addRow(row);
//				}
//			} else {
//				throw new NoDataFoundException("Aucune Utilisateur Trouver!!");
//			}
//		}
//	}
//	
	public void reReadUsers() {
		DefaultTableModel tableModel = view.getTableModel();
		ArrayList<Utilisateur> rows = model.getUtilisateurs();
		tableModel.setRowCount(0);
		for (Utilisateur user : rows) {
			String[] row = {
					Integer.toString(user.getId()),
					user.getNom(),
					user.getPrenom(),
					user.getTelephone(),
			};
			tableModel.addRow(row);
		}
	}
	
	public void filiterTable(List<Utilisateur> users) {
		DefaultTableModel tableModel = view.getTableModel();
		tableModel.setRowCount(0);
		for (Utilisateur user : users) {
			String[] row = {
					Integer.toString(user.getId()),
					user.getNom(),
					user.getPrenom(),
					user.getTelephone(),
			};
			tableModel.addRow(row);
		}
	}

	public void effacer() {
		view.getNomField().setText("");
		 view.getPrenomField().setText("");
		view.getTeleField().setText("");
		view.getModifierBtn().setEnabled(false);
	}
	
	
	
	
}
