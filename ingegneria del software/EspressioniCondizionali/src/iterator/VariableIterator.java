package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import operators.Condition;
import operators.Variable;

public class VariableIterator implements Iterator<Condition> {
	protected Condition c;
	private boolean flag = true;

	public VariableIterator(Variable v) {
		c = v;
	}

	@Override
	public boolean hasNext() {
		return flag;
	}

	@Override
	public Condition next() {
		if (flag) {
			flag = false;
			return c;
		}
		throw new NoSuchElementException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
