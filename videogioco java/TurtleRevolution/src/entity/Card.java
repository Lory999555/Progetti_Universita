package entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;
import main.Utility;
import manager.AudioManager.Sounds;

/**
 * La sottoclasse Card rappresenta l'entita' tessera necessaria ai fini del gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Card extends AbstractEntity {

	private final int SIDE = 100;

	/**
	 * Rapresenta la tipologia di tessera.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	public static enum Type {
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad una curva.
		 */
		CURVE, 
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad un incrocio a T.
		 */
		T,
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad una retta.
		 */
		STRAIGHT, 
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad un incrocio a croce.
		 */
		CROSS,
		/**
		 * Rappresenta la tessere il cui percorso corrisponde alla partenza.
		 */
		START, 
		/**
		 * Rappresenta la tessere il cui percorso corrisponde all'arrivo.
		 */
		ARRIVAL, 
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad un ostacolo di tipo bosco.
		 */
		WOOD, 
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad un ostacolo di tipo rocce.
		 */
		ROCKS, 
		/**
		 * Rappresenta la tessere il cui percorso corrisponde ad un ostacolo di tipo vulcano.
		 */
		VOLCANO
	};

	private Type type;
	private BufferedImage skin;
	private int corner, boundRotate;
	private boolean movement;

	/**
	 * Assegna la rotazione della tessera con quella ricevuta come parametro, imposta le dimensioni e le coordinate in base a 
	 * cio' che riceve come parametro. Imposta, inoltre, il movimento della tessera a false e i valori delle variabili 
	 * posCor, posPre e corner di default e assegna la skin in base al tipo ricevuto come parametro.
	 * @param tipo il tipo della tessera per la skin
	 * @param x la coordinata x della tessera
	 * @param y la coordinata y della tessera
	 * @param boundRotate la rotazione della tessera
	 */
	
	public Card(Type tipo, int x, int y, int boundRotate) {
		this.boundRotate= boundRotate;
		setPreferredSize(new Dimension(SIDE, SIDE));
		this.x = x; 
		this.y = y;
		this.type = tipo;
		corner = 0;
		movement = false;
		skinAssignment(tipo);
	}

	/**
	 * Assegna la skin alla tessera in base al tipo ricevuto come parametro.
	 * @param type il tipo della tessera
	 */
	
	private void skinAssignment(Type type) {
		switch (type) {
		case CROSS:
			movement = true;
			skin = ImageLoader.load("/card/croce.jpg");
			return;
		case WOOD:
			skin = ImageLoader.load("/card/bosco.jpg");
			return;
		case VOLCANO:
			skin = ImageLoader.load("/card/vulcano.jpg");
			return;
		case ROCKS:
			skin = ImageLoader.load("/card/rocce.jpg");
			return;
		case START:
			skin = ImageLoader.load("/card/aperto.jpg");
			return;
		case T:
			movement = true;
			skin = ImageLoader.load("/card/t.jpg");
			return;
		case CURVE:
			movement = true;
			skin = ImageLoader.load("/card/curva_l.jpg");
			return;
		case ARRIVAL:
			skin = ImageLoader.load("/card/arrivo.png");
			return;
		default:
			movement = true;
			skin = ImageLoader.load("/card/retta_i.jpg");
			return;
		}
	}

	/**
	 * Restituisce il tipo della tessera su cui viene invocato.
	 * @return il tipo della tessera.
	 */
	
	public Type getType() {
		return type;
	}

	/**
	 * Restituisce il movimento della tessera su cui viene invocato.
	 * @return il movimento della tessera.
	 */
	
	public boolean getMovement() {
		return movement;
	}

	/**
	 * Imposta la variabile movement con quella passatagli come parametro.
	 * @param movement - il nuovo valore della variabile movement
	 */
	
	public void setMovement(boolean movement) {
		this.movement = movement;
	}
	
	/**
	 * Informa sulla possibilita' di effettuare una rotazione.
	 * @return true se e' possibile la rotazione della tessera, false altrimenti.
	 */
	
	public boolean possibleRotation(){
		return boundRotate!=0;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(corner), SIDE / 2, SIDE / 2);// ruota in senso orario
		g2.drawImage(skin, 0, 0, SIDE, SIDE, null);
	}
	
	/**
	 * Effettua la rotazione della tessera e ridisegna la tessera stessa.
	 */

	public void rotate() {
		if(boundRotate==0) 				//nessuna rotazione
			return;
		else if (boundRotate<0){ 		//due rotaz adiacenti 0 e 270
			corner = (corner + 90) % 360;
			if (corner!=0)
				corner=270;
		}
		else if (boundRotate%2==0)  	//due rotaz di 180 per volta
			corner=(corner+180)%360;
		else 							//tutte le rotaz
			corner=(corner+90)%360;
		repaint();
		Utility.AUDIO.play(Sounds.ROTATE_CARD);
	}
	
	/**
	 * Imposta la variabile corner con quella passatagli come parametro.
	 * @param corner il nuovo valore della variabile corner
	 */
	
	public void setCorner(int corner){
		if(corner!=0 || corner!=90 || corner!=180 || corner!=270)
			this.corner=0;
		this.corner=corner;
	}

	/**
	 * In base al tipo di tessera su cui e' invocato, restituisce un vincolo rappresentante il percorso possibile all'interno della tessera stessa.
	 * @return il rettangolo coincidente con il vincolo.
	 */
	
	public Rectangle bondA () { 
		switch (type) {
			case CROSS: 
				return new Rectangle(x-18, y-52, 35, 102); 
			case CURVE:   
				if (corner==0)
					return new Rectangle(x-18, y-17, 35, 68); 
				if (corner==90)
					return new Rectangle(x-52, y-17, 68, 35);
				if (corner==180)
					return new Rectangle(x-18, y-52, 35, 68); 
				return new Rectangle(x-18, y-18, 68, 35); 
			case STRAIGHT: case T:
				if (corner==0 || corner==180)
					return new Rectangle(x-18, y-52, 35, 102);
				return new Rectangle(x-52, y-18, 102, 35); 
			case START: case ARRIVAL:
				return new Rectangle(x-52, y-52, 104, 104); 
			default: 
				return new Rectangle(0, 0, 0, 0);	
		}
	}
	
	/**
	 * In base al tipo di tessera su cui e' invocato, restituisce il secondo vincolo, se esiste, rappresentante il percorso possibile all'interno della tessera stessa.
	 * @return il rettangolo coincidente con il vincolo.
	 */
	
	public Rectangle bondB () { 
		switch (type) {
			case CROSS: 
				return new Rectangle(x-52, y-18, 102, 35); 
			case CURVE: case T:  
				if (corner==0)
					return new Rectangle(x-18, y-17, 68, 35); 
				if (corner==90)
					return new Rectangle(x-18, y-17, 35, 68);
				if (corner==180)
					return new Rectangle(x-52, y-18, 68, 35); 
				return new Rectangle(x-18, y-52, 35, 68); 
			default: 
				return new Rectangle(0, 0, 0, 0);
		}
	}

}
