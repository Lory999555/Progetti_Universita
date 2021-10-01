package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Constraint;
import facade.Controller;


public class FutoshikiScheme {
	private JFrame frame;
	private SchemePanel schemePanel;
	private NumberPanel numberPanel;
	private RequestPanel requestPanel; 
	private JTextField numSol;
	private JButton next,previous,empty;
	private boolean n,p;
	private Controller c;
	private int weight,height;
	private int centralPanelHeight=55;
	private int southPanelHeight=150;
	private int cellDimension=30;
	private int northPanelHeight;
	private int overhead=80;
	private int dimension;
	
	public FutoshikiScheme(int dimension,Controller c, Point p){
		this.dimension=dimension;
		this.northPanelHeight=(dimension*2-1)*cellDimension;
		this.weight=700;
		this.height=northPanelHeight+centralPanelHeight+southPanelHeight+overhead;
		this.c=c;
		frame= new JFrame("Futoshiki");
		frame.getContentPane();
		frame.setSize(weight, height);
		frame.setLocation(p);
		frame.setResizable(false);
		
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		ButtonListener listener=new ButtonListener();
		
		schemePanel=new SchemePanel(dimension);
		frame.add(schemePanel);
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
		previous= new JButton("<");
		previous.setName("previous");
		previous.setFont(new Font("Arial Black", Font.PLAIN, 18));
		previous.setBackground(Color.GRAY);
		previous.addActionListener(listener);
		previous.setEnabled(false);
		panel.add(previous);
		
		requestPanel=new RequestPanel(listener);
		panel.add(requestPanel);
		
		next= new JButton(">");
		next.setName("next");
		next.setFont(new Font("Arial Black", Font.PLAIN, 18));
		
		
		next.setBackground(Color.GRAY);
		next.addActionListener(listener);
		next.setEnabled(false);
		panel.add(next);
		
		panel.setPreferredSize(new Dimension(600,55));
		panel.setBackground(Color.GRAY);
		frame.add(panel);
    	
		numberPanel= new NumberPanel(listener,dimension);
		frame.add(numberPanel);
		
		empty= new JButton("EMPTY");
		empty.setName("empty");
		empty.setFont(new Font("Arial Black", Font.PLAIN, 12));
		empty.setSize(50, 50);
		empty.addActionListener(listener);
		frame.add(empty);
		
		JLabel label = new JLabel("Inserire numero soluzioni desiderate: ");
		label.setForeground(Color.BLACK);
		frame.add(label);
		int maxSol = 3*dimension;
    	numSol=new JTextField(Integer.toString(maxSol),4);
    	frame.add(numSol);
		frame.setVisible(true);
		
	
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComponent component=(JComponent)e.getSource();
			String name = component.getName();
			if(name.equals("1")) c.setValue(1);
			else if(name.equals("2")) c.setValue(2);
			else if(name.equals("3")) c.setValue(3);
			else if(name.equals("4")) c.setValue(4);
			else if(name.equals("5")) c.setValue(5);
			else if(name.equals("6")) c.setValue(6);
			else if(name.equals("7")) c.setValue(7);
			else if(name.equals("8")) c.setValue(8);
			else if(name.equals("9")) c.setValue(9);
			else if(name.equals("<")) c.setConstraint(Constraint.Lower,0);
			else if(name.equals(">")) c.setConstraint(Constraint.Greater,0);
			else if(name.equals("^")) c.setConstraint(Constraint.Lower,1);
			else if(name.equals("v")) c.setConstraint(Constraint.Greater,1);
			else if(name.equals("empty")) c.emptyCell();
			else if(name.equals("EMPTY TABLE")) c.emptyScheme();
			else if(name.equals("SOLVE")) c.solve();
			else if(name.equals("next")){
				if(p){
					c.next();
					p= false;
				}
				c.displayNext(); n=true;
			}
			else if(name.equals("previous")){
				if(n){
					c.previous();
					n=false;
				}
				c.displayPrevious(); p=true;
			}
		}
		
	}
	
	public Point getSelectedPoint(){
		return schemePanel.getSelectedPoint();
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void setPointValue(Point p,int value){
		schemePanel.setPointValue(p, value);
	}
	
	public void setPointString(Point p,String s){
		schemePanel.setPointString(p, s);
	}
	
	public void setEmpty(Point p){
		schemePanel.setEmpty(p);
	}
	
	public void emptyScheme(){
		schemePanel.emptyScheme();
		schemePanel.clearSelection();
	}
	
	public void setEnableSolve(boolean b){
		requestPanel.setEnableSolve(b);
	}
	
	public void setEnableNumbers(boolean b){
		numberPanel.setEnable(b);
		empty.setEnabled(b);
	}
	
	public void setEnableNext(boolean b){
		next.setEnabled(b);
	}
	
	public void setEnablePrevious(boolean b){
		previous.setEnabled(b);
	}
	
	public void setEnableSol(boolean b){
		numSol.setEnabled(b);
	}
	
	public String getNumSol(){
		return numSol.getText();
	}
	
	public void setScheme(int[][] s){
		schemePanel.setScheme(s);
	}
}
