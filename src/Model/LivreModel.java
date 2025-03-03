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
import Exceptions.LivreNotFoundException;

public class LivreModel implements LivreModelInterface {
	private ArrayList<Livre> livres = new ArrayList<Livre>();
	private String fileName;
	
	public LivreModel(String filename) {
		super();
		this.fileName = filename;
		lireLivreCSV();
		
	}
	
	
	@Override
	public void ajouterLivre(Livre l) {
		livres.add(l);
		sauvgardeCSV();
	} 
	
	
	
	public void supprimerLivre(int id) throws LivreNotFoundException {
		Livre livre = rechercheParId(id);
		if (livre == null) {
			throw new LivreNotFoundException();
		}else {
			livres.remove(livre);
			sauvgardeCSV();
		}
		
	}
	
	public void modifierLivre(Livre livre ,String titre, String auteur , String date, String genre) throws LivreNotFoundException, EmptyFieldException {
		if (!titre.equals("") && !auteur.equals("") && !date.equals("") && !genre.equals("")) {
			livre.setTitre(titre);
			livre.setAuteur(auteur);
			livre.setDatePublication(date);
			livre.setGenre(genre);
			sauvgardeCSV();
		} else {
			throw new EmptyFieldException();
			
		}
		
	}
	
	
	
	public Livre rechercheParTitre(String titre)  {
		Optional<Livre> livre = livres.stream().filter(l -> l.getTitre().equalsIgnoreCase(titre)).findFirst();
		if (livre.isPresent()) {
			return livre.get();
		}
		return null;
	}
	
//	public <Type> List<Livre> rechercher(Type search) {
//		List<Livre> livre = livres.stream().filter(l-> l.getAuteur().equalsIgnoreCase((String)search) || l.getDatePublication().equals(search) || 
//				l.getGenre().equalsIgnoreCase((String)search) || l.getTitre().equalsIgnoreCase((String)search)).collect(Collectors.toList());
//		if (livre.size() > 0) {
//			return livre;
//		}
//		return null;
//	}
//	
	public List<Livre> search(String search){
		List<Livre> livre = livres.stream().filter(l -> l.toString()
.contains(search)).collect(Collectors.toList());
		if (livre.size()> 0) {
			return livre;
		}
		return null;
	}
	
	
	public void sauvgardeCSV() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,false));
			bw.write("ID,titre,auteur,data_Publication,genre,disponibilite");
			bw.append('\n');
			for (Livre livre : livres) {
				bw.append(livre.toString());
				bw.append("\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Livre rechercheParId(int id) {
		Optional<Livre> livre = livres.stream().filter(e -> e.getId() == id).findFirst();
		if (livre.isPresent()) {
			return livre.get();
		}
		return null;
	}
	
	public void lireLivreCSV () {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			br.readLine();
			while((line = br.readLine()) != null) {
				String[] word = line.split(",");
				Livre livre = new Livre();
				livre.setId(Integer.parseInt(word[0]));
				livre.setTitre(word[1]);
				livre.setAuteur(word[2]);
				livre.setDatePublication(word[3]);
				livre.setGenre(word[4]);
				if(word[5].equals("disponible")) {
					livre.setDisponible(true);
				} else {
					livre.setDisponible(false);
				}
				livres.add(livre);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Livre> getLivres() {
		return livres;
	}

	public void setLivres(ArrayList<Livre> livres) {
		this.livres = livres;
	}
}
