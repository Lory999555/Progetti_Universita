package model;

public class ConstrainedMatrixFactory implements SchemeFactory{
	
	@Override
	public Scheme create(int dimension) {
		return new ConstrainedMatrixScheme(dimension);
	}
}
