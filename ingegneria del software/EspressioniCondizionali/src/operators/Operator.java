package operators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import iterator.OperatorIteratorSimm;
import iterator.OperatorIteratorPost;

public class Operator extends Condition {

	private Condition left, right;
	private Condition parent;

	public String getOp() {
		return null;
	}

	public Condition left() {
		return left;
	}

	public Condition right() {
		return right;
	}

	public void setLeft(Condition c) {
		left = c;
	}

	public void setRight(Condition c) {
		right = c;
	}

	public Condition getParent() {
		return parent;
	}

	public void setParent(Condition parent) {
		this.parent = parent;
	}

	public Iterator<Condition> iterator() {
		if (it == null)
			return new OperatorIteratorSimm(this);
		return it;
	}

	public String toString() {
		return null;
	}

}