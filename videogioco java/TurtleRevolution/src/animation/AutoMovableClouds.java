package animation;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import loaders.ImageLoader;

/**
 * La sottoclasse AutoMovableClouds rappresenta un oggetto Clouds capace di muoversi autonomamente sullo sfondo di un Component.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class AutoMovableClouds extends AutoMovableObject {

	private BufferedImage skin;
	/**
	 * Rappresenta le diverse tipologie di nuvole possibili.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	public static enum Type{
		
		/**
		 * Rappresenta le nuvole piccole.
		 */
		
		SMALL,
		
		/**
		 * Rappresenta le nuvole grandi.
		 */
		
		LARGE
	};
	private Type type;

	/**
	 * A seconda del {@link Type} colloca l'immagine in posizioni predefinite, associa il Component su cui effettuare la stampa, istanzia il Timer e carica l'immagine predefinita.
	 * @param c l'oggetto sul quale si disegna
	 * @param type il tipo di nuvola che si desidera creare
	 */
	
	public AutoMovableClouds(Component c, Type type) {
		this.c = c;
		this.type = type;
		switch(type){
			case SMALL:
				x = -2200;
				y = 30;
				timer = new Timer(21, this);
				skin = ImageLoader.load("/imageAnimation/cloudsS.png");
				return;
			case LARGE:
				x = 1000;
				y = 80;
				timer = new Timer(15, this);
				skin = ImageLoader.load("/imageAnimation/cloudsL.png");
				return;
		}
	}

	@Override
	public void move() {
		switch(type){
			case SMALL:
				if (x >= 900) 
					x = -1600;
				x += 1;
				return;
			case LARGE:
				if (x <= -1600)
					x = 1000;
				x -= 1;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(skin, x, y, 1600, 600, null);
	}

}
