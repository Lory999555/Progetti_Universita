package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ConstraintMatrix implements Scheme{
	private Cell[][] table;
	private int dim;
	private static final int EMPTY=0;

	public ConstraintMatrix(int dim) {
		if(dim <= 0)
			throw new IllegalArgumentException("dimensione negativa o nulla");
		this.dim=dim;
		table= new Cell[this.dim][this.dim];
		for(int i=0;i<this.dim;i++){
			for(int j=0;j<this.dim;j++){
				table[i][j]=new Cell();
			}
		}
	}

	@Override
	public void setValue(int val, int riga, int colonna,boolean modificabile) {
		controlli(val,riga,colonna);
		table[riga][colonna].setVal(val);
		table[riga][colonna].setModificabile(modificabile);
	}
	public boolean getModificabile(int riga , int colonna){
		controlli(0,riga,colonna);
		return table[riga][colonna].getModificabile();
	}

	@Override
	public int getValue(int riga, int colonna) {
		controlli(0,riga,colonna);
		return table[riga][colonna].getVal();
	}

	@Override
	public void setDown(Constraint down, int riga, int colonna) {
		controlli(0,riga,colonna);
		table[riga][colonna].setDown(down);
	}

	@Override
	public void setRight(Constraint right, int riga, int colonna) {
		controlli(0,riga,colonna);
		table[riga][colonna].setRight(right);
		
	}

	@Override
	public Constraint getDown(int riga, int colonna) {
		controlli(0,riga,colonna);
		return table[riga][colonna].getDown();
	}

	@Override
	public Constraint getRight(int riga, int colonna) {
		controlli(0,riga,colonna);
		return table[riga][colonna].getRight();
	}

	@Override
	public boolean checkColumn(int val, int colonna) {
		controlli(val,0,colonna);
		for(int i=0 ; i<dim;i++){
			if(table[i][colonna].getVal()==val){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkRow(int val, int riga) {
		controlli(val,riga,0);
		for(int i=0 ; i<dim;i++){
			if(table[riga][i].getVal()==val){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean check(int val,int riga,int colonna) {
		return !(checkRow(val,riga) || checkColumn(val,colonna));
	}
	private void controlli(int val,int riga,int colonna){
		if( val > dim || val < 0 || colonna > dim-1 || colonna < 0 || riga > dim-1 || riga < 0) 
			throw new IllegalArgumentException("valori non validi");
		return;
	}
	public int getDim(){
		return dim;
	}

	@Override
	public Scheme getScheme() {
		Scheme copia = new ConstraintMatrix(dim);
		for (int i = 0; i < dim; i++) {
			for(int j=0; j<dim;j++){
				copia.setDown(table[i][j].getDown() , i, j);
				copia.setRight(table[i][j].getRight() , i, j);
				copia.setValue(table[i][j].getVal() , i, j,table[i][j].getModificabile());
			}
		}

		return copia;
		
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				sb.append(table[i][j].getVal()+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public boolean checkConstraint(int val, int riga, int colonna) {
		controlli(val,riga,colonna);
		if(colonna!=0){
			int leftValue=table[riga][colonna-1].getVal();
			if(table[riga][colonna-1].getRight().equals(Constraint.Greater) && leftValue!=EMPTY && leftValue<val ){
				return false;
			}
			if(table[riga][colonna-1].getRight().equals(Constraint.Lower) && leftValue!=EMPTY && leftValue>val ){
				return false;
			}
		}
		if(colonna!=dim-1){
			int rightValue=table[riga][colonna+1].getVal();
			if(table[riga][colonna].getRight().equals(Constraint.Greater) && rightValue!=EMPTY && rightValue>val ){
				return false;
			}
			if(table[riga][colonna].getRight().equals(Constraint.Lower) && rightValue!=EMPTY && rightValue<val ){
				return false;
			}
		}
		if(riga!=0){
			int upValue=table[riga-1][colonna].getVal();
			if(table[riga-1][colonna].getDown().equals(Constraint.Greater) && upValue!=EMPTY && upValue<val ){
				return false;
			}
			if(table[riga-1][colonna].getDown().equals(Constraint.Lower) && upValue!=EMPTY && upValue>val ){
				return false;
			}
		}
		if(riga!=dim-1){
			int downValue=table[riga+1][colonna].getVal();
			if(table[riga][colonna].getDown().equals(Constraint.Greater) && downValue!=EMPTY && downValue>val ){
				return false;
			}
			if(table[riga][colonna].getDown().equals(Constraint.Lower) && downValue!=EMPTY && downValue<val ){
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean checkSyntax(){
		for (int i = 0; i < dim; i++) {
			for (int j =0;j<dim; j++){
				if(table[i][j].getVal()!=0 &&!checkConstraint(table[i][j].getVal(),i,j)) return false;
				if(!verify(table[i][j].getVal(),i,j)) return false;
			}
			List<Integer> riga = getRiga(i);
			riga.removeAll(Collections.singleton(0)); 
			if (riga.size() != new HashSet<Integer>(riga).size())
				return false;
			List<Integer> colonna = getColonna(i);
			colonna.removeAll(Collections.singleton(0)); 
			if (colonna.size() != new HashSet<Integer>(colonna).size())
				return false;
		}
		return true;
	}

	private boolean verify(int val,int riga,int colonna){
		if(val==1){
			if(table[riga][colonna].getDown()==Constraint.Greater || table[riga][colonna].getRight()==Constraint.Greater) return false;
			if(riga!=0 && table[riga-1][colonna].getDown()==Constraint.Lower) return false;
			if(colonna!=0 && table[riga][colonna-1].getRight()==Constraint.Lower) return false;
		}
		else if(val==dim){
			if(table[riga][colonna].getDown()==Constraint.Lower || table[riga][colonna].getRight()==Constraint.Lower) return false;
			if(riga!=0 && table[riga-1][colonna].getDown()==Constraint.Greater) return false;
			if(colonna!=0 && table[riga][colonna-1].getRight()==Constraint.Greater) return false;
		}
		return true;
	}
	private List<Integer> getRiga(int riga){
		List<Integer> row = new ArrayList<>(dim);
		for (int j = 0; j < dim; j++) {
			row.add(table[riga][j].getVal());
		}
		return row;
	}
	
	private List<Integer> getColonna(int colonna){
		List<Integer> column = new ArrayList<>(dim);
		for (int i = 0; i < dim; i++) {
			column.add(table[i][colonna].getVal());
		}
		return column;
	}
	public int[][] getMatrixValues() {
		int[][] sol = new int[dim][dim];
		for(int i=0;i<dim;i++){
			for(int j=0; j<dim; j++){
				sol[i][j]= table[i][j].getVal();
			}
		}
		return sol;
	}
	
}

