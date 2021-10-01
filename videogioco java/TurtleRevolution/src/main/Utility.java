package main;

import javax.swing.JFrame;

import file.FileManager;
import file.SaveGame;
import manager.AudioManager;
import manager.CursorsManager;
import manager.TurtleMovementManager;
import menuPanel.AboutMenu;
import menuPanel.ControlsMenu;
import menuPanel.GameMenu;
import menuPanel.GameOverMenu;
import menuPanel.LevelsMenu;
import menuPanel.LoadingMenu;
import menuPanel.Menu;
import menuPanel.OptionMenu;
import menuPanel.StatisticsMenu;
import menuPanel.VictoryMenu;
import miniPanel.MiniQuit;
import miniPanel.MiniSave;;

/**
 * La classe Utility e' una classe di utilita' che crea come variabili tutti quegli oggetti necessari al gioco, in modo tale 
 * da crearli solo una volta e far si che vengano, di volta in volta, solo richiamati.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class Utility {
	
	/**
	 * La classe non e' istanziabile.
	 */
	
	private Utility(){}
	
	/**
	 * Rappresenta il file su cui si effettua il salvataggio.
	 */
	
	public static FileManager FILE;
	
	/**
	 * Rappresenta il numero della partita corrente.
	 */
	
	public static int CURRENT_INDEX;
	
	/**
	 * Rappresenta il gioco corrente.
	 */
	
	public static SaveGame CURRENT_GAME;

	/**
	 * Rappresenta il frame su cui si basa tutto il gioco.
	 */
	
	public static JFrame FINESTRA;
	
	/**
	 * Rappresenta il menu iniziale.
	 */
	
	public static Menu MENU;
	
	/**
	 * Rappresenta il menu delle opzioni.
	 * 
	 */
	
	public static OptionMenu OPTION;
	
	/**
	 * Rappresenta il menu in cui si carica o si crea una nuova partita.
	 */
	
	public static LoadingMenu MENU_CARICA;
	
	/**
	 * Rappresenta il menu in cui e' possibile iniziare a giocare.
	 */
	
	public static GameMenu MENU_GIOCO; 
	
	/**
	 * Rappresenta il menu dei livelli.
	 */
	
	public static LevelsMenu MENU_LIVELLI;
	
	/**
	 * Rappresenta il pannello di vittoria.
	 */
	
	public static VictoryMenu VICTORY;
	
	/**
	 * Rappresenta il pannello di game over.
	 */
	
	public static GameOverMenu GAME_OVER;
	
	/**
	 * Rappresenta il menu delle informazioni del gioco.
	 */
	
	public static AboutMenu ABOUT;
	
	/**
	 * Rappresenta il menu delle statistiche del gioco.
	 */
	
	public static StatisticsMenu STATISTICS;
	
	/**
	 * Rappresenta la finestra di avvenuto salvataggio.
	 */
	
	public static MiniSave MINI_SAVE;
	
	/**
	 * Rappresenta la finestra di conferma uscita dal gioco.
	 */
	
	public static MiniQuit MINI_QUIT;
	
	/**
	 * Rappresenta il menu dei controlli del gioco.
	 */
	
	public static ControlsMenu CONTROLS;
	
	/**
	 * Rappresenta il gestore dei movimenti della tartaruga.
	 */
	
	public static TurtleMovementManager MOVEMENT_TURTLE;
	
	/**
	 * Rappresenta il gestore dell'audio del gioco.
	 */
	
	public static AudioManager AUDIO;
	
	/**
	 * Rappresenta il gestore dei cursori
	 */
	
	public static CursorsManager CURSOR;

}
