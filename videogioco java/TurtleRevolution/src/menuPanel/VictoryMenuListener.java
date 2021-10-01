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
 * La sottoclasse VictoryListenet rappresenta l'ascoltatore mouse e tastiera del menu Victory.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class VictoryMenuListener implements Listener{

	private VictoryMenu panel;
	private Rectangle save, menu, next; 
	
	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento.
	 */
	
	public VictoryMenuListener(VictoryMenu panel){
		
		this.panel = panel;
		
		next = new Rectangle(365, 550, 225, 60);
		menu = new Rectangle(45, 710, 220, 55);
		save = new Rectangle(655, 710, 210, 55);
		
	}
	
	@Override 
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (save.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME);
				panel.listener=false;
				panel.add(Utility.MINI_SAVE);
				Utility.MINI_SAVE.setPanel(panel);
				Utility.MINI_SAVE.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				Utility.FINESTRA.repaint();
			} else if (menu.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.ALL_MENU);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			} else if (panel.nextLevel<=18 && next.contains(x, y)) {
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.GAME);
				TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(panel.nextLevel)));
				t.start();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (save.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (menu.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (panel.nextLevel<=9 && next.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else{
				Utility.AUDIO.movementTurtle(false);
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(panel.listener){
			if (e.getKeyCode()==KeyEvent.VK_S){
				Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME);
				panel.listener=false;
				panel.add(Utility.MINI_SAVE);
				Utility.MINI_SAVE.setPanel(panel);
				Utility.MINI_SAVE.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				Utility.FINESTRA.repaint();
			}
			else if (e.getKeyCode()==KeyEvent.VK_M ) {
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.ALL_MENU);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			} else if (panel.nextLevel<=18 && e.getKeyCode()==KeyEvent.VK_N) {
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.GAME);
				TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(panel.nextLevel)));
				t.start();
			}
		}
	}

}
