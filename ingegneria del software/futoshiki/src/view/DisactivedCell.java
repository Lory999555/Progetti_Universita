package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class DisactivedCell extends DefaultTableCellRenderer{
    private int size;
	
	public DisactivedCell(int size) {
		super();
		this.size = size;
		this.setBackground(Color.GRAY);
	}
	
	@Override
	public Border getBorder() {
		return BorderFactory.createMatteBorder(size, size, size, size, Color.BLACK);
	}

	public int getBorderSize() {
		return size;
	}

	public void setBorderSize(int size) {
		this.size = size;
	}
}
