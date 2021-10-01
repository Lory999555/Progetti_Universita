package menuPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import loaders.ImageLoader;
import menuPanel.OptionMenu.Language;

/**
 * La sottoclasse ControlsMenu rappresenta il menu dei controlli.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ControlsMenu extends AbstractMenu {

	protected int page;
	
	private BufferedImage rxActive, rxInactive, lxActive, lxInactive, pageActive, pageInactive, bk, dt;
	
	private JLabel r1,r2,r3,r4,r5,r6,r7,r8,r9;
	private Font font, fontT;
	
	protected AbstractMenu c;
	
	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera, 
	 * carica le immagini presenti al suo interno, imposta la pagina iniziale a 0, carica e imposta i font e le JLabel all'interno del quale si trova il testo 
	 * insieme alla loro posizione sullo schermo.
	 */
	
	public ControlsMenu () {
		
		setLayout(null);
		
		page=0;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		ControlsMenuListener listener = new ControlsMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		rxActive = ImageLoader.load("/button/rxActive.png");
		rxInactive = ImageLoader.load("/button/rxInactive.png");
		
		lxActive = ImageLoader.load("/button/lxActive.png");
		lxInactive = ImageLoader.load("/button/lxInactive.png");
		
		pageActive = ImageLoader.load("/label/pageActive.png");
		pageInactive = ImageLoader.load("/label/pageInactive.png");
		
		bk = ImageLoader.load("/button/back.png");
		dt = ImageLoader.load("/button/dietro.png");
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/2GEMELLI_.TTF"));
			font=font.deriveFont(26f); 
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
		fontT=font.deriveFont(35f);
		fontT=fontT.deriveFont(Font.BOLD);
		
		r1 = new JLabel("");
		r2 = new JLabel("");
		r3 = new JLabel("");
		r4 = new JLabel("");
		r5 = new JLabel("");
		r6 = new JLabel("");
		r7 = new JLabel("");
		r8 = new JLabel("");
		r9 = new JLabel("");
		
		setDefaultLabel();
		
		r1.setVerticalAlignment(SwingConstants.BOTTOM); 
		r2.setVerticalAlignment(SwingConstants.BOTTOM);
		r3.setVerticalAlignment(SwingConstants.BOTTOM);
		r4.setVerticalAlignment(SwingConstants.BOTTOM);
		r5.setVerticalAlignment(SwingConstants.BOTTOM);
		r6.setVerticalAlignment(SwingConstants.BOTTOM);
		r7.setVerticalAlignment(SwingConstants.BOTTOM);
		r8.setVerticalAlignment(SwingConstants.BOTTOM);
		r9.setVerticalAlignment(SwingConstants.BOTTOM);
			
		add(r1);
		add(r2);
		add(r3);
		add(r4);
		add(r5);
		add(r6);
		add(r7);
		add(r8);
		add(r9);
	}
	
	@Override
	public void draw(Graphics g) { 
		
		if( OptionMenu.FLAG==Language.ENG){
			
			g.drawImage(bk, 180, 640, 500, 250, null);
			
			if (page==0) {
				
				setDefaultLabel();
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxInactive, 15, 290, 120, 120, null);
				
				g.drawImage(pageActive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Starting Menu Key Controls");
				r2.setText("N - to start a new game");
				r3.setText("L - to load an already existing game");
				r4.setText("O - to open the options");
				r5.setText("Q - to leave the game");
				r6.setText("Esc - to leave the game");
				r7.setText("A - to open the about menu");
				r8.setText("C - to open controls menu");
				r9.setText("");
				
			}
			
			else if (page==1) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageActive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Loading Menu Key Controls");
				r2.setText("B - to come back to the previous menu");
				r3.setText("");
				r4.setText("");
				r5.setFont(fontT);
				r5.setForeground(new Color(0,0,140));
				r5.setHorizontalAlignment(SwingConstants.CENTER);
				r5.setBounds(0,250,WIDTH, 150);
				r5.setText("Option Menu Key Controls");
				r6.setText("B - to come back to the previous menu");
				r7.setText("Q - to leave the game");
				r8.setText("Esc - to leave the game");
				r9.setText("");
				
			}
			
			else if (page==2) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageActive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("About Menu Key Controls");
				r2.setText("B - to come back to the previous menu");
				r3.setText("");
				r4.setText("");
				r5.setFont(fontT);
				r5.setForeground(new Color(0,0,140));
				r5.setHorizontalAlignment(SwingConstants.CENTER);
				r5.setBounds(0,250,WIDTH, 150);
				r5.setText("Controls Menu Key Controls");
				r6.setText("B - to come back to the previous menu");
				r7.setText("Right arrow key - to go to the next page");
				r8.setText("Left arrow key - to go to the previous page");
				r9.setText("");
				
			}
			
			else if (page==3) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageActive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Statistics Menu Key Controls");
				r2.setText("B - to come back to the previous menu");
				r3.setText("");
				r4.setFont(fontT);
				r4.setForeground(new Color(0,0,140));
				r4.setHorizontalAlignment(SwingConstants.CENTER);
				r4.setBounds(0,200,WIDTH, 150);
				r4.setText("Levels Menu Key Controls");
				r5.setText("Right arrow key - to go to the next page");
				r6.setText("Left arrow key - to go to the previous page");
				r7.setText("B - to come back to the previous menu");
				r8.setText("Q - to leave the game");
				r9.setText("Esc - to leave the game");
				
			}
			
			else if (page==4) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageActive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Game Menu Key Controls");
				r2.setText("Enter - to start the game");
				r3.setText("L - to choose a level");
				r4.setText("S - to save the game");
				r5.setText("C - to open controls menu");
				r6.setText("T - to open the statistics");
				r7.setText("B - to come back to the previous menu");
				r8.setText("Q - to leave the game");
				r9.setText("Esc - to leave the game");
				
			}
			
			else if (page==5) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageActive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Game Controls");
				r2.setText("Mouse - to move the turtle and cards");
				r3.setText("Up arrow key - to make the turtle go up");
				r4.setText("Down arrow key - to make the turtle go down");
				r5.setText("Right arrow key - to make the turtle go right");
				r6.setText("Left arrow key - to make the turtle go left");
				r7.setText("R - to restart the game");
				r8.setText("B - to come back to the previous menu");
				r9.setText("");
				
			}
			
			else if (page==6) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageActive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Game Over Key Controls");
				r2.setText("R - to restart the game");
				r3.setText("M - to come back to the game menu");
				r4.setText("Q - to leave the game");
				r5.setText("Esc - to leave the game");
				r6.setFont(fontT);
				r6.setForeground(new Color(0,0,140));
				r6.setHorizontalAlignment(SwingConstants.CENTER);
				r6.setBounds(0,330,WIDTH, 150);
				r6.setText("Victory Key Controls");
				r7.setBounds(110,390,WIDTH, 150);
				r7.setText("S - to save the game");
				r8.setBounds(110,440,WIDTH, 150);
				r8.setText("M - to come back to the game menu");
				r9.setBounds(110,490,WIDTH, 150);
				r9.setText("N - to go to the next level");
				
			}
			
			else if (page==7) {
				
				g.drawImage(rxInactive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageActive, 525, 650, 28, 28, null);
				
				r1.setText("Creating New Game Key Controls");
				r2.setText("Enter - to confirm");
				r3.setText("Esc - to close the window");
				r4.setText("");
				r5.setFont(fontT);
				r5.setForeground(new Color(0,0,140));
				r5.setHorizontalAlignment(SwingConstants.CENTER);
				r5.setBounds(0,250,WIDTH, 150);
				r5.setText("Overwriting Game Key Controls");
				r6.setText("Enter - to confirm");
				r7.setText("Esc - to close the window");
				r8.setText("");
				r9.setText("");
				
			}
		}
		else {
			
			g.drawImage(dt, 180, 640, 500, 250, null);
			
			if (page==0) {
				
				setDefaultLabel();
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxInactive, 15, 290, 120, 120, null);
				
				g.drawImage(pageActive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Menu' Iniziale");
				r2.setText("N - per iniziare una nuova partita");
				r3.setText("L - per caricare una partita esistente");
				r4.setText("O - per aprire le opzioni");
				r5.setText("Q - per lasciare il gioco");
				r6.setText("Esc - per lasciare il gioco");
				r7.setText("A - per aprire le informazioni");
				r8.setText("C - per aprire i controlli");
				r9.setText("");
				
			}
			
			else if (page==1) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageActive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Menu' Caricamento");
				r2.setText("B - per tornare al menu' precedente");
				r3.setText("");
				r4.setText("");
				r5.setFont(fontT);
				r5.setForeground(new Color(0,0,140));
				r5.setHorizontalAlignment(SwingConstants.CENTER);
				r5.setBounds(0,250,WIDTH, 150);
				r5.setText("Controlli Menu' Opzioni");
				r6.setText("B - per tornare al menu' precedente");
				r7.setText("Q - per lasciare il gioco");
				r8.setText("Esc - per lasciare il gioco");
				r9.setText("");
				
			}
			
			else if (page==2) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageActive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Menu' Informazioni");
				r2.setText("B - per tornare al menu' precedente");
				r3.setText("");
				r4.setText("");
				r5.setFont(fontT);
				r5.setForeground(new Color(0,0,140));
				r5.setHorizontalAlignment(SwingConstants.CENTER);
				r5.setBounds(0,250,WIDTH, 150);
				r5.setText("Controlli Menu' Controlli");
				r6.setText("B - per tornare al menu' precedente");
				r7.setText("Freccia destra - per andare alla pagina successiva");
				r8.setText("Freccia sinistra - per andare alla pagina precedente");
				r9.setText("");
				
			}
			
			else if (page==3) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageActive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Menu' Statistiche");
				r2.setText("B - per tornare al menu' precedente");
				r3.setText("");
				r4.setFont(fontT);
				r4.setForeground(new Color(0,0,140));
				r4.setHorizontalAlignment(SwingConstants.CENTER);
				r4.setBounds(0,200,WIDTH, 150);
				r4.setText("Controlli Menu' Livelli");
				r5.setText("Freccia destra - per andare alla pagina successiva");
				r6.setText("Freccia sinistra - per andare alla pagina precedente");
				r7.setText("B - per tornare al menu' precedente");
				r8.setText("Q - per lasciare il gioco");
				r9.setText("Esc - per lasciare il gioco");
				
			}
			
			else if (page==4) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageActive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Menu' Gioco");
				r2.setText("Enter - per iniziare a giocare");
				r3.setText("L - per scegliere un livello");
				r4.setText("S - per salvare il gioco");
				r5.setText("C - per aprire i controlli");
				r6.setText("T - per aprire le statistiche");
				r7.setText("B - per tornare al menu' precedente");
				r8.setText("Q - per lasciare il gioco");
				r9.setText("Esc - per lasciare il gioco");
				
			}
			
			else if (page==5) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageActive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Gioco");
				r2.setText("Mouse - per muovere la tartaruga e le tessere");
				r3.setText("Freccia su - per muovere la tartaruga in alto");
				r4.setText("Freccia giu' - per muovere la tartaruga in basso");
				r5.setText("Freccia destra - per muovere la tartaruga a destra");
				r6.setText("Freccia sinistra - per muovere la tartaruga a sinistra");
				r7.setText("R - per ricominciare la partita");
				r8.setText("B - per tornare al menu' precedente");
				r9.setText("");
				
			}
			
			else if (page==6) {
				
				g.drawImage(rxActive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageActive, 500, 650, 28, 28, null);
				g.drawImage(pageInactive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli GameOver");
				r2.setText("R - per ricominciare il gioco");
				r3.setText("M - per tornare al menu' gioco");
				r4.setText("Q - per lasciare il gioco");
				r5.setText("Esc - per lasciare il gioco");
				r6.setFont(fontT);
				r6.setForeground(new Color(0,0,140));
				r6.setHorizontalAlignment(SwingConstants.CENTER);
				r6.setBounds(0,330,WIDTH, 150);
				r6.setText("Controlli Vittoria");
				r7.setBounds(110,390,WIDTH, 150);
				r7.setText("S - per salbvare il gioco");
				r8.setBounds(110,440,WIDTH, 150);
				r8.setText("M - per tornare al menu' gioco");
				r9.setBounds(110,490,WIDTH, 150);
				r9.setText("N - per andare al livello successivo");
				
			}
			
			else if (page==7) {
				
				g.drawImage(rxInactive, 765, 290, 120, 120, null);
				g.drawImage(lxActive, 15, 290, 120, 120, null);
				
				g.drawImage(pageInactive, 350, 650, 28, 28, null);
				g.drawImage(pageInactive, 375, 650, 28, 28, null);
				g.drawImage(pageInactive, 400, 650, 28, 28, null);
				g.drawImage(pageInactive, 425, 650, 28, 28, null);
				g.drawImage(pageInactive, 450, 650, 28, 28, null);
				g.drawImage(pageInactive, 475, 650, 28, 28, null);
				g.drawImage(pageInactive, 500, 650, 28, 28, null);
				g.drawImage(pageActive, 525, 650, 28, 28, null);
				
				r1.setText("Controlli Creazione Nuovo Gioco");
				r2.setText("Enter - per confermare");
				r3.setText("Esc - per chiudere la finestra");
				r4.setText("");
				r5.setFont(fontT);
				r5.setForeground(new Color(0,0,140));
				r5.setHorizontalAlignment(SwingConstants.CENTER);
				r5.setBounds(0,250,WIDTH, 150);
				r5.setText("Controlli Sovrascrittura Gioco");
				r6.setText("Enter - per confermare");
				r7.setText("Esc - per chiudere la finestra");
				r8.setText("");
				r9.setText("");
				
			}
		}
		
	}
	
	/**
	 * Imposta le JLabel usate nelle loro impostazioni di default.
	 */
	
	protected void setDefaultLabel () {
		
		r1.setHorizontalAlignment(SwingConstants.CENTER);
		r2.setHorizontalAlignment(SwingConstants.LEFT);
		r3.setHorizontalAlignment(SwingConstants.LEFT);
		r4.setHorizontalAlignment(SwingConstants.LEFT);
		r5.setHorizontalAlignment(SwingConstants.LEFT);
		r6.setHorizontalAlignment(SwingConstants.LEFT);
		r7.setHorizontalAlignment(SwingConstants.LEFT);
		r8.setHorizontalAlignment(SwingConstants.LEFT);
		r9.setHorizontalAlignment(SwingConstants.LEFT);
		
		r1.setForeground(new Color(0,0,140));
		r2.setForeground(new Color(4,142,11));
		r3.setForeground(new Color(4,142,11));
		r4.setForeground(new Color(4,142,11));
		r5.setForeground(new Color(4,142,11));
		r6.setForeground(new Color(4,142,11));
		r7.setForeground(new Color(4,142,11));
		r8.setForeground(new Color(4,142,11));
		r9.setForeground(new Color(4,142,11));
		
		r1.setBounds(0,40,WIDTH, 150);
		r2.setBounds(110,110,WIDTH, 150);
		r3.setBounds(110,160,WIDTH, 150);
		r4.setBounds(110,210,WIDTH, 150);
		r5.setBounds(110,260,WIDTH, 150);
		r6.setBounds(110,310,WIDTH, 150);
		r7.setBounds(110,360,WIDTH, 150);
		r8.setBounds(110,410,WIDTH, 150);
		r9.setBounds(110,460,WIDTH, 150);
		
		r1.setFont(fontT);
		r2.setFont(font);
		r3.setFont(font);
		r4.setFont(font);
		r5.setFont(font);
		r6.setFont(font);
		r7.setFont(font);
		r8.setFont(font);
		r9.setFont(font);
		
	}
	
	/**
	 * Imposta la variabile AbstractMenu c con quello passato come parametro e la pagina a 0.
	 * @param c GameMenu da assocciare alla variabile c
	 */
	
	public void setPanel (AbstractMenu c) {
		this.c=c;
		page=0;
	}

}
