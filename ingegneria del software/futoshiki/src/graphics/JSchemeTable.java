package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class JSchemeTable extends JTable{
	
	private int dimension;
	
	public JSchemeTable(int dim){
		super(dim*2-1,dim*2-1);
		dimension=dim*2-1;
		this.setFont(new Font("Arial Black", Font.PLAIN, 15));
		this.setColumnSelectionAllowed(false);
		this.setCellSelectionEnabled(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowHeight(30);
		for (int i = 0; i < dimension; i++) {
			this.getColumnModel().getColumn(i).setPreferredWidth(30);
			this.getColumnModel().getColumn(i).setMinWidth(30);
		}
		
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		if(row%2==0 && column%2==0){
			return super.prepareRenderer(new BoundedCell(1), row, column);
		}
		else if(row%2!=0 && column%2!=0){
			return super.prepareRenderer(new DisactivedCell(1), row, column);
		}
		else return super.prepareRenderer(renderer, row, column);
	}
	
	
	public void clear() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				setValueAt(null, i, j);
			}
		}
	}
	
	

}
