package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import operators.Condition;
import operators.Operator;

public class OperatorIteratorSimm extends IOperator {
	private int now = left;

	public OperatorIteratorSimm(Operator o) {
		this.c = o;
		if (c.left() == null) {
			monadico = true;
		} else
			it = c.left().iterator();
	}

	@Override
	public boolean hasNext() {
		if (now == right) {
			return it.hasNext();
		}
		return true;
	}

	@Override
	public Condition next() {
		if (!monadico && it.hasNext())
			return it.next();
		if (now == left) {
			now = right;
			it = c.right().iterator();
			monadico = false;
			return c;
		}
		throw new NoSuchElementException();
	}

}
