package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;

/**
 * La sottoclasse Victory rappresenta il pannello di vittoria che appare quando si vince una partita.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class VictoryMenu extends AbstractMenu{

	private BufferedImage sv, menu, next, victory, vittoria, s ,av;
	protected int nextLevel;
	
	/**
	 * Imposta le caratteristiche standar del pannello, quali altezza, larghezza e sfondo. Fornisce il pannello di opportuni 
	 * ascoltatori per mouse e tastiera. Carica le immagini presenti all'interno del pannello stesso e imposta la variabile 
	 * listener a true di default.
	 */
	
	public VictoryMenu(){
		
		setLayout(null);
		listener=true;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		
		VictoryMenuListener listener = new VictoryMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		sv = ImageLoader.load("/button/save.png");
		victory = ImageLoader.load("/label/victory.png");
		menu = ImageLoader.load("/button/menu.png");
		next = ImageLoader.load("/button/next.png");
		
		vittoria = ImageLoader.load("/label/vittoria.png");
		s = ImageLoader.load("/button/salva.png");
		av = ImageLoader.load("/button/avanti.png");
		
	}

	/**
	 * Imposta la variabile nextLevel con quella passata come parametro.
	 * @param PreviousLevel il nuovo valore della variabile nextLevel
	 */
	public void setState(int PreviousLevel){
		nextLevel=PreviousLevel+1;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(menu, -110, 620, 500, 250, null);
		if(OptionMenu.Language.ENG==OptionMenu.FLAG){
			g.drawImage(victory, -30, 80, 900, 400, null);
			g.drawImage(sv, 500, 620, 500, 250, null);
			if(nextLevel<=18)
				g.drawImage(next, 220, 460, 500, 250, null);
		}
		else {
			g.drawImage(vittoria, -30, 80, 900, 400, null);
			g.drawImage(s, 500, 620, 500, 250, null);
			if(nextLevel<=18)
				g.drawImage(av, 220, 460, 500, 250, null);
		}
	}
	
}
