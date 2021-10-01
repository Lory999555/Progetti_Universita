package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NumberPanel extends JPanel{
	private JButton Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9;
	private int dim;
	private JButton lesser,greater,lesserVertical,greaterVertical;
	
	public NumberPanel(ActionListener listener, int dim){
		this.dim=dim;
		this.setPreferredSize(new Dimension(200,100));
		setLayout(new GridLayout(4,4));
		//ButtonListener listener= new ButtonListener();
		Font font= new Font("Arial Black", Font.PLAIN, 15);
		Button1=new JButton("1");
		Button1.setName("1");
		Button1.setFont(font);
		Button1.addActionListener(listener);
		this.add(Button1);
		Button2=new JButton("2");
		Button2.setName("2");
		Button2.setFont(font);
		Button2.addActionListener(listener);
		this.add(Button2);
		Button3=new JButton("3");
		Button3.setName("3");
		Button3.setFont(font);
		Button3.addActionListener(listener);
		this.add(Button3);
		if(dim>=4){
		Button4=new JButton("4");
		Button4.setName("4");
		Button4.setFont(font);
		Button4.addActionListener(listener);
		this.add(Button4);
		}
		if(dim>=5){
		Button5=new JButton("5");
		Button5.setName("5");
		Button5.setFont(font);
		Button5.addActionListener(listener);
		this.add(Button5);
		}
		if(dim>=6){
		Button6=new JButton("6");
		Button6.setName("6");
		Button6.setFont(font);
		Button6.addActionListener(listener);
		this.add(Button6);
		}
		if(dim>=7){
		Button7=new JButton("7");
		Button7.setName("7");
		Button7.setFont(font);
		Button7.addActionListener(listener);
		this.add(Button7);
		}
		if(dim>=8){
		Button8=new JButton("8");
		Button8.setName("8");
		Button8.setFont(font);
		Button8.addActionListener(listener);
		this.add(Button8);
		}
		if(dim==9){
		Button9=new JButton("9");
		Button9.setName("9");
		Button9.setFont(font);
		Button9.addActionListener(listener);
		this.add(Button9);
		}
		lesser=new JButton("<");
		lesser.setName("<");
		lesser.setFont(font);
		lesser.addActionListener(listener);
		this.add(lesser);
		greater=new JButton(">");
		greater.setName(">");
		greater.setFont(font);
		greater.addActionListener(listener);
		this.add(greater);
		lesserVertical=new JButton("^");
		lesserVertical.setName("^");
		lesserVertical.setFont(font);
		lesserVertical.addActionListener(listener);
		this.add(lesserVertical);
		greaterVertical=new JButton("v");
		greaterVertical.setName("v");
		greaterVertical.setFont(font);
		greaterVertical.addActionListener(listener);
		this.add(greaterVertical);
		//this.setBackground(Color.GRAY);
		
	}
	
	public void setEnable(boolean enable){
		Button1.setEnabled(enable);
		Button2.setEnabled(enable);
		Button3.setEnabled(enable);
		if(dim>=4) Button4.setEnabled(enable);
		if(dim>=5) Button5.setEnabled(enable);
		if(dim>=6) Button6.setEnabled(enable);
		if(dim>=7) Button7.setEnabled(enable);
		if(dim>=8) Button8.setEnabled(enable);
		if(dim==9) Button9.setEnabled(enable);
		lesser.setEnabled(enable);
		lesserVertical.setEnabled(enable);
		greater.setEnabled(enable);
		greaterVertical.setEnabled(enable);
	}
	
}
