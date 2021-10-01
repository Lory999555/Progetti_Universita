package operators;

import visitor.Visitor;

public class Not extends Operator {
	public void setLeft(Condition c) {
		throw new UnsupportedOperationException();
	} 
	public String toString() {
		return "( Not" + right() + ")";
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
	public String getOp() {
		return "Not";
	}
}
