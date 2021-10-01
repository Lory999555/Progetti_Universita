package operators;

import visitor.Visitor;

public class Or extends Operator {
	public String toString() {
		return "(" + left() + " Or " + right() + ")";
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public String getOp() {
		return "Or";
	}
}
