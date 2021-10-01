package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entity.Level;
import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Musics;
import manager.AudioManager.Sounds;

/**
 * La sottoclasse GameOverListener rappresenta l'ascoltatore mouse e tastiera del menu GameOver.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class GameOverMenuListener implements Listener{

	private GameOverMenu panel;
	private Rectangle quit, menu, restart; 

	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public GameOverMenuListener(GameOverMenu panel){
		this.panel = panel;
		
		restart = new Rectangle(310, 555, 310, 55);
		menu = new Rectangle(45, 710, 220, 55);
		quit = new Rectangle(660, 710, 205, 55);

	}
	
	@Override 
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (restart.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.GAME);
				TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(panel.level)));
				t.start();
			} else if (menu.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.ALL_MENU);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			} else if (quit.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.listener=false;
				panel.add(Utility.MINI_QUIT);
				Utility.MINI_QUIT.setPanel(panel);
				Utility.MINI_QUIT.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				Utility.FINESTRA.repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (quit.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (menu.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (restart.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(panel.listener){
			if (e.getKeyCode()==KeyEvent.VK_R) {
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.GAME);
				TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(panel.level)));
				t.start();
			} else if (e.getKeyCode()==KeyEvent.VK_M) {
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.ALL_MENU);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			} else if (e.getKeyCode()==KeyEvent.VK_Q || e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				panel.listener=false;
				panel.add(Utility.MINI_QUIT);
				Utility.MINI_QUIT.setPanel(panel);
				Utility.MINI_QUIT.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				Utility.FINESTRA.repaint();
			}
		}
	}

}
