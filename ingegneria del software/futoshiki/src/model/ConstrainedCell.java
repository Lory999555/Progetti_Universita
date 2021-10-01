package model;

public class ConstrainedCell {
	private int value;
	private Constraint right;
	private Constraint down;
	
	public ConstrainedCell(){
		this.value=0;
		this.right=Constraint.NONE;
		this.down=Constraint.NONE;
	}
	public ConstrainedCell(int value,Constraint right,Constraint down){
		this.value=value;
		this.right=right;
		this.down=down;
	}
	public ConstrainedCell(Constraint right,Constraint down){
		this.value=0;
		this.right=right;
		this.down=down;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Constraint getRight() {
		return right;
	}
	public void setRight(Constraint right) {
		this.right = right;
	}
	public Constraint getDown() {
		return down;
	}
	public void setDown(Constraint down) {
		this.down = down;
	}
	

}
