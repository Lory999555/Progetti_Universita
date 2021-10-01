package menuPanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entity.Card;
import entity.Level;
import main.Utility;
import manager.CursorsManager.TypeCursor;
import manager.MapManager;
import manager.TurtleMovementManager.Direction;
import manager.TransitionManager;
import manager.AudioManager.Musics;
import manager.AudioManager.Sounds;

/**
 * La sottoclasse GamePanelListener rappresenta l'ascoltatore mouse e tastiera del GamePanel.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class GamePanelListener implements Listener {

	private GamePanel panel;
	protected Rectangle restart, back;
	private Rectangle turtle, boundT1A, boundT1B,
			boundT2A, boundT2B,
			boundT3A, boundT3B;

	/**
	 * Imposta la variabile panel a quella passata per parametro e crea i rettangoli che fungono da bottoni.
	 * @param panel il panello a cui l'ascoltatore fa riferimento
	 */
	
	public GamePanelListener(GamePanel panel) {

		this.panel = panel;

		restart = new Rectangle(40, 725, 230, 45);
		back = new Rectangle(680, 720, 155, 60);
		
	}

	@Override 
	public void mousePressed(MouseEvent e) { 
		Object action=e.getSource();
		if(action==panel.turtle){
			panel.turtle.actionMouse();
		}else if (panel.t1.getMovement() && action == panel.t1) {
			MapManager.releaseCell(panel.t1.getXX(), panel.t1.getYY(), panel.map,panel.cell, 0);
		} else if (panel.t2.getMovement() && action == panel.t2) {
			MapManager.releaseCell(panel.t2.getXX(), panel.t2.getYY(), panel.map,panel.cell, 1);
		} else if (panel.t3.getMovement() && action == panel.t3) {
			MapManager.releaseCell(panel.t3.getXX(), panel.t3.getYY(), panel.map,panel.cell, 2);
		} else if (restart.contains(e.getX(), e.getY())) {
			Utility.AUDIO.play(Sounds.CLICK);
			GamePanel temp=new GamePanel(new Level(panel.level));
			Utility.FINESTRA.setContentPane(temp);;
			Utility.FINESTRA.validate();
			Utility.FINESTRA.repaint();
			Utility.CURRENT_GAME.incReset();
			Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME); //auto_save
			temp.requestFocusInWindow();
			temp.setFocusable(true);

		} else if (back.contains(e.getX(), e.getY())) {
			Utility.AUDIO.play(Sounds.CLICK);
			Utility.AUDIO.stopAll();
			Utility.AUDIO.play(Musics.ALL_MENU);
			TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
			t.start();
			Utility.MENU_GIOCO.startAnimation();
			Utility.CURRENT_GAME.incDismissed();
			Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME); //auto_save
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { 
		int x = e.getX();
		int y = e.getY(); 
		if (restart.contains(x, y)) {
			Utility.AUDIO.selectionButton(true);
			Utility.CURSOR.setCursor(TypeCursor.TURTLE);
		} else if (back.contains(x, y)) {
			Utility.AUDIO.selectionButton(true);
			Utility.CURSOR.setCursor(TypeCursor.TURTLE);
		}else if(e.getSource() == panel.t1 && panel.t1.possibleRotation()){
			Utility.CURSOR.setCursor(TypeCursor.ROTATE);
		}else if(e.getSource() == panel.t2 && panel.t2.possibleRotation()){
			Utility.CURSOR.setCursor(TypeCursor.ROTATE);
		}else if(e.getSource() == panel.t3 && panel.t3.possibleRotation()){
			Utility.CURSOR.setCursor(TypeCursor.ROTATE);
		}else if (e.getSource() == panel.turtle) {
			Utility.CURSOR.setCursor(TypeCursor.INVISIBLE);
		} else{
			Utility.AUDIO.selectionButton(false);
			Utility.CURSOR.setCursor(TypeCursor.DEFAULT);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >= 2) {
			Object action=e.getSource();
			if (action == panel.t1 && panel.t1.getMovement()) {
				panel.t1.rotate();
			} else if (panel.t2.getMovement() && action == panel.t2) {
				panel.t2.rotate();
			} else if (panel.t3.getMovement() && action == panel.t3) {
				panel.t3.rotate();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object action=e.getSource();
		if (action == panel.t1 && panel.t1.getMovement()) {
			
			panel.t1.setX(panel.t1.getX() + e.getX());
			panel.t1.setY(panel.t1.getY() + e.getY());
			if (MapManager.allocateCard(panel.t1, panel.map, panel.cell, 0)){
				panel.maxMovement--;
				panel.ar.start();
				panel.jl.setText(""+panel.maxMovement);
			}
			panel.t1.setBounds(panel.t1.getXX() - 50, panel.t1.getYY() - 50, 100, 100);
		} else if (action == panel.t2 && panel.t2.getMovement()) {
			panel.t2.setX(panel.t2.getX() + e.getX());
			panel.t2.setY(panel.t2.getY() + e.getY());
			 if (MapManager.allocateCard(panel.t2, panel.map, panel.cell, 1)){
				panel.maxMovement--;
				panel.ar.start();
				panel.jl.setText(""+panel.maxMovement);
			}
			panel.t2.setBounds(panel.t2.getXX() - 50, panel.t2.getYY() - 50, 100, 100);
		} else if (action == panel.t3 && panel.t3.getMovement()) {
			panel.t3.setX(panel.t3.getX() + e.getX());
			panel.t3.setY(panel.t3.getY() + e.getY());
			if (MapManager.allocateCard(panel.t3, panel.map, panel.cell, 2)){
				panel.maxMovement--;
				panel.ar.start();
				panel.jl.setText(""+panel.maxMovement);
			}
			panel.t3.setBounds(panel.t3.getXX() - 50, panel.t3.getYY() - 50, 100, 100);
		}

		if(panel.maxMovement<0){ //gameover
			Utility.GAME_OVER.setState(panel.level);
			Utility.FINESTRA.setContentPane(Utility.GAME_OVER);
			Utility.FINESTRA.validate();
			Utility.FINESTRA.repaint();
			Utility.CURRENT_GAME.incGOver();
			Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME); //auto_save
			Utility.GAME_OVER.requestFocusInWindow();
			Utility.GAME_OVER.setFocusable(true);
			
			Utility.AUDIO.stopAll();
			Utility.AUDIO.play(Sounds.GAMEOVER);
			Utility.AUDIO.play(Musics.GAME_LOW);

		}
	}
	
	public void mouseDragged(MouseEvent e) {
		generateBounds();
		Object action=e.getSource();
		if (action == panel.t1 && panel.t1.getMovement())
			panel.t1.setLocation(panel.t1.getX() + e.getX() - 50, panel.t1.getY() + e.getY() - 50);

		else if (action == panel.t2 && panel.t2.getMovement())
			panel.t2.setLocation(panel.t2.getX() + e.getX() - 50, panel.t2.getY() + e.getY() - 50);

		else if (action == panel.t3 && panel.t3.getMovement())
			panel.t3.setLocation(panel.t3.getX() + e.getX() - 50, panel.t3.getY() + e.getY() - 50);

		else if (action == panel.turtle)
			possibleMovement( e.getX(), e.getY());

	}

	@Override
	public void keyPressed(KeyEvent e) {
		generateBounds();
		int key=e.getKeyCode();
		//NB IL +14 CI STA PERCHE IN LOCATETURTLE LOCHIAMO A -14 IN MODO TALE CHE SE LA PRENDIAMO CON IL MOUSE LA PRENDIAMO AL CENTRO
		if (key==KeyEvent.VK_LEFT){
			possibleMovement( -2+14, 0+14);//VELOCITA 2
			panel.turtle.move(Direction.WEST);
			Utility.AUDIO.movementTurtle(true);
		}
		else if (key==KeyEvent.VK_RIGHT){
			possibleMovement( 2+14, 0+14);
			panel.turtle.move(Direction.EAST);
			Utility.AUDIO.movementTurtle(true);
		}
		else if (key==KeyEvent.VK_UP ){
			possibleMovement( 0+14, -2+14);
			panel.turtle.move(Direction.NORTH);
			Utility.AUDIO.movementTurtle(true);
		}
		else if (key==KeyEvent.VK_DOWN ){
			possibleMovement( 0+14, 2+14);
			panel.turtle.move(Direction.SOUTH);
			Utility.AUDIO.movementTurtle(true);
		}
		else if(key==KeyEvent.VK_R){ //restart
			GamePanel temp=new GamePanel(new Level(panel.level));
			Utility.FINESTRA.setContentPane(temp);;
			Utility.FINESTRA.validate();
			Utility.FINESTRA.repaint();
			Utility.CURRENT_GAME.incReset();
			Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME); //auto_save
			temp.requestFocusInWindow();
			temp.setFocusable(true);
		}
		else if(key==KeyEvent.VK_ESCAPE || key==KeyEvent.VK_B){
			Utility.AUDIO.stopAll();
			Utility.AUDIO.play(Musics.ALL_MENU);
			TransitionManager t = new TransitionManager(panel, Utility.MENU_GIOCO);
			t.start();
			Utility.MENU_GIOCO.startAnimation();
			Utility.CURRENT_GAME.incDismissed();
			Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME); //auto_save
			
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_RIGHT) {
			Utility.AUDIO.movementTurtle(false);
			for (int i=0; i<6; i++) {
				panel.turtle.move(Direction.IN_EAST);
				Utility.FINESTRA.repaint();
			}
			panel.turtle.setCorner(0);
		}
		if (key==KeyEvent.VK_LEFT) {
			Utility.AUDIO.movementTurtle(false);
			for (int i=0; i<6; i++) {
				panel.turtle.move(Direction.IN_WEST);
				Utility.FINESTRA.repaint();
			}
			panel.turtle.setCorner(Math.PI);
		}
		if (key==KeyEvent.VK_DOWN) {
			Utility.AUDIO.movementTurtle(false);
			for (int i=0; i<6; i++) {
				panel.turtle.move(Direction.IN_SOUTH);
				Utility.FINESTRA.repaint();
			}
			panel.turtle.setCorner(Math.PI/2);
		}
		if (key==KeyEvent.VK_UP) {
			Utility.AUDIO.movementTurtle(false);
			for (int i=0; i<6; i++) {
				panel.turtle.move(Direction.IN_NORTH);
				Utility.FINESTRA.repaint();
			}
			panel.turtle.setCorner(-Math.PI/2);
		}
	}

	/**
	 * Genera i vincoli della tartaruga di gioco e delle tessere, all'interno dei quali la tartaruga stessa piu' muoversi.
	 */
	
	private void generateBounds(){
		turtle= panel.turtle.shape();
		boundT1A=panel.t1.bondA();
		boundT1B=panel.t1.bondB();
		boundT2A=panel.t2.bondA();
		boundT2B=panel.t2.bondB();
		boundT3A=panel.t3.bondA();
		boundT3B=panel.t3.bondB();
	}
	
	/**
	 * Alloca la tartaruga di gioco nelle coordinate passategli come parametro.
	 * @param x il valore della coordinata x
	 * @param y il valore della coordinata y
	 */
	
	private void locateTurtle(int x, int y){
		
		panel.turtle.setLocation( x -14,  y-14 );
		panel.turtle.setX(x);
		panel.turtle.setY(y);

	}
	
	/**
	 * Attiva i movimenti sulle tessere e rende inattivo il movimento sulle tessere passategli come parametro.
	 * @param c1 la prima tessera su cui disattivare il movimento
	 * @param c2 la seconda tessera su cui disattivare il movimento
	 */
	
	private void busyCard(Card c1, Card c2){
		panel.t1.setMovement(true);
		panel.t2.setMovement(true);
		panel.t3.setMovement(true);
		if(c1!=null)
			c1.setMovement(false);
		if(c2!=null)
			c2.setMovement(false);
	}
	
	/**
	 * Verifica se il movimento della tartaruga di gioco all'interno della singola tessera passatagli come 
	 * parametro e' possibile nelle coordinate ricevute, anch'esse, come parametro.
	 * @param c la tessera in cui verificare il movimento
	 * @param x la coordinata x in cui verificare il movimento
	 * @param y la coordinata y in cui verificare il movimento
	 */
	
	private void verify1(Card c, int x, int y){

		Rectangle turtle=new Rectangle(x-7, y-7, 14, 14); 

		boolean firstBondOfC=c.bondA().intersects(turtle);
		boolean secondBondOfC=c.bondB().intersects(turtle); 

		//SE C E A INTERSECANO LA PALLINA ED OVVIAMENTE SE C!=A
		if(!c.equals(panel.A) && (firstBondOfC || secondBondOfC) &&
				(panel.A.bondA().intersects(turtle) || panel.A.bondB().intersects(turtle))){}
		//SE C E T1 INTERSECANO LA PALLINA ED OVVIAMENTE SE C!=T1
		else if(!c.equals(panel.t1) && (firstBondOfC || secondBondOfC) &&
				(panel.t1.bondA().intersects(turtle) || panel.t1.bondB().intersects(turtle))){}
		//SE C E T2 INTERSECANO LA PALLINA ED OVVIAMENTE SE C!=T2
		else if(!c.equals(panel.t2) && (firstBondOfC || secondBondOfC) &&
				(panel.t2.bondA().intersects(turtle) || panel.t2.bondB().intersects(turtle))){}
		//SE C E T3 INTERSECANO LA PALLINA ED OVVIAMENTE SE C!=T3
		else if(!c.equals(panel.t3) && (firstBondOfC ||secondBondOfC) &&
				(panel.t3.bondA().intersects(turtle) || panel.t3.bondB().intersects(turtle))){}
		//SE C E B INTERSECANO LA PALLINA ED OVVIAMENTE SE C!=B
		else if(!c.equals(panel.B) && !c.equals(panel.A) && (firstBondOfC || secondBondOfC) &&
				(panel.B.bondA().intersects(turtle) || panel.B.bondB().intersects(turtle))){}
		else if(!c.bondA().contains(turtle) && !c.bondB().contains(turtle))
			return;

		locateTurtle(x,y); 
	}
	
	/**
	 * Dopo aver invocato {@link #busyCard(Card,Card)} permette il movimento della tartaruga di gioco sulle due tessere 
	 * passategli come parametro nelle coordinate ricevute, anch'esse, come parametro.
	 * @param c1 la prima tessera su cui verificare il movimento
	 * @param x la coordinata x in cui verificare il movimento
	 * @param y coordinata y in cui verificare il movimento
	 */

	private void verify2(Card c1, Card c2, int x, int y ){
		busyCard(c1,c2);
		Rectangle turtle= new Rectangle(x-7, y-7, 14, 14);
		if(!((c1.bondB().intersects(turtle) || c1.bondA().intersects(turtle)) &&
				(c2.bondA().intersects(turtle) || c2.bondB().intersects(turtle))))
			return;
		locateTurtle(x,y);
	}
	
	/**
	 * Verifica ed effettua il possibile movimento della tartaruga di gioco nelle coordinate passategli come parametro, 
	 * invocando i metodi {@link #busyCard(Card,Card)} e {@link #locateTurtle(int,int)}. Nel caso in cui si raggiunga la 
	 * tessera finale effettua la transizione al pannello di vittoria, mentre nel caso in cui si superino le mosse massime 
	 * previste dal livello che si sta correntemente giocando effettua la transizione al pannello di game over.
	 * @param x la coordinata x in cui verificare il movimento
	 * @param y la coordinata y in cui verificare il movimento
	 */
	
	private void possibleMovement(int x, int y) {
		if (panel.B.bondB().contains(turtle)
				&& panel.B.bondB().contains(panel.turtle.getX() + x, panel.turtle.getY() + y)
				|| panel.B.bondA().contains(turtle)
				&& panel.B.bondA().contains(panel.turtle.getX() + x, panel.turtle.getY() + y)) {
			
			busyCard(null,null);
			locateTurtle( x+panel.turtle.getX(), panel.turtle.getY()+y);

			Utility.CURRENT_GAME.won(panel.level-1); 
			Utility.VICTORY.setState(panel.level);
			Utility.FINESTRA.setContentPane(Utility.VICTORY);
			Utility.FINESTRA.validate();
			Utility.FINESTRA.repaint();
			Utility.CURRENT_GAME.incWon();
			Utility.FILE.setGame(Utility.CURRENT_INDEX, Utility.CURRENT_GAME); //auto_save
			Utility.VICTORY.requestFocusInWindow();
			Utility.VICTORY.setFocusable(true);
			Utility.AUDIO.stopAll();
			Utility.AUDIO.play(Sounds.VICTORY);
			Utility.AUDIO.play(Musics.GAME_LOW);
		} // movimento su B

		else if (panel.A.bondB().contains(turtle)
				&& panel.A.bondB().contains(panel.turtle.getX() + x, panel.turtle.getY() + y)
				|| panel.A.bondA().contains(turtle)
						&& panel.A.bondA().contains(panel.turtle.getX() + x, panel.turtle.getY() + y)) {

			busyCard(null,null);
			verify1(panel.A, x+panel.turtle.getX(), panel.turtle.getY()+y);
		} // movimento su A

		else if (boundT1B.contains(turtle)
				&& boundT1B.contains(panel.turtle.getX() + x, panel.turtle.getY() + y)
				|| boundT1A.contains(turtle)
						&& boundT1A.contains(panel.turtle.getX() + x, panel.turtle.getY() + y)) {

			busyCard(panel.t1,null);
			verify1(panel.t1,x+panel.turtle.getX(), panel.turtle.getY()+y);
		} // movimento su t1

		else if (boundT2B.contains(turtle)
				&& boundT2B.contains(panel.turtle.getX() + x, panel.turtle.getY() + y)
				|| boundT2A.contains(turtle)
						&& boundT2A.contains(panel.turtle.getX() + x, panel.turtle.getY() + y)) {

			busyCard(panel.t2,null);
			verify1(panel.t2,x+panel.turtle.getX(), panel.turtle.getY()+y);
		} // movimento su t2

		else if (boundT3B.contains(turtle)
				&& boundT3B.contains(panel.turtle.getX() + x, panel.turtle.getY() + y)
				|| boundT3A.contains(turtle)
						&& boundT3A.contains(panel.turtle.getX() + x, panel.turtle.getY() + y)) {

			busyCard(panel.t3,null);
			verify1(panel.t3,x+panel.turtle.getX(), panel.turtle.getY()+y);
		} // movimento su t3
		
		// MOVIMENTI TRA TESSERE!
		else if (panel.A.bondB().intersects(turtle) || panel.A.bondA().intersects(turtle)){
			if (boundT1A.intersects(turtle) || boundT1B.intersects(turtle))
				verify2(panel.A, panel.t1,x+panel.turtle.getX(), panel.turtle.getY()+y);
			else if (boundT2A.intersects(turtle) || boundT2B.intersects(turtle))
				verify2(panel.A, panel.t2, x+panel.turtle.getX(), panel.turtle.getY()+y);
			else if (boundT3A.intersects(turtle) || boundT3B.intersects(turtle))
				verify2(panel.A, panel.t3, x+panel.turtle.getX(), panel.turtle.getY()+y);
		}
		else if (boundT1B.intersects(turtle) || boundT1A.intersects(turtle)){
			if (boundT2A.intersects(turtle) || boundT2B.intersects(turtle))
				verify2(panel.t1, panel.t2, x+panel.turtle.getX(), panel.turtle.getY()+y);
			else if (boundT3A.intersects(turtle) || boundT3B.intersects(turtle))
				verify2(panel.t1, panel.t3, x+panel.turtle.getX(), panel.turtle.getY()+y);
			else if (panel.B.bondA().intersects(turtle) || panel.B.bondB().intersects(turtle))
				verify2(panel.t1, panel.B, x+panel.turtle.getX(), panel.turtle.getY()+y);
		}
		else if (boundT2B.intersects(turtle) || boundT2A.intersects(turtle)){
			if (boundT3A.intersects(turtle) || boundT3B.intersects(turtle))
				verify2(panel.t3, panel.t2, x+panel.turtle.getX(), panel.turtle.getY()+y);
			else if (panel.B.bondA().intersects(turtle) || panel.B.bondB().intersects(turtle))
				verify2(panel.B, panel.t2, x+panel.turtle.getX(), panel.turtle.getY()+y);
		}
		else if (panel.B.bondB().intersects(turtle) || panel.B.bondA().intersects(turtle))
			if (boundT3A.intersects(turtle) || boundT3B.intersects(turtle))
				verify2(panel.B, panel.t3, x+panel.turtle.getX(), panel.turtle.getY()+y);
	}

}
