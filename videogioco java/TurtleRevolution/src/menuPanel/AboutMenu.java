package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;
import main.Utility;
import manager.AudioManager.Sounds;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;

/**
 * La sottoclasse AboutMenu rappresenta il menu About.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class AboutMenu extends AbstractMenu {
	
	private BufferedImage bk, title1, title2, di, textEng, textIta;
	private Rectangle back;
	
	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera 
	 * e carica le immagini presenti al suo interno.
	 */
	
	public AboutMenu () {
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		InnerListener listener = new InnerListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		bk = ImageLoader.load("/button/back.png");
		title1 = ImageLoader.load("/label/about.png");
		textIta = ImageLoader.load("/label/textAboutIta.png");
		
		title2 = ImageLoader.load("/label/aiuto.png");
		di = ImageLoader.load("/button/dietro.png");
		textEng = ImageLoader.load("/label/textAbout.png");
		
		back = new Rectangle(AbstractMenu.WIDTH / 2 - 115, 720, 230, 65);
		
	}

	@Override
	public void draw (Graphics g) {
		if(OptionMenu.Language.ENG==OptionMenu.FLAG){
			g.drawImage(bk, 180, 640, 500, 250, null);
			g.drawImage(title1, 180, -25, 500, 250, null);
			g.drawImage(textEng, 0, 0, WIDTH, HEIGHT, null);
		}
		else{
			g.drawImage(di, 180, 640, 500, 250, null);
			g.drawImage(title2, 180, -25, 500, 250, null);
			g.drawImage(textIta, 0, 0, WIDTH, HEIGHT, null);
		}
	}
	
	/**
	 * L'innerclass InnerListener e' l'ascoltatore mouse e tastiera del menu AboutMenu.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	private class InnerListener implements Listener {
		
		@Override
		public void mousePressed (MouseEvent e) {
			int x=e.getX();
			int y=e.getY();
			if (back.contains(x,y)) {
				Utility.AUDIO.play(Sounds.CLICK);
				TransitionManager t = new TransitionManager(AboutMenu.this, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
			}
		}
		
		@Override
		public void mouseMoved (MouseEvent e) {
			int x=e.getX();
			int y=e.getY();
			if (back.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}
			else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(AboutMenu.this, Utility.MENU);
				t.start();
				Utility.MENU.startAnimation();
			}
		}
		
	}

}