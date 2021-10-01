package view;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;

import model.ConstrainedCell;

public class SchemePanel extends JPanel{
	
	private JSchemeTable table;
	private int dim;
	
	public SchemePanel(int dim) {
		table= new JSchemeTable(dim);
		this.add(table);
		this.setBackground(Color.GRAY);
	}
	
	public Point getSelectedPoint(){
		return new Point(table.getSelectedRow(),table.getSelectedColumn());
	}
	
	public void setPointValue(Point p,int value){
		table.setValueAt(value, p.x, p.y);
		table.clearSelection();
	}
	
	public void setPointString(Point p,String constraint){
		table.setValueAt(constraint, p.x, p.y);
		table.clearSelection();
	}
	
	public void emptyScheme(){
		table.clear();
	}
	
	public void setEnable(boolean enable){
		table.setEnabled(enable);
	}

	public void clearSelection() {
		table.clearSelection();
	}

	public void setEmpty(Point p) {
		table.setValueAt(null,p.x, p.y);
		
	}
	
	public void setScheme(int[][] scheme2) {
		for (int i = 0; i < scheme2.length; i++) {
			for (int j = 0; j < scheme2.length; j++) {
				table.setValueAt(scheme2[i][j],i*2, j*2);
			}
		}
	}

}
