package graphics;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class BoundedCell extends DefaultTableCellRenderer{
	private int size;
	
	public BoundedCell(int size) {
		super();
		this.size = size;
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
