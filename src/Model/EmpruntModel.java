package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Exceptions.LivreNotDisponibleException;

public class EmpruntModel implements EmpruntModelInterface {
	
	private ArrayList<Emprunt> emprunts = new ArrayList<Emprunt>();
	private LivreModel livreModel = new LivreModel("livre.csv");
	private UtilisateurModel userModel = new UtilisateurModel("Utilisateur.csv");
	private String filename ;
	
	public EmpruntModel(String filename) {
		this.filename = filename;
		LireCSV(); 
	}
	
	public void emprunter(Utilisateur user, Livre livre  ) throws LivreNotDisponibleException {
		if (livre.isDisponible()) {
			Emprunt emprunt =new Emprunt(user, livre);
			livre.setDisponible(false);
			livreModel.sauvgardeCSV();
			emprunts.add(emprunt);
			sauvgarderCSV();
		} else {
			throw new LivreNotDisponibleException();
		}
	}
	
	public void retour(Emprunt emprunt) {
		if (emprunt.isEtatRetour()) {
			JOptionPane.showMessageDialog(null , "Ce livre est deja retouner");
		} else {
			emprunt.setEtatRetour(true);
			emprunt.getLivre().setDisponible(true);
			emprunt.setDateRetourEffective(LocalDate.now());
			sauvgarderCSV();
			livreModel.sauvgardeCSV();
		}
	}
	
	
	public void LireCSV() {
		try {

			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			br.readLine();
			while((line = br.readLine())!= null) {
				Emprunt emprunt = new Emprunt();
				String[] words = line.split(",");
				emprunt.setCodeEmprunt(Integer.parseInt(words[0]));
				emprunt.setUser(userModel.rechercherParId(Integer.parseInt(words[1]))) ;
				emprunt.setLivre(livreModel.rechercheParTitre(words[2]));
				emprunt.setDateEmprunt(LocalDate.parse(words[3]));
				emprunt.setDateRetourPrevue(LocalDate.parse(words[4]));
				if (words[5].equals("retouner")) {
					emprunt.setEtatRetour(true);
				} else {
					emprunt.setEtatRetour(false);
				}
				if (!words[6].equals("null")) {
					emprunt.setDateRetourEffective(LocalDate.parse(words[6]));
				} else {
					emprunt.setDateRetourEffective(null); 
				}
				emprunts.add(emprunt);
				
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Erreur : "+ e.getMessage());
		}
	}
	
	public void sauvgarderCSV() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write("codeEmprunt,UserID,Livre_title,Date_Emprunt,Date_Retour_Prevue,Etat,Date_return_Effective,User_Name");
			for (Emprunt emprunt : emprunts) {
				bw.append("\n");
				bw.append(emprunt.toString());
			}
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public List<Emprunt> chercher(String search) {
		List<Emprunt> emprunt = emprunts.stream().filter(e->e.toString().contains(search)).collect(Collectors.toList());
		if (emprunt.size()>0) {
			return emprunt;
		}
		return null;
	}
	
	public Emprunt chercherParId(int id ) {
		Optional<Emprunt> emprunt = emprunts.stream().filter(emp->emp.getCodeEmprunt() == id).findFirst();
		if (emprunt.isPresent()) {
			return emprunt.get();
		}
		return null;
	}
	
	public ArrayList<Emprunt> getEmprunts() {
		return emprunts;
	}
	public void setEmprunts(ArrayList<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public LivreModel getLivreModel() {
		return livreModel;
	}

	public void setLivreModel(LivreModel livreModel) {
		this.livreModel = livreModel;
	}

	public UtilisateurModel getUserModel() {
		return userModel;
	}

	public void setUsersModel(UtilisateurModel usersModel) {
		this.userModel = usersModel;
	}
	
}
