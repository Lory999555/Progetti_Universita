package menuPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import loaders.ImageLoader;

/**
 * La classe AbstractMenu rappresenta un menu generico che possiede alcune determinate caratteristiche quali le dimensioni della finestra.
 * E', inoltre, dotata della possibilita' di effettuare una transizione sia in entrata che in uscita tra due AbstractMenu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public abstract class AbstractMenu extends JPanel implements ActionListener {

	protected static final int WIDTH = 900;
	protected static final int HEIGHT = 820;
	protected boolean listener=true;
	private BufferedImage background;

	private Timer timer = new Timer(4, this);;
	private int alpha, cont;

	private final int FADE_IN = 90; 
	private final int LENGTH = 70; 
	private final int FADE_OUT = 40; 
	
	/**
	 * Disegna l'immagine di sfondo e il pannello tutto, invocando il metodo {@link #draw(Graphics)} che deve essere ridefinito poi da ogni classe concreta. 
	 * Inoltre, vengono impostati i colori.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		this.draw(g);

		g.setColor(new Color(255, 255, 255, alpha));
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	/**
	 * Disegna il menu attraverso l'uso del Graphics.
	 * @param g permette di disegnare all'interno del Component
	 */
	
	protected void draw(Graphics g) {
	}
	
	/**
	 * Carica l'immagine di sfondo.
	 * @param directory la directory dell'immagine da usare come sfondo
	 */
	
	protected void setBackground(String directory) {
		background = ImageLoader.load(directory);
	}

	/**
	 * Permette la transizione in uscita quando si cambia menu.
	 */
	
	public void transitionOut() {
		cont = FADE_IN + LENGTH;
		timer.start();
	}

	/**
	 * Permette la transizione in entrata quando si cambia menu.
	 */
	
	public void transitionIn() {
		cont = 0;
		timer.start();
	}

	/**
	 * Imposta il valore della variabile alpha prima della transizione in entrata.
	 */
	
	public void setAlphaBeforeIn() {
		alpha = 255;
	}

	/**
	 * Imposta il valore della variabile alpha dopo la transizione in uscita.
	 */
	
	public void setAlphaAfterOut() {
		alpha = 0;
	}

	/**
	 * Riceve un evento dal timer. Ogni volta che viene invocato aggiorna il valore della variabile alpha chiamando il metodo {@link #update()} e ridisegna il pannello.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}

	/**
	 * Verifica che la variabile timer sia attiva o meno.
	 * @return true se timer e' attivo.
	 */
	
	public boolean isFinished() {
		return !timer.isRunning();
	}

	/**
	 * Fa l'aggiornamento del valore della variabile alpha in base al tipo di transizione che si sta effettuando.
	 */
	
	private void update() {
		cont++;
		if (cont < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * cont / FADE_IN));
			if (alpha < 0)
				alpha = 0; // 0 trasparente
		}
		if (cont == FADE_IN) {
			timer.stop();
			alpha = 0;
		}
		if (cont > FADE_IN + LENGTH) {
			alpha = (int) (255 * (1.0 * cont - FADE_IN - LENGTH) / FADE_OUT);
			if (alpha > 255)
				alpha = 255; // 255 bianco
		}
		if (cont > FADE_IN + LENGTH + FADE_OUT) {
			timer.stop();
		}
	}
	
	/**
	 * Restituisce la larghezza del pannello.
	 * @return il valore della variabile width.
	 */
	
	public int getWidth(){
		return WIDTH;
	}
	
	/**
	 * Restituisce l'altezza del pannello.
	 * @return il valore della variabile height.
	 */
	
	public int getHeight(){
		return HEIGHT;
	}

	/**
	 * Imposta il valore della varibiale listener con quella ricevuta come parametro.
	 * @param listener il nuovo valore della variabile listener
	 */
	
	public void setListener(boolean listener) {
		this.listener = listener;
	}
	
}
