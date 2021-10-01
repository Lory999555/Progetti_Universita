package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Utility;
import menuPanel.AbstractMenu;

/**
 * La classe TransitionManager si occupa di gestire le transizioni tra pannelli.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class TransitionManager implements ActionListener {

	private Timer timer;
	private AbstractMenu currentMenu, nextMenu;

	/**
	 * Imposta le variabili AbstractMenu con quelle ricevute come parametro e inizializza il timer.
	 * @param currentMenu il menu corrente
	 * @param nextMenu il menu al quale passare
	 */
	
	public TransitionManager(AbstractMenu currentMenu, AbstractMenu nextMenu) { 
		this.currentMenu = currentMenu;
		this.nextMenu = nextMenu;
		timer = new Timer(8, this);
	}

	/**
	 * Avvia la transizione in uscita dal menu corrente e avvia il timer.
	 */
	
	public void start() {
		currentMenu.transitionOut();
		timer.start();
	}

	/**
	 * Quando il menu corrente ha finito la transizione in uscita carica il menu al quale si sta passando, 
	 * facendogli effettuare la transizione in entrata. Fatto cio' interrompe il timer.
	 */
	
	private void changedMenu() {
		if (currentMenu.isFinished()) {
			nextMenu.setAlphaBeforeIn();

			Utility.FINESTRA.setContentPane(nextMenu);
			Utility.FINESTRA.validate();
			Utility.FINESTRA.repaint();
			nextMenu.requestFocusInWindow();
			nextMenu.setFocusable(true);

			currentMenu.setAlphaAfterOut();
			nextMenu.transitionIn();
			timer.stop();
		}
	}

	/**
	 * Riceve un evento dal timer. Ogni qual volta e' invocato verifica che il menu corrente abbia terminato la transazione in uscita 
	 * e carica il menu al quale si sta passando, facendogli effetuare la transizione in entrata Dopodiche' interrompe il timer.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		changedMenu();
	}
	
}
