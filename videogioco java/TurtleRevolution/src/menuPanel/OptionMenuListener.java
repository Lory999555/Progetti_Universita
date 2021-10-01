package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Musics;
import manager.AudioManager.Sounds;
import menuPanel.OptionMenu.Language;

/**
 * La sottoclasse OptionMenuListener rappresenta l'ascoltatore mouse e tastiera del menu OptionMenu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class OptionMenuListener implements Listener{

	private OptionMenu panel;
	private Rectangle back, quit, sound, music, language;

	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public OptionMenuListener(OptionMenu panel) {
		
		this.panel = panel;

		back = new Rectangle(30, 695, 235, 80);
		quit = new Rectangle(660, 700, 210, 80);
		music= new Rectangle(560,515,80,70);
		sound= new Rectangle(570,380,60,80);
		language= new Rectangle(600,250,80,80);
		
	}

	@Override 
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (back.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
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
				Utility.FINESTRA.repaint();
			} else if (sound.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.AUDIO.changeSound();
				panel.repaint();
			}else if (music.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				Utility.AUDIO.changeMusic();
				if(Utility.AUDIO.getMusicActive())Utility.AUDIO.play(Musics.ALL_MENU);
				else Utility.AUDIO.stopAll();
				panel.repaint();
			}else if (language.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				if(OptionMenu.FLAG==Language.ENG)
					OptionMenu.FLAG=Language.ITA;
				else
					OptionMenu.FLAG=Language.ENG;
				panel.repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (back.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} else if (quit.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else if (sound.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else if (music.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else if (language.contains(x, y)) {
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
			if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(panel, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
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
