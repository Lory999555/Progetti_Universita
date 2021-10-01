package entity;

public class ConcreteFactorySchema implements FactorySchema{

	@Override
	public Scheme create(int dim) {
		return new ConstraintMatrix(dim);
	}

}
