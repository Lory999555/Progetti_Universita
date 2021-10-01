package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import operators.Condition;
import operators.Value;

public class ValueIterator implements Iterator<Condition> {
	protected Condition c;
	private boolean flag = true;

	public ValueIterator(Value v) {
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
