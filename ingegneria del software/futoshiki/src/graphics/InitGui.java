package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.ConcreteFactorySchema;
import entity.FactorySchema;
import facade.SchemeController;

public class InitGui {
	private JFrame frame;
	private JTextField dimension;
	private JButton ok;
	private FactorySchema factory;

	public InitGui(Point location, Dimension d, FactorySchema sf) {
		factory = sf;
		frame = new JFrame("Inizializza Futoshiki");
		frame.setLocation(location);
		frame.setSize(d);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		ButtonListener listener = new ButtonListener();

		JPanel center = new JPanel();
		center.add(new JLabel("Inserire Dimensione: "));
		dimension = new JTextField("3", 3);
		center.add(dimension);
		JPanel south = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(listener);
		south.add(ok);

		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ok) {
				int dim = 3;
				try {
					dim = Integer.parseInt(dimension.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Inserire un valore numerico");
					return;
				}
				if (dim <= 2 || dim > 9) {
					JOptionPane.showMessageDialog(null, "La dimensione deve essere compresa tra 3 e 9!");
					return;
				}
				SchemeController controller = new SchemeController(dim, factory);
				frame.setVisible(false);
			}
		}
	}

	public static void main(String[] args) {
		new InitGui(new Point(600, 400), new Dimension(500, 150), new ConcreteFactorySchema());
	}
}
