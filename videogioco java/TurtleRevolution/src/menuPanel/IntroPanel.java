package menuPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import loaders.ImageLoader;
import main.Utility;
import manager.AudioManager.Musics;

/**
 * La sottoclasse IntroPanel rappresenta il pannello di intro del gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class IntroPanel extends JPanel implements ActionListener {
		
	private boolean move;
	private int ticks, alpha, cutX, cutY, index,ticksPre;

	
	private final int FADE_IN=300;
	private final int FADE_OUT=230;	
	private final int LENGTH=270;
	
	private Timer timer;
	private BufferedImage logoA, logoB, support;
	private BufferedImage[] turtleRun;
	
	private final int WIDTH=AbstractMenu.WIDTH;
	private final int HEIGHT=AbstractMenu.HEIGHT;
	
	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza e larghezza. Carica le immagini di intro, ne imposta la posizione e inizializza il timer.
	 * Imposta le variabili cutX e cutY e carica nell'array turtleRun le immagini opportune.
	 */
	
	public IntroPanel () {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		logoA=ImageLoader.load("/label/introA.png");
		logoB=ImageLoader.load("/label/introB.png");
		timer=new Timer(15,this);
		timer.start();
		cutY=180;
		cutX=417; 

		turtleRun=new BufferedImage[8];
		turtleRun[0] = ImageLoader.load("/imageAnimation/run1.png");
		turtleRun[1] = ImageLoader.load("/imageAnimation/run2.png");
		turtleRun[2] = ImageLoader.load("/imageAnimation/run3.png");
		turtleRun[3] = ImageLoader.load("/imageAnimation/run4.png");
		turtleRun[4] = ImageLoader.load("/imageAnimation/run5.png");
		turtleRun[5] = ImageLoader.load("/imageAnimation/run6.png");
		turtleRun[6] = ImageLoader.load("/imageAnimation/run7.png");
		turtleRun[7] = ImageLoader.load("/imageAnimation/run8.png");
		
	}
	
	/**
	 * Fa l'aggiornamento del valore della variabile alpha in base al tipo di transizione che si sta effettuando.
	 */
	
	private void update () {
		ticks++;
		if (ticks < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if (ticks==420) 
			move=true;
		if (ticks > FADE_IN + LENGTH) { 
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if (ticks > FADE_IN + LENGTH + FADE_OUT) {
			timer.stop();
			Utility.MENU.setAlphaBeforeIn();
			Utility.FINESTRA.setContentPane(Utility.MENU);
			Utility.FINESTRA.validate();
			Utility.FINESTRA.repaint();
			Utility.MENU.transitionIn();
			Utility.MENU.startAnimation();
			Utility.AUDIO.stopAll();
			Utility.AUDIO.play(Musics.ALL_MENU);
		}
	}

	/**
	 * Disegna il pannello. In base alla circostanze vengono stampate delle immagini piuttosto che altre.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	public void paintComponent (Graphics g) { 
		
		g.setColor(Color.WHITE);
		g.drawImage(logoB, 0, 0, WIDTH, HEIGHT, null);
		g.drawImage(logoA, 0, 0, WIDTH, HEIGHT, null);

		if(cutY<HEIGHT-1)
			cutY++;
		support=logoB.getSubimage(0,cutY,WIDTH-483, HEIGHT-cutY);
		g.drawImage(support, 0, cutY, WIDTH-483, HEIGHT-cutY, null);
		
		if(move && cutX<WIDTH-1)
			cutX++;
		support=logoB.getSubimage(cutX,0,WIDTH-cutX, HEIGHT);
		g.drawImage(support, cutX, 0, WIDTH-cutX, HEIGHT, null);
		
		if(ticks-7==ticksPre){
			ticksPre=ticks;
			index=(1+index)%8;
			g.drawImage(turtleRun[index], ticks+10, 720, 100,100, null);
		}
		else{
			g.drawImage(turtleRun[index], ticksPre+10, 720, 100,100, null);
		}
		
		g.setColor(new Color(255, 255, 255, alpha));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
	}
	
	/**
	 * Riceve un evento dal timer. Ogni volta che viene invocato invoca a sua volta il metodo {@link #update()} e ridisegna il pannello.
	 */
	
	@Override
	public void actionPerformed (ActionEvent e) {
		update();
		repaint();
	}

}
