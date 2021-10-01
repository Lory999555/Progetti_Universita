package visitor;

import java.util.Iterator;

import operators.And;
import operators.Condition;
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

public class PrintVisitor implements Visitor{

	@Override
	public void visit(And c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Or c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}	
	}

	@Override
	public void visit(Not c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Plus c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Minus c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Mult c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Div c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Rem c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Power c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(LE c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(LT c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(GT c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(GE c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(EQ c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(NEQ c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Value c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}

	@Override
	public void visit(Variable c) {
		Iterator<Condition> it=c.iterator();
		while(it.hasNext()){
			System.out.print(it.next().getOp()+" ");
		}
	}
	

}
