package animation;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import loaders.AnimationLoader;

/**
 * La sottoclasse AutoMovableTurtle rappresenta un oggetto Turtle capace di muoversi autonomamente sullo sfondo di un Component.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class AutoMovableTurtle extends AutoMovableObject {

	private int location;
	private AnimationLoader al = new AnimationLoader();
	private BufferedImage image;

	/**
	 * Colloca l'immagine in posizioni predefinite, associa il Component su cui effettuare la stampa e istanzia il Timer.
	 * @param c l'oggetto sul quale si disegna
	 */
	
	public AutoMovableTurtle(Component c) {
		location = 1;
		x = -350;
		y = 675;
		this.c = c;
		timer = new Timer(8, this);
	}

	@Override
	public void move() {
		switch (location) {
			case 0:
				if (x <= -500) {
					x = -200;
					location = 1;
				}
				x -= 2;
				break;
			case 1:
				if (y <= 0) {
					x = 920;
					y = 60;
					location = 2;
				}
				x += 2;
				y -= 1;
				break;
			case 2:
				if (x <= -460) {
					x = -220;
					y = 60;
					location = 3;
				}
				x -= 3;
				break;
			case 3:
				if (y >= 530) {
					x = 920;
					y = 510;
					location = 0;
				}
				x += 4;
				y += 1;
				break;
		}
	}

	@Override
	public void draw(Graphics g) {
		switch (location) {
		case 0:
		case 2:
			image = al.getC();
			break;
		case 1:
			image = al.getA();
			break;
		case 3:
			image = al.getB();
			break;
		}
		g.drawImage(image, x, y, 250, 150, null);
	}
}
