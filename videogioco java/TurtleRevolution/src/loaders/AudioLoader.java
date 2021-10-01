package loaders;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * La classe AudioLoader ha il compito di caricare l'audio.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class AudioLoader {
	
	/**
	 * La classe non e' istanziabile.
	 */
	
	private AudioLoader(){}
	
	/**
	 * Provvede al caricamento dell'audio.
	 * @param directory il percorso dove e' presente l'audio da caricare
	 * @return l'audio appena caricato.
	 */
	
	public static Clip load(String directory){
		Clip clip=null;
		AudioInputStream audio=null;
		URL url =null;
		try {
			url = AudioLoader.class.getResource(directory);           
		    audio = AudioSystem.getAudioInputStream(url);
		    clip = AudioSystem.getClip();
		    clip.open(audio); //warning per Mac
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clip;
	}
	
}
