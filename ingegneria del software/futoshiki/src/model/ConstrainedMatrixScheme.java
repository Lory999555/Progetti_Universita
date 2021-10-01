package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ConstrainedMatrixScheme implements Scheme{
	private ConstrainedCell[][] scheme;
	private int dimension;
	private static final int EMPTY=0;
	private List<Point> emptyCells=new ArrayList<>();

	public ConstrainedMatrixScheme(int n){
		scheme= new ConstrainedCell[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				scheme[i][j]=new ConstrainedCell();
				emptyCells.add(new Point(i,j));
			}
		}
		dimension=n;
	}
	
	@Override
	public void setValue(int value, int row, int column) {
		if (row < 0 || row > dimension || column < 0 || column > dimension)
			throw new IllegalArgumentException("invalid point");
		if (value < 0 || value > dimension)
			throw new IllegalArgumentException("invalid value");

		scheme[row][column].setValue(value);

	}
	
	@Override
	public int getValue(int row, int column) {
		if (row < 0 || row > dimension || column < 0 || column > dimension)
			throw new IllegalArgumentException("invalid point");

		return scheme[row][column].getValue();
	}
	
	@Override
	public boolean checkValuesRow(int value,int row) {
		if (row < 0 || row > dimension) {
			throw new IllegalArgumentException("invalid row index");
		}

		for(int j= 0; j< dimension ;j++){
			if(scheme[row][j].getValue() == value)
				return false;
		}
		return true;
	}
	
	@Override
	public boolean checkValuesColumn(int value,int column) {
		if (column < 0 || column > dimension) {
			throw new IllegalArgumentException("invalid column index");
		}

		for(int i=0; i< dimension; i++){
			if(scheme[i][column].getValue() == value){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Scheme getStructure() {
		Scheme copia = new ConstrainedMatrixScheme(dimension);
		for (int i = 0; i < dimension; i++) {
			for(int j=0; j<dimension;j++){
				copia.setDownConstraint(scheme[i][j].getDown() , i, j);
				copia.setRightConstraint(scheme[i][j].getRight() , i, j);
				copia.setValue(scheme[i][j].getValue() , i, j);
			}
		}

		return copia;
	}
	
	@Override
	public void setDownConstraint(Constraint c,int row,int column){
		if (row < 0 || row > dimension || column < 0 || column > dimension)
			throw new IllegalArgumentException("invalid point");
		scheme[row][column].setDown(c);
	}
	
	@Override
	public void setRightConstraint(Constraint c,int row,int column){
		if (row < 0 || row > dimension || column < 0 || column > dimension)
			throw new IllegalArgumentException("invalid point");
		scheme[row][column].setRight(c);
	}
	
	@Override
	public boolean checkConstraints(int value, int row, int column){
		if (row < 0 || row > dimension || column < 0 || column > dimension)
			throw new IllegalArgumentException("invalid point");
		if(column!=0){
			int leftValue=scheme[row][column-1].getValue();
			if(scheme[row][column-1].getRight().equals(Constraint.GREATER) && leftValue!=EMPTY && leftValue<value ){
				return false;
			}
			if(scheme[row][column-1].getRight().equals(Constraint.LESSER) && leftValue!=EMPTY && leftValue>value ){
				return false;
			}
		}
		if(column!=dimension-1){
			int rightValue=scheme[row][column+1].getValue();
			if(scheme[row][column].getRight().equals(Constraint.GREATER) && rightValue!=EMPTY && rightValue>value ){
				return false;
			}
			if(scheme[row][column].getRight().equals(Constraint.LESSER) && rightValue!=EMPTY && rightValue<value ){
				return false;
			}
		}
		if(row!=0){
			int upValue=scheme[row-1][column].getValue();
			if(scheme[row-1][column].getDown().equals(Constraint.GREATER) && upValue!=EMPTY && upValue<value ){
				return false;
			}
			if(scheme[row-1][column].getDown().equals(Constraint.LESSER) && upValue!=EMPTY && upValue>value ){
				return false;
			}
		}
		if(row!=dimension-1){
			int downValue=scheme[row+1][column].getValue();
			if(scheme[row][column].getDown().equals(Constraint.GREATER) && downValue!=EMPTY && downValue>value ){
				return false;
			}
			if(scheme[row][column].getDown().equals(Constraint.LESSER) && downValue!=EMPTY && downValue<value ){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean check(){
		for (int i = 0; i < dimension; i++) {
			for (int j =0;j<dimension; j++){
				if(scheme[i][j].getValue()!=0 &&!checkConstraints(scheme[i][j].getValue(),i,j)) return false;
				if(!verify(scheme[i][j].getValue(),i,j)) return false;
			}
			List<Integer> row = getRow(i);
			row.removeAll(Collections.singleton(0)); 
			if (row.size() != new HashSet<Integer>(row).size())
				return false;
			List<Integer> column = getColumn(i);
			column.removeAll(Collections.singleton(0)); 
			if (column.size() != new HashSet<Integer>(column).size())
				return false;
		}
		return true;
	}
	
	private boolean verify(int value,int row,int column){
		if(value==1){
			if(scheme[row][column].getDown()==Constraint.GREATER || scheme[row][column].getRight()==Constraint.GREATER) return false;
			if(row!=0 && scheme[row-1][column].getDown()==Constraint.LESSER) return false;
			if(column!=0 && scheme[row][column-1].getRight()==Constraint.LESSER) return false;
		}
		else if(value==dimension){
			if(scheme[row][column].getDown()==Constraint.LESSER || scheme[row][column].getRight()==Constraint.LESSER) return false;
			if(row!=0 && scheme[row-1][column].getDown()==Constraint.GREATER) return false;
			if(column!=0 && scheme[row][column-1].getRight()==Constraint.GREATER) return false;
		}
		return true;
	}
	
	private List<Integer> getRow(int row){
		List<Integer> rowList = new ArrayList<>(dimension);
		for (int j = 0; j < dimension; j++) {
			rowList.add(scheme[row][j].getValue());
		}
		return rowList;
	}
	
	private List<Integer> getColumn(int column){
		List<Integer> columnList = new ArrayList<>(dimension);
		for (int i = 0; i < dimension; i++) {
			columnList.add(scheme[i][column].getValue());
		}
		return columnList;
	}
	
	@Override
	public void setCellValue(int value,int row,int column){
		Point p=new Point(row,column);
		if (value==0){
			if(!emptyCells.contains(p)) emptyCells.add(p);
		}
		else{
			if(emptyCells.contains(p)) emptyCells.remove(p);
		}
		setValue(value, row, column);
	}
	
	@Override
	public ArrayList<Point> getEmptyCell(){
		return new ArrayList<>(emptyCells);
	}
	
	@Override
	public int[][] getMatrixValues() {
		int[][] sol = new int[dimension][dimension];
		for(int i=0;i<dimension;i++){
			for(int j=0; j<dimension; j++){
				sol[i][j]= scheme[i][j].getValue();
			}
		}
		return sol;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				sb.append(scheme[i][j].getValue()+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
}
