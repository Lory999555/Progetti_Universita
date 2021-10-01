package entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;
import main.Utility;
import manager.TurtleMovementManager.Direction;

/**
 * La sottoclasse Turtle rappresenta l'entita' tartaruga necessaria ai fini del gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Turtle extends AbstractEntity {

	private final int SIDE = 28;
	private BufferedImage skinKey;
	private double corner;
	private BufferedImage skin;

	/**
	 * Imposta le dimensioni della tartaruga, la sua posizione e associa alla tartaruga stessa due skin che, inizialmente, 
	 * sono uguali di default.
	 * @param x la coordinata x della tartaruga
	 * @param y la coordinata y della tartaruga
	 */
	
	public Turtle(int x, int y) {
		setPreferredSize(new Dimension(SIDE, SIDE));
		this.x = x; 
		this.y = y;
		skin = ImageLoader.load("/imageAnimation/H.png");
		skinKey = skin;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(skinKey, 0, 0, SIDE, SIDE, null);
	}
	
	/**
	 * Imposta la variabile skinKey della tartaruga in base alla direzione in cui sta andando, ricevuta come parametro.
	 * @param d la direzione della tartaruga
	 */
	
	public void move(Direction d){
		skinKey=Utility.MOVEMENT_TURTLE.moveTurtle(d);
	}
	
	/**
	 * Crea un AffineTransform che ruota la skinKey della tartaruga quando invocato.
	 */
	
	public void actionMouse () {
		AffineTransform tx = new AffineTransform();
		tx.rotate(corner,62 / 2, 62 / 2);
		AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		skinKey=op.filter(skin,null);
	}

	/**
	 * Crea un rettangolo dalle dimensioni prefissate le cui coordinate sono date dalla posizione in cui la tartaruga si trova.
	 * @return il rettangolo appena creato.
	 */
	
	public Rectangle shape() {
		return new Rectangle(x-7, y-7, 14, 14);
	}

	/**
	 * Imposta la variabile corner con quella ricevuta come parametro.
	 * @param corner il nuovo valore della variabile corner
	 */
	
	public void setCorner (double corner) {
		this.corner=corner;
	}
}
