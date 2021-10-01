package operators;

import java.util.Iterator;


import iterator.ValueIterator;
import visitor.Visitor;

public class Value extends Condition{
	private int value;
	private final String REGEX="[0-9]+";
	public Value(String v){
		if(!v.matches(REGEX)) throw new IllegalArgumentException();
		this.value=Integer.parseInt(v);
	}
	public String toString() {
		return value+"";
	}
	public int getValue(){
		return value;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public Iterator<Condition> iterator() {
		return new ValueIterator(this);
	}
	@Override
	public String getOp() {
		return value+"";
	}
	

}
