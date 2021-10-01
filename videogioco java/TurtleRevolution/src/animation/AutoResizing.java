package animation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import main.Utility;
import manager.AudioManager.Sounds;

/**
 * La classe AutoResizing rappresenta un oggetto in grado di ridimensionare, attraverso un'animazione, il contenuto di una JLabel.
 * 
 * @author Gualtieri Stefano
 * @author Gagliardi Alessandra
 * @author Morelli Lorenzo
 * @author Cuconato Francesco
 * @version 1.0
 */

public class AutoResizing implements ActionListener {
	
	private Timer timer;
	private JLabel jl;
	private int cont;
	private Font font;
	
	/**
	 * Imposta la variabile jl con la JLabel ricevuta come parametro, inizializza il timer, il contatore e imposta la 
	 * variabile font con quella presa dalla JLabel ricevuta come parametro.
	 * @param jl la JLabel su cui l'oggetto deve lavorare
	 */
	
	public AutoResizing (JLabel jl) {
		
		this.jl=jl;
		timer=new Timer(10,this);
		cont=0;
		font=jl.getFont();
		
	}
	
	/**
	 * Da avvio all'animazione, ponendo a zero il contatore, impostando la grandezza del font della JLabel per l'animazione 
	 * stessa e avviando il timer.
	 */
	
	public void start () {
		Utility.AUDIO.play(Sounds.CARD);
		cont=0;
		font=font.deriveFont(90f);
		jl.setFont(font);
		timer.start();
	}
	
	/**
	 * Rappresenza l'azione vera e propria dell'animazione. Si incrementa il contatore e quando raggiunge un valore 
	 * prefissato ferma il timer e imposta la grandezza del font della JLabel a un valore piu' piccolo rispetto a quello 
	 * impostato quando si avvia il timer.
	 */
	
	public void action () {
		cont++;
		if (cont==30) {
			timer.stop();
			font=font.deriveFont(70f);
			jl.setFont(font);
		}
	}

	/**
	 * Riceve un evento dal timer. Ogni qual volta e' invocato richiama il metodo {@link #action()}.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		action();
	}
}
