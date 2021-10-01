package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;
import main.Utility;

/**
 * La sottoclasse OptionMenu rappresenta il menu delle opzioni.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */
@SuppressWarnings("serial")
public class OptionMenu extends AbstractMenu {

	/**
	 * Rappresenta la lingua.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	public static enum Language{
		
		/**
		 * Rappresenta la lingua Inglese.
		 */
		
		ENG,
		
		/**
		 * Rappresenta la lingua Italiana.
		 */
		
		ITA
	};

	/**
	 * Rappresenta la lingua corrente.
	*/
	public static Language FLAG;
	
	private BufferedImage bk, q, di,e, language, lingua, sound, 
							suono, music, musica, o , op, eng, ita,
							sActive, sInactive, mInactive, mActive;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera, 
	 * carica le immagini presenti al suo interno, imposta l'inglese come lingua predefinita e la musica e i suoni attivi per default.
	 */
	
	public OptionMenu() {
		
		FLAG=Language.ENG;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		OptionMenuListener listener = new OptionMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);

		bk = ImageLoader.load("/button/back.png");
		q = ImageLoader.load("/button/quit.png");
		language = ImageLoader.load("/label/language.png");
		sound = ImageLoader.load("/label/sound.png");
		music = ImageLoader.load("/label/music.png");
		o = ImageLoader.load("/button/option.png");
		
		di = ImageLoader.load("/button/dietro.png");
		e = ImageLoader.load("/button/esci.png");
		lingua = ImageLoader.load("/label/lingua.png");
		suono = ImageLoader.load("/label/suono.png");
		musica = ImageLoader.load("/label/musica.png");
		op = ImageLoader.load("/button/opzioni.png");
		
		sActive = ImageLoader.load("/button/soundActive.png");
		sInactive = ImageLoader.load("/button/soundInactive.png");
		mActive = ImageLoader.load("/button/musicActive.png");
		mInactive = ImageLoader.load("/button/musicInactive.png");
		ita = ImageLoader.load("/button/ita.png");
		eng = ImageLoader.load("/button/eng.png");

	}

	@Override
	public void draw(Graphics g) {
		if(Language.ENG==FLAG){
			g.drawImage(bk, -120, 620, 500, 250, null);
			g.drawImage(q, 500, 620, 500, 250, null);
			g.drawImage(language, 60, 170, 500, 250, null);
			g.drawImage(sound, 60, 300, 500, 250, null);
			g.drawImage(music, 60, 430, 500, 250, null);
			g.drawImage(o, WIDTH / 2 - 250,-10, 500, 250, null);
			g.drawImage(eng, 600, 250, 80, 80, null);
		}
		else {
			g.drawImage(di, -110, 620, 500, 250, null);
			g.drawImage(e, 500, 620, 500, 250, null);
			g.drawImage(lingua, 60, 170, 500, 250, null);
			g.drawImage(suono, 60, 300, 500, 250, null);
			g.drawImage(musica, 60, 430, 500, 250, null);
			g.drawImage(op, WIDTH / 2 - 250, -10, 500, 250, null);
			g.drawImage(ita, 600, 250, 80, 80, null);
			}
		
		if(Utility.AUDIO.getSoundActive())
			g.drawImage(sActive, 560, 380, 80, 80, null);
		else
			g.drawImage(sInactive, 560, 380, 80, 80, null);
		if(Utility.AUDIO.getMusicActive())
			g.drawImage(mActive, 560, 510, 80, 80, null);
		else
			g.drawImage(mInactive, 560, 510, 80, 80, null);
	}
	
}
