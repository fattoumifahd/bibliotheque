package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Exceptions.EmptyFieldException;
import Exceptions.UtilisateurNotFoundException;

public class UtilisateurModel implements UtilisateurModelInterface {
	private ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	private String filename ;
	public UtilisateurModel() {
		super();
		
	}
	public UtilisateurModel(String filename) {
		this.filename = filename;
		this.lireCSV();
		
	}
	
	public void ajouterUtilisateur(Utilisateur u) {
		utilisateurs.add(u);
		sauvgardeCSV();
	}
	 
	public void ModifieUtilisateur(Utilisateur user, String nom , String prenom , String tele) throws EmptyFieldException {
		if (!nom.equals("") && !prenom.equals("") && !tele.equals("")) {
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setTelephone(tele);
			sauvgardeCSV();			
		} else {
			throw new EmptyFieldException();
		}
	}
	
	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public void lireCSV()  {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			br.readLine();
			while((line = br.readLine())!= null) {
				String[] word = line.split(",");
				int id = Integer.parseInt(word[0]);
				String nom = word[1];
				String prenom = word[2];
				String telephone = word[3];
				Utilisateur u = new Utilisateur();
				u.setId(id);
				u.setNom(nom);
				u.setPrenom(prenom);
				u.setTelephone(telephone);
				utilisateurs.add(u);
				
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimer(int id ) throws UtilisateurNotFoundException {
			Utilisateur u = rechercherParId(id);
			if (u == null) {
				throw new UtilisateurNotFoundException(id);
			} else {
				utilisateurs.remove(u);
				sauvgardeCSV();
			}
			
		
		
	}
	
	public Utilisateur rechercherParId(int id) {
		Optional<Utilisateur> u = utilisateurs.stream().filter( e -> e.getId() == id).findFirst();
		if (u.isPresent()) {
			return u.get();
		}
		return null;
		
		
	}
	public Utilisateur chercherParTele(String tele) {
		Optional<Utilisateur> user = utilisateurs.stream().filter(u->u.getTelephone().contentEquals(tele)).findFirst();
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}
//	
//	public <Type> List<Utilisateur> rechercher(Type search) {
//		 List<Utilisateur> user = utilisateurs.stream().filter(u -> u.getNom().equalsIgnoreCase((String) search)
//				 || u.getPrenom().equalsIgnoreCase((String) search) || u.getTelephone().contentEquals((String) search)).collect(Collectors.toList());
//		 if (user.size()>0) {
//			 return user;
//		 }
//		 return null;
//	}
	
	public <Type> List<Utilisateur> search(Type search ){
		List<Utilisateur> user = utilisateurs.stream().filter(u -> u.toString().contains((String)search)).collect(Collectors.toList());
		if (user.size()> 0) {
			return user;
		}
		return null;
	}

	
	public void sauvgardeCSV() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write("id,nom,prenom,telephone");
			for (Utilisateur u : utilisateurs) {
				bw.append('\n');
				bw.write(u.toString());
				
			
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
