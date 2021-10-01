package menuPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import animation.AutoResizing;
import entity.Card;
import entity.Card.Type;
import entity.Level;
import entity.Turtle;
import loaders.ImageLoader;
import manager.MapManager;


/**
 * La sottoclasse GamePanel rappresenta il pannello di gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

@SuppressWarnings("serial")
public class GamePanel extends AbstractMenu {
	
	protected Card t1, t2, t3, B, A; //A partenza B arrivo
	private GamePanelListener listener;
	protected Turtle turtle;
	protected Card[][] map;
	protected Card[] cell;
	protected int level, maxMovement;
	private BufferedImage rs, bk, di;
	protected JLabel jl;
	protected Font font;
	protected AutoResizing ar;

	/**
	 * Imposta le caratteristiche standard del pannello, quali altezza, larghezza e sfondo, fornisce il pannello di appropriati ascoltatori per mouse e tastiera 
	 * e carica le immagini presenti al suo interno.
	 * Riceve come parametro il livello che viene poi associato alla variabile level e dispone il livello per essere giocato.
	 * @param l il livello da giocare
	 */
	
	public GamePanel(Level l) {

		setLayout(null);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground("/background/gameMap.jpg");
		listener = new GamePanelListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addKeyListener(listener);
		level = l.getLivello();

		rs = ImageLoader.load("/button/restart.png");
		bk = ImageLoader.load("/button/back.png");
		
		di = ImageLoader.load("/button/dietro.png");
		
		try { 
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/2GEMELLI_.TTF"));
			font=font.deriveFont(70f); 
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
		arrangeLevel(l);
		
		MapManager.resetting();
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(rs, -15, 660, 330, 190, null);
		if(OptionMenu.Language.ENG==OptionMenu.FLAG)
			g.drawImage(bk, 580, 660, 330, 200, null);
		else
			g.drawImage(di, 580, 660, 330, 200, null);
	}

	/**
	 * Crea il livello e dispone le tessere aggiungendole alla mappa in base al livello passatogli come parametro.
	 * @param l il livello da creare per giocare
	 */
	
	private void arrangeLevel(Level l) { 
		maxMovement=l.getMaxMovement();
		jl=new JLabel(""+maxMovement);
		jl.setFont(font);
		jl.setVerticalAlignment(SwingConstants.CENTER);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setForeground(new Color(0,0,0,80));
		add(jl);
		ar=new AutoResizing(jl);
		
		turtle = l.getTurtle();
		add(turtle);
		turtle.addMouseListener(listener);
		turtle.addMouseMotionListener(listener);
		turtle.setBounds(turtle.getXX() - 14, turtle.getYY() - 14, 28, 28);

		cell = l.getCell();
		t1 = cell[0];
		t2 = cell[1];
		t3 = cell[2];
		A = cell[3];
		B = cell[4];
		add(t1);
		add(t2);
		add(t3);
		add(A);
		add(B);
		t1.addMouseListener(listener);
		t2.addMouseListener(listener);
		t3.addMouseListener(listener);
		t1.addMouseMotionListener(listener);
		t2.addMouseMotionListener(listener);
		t3.addMouseMotionListener(listener);
		t1.setBounds(t1.getXX() - 50, t1.getYY() - 50, 100, 100);
		t2.setBounds(t2.getXX() - 50, t2.getYY() - 50, 100, 100);
		t3.setBounds(t3.getXX() - 50, t3.getYY() - 50, 100, 100);
		A.setBounds(A.getXX() - 50, A.getYY() - 50, 100, 100);
		B.setBounds(B.getXX() - 50, B.getYY() - 50, 100, 100);
		jl.setBounds(A.getXX() - 50, A.getYY() - 50, 100, 100);
		
		map = l.getMap();
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++){
				Card obstacle=map[i][j];
				if (obstacle != null && (obstacle.getType()==Type.WOOD || 
					obstacle.getType()==Type.VOLCANO || obstacle.getType()==Type.ROCKS)) {
					add(obstacle);
					obstacle.setBounds(obstacle.getXX() - 50, obstacle.getYY() - 50, 100, 100);
				}
			}

	}

}
