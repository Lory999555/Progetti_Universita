package miniPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextField;

import file.SaveGame;
import loaders.ImageLoader;
import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Sounds;
import menuPanel.Listener;
import menuPanel.LoadingMenu;
import menuPanel.OptionMenu;

/**
 * La classe MiniPanelNewGame rappresenta il pannello piu' piccolo che appare quando si crea una nuova partita.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class MiniPanelNewGame  extends JPanel{
	
	private int slot;
	private BufferedImage background, ok, cancel;
	private Rectangle okListener, cancelListener;
	private JTextField text;
	private LoadingMenu panel;
	private Font font;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo. Inoltre, imposta il layout a null, la variabile panel con quella passata 
	 * come parametro e fornisce il pannello stesso degli appropriati ascoltatori per mouse e tastiera. Imposta, infine, il font e la JTextArea 
	 * in cui poter scrivere.
	 * @param panel il pannello corrente
	 */
	
	public MiniPanelNewGame(LoadingMenu panel){
		
		this.panel = panel;
		setBounds(panel.getWidth()/2-260,panel.getHeight()/2-200,520,400);
		this.setLayout(null);
		InnerListener listener= new InnerListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		background = ImageLoader.load("/background/miniBackgroundD.png"); 
		ok = ImageLoader.load("/button/ok.png");
		cancel = ImageLoader.load("/button/cancel.png");
		
		okListener= new Rectangle(210,260,95,70);
		cancelListener= new Rectangle(22,22,45,45);
		
		text=new JTextField();
		add(text);
		text.setBounds(130,190,260,30);
		text.addKeyListener(listener);	
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/GRAFFITI.TTF"));
			font=font.deriveFont(45f);
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
	}
	
	/**
	 * Imposta il focus sul miniPanel.
	 */
	
	public void setFocus(){
		text.requestFocusInWindow();
		text.setFocusable(true);
		repaint();
	}
	
	/** 
	 * Imposta la variabile slot con quella passata come parametro.
	 * @param slot lo slot correntemente utilizzato
	 */
	
	public void setSlot(int slot){
		this.slot=slot;
	}
	
	/**
	 * Disegna l'immagine di sfondo e il pannello tutto. 
	 * Inoltre, vengono impostati i colori e il font.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 520,400, null);
		g.drawImage(ok, 40, 180, 420, 250, null);
		g.drawImage(cancel, 20, 20, 50, 50, null);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		if(OptionMenu.Language.ENG==OptionMenu.FLAG)
			g.drawString("Enter your Name:", 60, 150);
		else 
			g.drawString("Nome Partita:", 100, 150);
	}
	
	/**
	 * L'innerclass InnerListener rappresenta l'ascoltatore mouse e tastiera del menu MiniPanelNewGame.
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
				text.setText("");
				panel.remove(MiniPanelNewGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
			}
			else if (okListener.contains(x, y)) { 
				String name=text.getText();
				if (name.matches("") || name.matches(" ")) {
					if (OptionMenu.Language.ENG==OptionMenu.FLAG)
						name="Game #"+(slot+1);
					else
						name="Partita n"+(slot+1);
				}
				Utility.CURRENT_GAME = new SaveGame(name) ;
				Utility.FILE.setGame(slot, Utility.CURRENT_GAME );
				panel.remove(MiniPanelNewGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
				text.setText("");
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
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
				String name=text.getText();
				if (name.matches("") || name.matches(" ")) {
					if (OptionMenu.Language.ENG==OptionMenu.FLAG)
						name="Game #"+(slot+1);
					else
						name="Partita n"+(slot+1);
				}
				Utility.CURRENT_GAME = new SaveGame(name) ;
				Utility.FILE.setGame(slot, Utility.CURRENT_GAME );
				panel.remove(MiniPanelNewGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				text.setText("");
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			}
			else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				text.setText("");
				panel.remove(MiniPanelNewGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
			}
		}

	}

	
}


