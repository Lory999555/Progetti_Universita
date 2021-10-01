package entity;

/**
 * L'interfaccia Entity rappresenta il generico elemento presente all'interno del gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public interface Entity {

	/**
	 * Restituisce la coordinata x dell'entita'.
	 * @return un intero corrispondente alla coordinata x dell'entita'.
	 */
	
	int getXX();

	/**
	 * Restituisce la coordinata y dell'entita'.
	 * @return un intero corrispondente alla coordinata y dell'entita'.
	 */
	
	int getYY();

	/**
	 * Assegna alla variabile x dell'entita' quella passatagli come parametro.
	 * @param x il nuovo valore della x
	 */
	
	void setX(int x);

	/**
	 * Assegna alla variabile y dell'entita' quella passatagli come parametro.
	 * @param y il nuovo valore della y
	 */
	
	void setY(int y);

}
