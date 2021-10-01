package visitor;

import java.util.HashMap;

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

public class EvaluationVisitor implements Visitor{
	private Context context;
	private boolean result;
	private int operation=0;
	
	public EvaluationVisitor(Context context){
		this.context=context;
	}

	@Override
	public void visit(And c) {
		c.left().accept(this);
		if(!result) return;
		c.right().accept(this);
	}

	@Override
	public void visit(Or c) {
		c.left().accept(this);
		if(result) return;
		c.right().accept(this);
	}

	@Override
	public void visit(Not c) {
		c.right().accept(this);
		result=!result;
	}

	@Override
	public void visit(Plus c) {
		 if(c.left()==null){
			 c.right().accept(this);
		     int op2=operation;
		     operation=op2; 
		 }
		 else{
		     c.left().accept(this);
	         int op1=operation;
	         c.right().accept(this);
	         int op2=operation;
	         operation=op1+op2;
		 }
	}

	@Override
	public void visit(Minus c) {
		 if(c.left()==null){
			 c.right().accept(this);
		     int op2=operation;
		     operation=op2*(-1); 
		 }
		 else{
		     c.left().accept(this);
	         int op1=operation;
	         c.right().accept(this);
	         int op2=operation;
	         operation=op1-op2;
		 }
	}

	@Override
	public void visit(Mult c) {
		c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     operation=op1*op2;
	}

	@Override
	public void visit(Div c) {
		 c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     if(op2==0) throw new IllegalArgumentException("Divisione per 0 nel calcolo del resto");
	     operation=op1/op2;
	}

	@Override
	public void visit(Rem c) {
		c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     if(op2==0) throw new IllegalArgumentException("Divisione per 0 nel calcolo del resto");
	     operation=op1%op2;
	}

	@Override
	public void visit(Power c) {
		 c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     operation=op1^op2;
	}

	@Override
	public void visit(LE c) {
		c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     result=op1<=op2;
	}

	@Override
	public void visit(LT c) {
		 c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     result=op1<op2;
	}

	@Override
	public void visit(GT c) {
		c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     result=op1>op2;
	}

	@Override
	public void visit(GE c) {
		 c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     result=op1>=op2;
	}

	@Override
	public void visit(EQ c) {
		 c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     result=op1==op2;
	}

	@Override
	public void visit(NEQ c) {
	     c.left().accept(this);
	     int op1=operation;
	     c.right().accept(this);
	     int op2=operation;
	     result=op1!=op2;
	}

	@Override
	public void visit(Value c) {
		operation = c.getValue();
	}

	@Override
	public void visit(Variable c) {
		HashMap<String,Integer> variables = context.getVariables();
		if(variables.containsKey(c.toString())){
			operation=variables.get(c.toString());
		}
		else operation=0;
	}
	
	public boolean getResult(){
		return result;
	}
	

}
