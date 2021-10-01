package miniPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import loaders.ImageLoader;
import main.Utility;
import manager.AudioManager.Sounds;
import manager.CursorsManager.TypeCursor;
import menuPanel.AbstractMenu;
import menuPanel.Listener;
import menuPanel.OptionMenu;
import menuPanel.OptionMenu.Language;

/**
 * La classe MiniQuit rappresenta il pannello di conferma che appare quando si esce dal gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class MiniQuit extends JPanel {
	
	private BufferedImage background, yes, cancel, no, si;
	private Rectangle yesListener, noListener, cancelListener;
	
	private Font font;
	
	private AbstractMenu panel;
	
	/**
	  * Imposta lo sfondo del pannello e fornisce al pannello stesso opportuni 
	  * ascoltatori per mouse e tastiera. Carica le immagini e crea i rettangoli che fungono da bottoni. Imposta, infine, il font.
	  */

	public MiniQuit () {
		
		InnerListener listener = new InnerListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		background = ImageLoader.load("/background/miniBackgroundC.png");
		yes = ImageLoader.load("/button/yes.png");
		no = ImageLoader.load("/button/no.png");
		si = ImageLoader.load("/button/si.png");
		cancel = ImageLoader.load("/button/cancel.png");
		
		yesListener= new Rectangle(285,290,125,70);
		noListener= new Rectangle(105,290,95,65);
		cancelListener= new Rectangle(22,22,45,45);
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/2GEMELLI_.TTF"));
			font=font.deriveFont(35f);
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
	}
	
	/**
	 * Imposta il focus sul miniPanel.
	 */
	
	public void setFocus(){
		this.requestFocusInWindow();
		this.setFocusable(true);
	}
	
	/**
	 * Imposta la variabile AbstractMenu panel con quella passatagli come parametro.
	 * @param panel da associare alla variabile panel
	 */
	
	public void setPanel(AbstractMenu panel) {
		this.panel=panel;
	}
	
	/**
	 * Disegna l'immagine di sfondo e il pannello tutto. 
	 * Inoltre, vengono impostati i colori, il font e i testi.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 520,400, null);
		g.drawImage(no, -60, 210, 400, 230, null);
		g.drawImage(cancel, 20, 20, 50, 50, null);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		if( OptionMenu.FLAG==Language.ENG){
			g.drawString("Do you really want", 90, 150);
			g.drawString("to leave the game?", 90, 200);
			g.drawImage(yes, 135, 210, 400, 230, null);
		}
		else{
			g.drawString("Vuoi davvero uscire", 90, 150);
			g.drawString("dal gioco?", 180, 200);
			g.drawImage(si, 135, 210, 400, 230, null);
		}
			
	}
	
	/**
	 * L'innerclass InnerListener rappresenta l'ascoltatore mouse e tastiera del menu MiniQuit.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	private class InnerListener implements Listener {
		
		@Override 
		public void mousePressed (MouseEvent e) { 
			int x = e.getX();
			int y = e.getY();
			if (cancelListener.contains(x, y)) { 
				panel.remove(MiniQuit.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
			}
			else if (yesListener.contains(x, y)) { 
				Utility.AUDIO.play(Sounds.CLICK);
				System.exit(0);
			}
			else if (noListener.contains(x, y)) { 
				Utility.AUDIO.play(Sounds.CLICK);
				panel.remove(MiniQuit.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
			}
		}
		
		@Override
		public void mouseMoved (MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (yesListener.contains(x, y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (cancelListener.contains(x, y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (noListener.contains(x, y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}
	
		@Override
		public void keyPressed (KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) 
				System.exit(0);
			else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				panel.remove(MiniQuit.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
			}
		}

	}

}
