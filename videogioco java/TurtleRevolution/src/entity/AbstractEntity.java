package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * La classe AbstractEntity rappresenta il generico elemento astratto presente all'interno del gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public abstract class AbstractEntity extends JPanel implements Entity {

	protected int x, y;
	protected BufferedImage skin; 
	
	@Override
	public int getXX() {
		return x;
	}
	
	@Override
	public int getYY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Disegna l'entita' attraverso l'uso del Graphics ricevuto come parametro.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	@Override
	public void paintComponent(Graphics g) {
	}

}
