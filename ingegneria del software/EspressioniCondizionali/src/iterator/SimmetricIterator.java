package iterator;

import operators.Operator;
import operators.Value;
import operators.Variable;

public class SimmetricIterator implements ConditionIterators {

	@Override
	public ValueIterator getValueIterator(Value v) {
		return new ValueIterator(v);
	}

	@Override
	public VariableIterator getVariableIterator(Variable v) {
		return new VariableIterator(v);
	}

	@Override
	public IOperator getOperatorIterator(Operator o) {
		return new OperatorIteratorSimm(o);
	}

}
