package iterator;

import operators.Operator;
import operators.Value;
import operators.Variable;

public interface ConditionIterators {
	public ValueIterator getValueIterator(Value v);

	public VariableIterator getVariableIterator(Variable v);

	public IOperator getOperatorIterator(Operator o);

}
