package menuPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;
import main.Utility;

/**
 * La sottoclasse LevelsMenu rappresenta il menu nel quale e' possibile visualizzare ed eventualmente scegliere il livello da giocare.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class LevelsMenu extends AbstractMenu {

	private BufferedImage lv1Active, lv2Active, lv3Active, 
						  lv4Active, lv5Active, lv6Active,
						  lv7Active, lv8Active, lv9Active,
						  lv10Active, lv11Active, lv12Active, 
						  lv13Active, lv14Active, lv15Active,
						  lv16Active, lv17Active, lv18Active,
						  lvLoked, pageActive, pageInactive,
						  rxActive, lxActive, rxInactive, lxInactive, bk, q, di, e;
	protected int page;
	protected boolean[] games;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera, 
	 * carica le immagini presenti al suo interno e imposta la pagina iniziale alla pagina 0.
	 */
	
	public LevelsMenu() {
				
		page=0;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		LevelsMenuListener listener = new LevelsMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);

		lvLoked = ImageLoader.load("/preview/locked.png");
		lv1Active = ImageLoader.load("/preview/lv1.png");
		lv2Active = ImageLoader.load("/preview/lv2.png");
		lv3Active = ImageLoader.load("/preview/lv3.png");
		lv4Active = ImageLoader.load("/preview/lv4.png");
		lv5Active = ImageLoader.load("/preview/lv5.png");
		lv6Active = ImageLoader.load("/preview/lv6.png");
		lv7Active = ImageLoader.load("/preview/lv7.png");
		lv8Active = ImageLoader.load("/preview/lv8.png");
		lv9Active = ImageLoader.load("/preview/lv9.png");
		lv10Active = ImageLoader.load("/preview/lv10.png");
		lv11Active = ImageLoader.load("/preview/lv11.png");
		lv12Active = ImageLoader.load("/preview/lv12.png");
		lv13Active = ImageLoader.load("/preview/lv13.png");
		lv14Active = ImageLoader.load("/preview/lv14.png");
		lv15Active = ImageLoader.load("/preview/lv15.png");
		lv16Active = ImageLoader.load("/preview/lv16.png");
		lv17Active = ImageLoader.load("/preview/lv17.png");
		lv18Active = ImageLoader.load("/preview/lv18.png");
		
		rxActive=ImageLoader.load("/button/rxActive.png");
		lxActive=ImageLoader.load("/button/lxActive.png");
		
		rxInactive=ImageLoader.load("/button/rxInactive.png");
		lxInactive=ImageLoader.load("/button/lxInactive.png");
		
		pageActive=ImageLoader.load("/label/pageActive.png");
		pageInactive=ImageLoader.load("/label/pageInactive.png");

		bk = ImageLoader.load("/button/back.png");
		q = ImageLoader.load("/button/quit.png");
		
		di = ImageLoader.load("/button/dietro.png");
		e = ImageLoader.load("/button/esci.png");
		
	}
	
	/**
	 * Associa all'array di boolean partite le partite vinte relative al gioco corrente, e setta la pagina corrente a 0.
	 */
	
	public void set(){
		games= Utility.CURRENT_GAME.getWonLevels();
		page=0;
	}
	
	@Override
	public void draw(Graphics g) { 
		if(OptionMenu.Language.ENG==OptionMenu.FLAG){
			g.drawImage(bk, -120, 620, 500, 250, null);
			g.drawImage(q, 500, 620, 500, 250, null);
		}
		else{
			g.drawImage(di, -110, 620, 500, 250, null);
			g.drawImage(e, 500, 620, 500, 250, null);
		}

		if (page==0) {
			
			g.drawImage(rxActive, 765, 290, 120, 120, null);
			g.drawImage(lxInactive, 15, 290, 120, 120, null);
			
			g.drawImage(lv1Active, 155, 79, 190, 142, null);
			
			g.drawImage(pageActive, 420, 685, 28, 28, null);
			g.drawImage(pageInactive, 445, 685, 28, 28, null);
			
			BufferedImage temp = games[0] ? lv2Active : lvLoked;
			g.drawImage(temp, 355, 79, 190, 142, null);
			
			temp = games[1] ? lv3Active : lvLoked;
			g.drawImage(temp, 555, 79, 190, 142, null);
			
			temp = games[2] ? lv4Active : lvLoked;
			g.drawImage(temp, 155, 279, 190, 142, null);
			
			temp = games[3] ? lv5Active : lvLoked;
			g.drawImage(temp, 355, 279, 190, 142, null);
			
			temp = games[4] ? lv6Active : lvLoked;
			g.drawImage(temp, 555, 279, 190, 142, null);
			
			temp = games[5] ? lv7Active : lvLoked;
			g.drawImage(temp, 155, 479, 190, 142, null);
			
			temp = games[6] ? lv8Active : lvLoked;
			g.drawImage(temp, 355, 479, 190, 142, null);
			
			temp = games[7] ? lv9Active : lvLoked;
			g.drawImage(temp, 555, 479, 190, 142, null);
		}
		
		if (page==1) {
		
			g.drawImage(rxInactive, 765, 290, 120, 120, null);
			g.drawImage(lxActive, 15, 290, 120, 120, null);
			
			g.drawImage(pageInactive, 420, 685, 28, 28, null);
			g.drawImage(pageActive, 445, 685, 28, 28, null);
			
			BufferedImage temp = games[8] ? lv10Active : lvLoked;
			g.drawImage(temp, 155, 79, 190, 142, null);
			
			temp = games[9] ? lv11Active : lvLoked;
			g.drawImage(temp, 355, 79, 190, 142, null);
			
			temp = games[10] ? lv12Active : lvLoked;
			g.drawImage(temp, 555, 79, 190, 142, null);
			
			temp = games[11] ? lv13Active : lvLoked;
			g.drawImage(temp, 155, 279, 190, 142, null);
			
			temp = games[12] ? lv14Active : lvLoked;
			g.drawImage(temp, 355, 279, 190, 142, null);
			
			temp = games[13] ? lv15Active : lvLoked;
			g.drawImage(temp, 555, 279, 190, 142, null);
			
			temp = games[14] ? lv16Active : lvLoked;
			g.drawImage(temp, 155, 479, 190, 142, null);
			
			temp = games[15] ? lv17Active : lvLoked;
			g.drawImage(temp, 355, 479, 190, 142, null);
			
			temp = games[16] ? lv18Active : lvLoked;
			g.drawImage(temp, 555, 479, 190, 142, null);
		}
		
	}

}
