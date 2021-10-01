package entity;

public interface Scheme {
	void setValue(int val, int riga, int colonna,boolean modificabile);
	int getValue(int riga,int colonna);
	void setDown(Constraint down,int riga,int colonna);
	void setRight(Constraint right,int riga,int colonna);
	Constraint getDown(int riga,int colonna);
	Constraint getRight(int riga,int colonna);
	boolean checkColumn(int val,int colonna);
	boolean checkRow(int val,int riga);
	boolean checkConstraint(int val,int riga,int colonna);
	boolean check(int val,int riga,int colonna);
	boolean getModificabile(int riga,int colonna);
	int getDim();
	Scheme getScheme();
	String toString();
	boolean checkSyntax();
	int[][] getMatrixValues();

}
