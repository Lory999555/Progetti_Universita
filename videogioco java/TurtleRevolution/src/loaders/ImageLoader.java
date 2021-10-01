package loaders;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * La classe ImageLoader ha il compito di caricare le immagini.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class ImageLoader {
	
	/**
	 * La classe non e' istanziabile.
	 */
	
	private ImageLoader(){}
	
	/**
	 * Provvede al caricamento dell'immagine.
	 * @param directory il percorso dove e' presente l'immagine da caricare
	 * @return l'immagine appena caricata
	 */

	public static BufferedImage load(String directory) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(ImageLoader.class.getResourceAsStream(directory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

}
