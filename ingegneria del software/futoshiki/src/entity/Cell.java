package entity;

public class Cell {
	private int val;
	private Constraint down;
	private Constraint right;
	private boolean modifiable;
	
	public Cell(){
		this.val=0;
		this.down=Constraint.Null;
		this.right=Constraint.Null;
		this.modifiable=true;
	}
	public Cell(int val){
		this.val=val;
		this.down=Constraint.Null;
		this.right=Constraint.Null;
	}
	public Cell(int val, Constraint down,Constraint right){
		this.val=val;
		this.down=down;
		this.right=right;
	}
	public int getVal(){
		return this.val;
	}
	public Constraint getDown(){
		return down;
	}
	public Constraint getRight(){
		return right;
	}
	public void setVal(int val){
		this.val=val;
	}
	public void setDown(Constraint down){
		this.down=down;
	}
	public void setRight(Constraint right){
		this.right=right;
	}
	public void setModificabile(boolean modificabile){
		this.modifiable=modificabile;
	}
	public boolean getModificabile(){
		return this.modifiable;
	}
}
