package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import animation.AutoMovableClouds;
import animation.AutoMovableClouds.Type;
import animation.AutoMovableTurtle;
import loaders.ImageLoader;

/**
 * La sottoclasse GameMenu rappresenta il menu di gioco da cui iniziare a giocare.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class GameMenu extends AbstractMenu {

	protected AutoMovableTurtle turtle;
	protected AutoMovableClouds cloudsL, cloudsS;
	private BufferedImage st, lv, sv, bk, q, statistics, controls, e,s ,di, liv, in;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera, 
	 * carica le immagini presenti al suo interno insieme agli oggetti AutoMovableObject presenti al suo interno. Imposta, infine, la variabile listener a true di default.
	 */
	
	public GameMenu() {

		listener=true;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		
		GameMenuListener listener = new GameMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);

		st = ImageLoader.load("/button/start.png");
		lv = ImageLoader.load("/button/levels.png");
		sv = ImageLoader.load("/button/save.png");
		bk = ImageLoader.load("/button/back.png");
		q = ImageLoader.load("/button/quit.png");
		
		statistics = ImageLoader.load("/button/statistics.png");
		controls = ImageLoader.load("/button/controls.png");
		
		di = ImageLoader.load("/button/dietro.png");
		e = ImageLoader.load("/button/esci.png");
		s = ImageLoader.load("/button/salva.png");
		liv = ImageLoader.load("/button/livelli.png");
		in = ImageLoader.load("/button/inizia.png");

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

		cloudsS.draw(g);
		cloudsL.draw(g);
		turtle.draw(g);
		g.drawImage(statistics, 50, 690, 120, 120, null);
		g.drawImage(controls, 730, 690, 120, 120, null);
		if(OptionMenu.Language.ENG==OptionMenu.FLAG){
			g.drawImage(st, 200, 30, 500, 250, null);
			g.drawImage(lv, 200, 160, 500, 250, null);
			g.drawImage(sv, 200, 290, 500, 250, null);
			g.drawImage(bk, 200, 420, 500, 250, null);
			g.drawImage(q, 200, 550, 500, 250, null);
		}
		else {
			g.drawImage(in, 200, 30, 500, 250, null);
			g.drawImage(liv, 200, 160, 500, 250, null);
			g.drawImage(s, 200, 290, 500, 250, null);
			g.drawImage(di, 200, 420, 500, 250, null);
			g.drawImage(e, 200, 550, 500, 250, null);
		}	
	}

}
