package loaders;

import java.awt.image.BufferedImage;

/**
 * La classe AnimationLoader si occupa di caricare su degli array una sequenza di immagini che fungono da animazioni.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public final class AnimationLoader {

	private BufferedImage[] arrayA, arrayB, arrayC;
	private int index, delay;

	/**
	 * Inizializza gli array e acquisisce le immagini.
	 */
	
	public AnimationLoader() {

		arrayA = new BufferedImage[6];
		arrayB = new BufferedImage[6];
		arrayC = new BufferedImage[6];

		arrayA[0] = ImageLoader.load("/imageAnimation/TurtleA1.png");
		arrayA[1] = ImageLoader.load("/imageAnimation/TurtleA2.png");
		arrayA[2] = ImageLoader.load("/imageAnimation/TurtleA3.png");
		arrayA[3] = ImageLoader.load("/imageAnimation/TurtleA4.png");
		arrayA[4] = ImageLoader.load("/imageAnimation/TurtleA5.png");
		arrayA[5] = ImageLoader.load("/imageAnimation/TurtleA6.png");

		arrayB[0] = ImageLoader.load("/imageAnimation/TurtleB1.png");
		arrayB[1] = ImageLoader.load("/imageAnimation/TurtleB2.png");
		arrayB[2] = ImageLoader.load("/imageAnimation/TurtleB3.png");
		arrayB[3] = ImageLoader.load("/imageAnimation/TurtleB4.png");
		arrayB[4] = ImageLoader.load("/imageAnimation/TurtleB5.png");
		arrayB[5] = ImageLoader.load("/imageAnimation/TurtleB6.png");

		arrayC[0] = ImageLoader.load("/imageAnimation/TurtleC1.png");
		arrayC[1] = ImageLoader.load("/imageAnimation/TurtleC2.png");
		arrayC[2] = ImageLoader.load("/imageAnimation/TurtleC3.png");
		arrayC[3] = ImageLoader.load("/imageAnimation/TurtleC4.png");
		arrayC[4] = ImageLoader.load("/imageAnimation/TurtleC5.png");
		arrayC[5] = ImageLoader.load("/imageAnimation/TurtleC6.png");

	}

	/**
	 * Ogni volta che viene invocato restituisce un'immagine della sequenza A. Poiche' lavora esclusivamente sulla sequenza A, una volta raggiunto l'indice ultimo della sequenza stessa gli indici si aggiornano in quanto basato sull'aritmentica modulare.
	 * @return l'immagine.
	 */
	
	public BufferedImage getA() { 

		delay = (delay + 1) % 10;
		if (delay == 0)
			index = (index + 1) % 6;
		return arrayA[index];
	}

	/**
	 * Ogni volta che viene invocato restituisce un'immagine della sequenza B. Poiche' lavora esclusivamente sulla sequenza B, una volta raggiunto l'indice ultimo della sequenza stessa gli indici si aggiornano in quanto basato sull'aritmentica modulare.
	 * @return l'immagine.
	 */
	
	public BufferedImage getB() { 

		delay = (delay + 1) % 10;
		if (delay == 0)
			index = (index + 1) % 6;
		return arrayB[index];
	}

	/**
	 * Ogni volta che viene invocato restituisce un'immagine della sequenza C. Poiche' lavora esclusivamente sulla sequenza C, una volta raggiunto l'indice ultimo della sequenza stessa gli indici si aggiornano in quanto basato sull'aritmentica modulare.
	 * @return l'immagine.
	 */
	
	public BufferedImage getC() { 

		delay = (delay + 1) % 10;
		if (delay == 0)
			index = (index + 1) % 6;
		return arrayC[index];
	}

}
