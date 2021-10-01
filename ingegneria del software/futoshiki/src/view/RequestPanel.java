package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RequestPanel extends JPanel{
	
	private JButton solve,emptyTable;
	
	public RequestPanel(ActionListener listener){
		super();
		this.setPreferredSize(new Dimension(200,75));
		setLayout(new GridLayout(2,2));
		solve= new JButton("SOLVE");
		solve.setName("SOLVE");
		solve.addActionListener(listener);
		Font font=new Font("Arial Black", Font.PLAIN, 12);
		solve.setFont(font);
		this.add(solve);
		emptyTable= new JButton("EMPTY TABLE");
		emptyTable.setName("EMPTY TABLE");
		emptyTable.setFont(font);
		emptyTable.addActionListener(listener);
		this.add(emptyTable);
		this.setBackground(Color.GRAY);
	}
	
	public void setEnableSolve(boolean enable){
		solve.setEnabled(enable);
	}

}
