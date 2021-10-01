package main;

import file.FileManager;
import manager.CursorsManager;
import manager.TurtleMovementManager;
import manager.AudioManager.Musics;
import manager.AudioManager;
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
 * La classe Loading si occupa di istanziare tutte le variabili della classe Utility.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 * 
 * @see Utility
 */

public class Loading extends Thread{

	/**
	 * Quando avviato istanzia tutte le variabili della classe Utility prima di auto interrompersi.
	 */
	
	public void run () {
		Utility.AUDIO=new AudioManager();
		Utility.AUDIO.play(Musics.INTRO);
		Utility.FILE = new FileManager();
		Utility.FINESTRA=Main.FINESTRA;
		Utility.MENU=new Menu();
		Utility.OPTION=new OptionMenu();
		Utility.MENU_CARICA=new LoadingMenu();
		Utility.MENU_GIOCO=new GameMenu(); 
		Utility.MENU_LIVELLI=new LevelsMenu();
		Utility.VICTORY=new VictoryMenu();
		Utility.GAME_OVER=new GameOverMenu();
		Utility.ABOUT=new AboutMenu();
		Utility.STATISTICS=new StatisticsMenu();
		Utility.CONTROLS=new ControlsMenu();
		Utility.CURSOR=new CursorsManager();
		Utility.MOVEMENT_TURTLE=new TurtleMovementManager();
		
		Utility.MINI_SAVE=new MiniSave();
		Utility.MINI_QUIT=new MiniQuit();
		
		Utility.MINI_SAVE.setBounds(Utility.MENU.getWidth()/2-260,Utility.MENU.getHeight()/2-200,520,400);
		Utility.MINI_QUIT.setBounds(Utility.MENU.getWidth()/2-260,Utility.MENU.getHeight()/2-200,520,400);
		
		this.interrupt();
	}
}
