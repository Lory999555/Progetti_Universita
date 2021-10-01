package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;

/**
 * La sottoclasse GameOver rappresenta il pannello di game over che appare quando si perde la partita.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class GameOverMenu extends AbstractMenu{
	
	private BufferedImage restart, menu, quit, gameOver, e;
	protected int level;
	
	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera 
	 * e carica le immagini presenti al suo interno.
	 */
	
	public GameOverMenu(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		GameOverMenuListener listener = new GameOverMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		quit = ImageLoader.load("/button/quit.png");
		gameOver = ImageLoader.load("/label/gameOver.png");
		restart = ImageLoader.load("/button/restart.png");
		menu = ImageLoader.load("/button/menu.png");
		
		e = ImageLoader.load("/button/esci.png");
		
	}
	
	/**
	 * Imposta la variabile level a quella passata come parametro.
	 * @param level il numero del livello
	 */
	
	public void setState(int level){
		this.level=level;
	}
	
	@Override
	public void draw(Graphics g) {
		if(OptionMenu.Language.ENG==OptionMenu.FLAG)
			g.drawImage(quit, 500, 620, 500, 250, null);
		else 
			g.drawImage(e, 500, 620, 500, 250, null);
		g.drawImage(menu, -110, 620, 500, 250, null);
		g.drawImage(restart, 235, 475, 450, 230, null);
		g.drawImage(gameOver, 100, 70, 750, 400, null);
	}
	
}
