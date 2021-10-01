package operators;

import java.util.Iterator;

import iterator.VariableIterator;
import visitor.Visitor;

public class Variable extends Condition {
	private String var;
	private final String REGEX = "[a-zA-Z][a-zA-Z0-9]*";

	public Variable(String var) {
		if (!var.matches(REGEX))
			throw new IllegalArgumentException();
		this.var = var;
	}

	public String toString() {
		return var;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Iterator<Condition> iterator() {
		return new VariableIterator(this);
	}

	@Override
	public String getOp() {
		return var;
	}

}
