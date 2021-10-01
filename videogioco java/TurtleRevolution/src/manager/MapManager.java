package manager;

import entity.Card;

/**
 * La classe MapManager si occupa di gestire la mappa di gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class MapManager {
	
	/**
	 * Rappresenta la precedente posizione di x.
	 */
	private static int preX;
	
	/**
	 * Rappresenta la precedente posizione di y.
	 */
	
	private static int preY;
	
	/**
	 * La classe non e' instanziabile.
	 */
	
	private MapManager(){}
	
	/**
	 * Alloca la tessera ricevuta come parametro nel piu' vicino spazio libero. Se il piu' vicino spazio libero 
	 * e' gia' occupato la tessera torna nella posizione precedente.
	 * @param t la tessera da allocare
	 * @param map la mappa in cui allocare la tessera
	 * @param cell il vettore di tessere del livello
	 * @param n l'indice corrispondente alla tessera da allocare nel vettore di tessere
	 * @return true se e' stato possibile allocare la tessera, false altrimenti.
	 */
	
	public static boolean allocateCard (Card t, Card[][] map, Card[] cell, int n) {
		
		int x=allocateX(t.getXX()), y=allocateY(t.getYY());
		if (x==-1 || y==-1 || map[y][x]!=null){
			notAllocatable(t, cell, n);
			return false;
		}
		map[y][x]=t; 
		t.setX(set(x));
		t.setY(set(y));
		if(set(x)+n==preX && preY==set(y)+n) 
			return false;
		return true;
	}
	
	/**
	 * Viene invocato da {@link #allocateCard(Card, Card[][], Card[], int)} quando non e' stato possibile allocare la 
	 * tessera che ha ricevuto come parametro. Riposiziona la tessera stessa nella sua posizione originale.
	 * @param t la tessera da riposizionare nella posizione iniziale
	 * @param celle il vettore di tessere del livello
	 * @param n l'indice corrispondente alla tessera da riposizione nel vettore di tessere
	 */
	
	private static void notAllocatable (Card t, Card[] celle, int n) { 
		switch (n) {
			case 0: 
				t.setY(720);
				t.setX(350);
				celle[n]=t;
				return;
			case 1: 
				t.setY(720);
				t.setX(450);
				celle[n]=t;
				return;
			case 2: 
				t.setY(720);
				t.setX(550);
				celle[n]=t;
				return;
			default:
				assert false:n;
		}
	}
	
	/**
	 * Riceve la coordinata x nella quale e' stata rilasciata la tessera e determina la coordinata della matrice piu' vicina 
	 * nella quale la tessera andrebbe a posizionarsi.
	 * @param x la coordinata x della GUI in cui e' stata rilasciata la tessera.
	 * @return la coordinata x della mappa piu' vicina alla tessera.
	 */
	
	private static int allocateX (int x) {
		double temp=x/100.0; 
		int cx=(int)Math.round(temp);
		if (cx>8 || cx<1)
			return -1;
		return cx-1;
	}
	
	/**
	 * Riceve la coordinata y nella quale e' stata rilasciata la tessera e determina la coordinata della matrice piu' vicina 
	 * nella quale la tessera andrebbe a posizionarsi.
	 * @param y la coordinata y della GUI in cui e' stata rilasciata la tessera.
	 * @return la coordinata y della mappa piu' vicina alla tessera.
	 */
	
	private static int allocateY (int y) {
		double temp=y/100.0; 
		int cy=(int)Math.round(temp); 
		if (cy>6 || cy<1){
			return -1;}
		return cy-1;
	}

	/**
	 * Riceve la coordianata n della matrice e le assegna la posizione n della GUI.
	 * @param n la coordinata n della matrice
	 * @return la posizione n della GUI.
	 */
	
	private static int set (int n) {
		return n*100+100;
	}
	
	/**
	 * Rende libera la posizione della mappa corrispondente alle coordinate x e y ricevute come parametro.
	 * @param x la coordinata x della cella della mappa che si sta liberando
	 * @param y la coordinata y della cella della mappa che si sta libernado
	 * @param map la mappa in cui si sta libernado la cella
	 * @param cell il vettore di tessere
	 * @param n l'indice corrispondente alla tessera che si sta allocando
	 */
	
	public static void releaseCell (int x, int y, Card[][] map, Card[] cell, int n) { 
		if(y==720 && x==350){
			cell[0]=null;
			return;
		}
		if(y==720 && x==450){
			cell[1]=null;
			return;
		}
		if(y==720){
			cell[2]=null;
			return;
		}
		map[allocateY(y)][allocateX(x)]=null;
		
		preX=set(allocateX(x))+n;
		preY=set(allocateY(y))+n;
	}
	
	/**
	 * Resetta i valori delle variabili presX e preY.
	 */
	
	public static void resetting() {
		preX=0;
		preY=0;
	}
}
