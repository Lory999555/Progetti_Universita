package operators;

import visitor.Visitor;

public class And extends Operator{
	public String toString() {
		return "(" + left() + " And " + right() + ")";
	}
	
	public String getOp() {
		return "And";
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
}
