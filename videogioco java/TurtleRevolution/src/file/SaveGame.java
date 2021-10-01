package file;

import java.io.Serializable;

/**
 * La classe SaveGame rappresenta la partita nel file di salvataggio.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class SaveGame implements Serializable {

	private static final long serialVersionUID = 3454278136093260027L;

	private boolean[] wonLevels = new boolean[18]; 
	private String name;
	private long gameOver, reset, won, dismissed;
	
	/**
	 * Associa il nome della partita alla stringa passata come parametro.  
	 * @param name il nome della partita
	 */
	
	public SaveGame(String name) {
		this.name=name;
	}

	/**
	 * Restituisce il nome della partita.
	 * @return il nome.
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Restituisce un array di boolean che corrispondono ai livelli superati.
	 * @return l'array di livelli superati.
	 */
	
	public boolean[] getWonLevels() {
		return wonLevels;
	}
	
	/**
	 * Incrementa il numero di partite vinte.
	 */
	
	public void incWon(){
		won=won+1;
	}
	
	/**
	 * Incrementa il numero di partite resettate.
	 */
	
	public void incReset(){
		reset=reset+1;
	}
	
	/**
	 * Incrementa il numero di partite perse.
	 */
	
	public void incGOver(){
		gameOver=gameOver+1;
	}
	
	/**
	 * Incrementa il numero di partite abbandonate.
	 */
	
	public void incDismissed(){
		dismissed=dismissed+1;
	}
	
	/**
	 * Restituisce il numero di partite vinte.
	 * @return il numero di partite vinte.
	 */
	
	public long getW(){
		return won;
	}

	/**
	 * Restituisce il numero di partite perse.
	 * @return il numero di partite perse.
	 */
	
	public long getGO(){
		return gameOver;
	}

	/**
	 * Restituisce il numero di partite resettate.
	 * @return il numero di partite resettate.
	 */
	
	public long getR(){
		return reset;
	}

	/**
	 * Restituisce il numero di partite abbandonate.
	 * @return il numero di partite abbandonate.
	 */
	
	public long getD(){
		return dismissed;
	}
	
	/**
	 * Segna come vinto il livello passato come parametro.
	 * @param level il livello appena vinto
	 */
	
	public void won(int level) {
		wonLevels[level]=true;
	}

}
