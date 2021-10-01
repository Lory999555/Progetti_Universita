package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Sounds;

/**
 * La sottoclasse ControlsMenuListener rappresenta l'ascoltatore mouse e tastiera del menu ControlsMenu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class ControlsMenuListener implements Listener {
	
	private ControlsMenu panel;
	private Rectangle rx, lx, back;
	
	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public ControlsMenuListener (ControlsMenu panel) {
		
		this.panel=panel;
		
		rx = new Rectangle(800, 330, 45, 45);
		lx = new Rectangle(53, 330, 40, 40);
		back = new Rectangle(AbstractMenu.WIDTH / 2 - 115, 720, 230, 65);
			
	}
	
	@Override 
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (panel.page==0) {
			if (rx.contains(x,y)) {
				Utility.AUDIO.play(Sounds.PAGE);
				panel.page=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (back.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				TransitionManager t = new TransitionManager(panel, panel.c);
				t.start();
				if(panel.c instanceof Menu)
					Utility.MENU.startAnimation();
				else
					Utility.MENU_GIOCO.startAnimation();
			}
		}
		else if (panel.page>0 && panel.page<7) {
			if (lx.contains(x,y)) {
				Utility.AUDIO.play(Sounds.PAGE);
				panel.page-=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (rx.contains(x,y)) {
				Utility.AUDIO.play(Sounds.PAGE);
				panel.page+=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (back.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				TransitionManager t = new TransitionManager(panel, panel.c);
				t.start();
				if(panel.c instanceof Menu)
					Utility.MENU.startAnimation();
				else
					Utility.MENU_GIOCO.startAnimation();
			}
		}
		else if (panel.page==7) {
			if (lx.contains(x,y)) {
				Utility.AUDIO.play(Sounds.PAGE);
				panel.page-=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (back.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				TransitionManager t = new TransitionManager(panel, panel.c);
				t.start();
				if(panel.c instanceof Menu)
					Utility.MENU.startAnimation();
				else
					Utility.MENU_GIOCO.startAnimation();
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		if (panel.page==0) {
			if (rx.contains(x, y)){ 
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (back.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}
		else if (panel.page>0 && panel.page<7) {
			if (lx.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (rx.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (back.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}
		else if (panel.page==7) {
			if (lx.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (back.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (panel.page==0) {
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) { 
				panel.page=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(panel, panel.c);
				t.start();	
				if(panel.c instanceof Menu)
					Utility.MENU.startAnimation();
				else
					Utility.MENU_GIOCO.startAnimation();
			}
		}
		else if (panel.page>0 && panel.page<7) {
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				panel.page-=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) { 
				panel.page+=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(panel, panel.c);
				t.start();
				if(panel.c instanceof Menu)
					Utility.MENU.startAnimation();
				else
					Utility.MENU_GIOCO.startAnimation();
			} 
		}
		else if (panel.page==7) {
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				panel.page-=1;
				panel.setDefaultLabel();
				panel.repaint();
			} else if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(panel, panel.c);
				t.start();
				if(panel.c instanceof Menu)
					Utility.MENU.startAnimation();
				else
					Utility.MENU_GIOCO.startAnimation();
			} 
		}
	}
	
}
