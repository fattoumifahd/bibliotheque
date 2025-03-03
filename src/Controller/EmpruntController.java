package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Exceptions.LivreNotDisponibleException;
import Exceptions.LivreNotFoundException;
import Exceptions.NoEmpruntFoundException;
import Exceptions.UtilisateurNotFoundException;
import Model.Emprunt;
import Model.EmpruntModel;
import Model.Livre;
import Model.Utilisateur;
import Views.EmpruntFrame;

public class EmpruntController {
	
	private EmpruntFrame view = new EmpruntFrame();
	private EmpruntModel model = new EmpruntModel("emprunt.csv");
	private ImageIcon successIcon = new ImageIcon("success-icon.png");
	
	public EmpruntController() {
		writeInTable();
		view.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					retourner();
				}
			}
		});
		
		view.getAjouter().addActionListener(e->{
			try {
				ajouterClick();
			} catch (UtilisateurNotFoundException excep) {
				
			}catch (LivreNotDisponibleException excep) {
				
			} catch(LivreNotFoundException excep) {
				
			}
		});
		
		view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
				}catch (Exception e1) {
					e1.printStackTrace();
					
				}
			}
		});
		
		view.getUserBtn().addActionListener(e->{
			view.setVisible(false);
			new UtilisateurController();
		});
		
		view.getLivreBtn().addActionListener(e->{
			view.setVisible(false);
			new LivreController();
		});
		
		view.getFiltercomboBox().addActionListener(e->{
			String item = (String)view.getFiltercomboBox().getSelectedItem();
			filter(item);
		});
	}
	
	
		
	public void filter(String item) {
		if (item.equals("Tout")) {
			writeInTable(model.getEmprunts());
		} else if (item.equalsIgnoreCase("Retourne")) {
			List<Emprunt> emprunts = model.getEmprunts().stream().filter(emprunt -> emprunt.isEtatRetour() == true).collect(Collectors.toList());
			writeInTable(emprunts);
		} else if (item.equalsIgnoreCase("En Cours")) {
			List<Emprunt> emprunts = model.getEmprunts().stream().filter(emprunt -> emprunt.isEtatRetour() == false).collect(Collectors.toList());
			writeInTable(emprunts);
		}
	}
	
	
	public void ajouterClick() throws UtilisateurNotFoundException , LivreNotFoundException , LivreNotDisponibleException{
		JLabel teleLabel = new JLabel("Telephone");
		JTextField teleField = new JTextField(20);
		JLabel livreLabel = new JLabel("Livre");
		JTextField livreField = new JTextField(20);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.add(teleLabel);
		p1.add(Box.createHorizontalStrut(2));
		p1.add(teleField);
		p2.add(livreLabel);
		p2.add(Box.createHorizontalStrut(22));
		p2.add(livreField);
		JComponent[] components = new JComponent[] {
				p1, p2
		};
		String[] options = {"Ajouter" , "Annuler"};
		int option =  JOptionPane.showOptionDialog(view, components, "Ajoute", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,options , 0);
		if (option == JOptionPane.YES_OPTION) {
			String telephone = teleField.getText();
			String titre = livreField.getText();
			Utilisateur user = model.getUserModel().chercherParTele(telephone);
			Livre livre = model.getLivreModel().rechercheParTitre(titre);
			if (user.equals(null)) {
				throw new UtilisateurNotFoundException("Aucune Utilisateur avec ce numero ");
			} else if (livre== null) {
				throw new LivreNotFoundException();
			} else {
				if (livre.isDisponible()) {
					model.emprunter(user, livre);
					JOptionPane.showMessageDialog(view, "Un Emprunt a ete ajouter !", "Ajouter", JOptionPane.PLAIN_MESSAGE , successIcon);
					writeInTable();
				} else {
					throw new LivreNotDisponibleException();
				}
			}
			
		}
		
	}
	
	public void retourner() {
		int row = view.getTable().getSelectedRow();
		JButton retounerBtn = view.getRetourn();
		if (row >= 0) {
			retounerBtn.setEnabled(true);
			int id = Integer.parseInt((String)view.getTableModel().getValueAt(row, 0));
			retounerBtn.addActionListener(e->{
				Emprunt emprunt = model.chercherParId(id);
				model.retour(emprunt);			
				writeInTable();
			});
		}
	}
	
	public void search() {
	    String search = view.getSearchField().getText().trim().toLowerCase(); 
	    List<Emprunt> emprunts = new ArrayList<>();

	    try {
	        Object[] rows = view.getTableModel().getDataVector().toArray();
	        List<Integer> ids = new ArrayList<>();
	        for (Object row : rows) {
	            try {
	                ids.add(Integer.parseInt(row.toString().replace("[", "").replace(" ", "").split(",")[0]));
	            } catch (NumberFormatException ex) {
	                System.err.println("Invalid ID format: " + row);
	            }
	        }

	        for (int id : ids) {
	            Emprunt emprunt = model.chercherParId(id);
	            if (emprunt != null) {
	                emprunts.add(emprunt);
	            }
	        }
	        if (search.isEmpty()) {

	        	
	            filter((String)view.getFiltercomboBox().getSelectedItem());

	        } else {
	        	List<Emprunt> tableEmprunt = emprunts.stream()
	            .filter(emprunt -> emprunt.toString().toLowerCase().contains(search))
	            .collect(Collectors.toList());
	        	writeInTable(!tableEmprunt.isEmpty() ? tableEmprunt : null);
	        }


	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
//		List<Emprunt> emprunts =  model.chercher(search);
//		writeInTable(emprunts);
	}

	

	
	
	public void writeInTable(List<Emprunt> emprunts) {
		DefaultTableModel tableModel = view.getTableModel();
		tableModel.setRowCount(0);
		if (emprunts != null) {
			for(Emprunt emprunt : emprunts) {
				String status ; 
				if (!emprunt.isEtatRetour()) {
					if (emprunt.getDateRetourPrevue().isBefore(LocalDate.now())) {
						status = "en Retard";
					} else status = "en Cours";
					
				} else status = "Retourne";
				String[] row = {
						Integer.toString(emprunt.getCodeEmprunt()),
						emprunt.getLivre().getTitre(),
						emprunt.getUser().getNom() + " " +emprunt.getUser().getPrenom(),
						emprunt.getDateEmprunt().toString(),
						emprunt.getDateRetourPrevue().toString(),
						status,
						(emprunt.getDateRetourEffective() != null) ? emprunt.getDateRetourEffective().toString() : "" 
				};
				tableModel.addRow(row);
			}
		}
		
	}
	
	public void writeInTable() {
	DefaultTableModel tableModel = view.getTableModel();
	tableModel.setRowCount(0);
	ArrayList<Emprunt> emprunts = model.getEmprunts();
	for(Emprunt emprunt : emprunts) {
		if (!emprunt.isEtatRetour()) {
			String status ;
			if (emprunt.getDateRetourPrevue().isBefore(LocalDate.now())) {
				status = "en Retard";
			} else {
				status = "en Cours";
			}
			
			String[] row = {
				Integer.toString(emprunt.getCodeEmprunt()),
				emprunt.getLivre().getTitre(),
				emprunt.getUser().getNom() + " " +emprunt.getUser().getPrenom(),
				emprunt.getDateEmprunt().toString(),
				emprunt.getDateRetourPrevue().toString(),
				status,
				(emprunt.getDateRetourEffective() != null) ? emprunt.getDateRetourEffective().toString() : "" 
			};
			tableModel.addRow(row);
		}
	}
}

	public EmpruntFrame getView() {
		return view;
	}


	public void setView(EmpruntFrame view) {
		this.view = view;
	}

}
