package main;

import javax.swing.JFrame;

import menuPanel.IntroPanel;

/**
 * La classe Main rappresenta il main del gioco, attraverso il quale esso stesso e' avviato.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class Main {
	
	/**
	 * La classe non e' istanziabile.
	 */
	
	private Main(){}
	
	/**
	 * Rappresenta il frame sul quale si basa tutto il gioco.
	 */
	
	protected static final JFrame FINESTRA=new JFrame("TurtleRevolution");
	
	/**
	 * Crea e avvia il loading in modo tale da creare tutte le parti necessarie al gioco mentre mostra l'intro. Imposta 
	 * le caratteristiche standard della finestra quali dimensione, chiusura, ridimensionamento a false di default, la visibilita' 
	 * e la posizione al centro dello schermo.
	 * @param args argomento standard del metodo main
	 */
	
	public static void main (String...args) {
		
		Loading loading=new Loading();
		loading.start();
		FINESTRA.setContentPane(new IntroPanel()); 
		FINESTRA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FINESTRA.setResizable(false);
		FINESTRA.setUndecorated(true); //toglie la barra sopra
		FINESTRA.pack(); //prende dimensioni pannello
		FINESTRA.setVisible(true);
		FINESTRA.setLocationRelativeTo(null); //posizione centrale sullo schermo
		
	}
	
}


