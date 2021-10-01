package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import operators.Condition;
import operators.Operator;

public class OperatorIteratorPost extends IOperator {

	private int now = left;

	public OperatorIteratorPost(Operator o) {
		this.c = o;
		if (c.left() == null) {
			monadico = true;
		} else
			it = c.left().iterator();
	}

	@Override
	public boolean hasNext() {
		return now != center;
	}

	@Override
	public Condition next() {
		if (!monadico && it.hasNext())
			return it.next();
		if (now == left) {
			now = right;
			it = c.right().iterator();
			monadico = false;
			return it.next();
		}
		if (now == right) {
			now = center;
			return c;
		}
		throw new NoSuchElementException();
	}

}
