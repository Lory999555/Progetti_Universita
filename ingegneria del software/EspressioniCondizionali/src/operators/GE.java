package operators;

import visitor.Visitor;

public class GE extends Operator {
	public String toString() {
		return "(" + left() + " >= " + right() + ")";
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public String getOp() {
		return ">=";
	}

}
