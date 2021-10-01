package manager;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import loaders.ImageLoader;

/**
 * La classe TurtleMovementManager si occupa di gestire il movimento della tartaruga di gioco.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class TurtleMovementManager {
	
	private BufferedImage[] north, south, east, west, inEast, inWest, inNorth, inSouth;
	
	/**
	 * Rappresenta la direzione verso cui si muove la tartaruga e la direzione in cui eventualmente rientra nel guscio.
	 * 
	 * @author Gualtieri Stefano
	 * @author Gagliardi Alessandra
	 * @author Morelli Lorenzo
	 * @author Cuconato Francesco
	 * @version 1.0
	 */
	
	public static enum Direction{
		
		/**
		 * Rappresenta la direzione nord.
		 */
		
		NORTH,
		
		/**
		 * Rappresenta la direzione sud.
		 */
		
		SOUTH,
		
		/**
		 * Rappresenta la direzione ovest.
		 */
		
		WEST,
		
		/**
		 * Rappresenta la direzione est.
		 */
		
		EAST,
		
		/**
		 * Rappresenta la direzione est in cui la tartaruga rientra nel guscio.
		 */
		
		IN_EAST,
		
		/**
		 * Rappresenta la direzione ovest in cui la tartaruga rientra nel guscio.
		 */
		
		IN_WEST, 
		
		/**
		 * Rappresenta la direzione nord in cui la tartaruga rientra nel guscio.
		 */
		
		IN_NORTH,
		
		/**
		 * Rappresenta la direzione sud in cui la tartaruga rientra nel guscio.
		 */
		
		IN_SOUTH
	}; 
	private int nP,oP,eP,sP, inP;
	
	/**
	 * Associa ai vettori di BufferedImage le relative immagini dopo averle caricate e ruotate in modo opportuno.
	 */
	
	public TurtleMovementManager(){

		north= new BufferedImage[6]; nP=-1;
		south= new BufferedImage[6]; sP=-1;
		east= new BufferedImage[6]; eP=-1;
		west= new BufferedImage[6]; oP=-1;
		inEast= new BufferedImage[6]; inP=-1;
		inWest= new BufferedImage[6];
		inNorth= new BufferedImage[6];
		inSouth= new BufferedImage[6];
		
		inEast[0] = ImageLoader.load("/imageAnimation/A.png");
		inEast[1] = ImageLoader.load("/imageAnimation/A.png");
		inEast[2] = ImageLoader.load("/imageAnimation/F.png");
		inEast[3] = ImageLoader.load("/imageAnimation/F.png");
		inEast[4] = ImageLoader.load("/imageAnimation/G.png");
		inEast[5] = ImageLoader.load("/imageAnimation/G.png");

		east[0] = ImageLoader.load("/imageAnimation/B.png");
		east[1] = ImageLoader.load("/imageAnimation/C.png");
		east[2] = east[0];
		east[3] = ImageLoader.load("/imageAnimation/D.png");
		east[4] = ImageLoader.load("/imageAnimation/E.png");
		east[5] = east[3];
		
		imageGeneretor();
	}
	
	/**
	 * Muove la tartaruga di gioco nella direzione specificata dal parametro ricevuto.
	 * @param d la direzione della tartaruga
	 * @return l'immagine della tartaruga corrispondente alla direzione specificata come parametro.
	 */
	
	public BufferedImage moveTurtle(Direction d){
		switch(d){
			case NORTH:
				nP=(nP+1)%north.length;
				return north[nP];
			case SOUTH:
				sP=(sP+1)%south.length;
				return south[sP];
			case EAST:
				eP=(eP+1)%east.length;
				return east[eP];
			case WEST:
				oP=(oP+1)%west.length;
				return west[oP];
			case IN_SOUTH:
				inP=(inP+1)%inSouth.length;
				return inSouth[inP];
			case IN_WEST:
				inP=(inP+1)%inWest.length;
				return inWest[inP];
			case IN_NORTH:
				inP=(inP+1)%inNorth.length;
				return inNorth[inP];
			default:
				inP=(inP+1)%inEast.length;
				return inEast[inP];
		}
	}
	
	/**
	 * Ruota e carica le immagini negli appositi array.
	 */
	
	private void imageGeneretor () {
		AffineTransform tx = new AffineTransform();
		double angle=Math.PI/2;
		tx.rotate(angle,62 / 2, 62 / 2);
		AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		
		BufferedImage temp1=op.filter(east[0],null);
		BufferedImage temp2=op.filter(east[1],null);
		BufferedImage temp3=op.filter(east[2],null);
		BufferedImage temp4=op.filter(east[3],null);
		BufferedImage temp5=op.filter(east[4],null);
		BufferedImage temp6=op.filter(east[5],null);
		
		south[0] = temp1;
		south[1] = temp2;
		south[2] = temp3;
		south[3] = temp4;
		south[4] = temp5;
		south[5] = temp6;
		
		temp1=op.filter(south[0],null);
		temp2=op.filter(south[1],null);
		temp3=op.filter(south[2],null);
		temp4=op.filter(south[3],null);
		temp5=op.filter(south[4],null);
		temp6=op.filter(south[5],null);
		
		west[0] = temp1;
		west[1] = temp2;
		west[2] = temp3;
		west[3] = temp4;
		west[4] = temp5;
		west[5] = temp6;
		
		temp1=op.filter(west[0],null);
		temp2=op.filter(west[1],null);
		temp3=op.filter(west[2],null);
		temp4=op.filter(west[3],null);
		temp5=op.filter(west[4],null);
		temp6=op.filter(west[5],null);
		
		north[0] = temp1;
		north[1] = temp2;
		north[2] = temp3;
		north[3] = temp4;
		north[4] = temp5;
		north[5] = temp6;
		
		temp1=op.filter(inEast[0],null);
		temp2=op.filter(inEast[1],null);
		temp3=op.filter(inEast[2],null);
		temp4=op.filter(inEast[3],null);
		temp5=op.filter(inEast[4],null);
		temp6=op.filter(inEast[5],null);
		
		inSouth[0] = temp1;
		inSouth[1] = temp2;
		inSouth[2] = temp3;
		inSouth[3] = temp4;
		inSouth[4] = temp5;
		inSouth[5] = temp6;
		
		temp1=op.filter(inSouth[0],null);
		temp2=op.filter(inSouth[1],null);
		temp3=op.filter(inSouth[2],null);
		temp4=op.filter(inSouth[3],null);
		temp5=op.filter(inSouth[4],null);
		temp6=op.filter(inSouth[5],null);
		
		inWest[0] = temp1;
		inWest[1] = temp2;
		inWest[2] = temp3;
		inWest[3] = temp4;
		inWest[4] = temp5;
		inWest[5] = temp6;
		
		temp1=op.filter(inWest[0],null);
		temp2=op.filter(inWest[1],null);
		temp3=op.filter(inWest[2],null);
		temp4=op.filter(inWest[3],null);
		temp5=op.filter(inWest[4],null);
		temp6=op.filter(inWest[5],null);
		
		inNorth[0] = temp1;
		inNorth[1] = temp2;
		inNorth[2] = temp3;
		inNorth[3] = temp4;
		inNorth[4] = temp5;
		inNorth[5] = temp6;
	}
	
}
