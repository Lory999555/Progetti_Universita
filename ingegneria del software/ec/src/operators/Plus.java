package operators;

import visitor.Visitor;

public class Plus extends Operator{
	public String toString() {
		if(left()==null) return "( + "+right();
		return "(" + left() + " + " + right() + ")";
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
	public String getOp() {
		return "+";
	}

}
