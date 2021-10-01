package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public interface Scheme {
	void setValue(int value, int row, int column);
	int getValue(int row, int column);
	boolean checkValuesRow(int value,int row);
	boolean checkValuesColumn(int value,int column);
	boolean checkConstraints(int value, int row, int column);
	void setRightConstraint(Constraint c,int row,int column);
	void setDownConstraint(Constraint c,int row,int column);
	Scheme getStructure();
	boolean check();
	ArrayList<Point> getEmptyCell();
	void setCellValue(int value,int row,int column);
	int[][] getMatrixValues();

}
