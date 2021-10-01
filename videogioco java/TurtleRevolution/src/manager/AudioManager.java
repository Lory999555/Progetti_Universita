package manager;

import javax.sound.sampled.Clip;

import loaders.AudioLoader;

/**
 * La classe AudioManager si occupa di gestire gli audio.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class AudioManager {
	
	private Clip allMenu,game,intro,gameLow;
	private Clip click,selection,page,rotate,card,move, win, gameOver;
	private boolean musicActive=true;
	private boolean soundActive=true;
	
	/**
	 * Rappresenta le diverse musiche nel gioco.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	public static enum Musics{
		
		/**
		 * Rappresenta la musica nella intro.
		 */
		
		INTRO, 	
		
		/**
		 * Rappresenta la musica di tutti i menu'.
		 */
		
		ALL_MENU, 	
		
		/**
		 * Rappresenta la musica nel pannello di gioco.
		 */
		
		GAME, 	
			
		/**
		* Rappresenta la musica nel pannello di gioco, ma con volume piu' basso.  
		* Viene usata come sottofondo in VictoryMenu e GameOverMenu.
		*/
		
		GAME_LOW
	};
	
	/**
	 * Rappresenta i diversi suoni nel gioco.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	public static enum Sounds{
		
		/**
		* Rappresenta il suono associato alla pressione di un qualsiasi tasto.
		*/
		
		CLICK, 	
		
		/**
		* Rappresenta il suono associato alle "frecce direzionali" per cambiare pagina.
		* Viene usato in ControlsMenu e LevelsMenu.
		*/
		
		PAGE, 	
		
		/**
		* Rappresenta il suono associato all'allocamento (riuscito) di una tessera nel pannello di gioco.
		*/
		
		CARD, 	
	
		/**
		* Rappresenta il suono associato alla rotazione di una tessera.
		*/
		
		ROTATE_CARD, 	
	
		/**
		* Rappresenta il suono associato alla vittoria di un livello.
		*/
		
		VICTORY, 	
	
		/**
		* Rappresenta il suono associato alla perdita del livello.
		*/
		
		GAMEOVER
		};
	
	/**
	 * Carica gli audio del gioco.
	 */

	public AudioManager(){
		
		selection=AudioLoader.load("/sounds/evidenzia.wav");
		click=AudioLoader.load("/sounds/selezione.wav");
		page=AudioLoader.load("/sounds/page.wav");
		card=AudioLoader.load("/sounds/card.wav");
		rotate=AudioLoader.load("/sounds/rotate.wav");
		move=AudioLoader.load("/sounds/move.wav");
		win=AudioLoader.load("/sounds/win.wav");
		gameOver=AudioLoader.load("/sounds/gameOver.wav");
		
		allMenu=AudioLoader.load("/musics/menu.wav");
		game=AudioLoader.load("/musics/playGame.wav");
		gameLow=AudioLoader.load("/musics/lowPlayGame.wav");
		intro=AudioLoader.load("/musics/intro.wav");
		
	}
	
	/**
	 * Restituisce il valore della variabile soundActive.
	 * @return il valore boolean di soundActive.
	 */
	
	public boolean getSoundActive(){
		return soundActive;
	}
	
	/**
	 * Cambia il valore della variabile soundActive.
	 */
	
	public void changeSound(){
		soundActive=!soundActive;
	}
	
	/**
	 * Cambia il valore della variabile musicActive.
	 */
	
	public void changeMusic(){
		musicActive=!musicActive;
	}
	
	/**
	 * Restituisce il valore della variabile musicActive.
	 * @return il valore boolean di musicActive.
	 */
	
	public boolean getMusicActive(){
		return musicActive;
	}
	
	/**
	 * In base al valore della variabile ricevuta in ingresso gestisce il suono del movimento del mouse sul bottone.
	 * @param type il valore in base al quale gestire il suono
	 */
	
	public void selectionButton(boolean type){
		if(type && soundActive)
			selection.start();
		if(!type && !selection.isRunning()) //serve per evitare il sovrapporsi degli audio selecetion
			selection.setMicrosecondPosition(0);
	}
	
	/**
	 * In base al valore della variabile ricevuta in ingresso fa partire in loop la clip opportuna.
	 * @param m il tipo della musica da far partire
	 */
	
	public void play(Musics m){
		if(musicActive){
			switch(m){
				case INTRO:
					intro.loop(Clip.LOOP_CONTINUOUSLY);
					return;
				case GAME:
					game.loop(Clip.LOOP_CONTINUOUSLY);
					return;
				case ALL_MENU:
					allMenu.loop(Clip.LOOP_CONTINUOUSLY);
					return;
				case GAME_LOW:
					gameLow.loop(Clip.LOOP_CONTINUOUSLY);
					return;
			}
		}
	}
	
	/**
	 * In base al valore della variabile ricevuta in ingresso fa partire la clip opportuna.
	 * @param s il tipo di suono da far partire
	 */
	
	public void play(Sounds s){
		if(soundActive){
			switch(s){
			case CLICK:
				click.start();
				click.setFramePosition(0); //riavvolge l'audio
				return;
			case PAGE:
				page.start();
				page.setFramePosition(0); 
				return;
			case CARD:
				card.start();
				card.setFramePosition(0); 
				return;
			case ROTATE_CARD:
				rotate.start();
				rotate.setFramePosition(0); 
				return;
			case VICTORY:
				win.start();
				win.setFramePosition(0); 
				return;
			case GAMEOVER:
				gameOver.start();
				gameOver.setFramePosition(0); 
				return;
			}
		}
	}
	
	/**
	 * Interrompe tutte le musiche.
	 */
	
	public void stopAll(){
		allMenu.stop();
		game.stop();
		gameLow.stop();
		intro.stop();
		win.stop();
		gameOver.stop();
		move.stop();
	}
	
	/**
	 * Gestisce il suono del movimento della tartaruga in base al valore ricevuto come parametro.
	 * @param active il valore attraverso il quale capire se il suono deve rimanere attivo in loop o meno
	 */
	
	public void movementTurtle(boolean active){
		if(soundActive){
			if(active)
				move.loop(Clip.LOOP_CONTINUOUSLY);
			else
				move.stop();
		}
	}
}
