package entity;

import entity.Card.Type;

/**
 * La classe Level rappresenta l'oggetto livello necessario ai fini del gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class Level{

	private int level, maxMovement;
	private Card [][] map=new Card[6][8]; 
	private Card [] cell=new Card[5]; //prime tre tessere, poi partenza e arrivo
	private Turtle turtle;
	
	/**
	 * Associa alla variabile level il valore dell'intero ricevuto come parametro.
	 * @param level il numero corrispondente al livello da creare
	 */
	
	public Level (int level) {
		this.level=level;
		loadLevel(level);
	}
	
	/**
	 * Restituisce il livello dell'oggetto.
	 * @return il valore corrispondente alla variabile level.
	 */
	
	public int getLivello () {
		return level;
	}
	
	/**
	 * Restituisce la mappa del livello.
	 * @return la matrice corrispondente alla mappa del livello.
	 */
	
	public Card[][] getMap () {
		return map;
	}
	
	/**
	 * Restituisce il vettore contente le tre tessere da utilizzare in ogni livello oltre all'arrivo e alla partenza.
	 * @return il vettore di tessere.
	 */
	
	public Card[] getCell () {
		return cell;
	}
	
	/**
	 * Restituisce la tartaruga di gioco del livello.
	 * @return la tartaruga di gioco.
	 */
	
	public Turtle getTurtle () {
		return turtle;
	}
	
	/**
	 * Restituisce i movimenti massimi possibili all'interno del livello.
	 * @return il valore della variabile maxMovement.
	 */
	
	public int getMaxMovement () {
		return maxMovement;
	}

	/**
	 * Carica il livello in base al valore della variabile level ricevuta come parametro.
	 * @param level il livello da caricare
	 */
	
	private void loadLevel (int level) {
		Card temp1;
		Card temp2;
		switch (level) {
			case 1:
				maxMovement=11; 
				map[0][3]=new Card(Type.WOOD,400,100,0); 
				map[1][3]=new Card(Type.ROCKS,400,200,0); 
				map[1][6]=new Card(Type.WOOD,700,200,0); 
				map[2][4]=new Card(Type.VOLCANO,500,300,0); 
				map[3][2]=new Card(Type.ROCKS,300,400,0); 
				map[4][5]=new Card(Type.ROCKS,600,500,0); 
				map[5][4]=new Card(Type.WOOD,500,600,0); 
				map[4][0]=new Card(Type.WOOD,100,500,0); 
				
				cell[0]=new Card(Type.CROSS,350,720,3);
				cell[1]=new Card(Type.STRAIGHT,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,2);
				
				cell[1].setCorner(90);
				cell[2].setCorner(90);
				
				temp1 = new Card(Type.START,100,100,0);
				temp2 = new Card(Type.ARRIVAL,800,600,0);
				map[0][0]=temp1; 
				map[5][7]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(100,100);
				return;
				
			case 2:
				maxMovement=11; 
				map[0][0]=new Card(Type.WOOD,100,100,0); 
				map[0][2]=new Card(Type.ROCKS,300,100,0); 
				map[0][5]=new Card(Type.WOOD,600,100,0); 
				map[1][4]=new Card(Type.VOLCANO,500,200,0); 
				map[2][1]=new Card(Type.ROCKS,200,300,0); 
				map[2][6]=new Card(Type.WOOD,700,300,0); 
				map[3][6]=new Card(Type.ROCKS,700,400,0); 
				map[4][2]=new Card(Type.VOLCANO,300,500,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				
				cell[0]=new Card(Type.CROSS,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,0);
				cell[2]=new Card(Type.STRAIGHT,550,720,1);
				cell[1].setCorner(270);
				
				temp1 = new Card(Type.START,200,100,0);
				temp2 = new Card(Type.ARRIVAL,800,300,0);
				map[0][1]=temp1; 
				map[2][7]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(200,100);
				return;
				
			case 3:
				maxMovement=9; 
				map[0][3]=new Card(Type.WOOD,400,100,0); 
				map[1][4]=new Card(Type.VOLCANO,500,200,0); 
				map[1][5]=new Card(Type.ROCKS,600,200,0); 
				map[2][0]=new Card(Type.WOOD,100,300,0); 
				map[2][4]=new Card(Type.ROCKS,500,300,0); 
				map[2][7]=new Card(Type.WOOD,800,300,0); 
				map[4][1]=new Card(Type.ROCKS,200,500,0); 
				map[4][3]=new Card(Type.WOOD,400,500,0); 
				
				cell[0]=new Card(Type.T,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,-1);
				cell[2]=new Card(Type.STRAIGHT,550,720,0);
				cell[0].setCorner(180);
				cell[1].setCorner(270);
				cell[2].setCorner(90);
				
				temp1 = new Card(Type.START,100,400,0);
				temp2 = new Card(Type.ARRIVAL,700,200,0);
				map[3][0]=temp1; 
				map[1][6]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(100,400);
				return;
			
			case 4:
				maxMovement=13; 
				map[0][5]=new Card(Type.WOOD,600,100,0); 
				map[1][3]=new Card(Type.WOOD,400,200,0); 
				map[2][0]=new Card(Type.WOOD,100,300,0); 
				map[2][2]=new Card(Type.ROCKS,300,300,0); 
				map[2][5]=new Card(Type.ROCKS,600,300,0); 
				map[3][4]=new Card(Type.VOLCANO,500,400,0); 
				map[3][7]=new Card(Type.WOOD,800,400,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				map[5][1]=new Card(Type.ROCKS,200,600,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.T,450,720,-1);
				cell[2]=new Card(Type.WOOD,550,720,0);
				cell[0].setCorner(0);
				cell[1].setCorner(270);
				
				temp1 = new Card(Type.START,300,400,0);
				temp2 = new Card(Type.ARRIVAL,500,300,0);
				map[3][2]=temp1; 
				map[2][4]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(300,400);
				return;
				
			case 5:
				maxMovement=11; 
				map[0][1]=new Card(Type.WOOD,200,100,0); 
				map[0][2]=new Card(Type.ROCKS,300,100,0); 
				map[0][3]=new Card(Type.WOOD,400,100,0); 
				map[2][1]=new Card(Type.WOOD,200,300,0); 
				map[4][0]=new Card(Type.ROCKS,100,500,0); 
				map[3][3]=new Card(Type.ROCKS,400,400,0); 
				map[2][5]=new Card(Type.VOLCANO,600,300,0); 
				map[3][6]=new Card(Type.WOOD,700,400,0); 
				map[5][4]=new Card(Type.WOOD,500,600,0); 
				
				cell[0]=new Card(Type.CURVE,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,2);
				cell[2]=new Card(Type.STRAIGHT,550,720,1);
				cell[0].setCorner(270);
				cell[1].setCorner(180);
				
				temp1 = new Card(Type.START,200,200,0);
				temp2 = new Card(Type.ARRIVAL,600,200,0);
				map[1][1]=temp1; 
				map[1][5]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(200,200);
				return;
				
			case 6:
				maxMovement=11; 
				map[1][1]=new Card(Type.WOOD,200,200,0); 
				map[3][1]=new Card(Type.ROCKS,200,400,0); 
				map[4][0]=new Card(Type.WOOD,100,500,0); 
				map[5][2]=new Card(Type.ROCKS,300,600,0); 
				map[5][3]=new Card(Type.VOLCANO,400,600,0); 
				map[3][4]=new Card(Type.WOOD,500,400,0); 
				map[2][5]=new Card(Type.WOOD,600,300,0); 
				map[1][4]=new Card(Type.ROCKS,500,200,0); 
				map[4][6]=new Card(Type.WOOD,700,500,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,2);
				cell[2]=new Card(Type.STRAIGHT,550,720,3);
				cell[1].setCorner(270);
				
				temp1 = new Card(Type.START,100,200,0);
				temp2 = new Card(Type.ARRIVAL,700,600,0);
				map[1][0]=temp1; 
				map[5][6]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(100,200);
				return;
				
			case 7:
				maxMovement=12; 
				map[2][7]=new Card(Type.ROCKS,800,300,0); 
				map[3][4]=new Card(Type.ROCKS,500,400,0); 
				map[2][2]=new Card(Type.WOOD,300,300,0); 
				map[4][4]=new Card(Type.WOOD,500,500,0); 
				map[4][6]=new Card(Type.WOOD,700,500,0); 
				map[5][1]=new Card(Type.ROCKS,200,600,0); 
				map[0][0]=new Card(Type.VOLCANO,100,100,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.CURVE,450,720,2);
				cell[2]=new Card(Type.STRAIGHT,550,720,3);
				cell[1].setCorner(180);
				
				temp1 = new Card(Type.START,600,400,0);
				temp2 = new Card(Type.ARRIVAL,600,500,0);
				map[3][5]=temp1; 
				map[4][5]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(600,400);
				return;
				
			case 8:
				maxMovement=11; 
				map[0][5]=new Card(Type.WOOD,600,100,0); 
				map[1][3]=new Card(Type.ROCKS,400,200,0); 
				map[2][1]=new Card(Type.WOOD,200,300,0); 
				map[2][6]=new Card(Type.ROCKS,700,300,0); 
				map[3][3]=new Card(Type.ROCKS,400,400,0); 
				map[4][3]=new Card(Type.VOLCANO,400,500,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				map[5][4]=new Card(Type.WOOD,500,600,0); 
				
				cell[0]=new Card(Type.CROSS,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,0);
				cell[2]=new Card(Type.STRAIGHT,550,720,3);
				
				temp1 = new Card(Type.START,200,600,0);
				temp2 = new Card(Type.ARRIVAL,700,500,0);
				map[5][1]=temp1; 
				map[4][6]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(200,600);
				return;
			
			case 9:
				maxMovement=9; 
				map[0][2]=new Card(Type.WOOD,300,100,0); 
				map[2][3]=new Card(Type.VOLCANO,400,300,0); 
				map[2][5]=new Card(Type.WOOD,600,300,0); 
				map[3][1]=new Card(Type.ROCKS,200,400,0); 
				map[3][5]=new Card(Type.ROCKS,600,400,0); 
				map[4][2]=new Card(Type.WOOD,300,500,0); 
				map[4][4]=new Card(Type.WOOD,500,500,0); 
				map[5][7]=new Card(Type.ROCKS,800,600,0); 
				
				cell[0]=new Card(Type.CROSS,350,720,0);
				cell[1]=new Card(Type.T,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,0);
				cell[2].setCorner(180);
				
				temp1 = new Card(Type.START,200,300,0);
				temp2 = new Card(Type.ARRIVAL,600,500,0);
				map[2][1]=temp1; 
				map[4][5]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(200,300);
				return;

			case 10:
				maxMovement=12; 
				map[0][4]=new Card(Type.WOOD,500,100,0); 
				map[1][3]=new Card(Type.ROCKS,400,200,0); 
				map[1][0]=new Card(Type.WOOD,100,200,0); 
				map[2][6]=new Card(Type.ROCKS,700,300,0); 
				map[2][4]=new Card(Type.ROCKS,500,300,0); 
				map[3][2]=new Card(Type.VOLCANO,300,400,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				map[5][0]=new Card(Type.WOOD,100,600,0); 
				
				cell[0]=new Card(Type.T,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,-1);
				cell[2]=new Card(Type.STRAIGHT,550,720,0);
				cell[0].setCorner(180);
				cell[1].setCorner(270);
				cell[2].setCorner(90);
				
				temp1 = new Card(Type.START,700,200,0);
				temp2 = new Card(Type.ARRIVAL,200,200,0);
				map[1][6]=temp1; 
				map[1][1]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(700,200);
				return;

			case 11:
				maxMovement=14;
				map[0][2]=new Card(Type.WOOD,300,100,0); 
				map[2][1]=new Card(Type.VOLCANO,200,300,0); 
				map[0][5]=new Card(Type.VOLCANO,600,100,0); 
				map[2][4]=new Card(Type.ROCKS,500,300,0); 
				map[3][2]=new Card(Type.WOOD,300,400,0); 
				map[4][6]=new Card(Type.WOOD,700,500,0); 
				map[4][0]=new Card(Type.WOOD,100,500,0); 
				map[5][4]=new Card(Type.ROCKS,500,600,0); 
				map[3][3]=new Card(Type.WOOD,400,400,0);
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.CURVE,450,720,0);
				cell[2]=new Card(Type.T,550,720,0);
				cell[1].setCorner(180);
				
				temp1 = new Card(Type.START,400,200,0);
				temp2 = new Card(Type.ARRIVAL,200,500,0);
				map[1][3]=temp1; 
				map[4][1]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
			
				turtle=new Turtle(400,200);
				return;
			case 15:
				maxMovement=10; 
				map[0][3]=new Card(Type.WOOD,400,100,0); 
				map[1][2]=new Card(Type.ROCKS,300,200,0); 
				map[4][0]=new Card(Type.WOOD,100,500,0); 
				map[1][6]=new Card(Type.WOOD,700,200,0); 
				map[3][2]=new Card(Type.WOOD,300,400,0); 
				map[3][4]=new Card(Type.VOLCANO,500,400,0); 
				map[4][7]=new Card(Type.ROCKS,800,500,0); 
				map[5][4]=new Card(Type.ROCKS,500,600,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.T,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,2);
			
				cell[2].setCorner(180);
				
				temp1 = new Card(Type.START,600,300,0);
				temp2 = new Card(Type.ARRIVAL,100,300,0);
				map[2][5]=temp1; 
				map[2][0]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(600,300);
				return;
			case 12:
				maxMovement=14; 
				map[0][4]=new Card(Type.WOOD,500,100,0); 
				map[0][5]=new Card(Type.ROCKS,600,100,0); 
				map[1][1]=new Card(Type.ROCKS,200,200,0); 
				map[2][3]=new Card(Type.VOLCANO,400,300,0); 
				map[2][6]=new Card(Type.WOOD,700,300,0); 
				map[3][1]=new Card(Type.WOOD,200,400,0); 
				map[4][4]=new Card(Type.ROCKS,500,500,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				map[5][2]=new Card(Type.ROCKS,300,600,0); 
				
				cell[0]=new Card(Type.T,350,720,0);
				cell[1]=new Card(Type.CURVE,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,0);
				cell[1].setCorner(180);
				
				temp1 = new Card(Type.START,100,500,0);
				temp2 = new Card(Type.ARRIVAL,800,100,0);
				map[4][0]=temp1; 
				map[0][7]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(100,500);
				return;
			case 14:
				maxMovement=16; 
				map[0][4]=new Card(Type.WOOD,500,100,0); 
				map[1][1]=new Card(Type.WOOD,200,200,0); 
				map[4][0]=new Card(Type.ROCKS,100,500,0); 
				map[3][3]=new Card(Type.ROCKS,400,400,0); 
				map[3][4]=new Card(Type.VOLCANO,500,400,0); 
				map[4][4]=new Card(Type.WOOD,500,500,0); 
				map[5][5]=new Card(Type.ROCKS,600,600,0); 
				map[2][6]=new Card(Type.WOOD,700,300,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.T,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,0);
				cell[2].setCorner(180);
				
				temp1 = new Card(Type.START,200,600,0);
				temp2 = new Card(Type.ARRIVAL,700,600,0);
				map[5][1]=temp1; 
				map[5][6]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(200,600);
				return;
			case 13:
				maxMovement=11; 
				map[1][0]=new Card(Type.VOLCANO,100,200,0); 
				map[1][2]=new Card(Type.WOOD,300,200,0); 
				map[3][5]=new Card(Type.ROCKS,600,400,0); 
				map[4][2]=new Card(Type.ROCKS,300,500,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				map[4][7]=new Card(Type.WOOD,800,500,0); 
				map[5][2]=new Card(Type.ROCKS,300,600,0); 
				map[5][3]=new Card(Type.WOOD,400,600,0); 
				map[0][4]=new Card(Type.ROCKS,500,100,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.CURVE,450,720,2);
				cell[2]=new Card(Type.CURVE,550,720,0);
				cell[1].setCorner(270);
				
				temp1 = new Card(Type.START,200,500,0);
				temp2 = new Card(Type.ARRIVAL,700,400,0);
				map[4][1]=temp1; 
				map[3][6]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(200,500);
				return;
			case 17:
				maxMovement=21; 
				map[0][1]=new Card(Type.ROCKS,200,100,0); 
				map[2][2]=new Card(Type.WOOD,300,300,0); 
				map[1][3]=new Card(Type.WOOD,400,200,0); 
				map[4][5]=new Card(Type.ROCKS,600,500,0); 
				map[2][6]=new Card(Type.WOOD,700,300,0); 
				map[5][2]=new Card(Type.WOOD,300,600,0); 
				map[0][6]=new Card(Type.WOOD,700,100,0); 
				map[3][4]=new Card(Type.VOLCANO,500,400,0); 
				map[0][7]=new Card(Type.ROCKS,800,100,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,0);
				cell[1]=new Card(Type.T,450,720,-1);
				cell[2]=new Card(Type.STRAIGHT,550,720,0);
				cell[2].setCorner(90);
				
				temp1 = new Card(Type.START,500,200,0);
				temp2 = new Card(Type.ARRIVAL,100,200,0);
				map[1][4]=temp1; 
				map[1][0]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(500,200);
				return;
			case 16:
				
				maxMovement=21; 
				map[0][1]=new Card(Type.ROCKS,200,100,0); 
				map[2][2]=new Card(Type.ROCKS,300,300,0); 
				map[4][3]=new Card(Type.WOOD,400,500,0); 
				map[4][5]=new Card(Type.WOOD,600,500,0); 
				map[3][5]=new Card(Type.WOOD,600,400,0);  
				map[5][1]=new Card(Type.WOOD,200,600,0); 
				map[5][3]=new Card(Type.VOLCANO,400,600,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.CURVE,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,0);
				cell[1].setCorner(90);
				cell[2].setCorner(270);
				
				temp1 = new Card(Type.START,100,500,0);
				temp2 = new Card(Type.ARRIVAL,800,400,0);
				map[4][0]=temp1; 
				map[3][7]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				
				turtle=new Turtle(100,500);
				return;
			case 18:
				maxMovement=21; 
				map[0][7]=new Card(Type.ROCKS,800,100,0); 
				map[1][2]=new Card(Type.ROCKS,300,200,0); 
				map[1][4]=new Card(Type.WOOD,500,200,0); 
				map[2][1]=new Card(Type.WOOD,200,300,0); 
				map[2][5]=new Card(Type.WOOD,600,300,0); 
				map[4][7]=new Card(Type.ROCKS,800,500,0); 
				map[5][5]=new Card(Type.VOLCANO,600,600,0); 
				
				cell[0]=new Card(Type.STRAIGHT,350,720,3);
				cell[1]=new Card(Type.CURVE,450,720,0);
				cell[2]=new Card(Type.CURVE,550,720,0);
				cell[1].setCorner(90);
				cell[2].setCorner(270);
				
				temp1 = new Card(Type.CURVE,200,500,0);
				temp2 = new Card(Type.ARRIVAL,600,100,0);
				map[4][1]=temp1; 
				map[0][5]=temp2; 
				cell[3]=temp1; 
				cell[4]=temp2; 
				cell[3].setCorner(180);
				
				turtle=new Turtle(200,500);
				return;
			default:
				return;
		}
	}
	
}
