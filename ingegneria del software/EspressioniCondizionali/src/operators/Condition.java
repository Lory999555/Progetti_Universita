package operators;

import java.util.Iterator;

import visitor.Visitor;

public abstract class Condition implements Iterable<Condition> {

	Iterator<Condition> it;

	public Iterator<Condition> iterator() {
		return it;
	}

	public void setIterator(Iterator<Condition> it) {
		this.it = it;
	}

	public Condition left() {
		throw new UnsupportedOperationException();
	}

	public Condition right() {
		throw new UnsupportedOperationException();
	}

	public void setLeft(Condition c) {
		throw new UnsupportedOperationException();
	}

	public void setRight(Condition c) {
		throw new UnsupportedOperationException();
	}

	public Condition getParent() {
		throw new UnsupportedOperationException();
	}

	public void setParent(Condition parent) {
		throw new UnsupportedOperationException();
	}

	public void accept(Visitor v) {
		throw new UnsupportedOperationException();
	}

	public abstract String toString();

	public abstract String getOp();
}
