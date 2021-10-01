package visitor;

import iterator.ConditionIterators;
import iterator.OperatorIteratorPost;
import iterator.ValueIterator;
import operators.And;
import operators.Div;
import operators.EQ;
import operators.GE;
import operators.GT;
import operators.LE;
import operators.LT;
import operators.Minus;
import operators.Mult;
import operators.NEQ;
import operators.Not;
import operators.Or;
import operators.Plus;
import operators.Power;
import operators.Rem;
import operators.Value;
import operators.Variable;

public class IteratorVisitor implements Visitor {
	private ConditionIterators it;

	public IteratorVisitor(ConditionIterators ci) {
		it = ci;
	}

	@Override
	public void visit(And c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Or c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));

	}

	@Override
	public void visit(Not c) {
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Plus c) {
		if (c.left() != null)
			c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Minus c) {
		if (c.left() != null)
			c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Mult c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Div c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Rem c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Power c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(LE c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(LT c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(GT c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(GE c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(EQ c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(NEQ c) {
		c.left().accept(this);
		c.right().accept(this);
		c.setIterator(it.getOperatorIterator(c));
	}

	@Override
	public void visit(Value c) {
		c.setIterator(it.getValueIterator(c));

	}

	@Override
	public void visit(Variable c) {
		c.setIterator(it.getVariableIterator(c));

	}

	public void setConditionIterator(ConditionIterators ci) {
		it = ci;
	}

}
