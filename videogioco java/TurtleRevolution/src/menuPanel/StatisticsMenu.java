package menuPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import loaders.ImageLoader;
import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.TransitionManager;
import manager.AudioManager.Sounds;

/**
 * La sottoclasse StatisticsMenu rappresenta il menu delle statistiche.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class StatisticsMenu extends AbstractMenu {
	
	private BufferedImage bk, di, sti, ste;
	private Rectangle back;
	private JLabel gO, rs, wn, dsm;
	private Font font;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo. Aggiunge gli appropriati ascoltatori 
	 * per mouse e tastiera. Imposta il font, le JLabel e le loro posizioni predefinite. Carica, infine, le immagini presenti nel 
	 * pannello stesso.
	 */
	
	public StatisticsMenu () {
		
		setLayout(null);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		InnerListener listener = new InnerListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		bk = ImageLoader.load("/button/back.png");
		di = ImageLoader.load("/button/dietro.png");
		sti = ImageLoader.load("/label/statistiche.png");
		ste = ImageLoader.load("/label/statistics.png");
		
		back = new Rectangle(AbstractMenu.WIDTH / 2 - 115, 630, 230, 65);
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/2GEMELLI_.TTF"));
			font=font.deriveFont(45f);
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
		gO = new JLabel("");
		rs = new JLabel("");
		wn = new JLabel("");
		dsm = new JLabel("");
		
		gO.setHorizontalAlignment(SwingConstants.CENTER);
		rs.setHorizontalAlignment(SwingConstants.CENTER);
		wn.setHorizontalAlignment(SwingConstants.CENTER);
		dsm.setHorizontalAlignment(SwingConstants.CENTER);
		
		gO.setVerticalAlignment(SwingConstants.BOTTOM); 
		rs.setVerticalAlignment(SwingConstants.BOTTOM);
		wn.setVerticalAlignment(SwingConstants.BOTTOM);
		dsm.setVerticalAlignment(SwingConstants.BOTTOM);
		
		gO.setForeground(new Color(4,142,11));
		rs.setForeground(new Color(4,142,11));
		wn.setForeground(new Color(4,142,11));
		dsm.setForeground(new Color(4,142,11));
		
		gO.setBounds(0,170,WIDTH, 150);
		rs.setBounds(0,240,WIDTH, 150);
		wn.setBounds(0,310,WIDTH, 150);
		dsm.setBounds(0,380,WIDTH, 150);
		
		gO.setFont(font);
		rs.setFont(font);
		wn.setFont(font);
		dsm.setFont(font);
		
		add(gO);
		add(rs);
		add(wn);
		add(dsm);
		
	}
	
	/**
	 * Imposta, in base alla lingua, il testo presente nel pannello.
	 */
	
	public void set () {
		if(OptionMenu.Language.ENG==OptionMenu.FLAG){
			gO.setText("Lost Games: "+Utility.CURRENT_GAME.getGO());
			rs.setText("Restarted Games: "+Utility.CURRENT_GAME.getR());
			wn.setText("Won Games: "+Utility.CURRENT_GAME.getW());
			dsm.setText("Dismissed Games: "+Utility.CURRENT_GAME.getD());
		}
		else{
			gO.setText("Partite Perse: "+Utility.CURRENT_GAME.getGO());
			rs.setText("Partite Resettate: "+Utility.CURRENT_GAME.getR());
			wn.setText("Partite Vinte: "+Utility.CURRENT_GAME.getW());
			dsm.setText("Partite Abbandonate: "+Utility.CURRENT_GAME.getD());
		}
	}
	
	@Override
	public void draw (Graphics g) {
		if(OptionMenu.Language.ENG==OptionMenu.FLAG){
			g.drawImage(bk, 180, 550, 500, 250, null);
			g.drawImage(ste, 180, 0, 500, 250, null);
		}
		else {
			g.drawImage(di, 180, 550, 500, 250, null);
			g.drawImage(sti, 175, 0, 500, 250, null);
		}
	}
	
	/**
	 * L'innerclass InnerListener e' l'ascoltatore mouse e tastiera del menu StatisticsPanel.
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
				TransitionManager t = new TransitionManager(StatisticsMenu.this, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			}
		}
		
		@Override
		public void mouseMoved (MouseEvent e) {
			int x=e.getX();
			int y=e.getY();
			if (back.contains(x,y)){
				Utility.AUDIO.selectionButton(true);
				Utility.CURSOR.setCursor(TypeCursor.TURTLE);
			}else{
				Utility.AUDIO.selectionButton(false);
				Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_B) {
				TransitionManager t = new TransitionManager(StatisticsMenu.this, Utility.MENU_GIOCO);
				t.start();
				Utility.MENU_GIOCO.startAnimation();
			}
		}

	}
	
}
