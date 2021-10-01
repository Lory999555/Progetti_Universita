package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * La classe FileManager gestisce il file da salvare e caricare.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class FileManager {
	
	private String folder;
	private SaveGame[] saveDate;

	/**
	 * Inizializza l'array di partite ed effettua i controlli sul file.
	 */
	
	public FileManager() {
		saveDate = new SaveGame[3];
		folder=getDataFolder();
		checks();
	}

	/**
	 * Salva su file.
	 */
	
	private void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(folder+"save.dat")));
			out.writeObject(saveDate);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carica il file.
	 */
	
	@SuppressWarnings("resource")
	private void load() {
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(folder+"save.dat")));
			Object x = in.readObject();
			if (!(x instanceof SaveGame[])) {
				throw new RuntimeException();
			}
			saveDate = (SaveGame[]) x;
			in.close();
		} catch (Exception e) { // caricamento non riuscito
			throw new RuntimeException();
		}
	}

	/**
	 * Effettua dei controlli sul file: se non esiste lo crea, se esiste lo carica, se danneggiato lo sovrascrive.
	 */
	
	private void checks() {
		File f = new File(folder+"save.dat");
		if (!f.exists()) {
			save();
			return;
		}
		try {
			load();
		} catch (Exception e) {
			f.delete();
			save();
		}
	}

	/**
	 * Restituisce l'n-esima partita.
	 * @param n l'indice della partita
	 * @return la partita.
	 */
	
	public SaveGame getGame(int n) {
		if (n < 0 || n > 2)
			throw new IllegalArgumentException();
		return saveDate[n];
	}

	/**
	 * Assegna alla posizione n-esima la partita passatagli come parametro e salva il file.
	 * @param n l'indice della partita da sovrascrivere
	 * @param newG la partita da sostituire alla precedente
	 */
	
	public void setGame(int n, SaveGame newG) {
		if (n < 0 || n > 2)
			throw new IllegalArgumentException();
		saveDate[n] = newG;
		save();
	}

	/**
	 * Verifica che la partita n-esima sia attiva.
	 * @param n l'indice della partita
	 * @return true se la partita attiva, false altrimenti.
	 */
	
	public boolean ActiveGame(int n) {
		if (n < 0 || n > 2)
			throw new IllegalArgumentException();
		return saveDate[n] != null;
	}
	
	/**
	 * In base al sistema operativo in uso crea nel file system una cartella in cui memorizzare i salvataggi del gioco.
	 * @return la directory della cartella appena creata.
	 */
	
	private String getDataFolder(){
	   String folder = System.getProperty("user.home");
	   String os = System.getProperty("os.name").toLowerCase();
	   
	   if(os.contains("win")) {
	      if(os.contains("xp"))
	         folder += "\\Application Data\\TurtleRevolution\\";
	      else if(os.contains("7") || os.contains("vista") || os.contains("8") || os.contains("10"))
	         folder += "\\AppData\\Roaming\\TurtleRevolution\\";
	   }
	   else if(os.contains("mac"))
	      folder += "/Library/Application Support/TurtleRevolution/";
	   
	   //crea la directory (aggiunge la cartella turtle revolution)
	   new File(folder).mkdirs();
	   return folder;
	}

}
