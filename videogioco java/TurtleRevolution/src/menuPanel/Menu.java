package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import animation.AutoMovableClouds;
import animation.AutoMovableClouds.Type;
import animation.AutoMovableTurtle;
import loaders.ImageLoader;
import menuPanel.OptionMenu.Language;

/**
 * La sottoclasse Menu rappresenta il menu iniziale.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Menu extends AbstractMenu {

	private BufferedImage nG, lG, o, q,nu,cG,op,e, about, controls, logoA, logoB;
	private AutoMovableTurtle turtle;
	private AutoMovableClouds cloudsL, cloudsS;
	private int flashTitle; 

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera, 
	 * carica le immagini presenti al suo interno insieme agli oggetti AutoMovableObject presenti al suo interno. Imposta, infine, la variabile flashTitle a 0 di default.
	 */
	
	public Menu() {
		flashTitle=0;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		MenuListener listener = new MenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		nG = ImageLoader.load("/button/newGame.png");
		lG = ImageLoader.load("/button/loadGame.png");
		o = ImageLoader.load("/button/option.png");
		q = ImageLoader.load("/button/quit.png");
		
		nu = ImageLoader.load("/button/nuovo.png");
		cG = ImageLoader.load("/button/carica.png");
		op = ImageLoader.load("/button/opzioni.png");
		e = ImageLoader.load("/button/esci.png");
		
		about = ImageLoader.load("/button/about.png");
		controls = ImageLoader.load("/button/controls.png");
		logoA = ImageLoader.load("/label/TR_A.png");
		logoB = ImageLoader.load("/label/TR_B.png");

		turtle = new AutoMovableTurtle(this);

		cloudsL = new AutoMovableClouds(this, Type.LARGE);

		cloudsS = new AutoMovableClouds(this, Type.SMALL);

	}
	
	/**
	 * Da inizio alle animazioni, attivando i timer degli oggetti auto movibili.
	 */
	
	public void startAnimation(){
		turtle.start();
		cloudsL.start();
		cloudsS.start();
	}
	
	/**
	 * Ferma le animazioni degli oggetti auto movibili bloccandone il timer.
	 */
	
	public void stopAnimation(){
		turtle.stop();
		cloudsL.stop();
		cloudsS.stop();
	}
	
	@Override
	public void draw(Graphics g) {
		flashTitle++;
		
		cloudsS.draw(g);
		cloudsL.draw(g);
		turtle.draw(g);
		
		if(flashTitle<70)
			g.drawImage(logoA, 150, 40, 600, 260, null);
		else if(flashTitle<139)
			g.drawImage(logoB, 150, 40, 600, 260, null);
		else{
			flashTitle=0;
			g.drawImage(logoB, 150, 40, 600, 260, null);
		}
		
		g.drawImage(about, 50, 700, 100, 100, null);
		g.drawImage(controls, 730, 690, 120, 120, null);
		
		if( OptionMenu.FLAG==Language.ENG){
			g.drawImage(nG, WIDTH / 2 - 250, 250, 500, 250, null);
			g.drawImage(lG, WIDTH / 2 - 250, 380, 500, 250, null);
			g.drawImage(o, WIDTH / 2 - 250, 510, 500, 250, null);
			g.drawImage(q, WIDTH / 2 - 250, 640, 500, 250, null);
		}
		else {
			g.drawImage(nu, WIDTH / 2 - 250, 250, 500, 250, null);
			g.drawImage(cG, WIDTH / 2 - 250, 380, 500, 250, null);
			g.drawImage(op, WIDTH / 2 - 250, 510, 500, 250, null);
			g.drawImage(e, WIDTH / 2 - 250, 640, 500, 250, null);
		}
	}

}
