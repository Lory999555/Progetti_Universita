package menuPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import loaders.ImageLoader;
import main.Utility;
import menuPanel.OptionMenu.Language;

/**
 * La sottoclasse LoadingMenu rappresenta il menu di caricamento e di creazione di una partita.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class LoadingMenu extends AbstractMenu {
	
	private BufferedImage slotActive, slotInactive, slotActiveIta, slotInactiveIta, bk, di;
	protected boolean state;
	private Font font;
	private JLabel l1,l2,l3;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera, 
	 * carica le immagini presenti al suo interno, imposta la pagina iniziale a 0, carica e imposta i font e le JLabel all'interno del quale si trova il testo 
	 * insieme alla loro posizione sullo schermo.
	 */
	
	public LoadingMenu() {

		setLayout(null);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/sky.jpg");
		LoadingMenuListener listener = new LoadingMenuListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		
		slotActive = ImageLoader.load("/button/emptyActive.png"); 
		slotInactive = ImageLoader.load("/button/emptyInactive.png"); 
		slotActiveIta = ImageLoader.load("/button/vuotoActive.png"); 
		slotInactiveIta = ImageLoader.load("/button/vuotoInactive.png"); 
		
		bk = ImageLoader.load("/button/back.png");
		
		di = ImageLoader.load("/button/dietro.png");
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/GRAFFITI.TTF"));
			font=font.deriveFont(95f);
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
		l1= new JLabel("");
		l2= new JLabel("");
		l3= new JLabel("");
		
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setVerticalAlignment(SwingConstants.BOTTOM); 
		l2.setVerticalAlignment(SwingConstants.BOTTOM);
		l3.setVerticalAlignment(SwingConstants.BOTTOM);
		l1.setForeground(new Color(4,142,11));
		l2.setForeground(new Color(4,142,11));
		l3.setForeground(new Color(4,142,11));
		l1.setBounds(0,220,WIDTH, 150);
		l2.setBounds(0,345,WIDTH, 150);
		l3.setBounds(0,475,WIDTH, 150);
		l1.setFont(font);
		l2.setFont(font);
		l3.setFont(font);
		add(l1);
		add(l2);
		add(l3);
	}
	
	/**
	 * Imposta la variabile booleana state a quella passatagli come parametro.
	 * @param state lo stato corrente
	 */
	
	public void setState(boolean state){
		this.state=state;
	}
	
	@Override
	public void draw(Graphics g) {
		if (OptionMenu.FLAG==Language.ENG)
			g.drawImage(bk, WIDTH / 2 - 250, 620, 500, 250, null);
		else
			g.drawImage(di, WIDTH / 2 - 250 - 10, 620, 500, 250, null);

		if(Utility.FILE.ActiveGame(0)){ 
			String name= Utility.FILE.getGame(0).getName();
			l1.setText(name);
		}
		else if(OptionMenu.FLAG==Language.ENG){
			BufferedImage temp = (state) ? slotActive : slotInactive;
			g.drawImage(temp, WIDTH / 2 - 250, 150, 500, 250, null);
			l1.setText("");
		}
		else {
			BufferedImage temp = (state) ? slotActiveIta : slotInactiveIta;
			g.drawImage(temp, WIDTH / 2 - 250, 150, 500, 250, null);
			l1.setText("");
		}
		
		if(Utility.FILE.ActiveGame(1)){
			String name=Utility.FILE.getGame(1).getName();
			l2.setText(name);
		}
		else if(OptionMenu.FLAG==Language.ENG){
			 BufferedImage temp=(state)?slotActive:slotInactive;
			 g.drawImage(temp, WIDTH / 2 - 250, 280, 500, 250, null);
			 l2.setText("");
		}
		else {
			BufferedImage temp=(state)?slotActiveIta:slotInactiveIta;
			g.drawImage(temp, WIDTH / 2 - 250, 280, 500, 250, null);
			l2.setText("");
		}
		
		if(Utility.FILE.ActiveGame(2)){
			 String name=Utility.FILE.getGame(2).getName();
			 l3.setText(name);
		}
		else if(OptionMenu.FLAG==Language.ENG){
			BufferedImage temp=(state)?slotActive:slotInactive;
			g.drawImage(temp, WIDTH / 2 - 250, 410, 500, 250, null);
			l3.setText("");
		}
		else {
			BufferedImage temp=(state)?slotActiveIta:slotInactiveIta;
			g.drawImage(temp, WIDTH / 2 - 250, 410, 500, 250, null);
			l3.setText("");
		}

	}

}
