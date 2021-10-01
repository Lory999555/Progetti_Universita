package view;

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

import controller.SchemeController;
import model.ConstrainedMatrixFactory;
import model.SchemeFactory;

public class InitGui {
    private JFrame frame;
    private JTextField dimension;
    private JButton ok;
    private SchemeFactory factory;
    
    public InitGui(Point location, Dimension d,SchemeFactory sf){
    	factory=sf;
    	frame=new JFrame("Inizializza Futoshiki");
    	frame.setLocation(location);
    	frame.setSize(d);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	ButtonListener listener=new ButtonListener();
    	
    	JPanel center=new JPanel();
    	center.add(new JLabel("Inserire Dimensione: "));
    	dimension=new JTextField("1",3);
    	center.add(dimension);
    	/*
    	center.add(new JLabel("Inserire numero Soluzioni: "));
    	numSolutions=new JTextField("1",3);
    	center.add(numSolutions);
    	*/
    	JPanel south=new JPanel();
    	ok=new JButton("ok");
    	ok.addActionListener(listener);
    	south.add(ok);
    	
    	frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		frame.setVisible(true);
    }
    
    private class ButtonListener implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource()==ok){
    			int num,dim;
    			dim=Integer.parseInt(dimension.getText());
    			if(dim<=2 || dim>9){
    				JOptionPane.showMessageDialog(null, "La dimensione deve essere compresa tra 3 e 9!");
    				return;
    			}
    			SchemeController controller=new SchemeController(dim,factory);
    			//new FutoshikiScheme(dim,factory,new Point(100, 100));
    			//frame.dispose();
    		}
    	}
    }
    public static void main (String[]args){
    	new InitGui(new Point(200, 200), new Dimension(300, 150),new ConstrainedMatrixFactory());
    }
}
