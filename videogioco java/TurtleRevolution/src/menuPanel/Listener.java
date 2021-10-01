package menuPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * L'interfaccia Listener rappresenta il generico ascoltatore mouse e tastiera per i menu.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public interface Listener extends MouseListener, KeyListener, MouseMotionListener {

	/**
	 * Viene invocato quando un tasto della tastiera e' premuto.
	 */
	
	@Override
	public default void keyPressed(KeyEvent e) {}
	
	/**
	 * Viene invocato quando un tasto della tastiera e' rilasciato.
	 */
	
	@Override
	public default void keyReleased(KeyEvent e) {}
	
	/**
	 * Viene invocato quando un tasto della tastiera e' digitato. 
	 */
	
	@Override
	public default void keyTyped(KeyEvent e) {}

	/**
	 * Viene invocato quando i tasti del mouse sono cliccati (premuti e rilasciati) su un componente.
	 */
	
	@Override
	public default void mouseClicked(MouseEvent e) {}
	
	/**
	 * Viene invocato quando i tasti del mouse sono premuti su un componente.
	 */
	
	@Override
	public default void mousePressed(MouseEvent e) {}

	/**
	 * Viene invocato quando i tasti del mouse sono rilasciati su un componente.
	 */
	
	@Override
	public default void mouseReleased(MouseEvent e) {}

	/**
	 * Viene invocato quando un componente del pannello viene premuto e trascianto col mouse.
	 */
	
	@Override
	public default void mouseDragged(MouseEvent e) {}

	/**
	 * Viene invocato quando il mouse viene mosso sul componente.
	 */
	
	@Override
	public default void mouseMoved(MouseEvent e) {}
	
	/**
	 * Viene invocato quando il mouse entra in un componente.
	 */
	
	@Override
	public default void mouseEntered(MouseEvent e) {}

	/**
	 * Viene invocato quando il mouse esce da un componente.
	 */
	
	@Override
	public default void mouseExited(MouseEvent e) {}

}
