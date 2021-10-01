package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Sounds;

/**
 * La sottoclasse MenuListener rappresenta l'ascoltatore mouse e tastiera del menu Menu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class MenuListener implements Listener{

	private Menu panel;
	private Rectangle newGame, loadGame, option, quit, about, controls;

	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public MenuListener(Menu panel) {

		this.panel = panel;

		newGame = new Rectangle(AbstractMenu.WIDTH / 2 - 215, 350, 430, 60);
		loadGame = new Rectangle(AbstractMenu.WIDTH / 2 - 225, 470, 465, 70);
		option = new Rectangle(AbstractMenu.WIDTH / 2 - 140, 600, 290, 80);
		quit = new Rectangle(AbstractMenu.WIDTH / 2 - 90, 720, 205, 70);
		about = new Rectangle(64, 700, 80, 95);
		controls=new Rectangle(745, 700, 95, 95);

	}

	@Override 
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (newGame.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				Utility.MENU_CARICA.setState(true);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_CARICA);
				t.start();
			} else if (loadGame.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				Utility.MENU_CARICA.setState(false);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_CARICA);
				t.start();
			} else if (option.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.OPTION);
				t.start();
			} else if (quit.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.listener=false;
				panel.add(Utility.MINI_QUIT);
				Utility.MINI_QUIT.setPanel(panel);
				Utility.MINI_QUIT.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				Utility.FINESTRA.repaint();
			}else if (about.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.ABOUT);
				t.start();
			}else if (controls.contains(x, y)) {
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
			if (newGame.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (loadGame.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (option.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (quit.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else if (about.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.HELP);
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
			if (e.getKeyCode()==KeyEvent.VK_N) {
				panel.stopAnimation();
				Utility.MENU_CARICA.setState(true);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_CARICA);
				t.start();
			} else if (e.getKeyCode()==KeyEvent.VK_L) {
				panel.stopAnimation();
				Utility.MENU_CARICA.setState(false);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_CARICA);
				t.start();
			} else if (e.getKeyCode()==KeyEvent.VK_O) {
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.OPTION);
				t.start();
			} else if (e.getKeyCode()==KeyEvent.VK_Q || e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				panel.listener=false;
				panel.add(Utility.MINI_QUIT);
				Utility.MINI_QUIT.setPanel(panel);
				Utility.MINI_QUIT.setFocus();
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				Utility.FINESTRA.repaint();
			}else if (e.getKeyCode()==KeyEvent.VK_A) {
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.ABOUT);
				t.start();
			}else if (e.getKeyCode()==KeyEvent.VK_C) {
				panel.stopAnimation();
				TransitionManager t = new TransitionManager(panel, Utility.CONTROLS);
				t.start();
				Utility.CONTROLS.setPanel(panel);
			}
		}
	}

}
