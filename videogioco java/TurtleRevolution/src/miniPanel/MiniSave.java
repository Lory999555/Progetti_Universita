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
 * La classe MiniSave rappresenta il pannello piu' piccolo di conferma salvataggio che appare ogni qual volta si salva la partita.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class MiniSave extends JPanel{
	
	private BufferedImage background, ok, cancel;
	private Rectangle okListener, cancelListener;
	private Font font;
	private AbstractMenu panel;

	/**
	 * Imposta lo sfondo del pannello e fornisce al pannello stesso opportuni 
	 * ascoltatori per mouse e tastiera. Imposta il font e si caricano le immagini e crea i rettangoli che fungono poi da bottoni.
	 */
	
	public MiniSave(){
		
		InnerListener mouse= new InnerListener();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(mouse);
		
		background = ImageLoader.load("/background/miniBackgroundA.png"); 
		ok = ImageLoader.load("/button/ok.png");
		cancel = ImageLoader.load("/button/cancel.png");
		
		okListener= new Rectangle(215,285,95,70);
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
	 * Imposta la variabile AbstractMenu panel passato come parametro.
	 * @param panel menu da assocciare alla variabile panel
	 */
	
	public void setPanel ( AbstractMenu panel){
		this.panel=panel;
	}
	
	/**
	 * Disegna l'immagine di sfondo e il pannello tutto. 
	 * Inoltre, vengono impostati i colori, il font e il testo.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 520,400, null);
		g.drawImage(ok, 55, 210, 400, 230, null);
		g.drawImage(cancel, 20, 20, 50, 50, null);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		if( OptionMenu.FLAG==Language.ENG){
			g.drawString("Game has been", 130, 150);
			g.drawString("successfully saved", 100, 200);
		}
		else{
			g.drawString("Il gioco e' stato", 140, 150);
			g.drawString("salvato con successo", 80, 200);
		}
	}
	
	/**
	 * L'innerclass InnerListener rappresenta l'ascoltatore mouse e tastiera del menu MiniSave.
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
				panel.remove(MiniSave.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
			}
			else if (okListener.contains(x, y)) { 
				panel.remove(MiniSave.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
			}
		}
		
		@Override
		public void mouseMoved (MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (okListener.contains(x, y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (cancelListener.contains(x, y)){
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
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				panel.remove(MiniSave.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
			}
			else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				panel.remove(MiniSave.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
			}
		}

	}
	
}


