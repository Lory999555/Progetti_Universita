package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Sounds;
import miniPanel.MiniPanelNewGame;
import miniPanel.MiniPanelOldGame;

/**
 * La sottoclasse LoadingMenuListener rappresenta l'ascoltatore mouse e tastiera del menu LoadingMenu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class LoadingMenuListener implements Listener {

	private LoadingMenu panel;
	protected Rectangle slot1, slot2, slot3, back;
	private MiniPanelNewGame mPNG;
	private MiniPanelOldGame mPOG;

	/**
	 * Imposta la variabile panel a quella passata per parametro, crea i rettangoli che fungono da bottoni e i due MiniPanel.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public LoadingMenuListener(LoadingMenu panel) {

		this.panel = panel;
		mPNG= new MiniPanelNewGame(panel);
		mPOG= new MiniPanelOldGame(panel);

		slot1 = new Rectangle(AbstractMenu.WIDTH / 2 - 220, 250, 430, 70);
		slot2 = new Rectangle(AbstractMenu.WIDTH / 2 - 220, 380, 430, 70);
		slot3 = new Rectangle(AbstractMenu.WIDTH / 2 - 220, 510, 430, 70);
		back = new Rectangle(AbstractMenu.WIDTH / 2 - 90, 695, 235, 80);

	}

	@Override 
	public void mousePressed(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
	
			if (slot1.contains(x, y)) {
				Utility.CURRENT_INDEX=0;
				Utility.AUDIO.play(Sounds.CLICK);
				//la partita viene sovrascritta
				if(panel.state && Utility.FILE.ActiveGame(0)){        
					 mPOG.setSlot(0);
					 mPOG.setName(Utility.FILE.getGame(0).getName());
					 panel.add(mPOG);
					 panel.listener=false;
					 mPOG.setFocus();	
				}
				//la partita viene creata come nuova
				else if(panel.state && !Utility.FILE.ActiveGame(0)){ 
					 mPNG.setSlot(0);
					 panel.add(mPNG);
					 panel.listener=false;
					 mPNG.setFocus();
				}
				//la partita viene caricata
				else if(!panel.state && Utility.FILE.ActiveGame(0)){
					 Utility.CURRENT_GAME = Utility.FILE.getGame(0);
					 TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					 t.start();
					 Utility.MENU_GIOCO.startAnimation();
				}
			}
			else if (slot2.contains(x, y)) {
				 Utility.CURRENT_INDEX=1;
				 Utility.AUDIO.play(Sounds.CLICK);
				//la partita viene sovrascritta
				if(panel.state && Utility.FILE.ActiveGame(1)){ 
					 mPOG.setSlot(1);
					 mPOG.setName(Utility.FILE.getGame(1).getName());
					 panel.add(mPOG);
					 panel.listener=false;
					 mPOG.setFocus();
					 
				}
				//la partita viene creata come nuova
				else if(panel.state && !Utility.FILE.ActiveGame(1)){ 
					 mPNG.setSlot(1);
					 panel.add(mPNG);
					 panel.listener=false;
					 mPNG.setFocus();
					 
				}
				//la partita viene caricata
				else if(!panel.state && Utility.FILE.ActiveGame(1)){ 
					 Utility.CURRENT_GAME = Utility.FILE.getGame(1);
					 TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					 t.start();
					 Utility.MENU_GIOCO.startAnimation();
				}
			} 
			
			else if (slot3.contains(x, y)) {
				Utility.CURRENT_INDEX=2;
				Utility.AUDIO.play(Sounds.CLICK);
				//la partita viene sovrascritta
				if(panel.state && Utility.FILE.ActiveGame(2)){ 
					 mPOG.setSlot(2);
					 mPOG.setName(Utility.FILE.getGame(2).getName());
					 panel.add(mPOG);
					 panel.listener=false;
					 mPOG.setFocus();
					 
				}
				//la partita viene creata come nuova
				else if(panel.state && !Utility.FILE.ActiveGame(2)){ 
					 mPNG.setSlot(2);
					 panel.add(mPNG);
					 panel.listener=false;
					 mPNG.setFocus();
					 
				}
				//la partita viene caricata
				else if(!panel.state && Utility.FILE.ActiveGame(2)){
					 Utility.CURRENT_GAME = Utility.FILE.getGame(2);
					 TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
					 t.start();
					 Utility.MENU_GIOCO.startAnimation();
				}
				 
			} 
			else if (back.contains(x, y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				TransitionManager t = new TransitionManager(panel, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
			} 
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(panel.listener){
			int x = e.getX();
			int y = e.getY();
			if (!panel.state && slot1.contains(x, y) && !Utility.FILE.ActiveGame(0)) {
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
			else if (!panel.state && slot2.contains(x, y) && !Utility.FILE.ActiveGame(1)) {
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
			else if (!panel.state && slot3.contains(x, y) && !Utility.FILE.ActiveGame(2)) {
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
			else if (slot1.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} 
			else if (slot2.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} 
			else if (slot3.contains(x, y)) {
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			} 
			else if (back.contains(x, y)) {
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
		if(panel.listener){
			if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(panel, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
			} 
		}
	}

}