package animation;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * La classe AutoMovableObject rappresenta un oggetto capace di muoversi autonomamente sullo sfondo di un Component.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public abstract class AutoMovableObject implements ActionListener {

	protected int x, y;
	protected Component c;
	protected Timer timer;
	
	/**
	 * Avvia il timer.
	 */
	
	public void start() {
		timer.start();
	}

	/**
	 * Ferma il timer.
	 */
	
	public void stop() {
		timer.stop();
	}

	/**
	 * Provvede al movimento dell'immagine.
	 */
	
	protected abstract void move();

	/**
	 * Disegna l'immagine attraverso l'uso del Graphics.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	protected abstract void draw(Graphics g);

	/**
	 * Riceve un evento dal timer. Ogni volta che viene invocato richiama il metodo {@link #move()} e ridisegna il pannello.
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		move();
		c.repaint();
	}

}
