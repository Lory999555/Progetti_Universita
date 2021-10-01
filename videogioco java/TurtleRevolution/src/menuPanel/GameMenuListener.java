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
 * La sottoclasse GameMenuListener rappresenta l'ascolatore mouse e tastiera del GameMenu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class GameMenuListener implements Listener {

	private GameMenu panel;
	protected Rectangle start, levels, save, back, quit, statistics, controls;

	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel panello a cui l'ascoltatore fa riferimento.
	 */

	public GameMenuListener(GameMenu panel) {

		this.panel = panel;

		start = new Rectangle(AbstractMenu.WIDTH / 2 - 115, 115, 255, 70);
		levels = new Rectangle(AbstractMenu.WIDTH / 2 - 120, 240, 260, 70);
		save = new Rectangle(AbstractMenu.WIDTH / 2 - 100, 375, 220, 65);
		back = new Rectangle(AbstractMenu.WIDTH / 2 - 95, 495, 230, 75);
		quit = new Rectangle(AbstractMenu.WIDTH / 2 - 90, 630, 205, 70);
		statistics = new Rectangle(64, 700, 100, 90);
		controls=new Rectangle(745, 700, 95, 95);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (start.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.AUDIO.stopAll();
				Utility.AUDIO.play(Musics.GAME);
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(1)));
				t.start();
			} else if (levels.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				Utility.MENU_LIVELLI.set();
				TransitionManager t = new TransitionManager(panel, Utility.MENU_LIVELLI);
				t.start();
			} else if (save.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME);
				panel.listener=false;
				panel.add(Utility.MINI_SAVE);
				Utility.MINI_SAVE.setPanel(panel);
				Utility.MINI_SAVE.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			} else if (back.contains(x, y)) { 
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
			} else if (quit.contains(x, y)) { 
				Utility.AUDIO.play(Sounds.CLICK);
				panel.listener=false;
				panel.add(Utility.MINI_QUIT);
				Utility.MINI_QUIT.setPanel(panel);
				Utility.MINI_QUIT.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			} else if (statistics.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.STATISTICS);
				t.start();
				Utility.STATISTICS.set();
			} else if (controls.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.CONTROLS);
				t.start();
				Utility.CONTROLS.setPanel(panel);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (start.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (levels.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (save.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (back.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (quit.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else if (statistics.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else if (controls.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.HELP);
			} else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(panel.listener){
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(1)));
				t.start();
			} else if (e.getKeyCode()==KeyEvent.VK_L) {
				panel.stopAnimation();
				Utility.MENU_LIVELLI.set();
				TransitionManager t = new TransitionManager(panel, Utility.MENU_LIVELLI);
				t.start();
			} else if (e.getKeyCode()==KeyEvent.VK_S) {
				Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME);
				panel.listener=false;
				panel.add(Utility.MINI_SAVE);
				Utility.MINI_SAVE.setPanel(panel);
				Utility.MINI_SAVE.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			} else if (e.getKeyCode()==KeyEvent.VK_B) { 
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
			} else if (e.getKeyCode()==KeyEvent.VK_Q || e.getKeyCode()==KeyEvent.VK_ESCAPE) { 
				panel.listener=false;
				panel.add(Utility.MINI_QUIT);
				Utility.MINI_QUIT.setPanel(panel);
				Utility.MINI_QUIT.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}else if (e.getKeyCode()==KeyEvent.VK_C) {
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.CONTROLS);
				t.start();
				Utility.CONTROLS.setPanel(panel);
			} else if(e.getKeyCode()==KeyEvent.VK_T){
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.STATISTICS);
				t.start();
				Utility.STATISTICS.set();
			}
		}
	}

}
