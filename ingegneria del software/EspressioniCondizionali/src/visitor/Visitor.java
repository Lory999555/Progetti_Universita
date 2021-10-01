package visitor;

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

public interface Visitor {

	public void visit(And c);

	public void visit(Or c);

	public void visit(Not c);

	public void visit(Plus c);

	public void visit(Minus c);

	public void visit(Mult c);

	public void visit(Div c);

	public void visit(Rem c);

	public void visit(Power c);

	public void visit(LE c);

	public void visit(LT c);

	public void visit(GT c);

	public void visit(GE c);

	public void visit(EQ c);

	public void visit(NEQ c);

	public void visit(Value c);

	public void visit(Variable c);

}
