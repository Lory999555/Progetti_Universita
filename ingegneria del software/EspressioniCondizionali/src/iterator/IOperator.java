package iterator;

import java.util.Iterator;

import operators.Condition;

public abstract class IOperator implements Iterator<Condition> {
	protected Condition c;
	protected final int left = 0, right = 1, center = 2;
	protected boolean monadico = false;
	protected Iterator<Condition> it = null;

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public abstract boolean hasNext();

	public abstract Condition next();

}
