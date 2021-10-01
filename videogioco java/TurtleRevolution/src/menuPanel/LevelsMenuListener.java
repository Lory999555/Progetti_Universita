package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entity.Level;
import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Musics;
import manager.AudioManager.Sounds;;

/**
 * La sottoclasse LevelsMenuListener rappresenta l'ascoltatore mouse e tastiera del menu LevelsMenu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class LevelsMenuListener implements Listener {

	private LevelsMenu panel;
	protected Rectangle l1, l2, l3, l4, l5, l6, l7, l8, l9, rx, lx, back, quit;

	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public LevelsMenuListener(LevelsMenu panel) {
		
		this.panel = panel;

		l1 = new Rectangle(155, 79, 190, 142);
		l2 = new Rectangle(355, 79, 190, 142);
		l3 = new Rectangle(555, 79, 190, 142);
		l4 = new Rectangle(155, 279, 190, 142);
		l5 = new Rectangle(355, 279, 190, 142);
		l6 = new Rectangle(555, 279, 190, 142);
		l7 = new Rectangle(155, 479, 190, 142);
		l8 = new Rectangle(355, 479, 190, 142);
		l9 = new Rectangle(555, 479, 190, 142);
		rx = new Rectangle(800, 330, 45, 45);
		lx = new Rectangle(53, 330, 40, 40);
		back = new Rectangle(30, 695, 235, 80);
		quit = new Rectangle(660, 700, 210, 80);


	}
	
	@Override 
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (panel.page==0) {
				if (l1.contains(x, y)) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(1)));
					t.start();
				} else if (l2.contains(x, y) && panel.games[0]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(2)));
					t.start();
				} else if (l3.contains(x, y) && panel.games[1]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(3)));
					t.start();
				} else if (l4.contains(x, y) && panel.games[2]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(4)));
					t.start();
				} else if (l5.contains(x, y) && panel.games[3]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(5)));
					t.start();
				} else if (l6.contains(x, y) && panel.games[4]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(6)));
					t.start();
				} else if (l7.contains(x, y) && panel.games[5]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(7)));
					t.start();
				} else if (l8.contains(x, y) && panel.games[6]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);;
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(8)));
					t.start();
				} else if (l9.contains(x, y) && panel.games[7]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(9)));
					t.start();
				} else if (rx.contains(x, y) ) { 
					Utility.AUDIO.play(Sounds.PAGE);
					panel.page=1;
					panel.repaint();
				} else if (back.contains(x, y)) {
					Utility.AUDIO.play(Sounds.CLICK);
					TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					t.start();
					Utility.MENU_GIOCO.startAnimation();
				} else if (quit.contains(x, y)){
					Utility.AUDIO.play(Sounds.CLICK);
					panel.listener=false;
					panel.add(Utility.MINI_QUIT);
					Utility.MINI_QUIT.setPanel(panel);
					Utility.MINI_QUIT.setFocus();
					Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
					Utility.FINESTRA.repaint();
				}
					
			}
			else if (panel.page==1) {
				if (l1.contains(x, y) && panel.games [8]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(10)));
					t.start();
				} else if (l2.contains(x, y) && panel.games[9]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);;
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(11)));
					t.start();
				} else if (l3.contains(x, y) && panel.games[10]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(12)));
					t.start();
				} else if (l4.contains(x, y) && panel.games[11]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(13)));
					t.start();
				} else if (l5.contains(x, y) && panel.games[12]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(14)));
					t.start();
				} else if (l6.contains(x, y) && panel.games[13]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(15)));
					t.start();
				} else if (l7.contains(x, y) && panel.games[14]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(16)));
					t.start();
				} else if (l8.contains(x, y) && panel.games[15]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(17)));
					t.start();
				} else if (l9.contains(x, y) && panel.games[16]) {
					Utility.AUDIO.play(Sounds.CLICK);
					Utility.AUDIO.stopAll();
					Utility.AUDIO.play(Musics.GAME);
					TransitionManager t = new TransitionManager(panel, new GamePanel(new Level(18)));
					t.start();
				} else if (lx.contains(x, y) ) { 
					Utility.AUDIO.play(Sounds.PAGE);
					panel.page=0;
					panel.repaint();
				} else if (back.contains(x, y)) {
					Utility.AUDIO.play(Sounds.CLICK);
					TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					t.start();
					Utility.MENU_GIOCO.startAnimation();
				} else if (quit.contains(x, y)){
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
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			
			if (panel.page==0) {
				if (l1.contains(x, y)) 
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l2.contains(x, y) && panel.games[0])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l3.contains(x, y) && panel.games[1])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l4.contains(x, y) && panel.games[2])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l5.contains(x, y) && panel.games[3])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l6.contains(x, y) && panel.games[4])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l7.contains(x, y) && panel.games[5])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l8.contains(x, y) && panel.games[6])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l9.contains(x, y) && panel.games[7])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (rx.contains(x, y) ) {
					Utility.AUDIO.selectionButton(true);
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				}
				else if (back.contains(x, y)){
					Utility.AUDIO.selectionButton(true);
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				}
				else if (quit.contains(x, y)){
					Utility.AUDIO.selectionButton(true);
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				}
				else{
					Utility.AUDIO.selectionButton(false);
					Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				}
			}
			else if (panel.page==1) {
				if (l1.contains(x, y) && panel.games[8]) 
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l2.contains(x, y) && panel.games[9])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l3.contains(x, y) && panel.games[10])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l4.contains(x, y) && panel.games[11])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l5.contains(x, y) && panel.games[12])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l6.contains(x, y) && panel.games[13])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l7.contains(x, y) && panel.games[14])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l8.contains(x, y) && panel.games[15])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (l9.contains(x, y) && panel.games[16])
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				else if (lx.contains(x, y)){
					Utility.AUDIO.selectionButton(true);
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				}
				else if (back.contains(x, y)){
					Utility.AUDIO.selectionButton(true);
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				}
				else if (quit.contains(x, y)){
					Utility.AUDIO.selectionButton(true);
					Utility.CURSOR.setCursor(TypeCursor.TURTLE);
				}
				else{
					Utility.AUDIO.selectionButton(false);
					Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
				}
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(panel.listener){
			if (panel.page==0) {
				if (e.getKeyCode()==KeyEvent.VK_RIGHT) { 
					panel.page=1;
					panel.repaint();
				} else if (e.getKeyCode()==KeyEvent.VK_B) {
					TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					t.start();
					Utility.MENU_GIOCO.startAnimation();
				} else if (e.getKeyCode()==KeyEvent.VK_Q || e.getKeyCode()==KeyEvent.VK_ESCAPE){
					panel.listener=false;
					panel.add(Utility.MINI_QUIT);
					Utility.MINI_QUIT.setPanel(panel);
					Utility.MINI_QUIT.setFocus();
					Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
					Utility.FINESTRA.repaint();
				}
			}
			else if (panel.page==1) {
				if (e.getKeyCode()==KeyEvent.VK_LEFT) { 
					panel.page=0;
					panel.repaint();
				} else if (e.getKeyCode()==KeyEvent.VK_B) {
					TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					t.start();
					Utility.MENU_GIOCO.startAnimation();
				} else if (e.getKeyCode()==KeyEvent.VK_Q || e.getKeyCode()==KeyEvent.VK_ESCAPE){
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

}
