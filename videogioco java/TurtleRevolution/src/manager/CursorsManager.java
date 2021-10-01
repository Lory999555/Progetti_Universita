package manager;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;
import main.Utility;

/**
 * La classe CursorsManager si occupa di gestire i cursori.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class CursorsManager {
	
	private Cursor c1, c2, c3, c4, c5;
	
	/**
	 * Rappresenta il tipo di cursore.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	public static enum TypeCursor{
		
		/**
		 * Rappresenta il cursore di default.
		 */
		
		DEFAULT, 
		
		/**
		 * Rappresenta il cursore di help.
		 */
		
		HELP, 
		
		/**
		 * Rappresenta il cursore tartaruga.
		 */
		
		TURTLE, 
		
		/**
		 * Rappresenta il cursore di rotazione.
		 */
		
		ROTATE, 
		
		/**
		 * Rappresenta il cursore invisibile.
		 */
		
		INVISIBLE
	};
	
	/**
	 * Crea le BufferedImage con i vari cursori e le associa alle variabili dei cursori stessi. Imposta, infine, il 
	 * cursore di default della finestra di gioco.
	 */
	
	public CursorsManager(){
		
		BufferedImage cursor1 = ImageLoader.load("/pointer/default.png");
		BufferedImage cursor2 = ImageLoader.load("/pointer/help.png");
		BufferedImage cursor3 = ImageLoader.load("/pointer/rotate.png");
		BufferedImage cursor4 = ImageLoader.load("/pointer/turtle.png");
		BufferedImage cursor5 =  ImageLoader.load("/pointer/invisible.png");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		c1 = toolkit.createCustomCursor(cursor1, new Point(0, 0), "default");
		c2 = toolkit.createCustomCursor(cursor2, new Point(0, 0), "help");
		c3 = toolkit.createCustomCursor(cursor3, new Point(1, 1), "rotate");
		c4 = toolkit.createCustomCursor(cursor4, new Point(0, 0), "turtle");
		c5 = toolkit.createCustomCursor(cursor5, new Point(0, 0), "invisible");
		Utility.FINESTRA.setCursor(c1);
	}
	
	/**
	 * Imposta il cursore della finestra in base al tipo ricevuto come parametro.
	 * @param type il tipo del cursore
	 */
	
	public void setCursor(TypeCursor type){
		switch (type) {
			case DEFAULT: 
				Utility.FINESTRA.setCursor(c1);
				return;
			case HELP: 
				Utility.FINESTRA.setCursor(c2);
				return;
			case ROTATE: 
				Utility.FINESTRA.setCursor(c3);
				return;
			case INVISIBLE:
				Utility.FINESTRA.setCursor(c5);
				return;
			default:
				Utility.FINESTRA.setCursor(c4);
		}
	}
	
}
