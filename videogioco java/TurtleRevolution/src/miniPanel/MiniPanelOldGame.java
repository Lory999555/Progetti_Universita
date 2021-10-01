package miniPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
 * La classe MiniPanelOldGame rappresenta il pannello piu' piccolo che appare quando si sovrascrive una parita gia' esistente.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MiniPanelOldGame  extends JPanel{
	
	private int slot;
	private BufferedImage background, overwrite, cancel, delete, sovrascrivi;
	private Rectangle overwriteListener, cancelListener, deleteListener;
	private JTextField text;
	private JLabel name;
	private LoadingMenu panel;
	private Font font;
	
	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo. Inoltra, imposta il layout a null, la variabile panel con quella passata 
	 * come parametro e fornisce il pannello stesso degli appropriati ascoltatori per mouse e tastiera. Imposta, infine, il font, la JTextArea 
	 * in cui poter scrivere e la JLabel.
	 * @param panel il pannello corrente
	 */
	
	public MiniPanelOldGame(LoadingMenu panel){
		
		this.panel = panel;
		setBounds(panel.getWidth()/2-260,panel.getHeight()/2-200,520,400);
		this.setLayout(null);
		InnerListener listener=new InnerListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		background = ImageLoader.load("/background/miniBackgroundB.png"); 
		overwrite = ImageLoader.load("/button/overwrite.png");
		cancel = ImageLoader.load("/button/cancel.png");
		delete = ImageLoader.load("/button/delete.png");
		
		sovrascrivi = ImageLoader.load("/button/sovrascrivi.png");
		
		cancelListener= new Rectangle(22,22,45,45);
		deleteListener= new Rectangle(447,22,45,45);
		overwriteListener= new Rectangle(110,280,305,50);
		
		text=new JTextField();
		
		add(text);
		text.setBounds(130,190,260,30);
		text.addKeyListener(listener);
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/GRAFFITI.TTF"));
			font=font.deriveFont(35f);
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
		name= new JLabel("");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setVerticalAlignment(SwingConstants.BOTTOM); 
		name.setForeground(Color.WHITE);
		name.setBounds(0,70,520, 100);
		name.setFont(font);
		add(name);
		
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
	 * Imposta la variabile JLabel name con la stringa passatagli come parametro.
	 * @param n il nuovo nome della variabile name
	 */
	
	public void setName(String n){
		name.setText(n);
	}
	
	/**
	 * Disegna l'immagine di sfondo e il pannello tutto. 
	 * Inoltre, vengono impostati i colori e il font.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 520,400, null);
		g.drawImage(cancel, 20, 20, 50, 50, null);
		g.drawImage(delete, 445, 20, 50, 50, null);
		if (OptionMenu.Language.ENG==OptionMenu.FLAG)
			g.drawImage(overwrite, 85, 225, 350, 175, null);
		else
			g.drawImage(sovrascrivi, 85, 225, 350, 175, null);		
	}

	/**
	 * L'innerclass InnerListener rappresenta l'ascoltatore mouse e tastiera del menu MiniPanelOldGame.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	private class InnerListener implements Listener {
		
		@Override 
		public void mousePressed(MouseEvent e) { 
			int x = e.getX();
			int y = e.getY();
			if (cancelListener.contains(x, y)) { 
				text.setText("");
				panel.remove(MiniPanelOldGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
			}
			else if (overwriteListener.contains(x, y)) {
				String name=text.getText();
				if (name.matches("") || name.matches(" ")) {
					if (OptionMenu.Language.ENG==OptionMenu.FLAG)
						name="Game #"+(slot+1);
					else
						name="Partita n"+(slot+1);
				}
				Utility.CURRENT_GAME = new SaveGame(name) ;
				Utility.FILE.setGame(slot, Utility.CURRENT_GAME );
				panel.remove(MiniPanelOldGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				text.setText("");
				Utility.AUDIO.play(Sounds.CLICK);
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			}
			else if (deleteListener.contains(x, y)) {
				text.setText("");
				Utility.FILE.setGame(slot, null );
				panel.remove(MiniPanelOldGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				Utility.AUDIO.play(Sounds.CLICK);
				
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (overwriteListener.contains(x, y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (cancelListener.contains(x, y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else if (deleteListener.contains(x, y)){
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
				panel.remove(MiniPanelOldGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
				text.setText("");
				TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			}
			else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				text.setText("");
				panel.remove(MiniPanelOldGame.this);
				Utility.FINESTRA.repaint();
				panel.setListener(true);
			}
		}

	}
	
}


