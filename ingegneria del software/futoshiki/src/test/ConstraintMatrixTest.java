package test;

import org.junit.Assert;
import org.junit.Test;

import entity.ConcreteFactorySchema;
import entity.Constraint;
import entity.Scheme;

public class ConstraintMatrixTest {
	Scheme scheme = new ConcreteFactorySchema().create(5);

	@Test(expected = IllegalArgumentException.class)
	public void NegativeIndex() {
		scheme.setRight(Constraint.Lower, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void UncorrectIndex() {
		scheme.setValue(1, 1, 9, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void NegativeValue() {
		scheme.setValue(-1, 1, 1, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void UncorrectValue() {
		scheme.setValue(6, 1, 1, false);
	}

	@Test
	public void correctInsert() {
		scheme.setValue(1, 1, 1, false);
		Assert.assertTrue(scheme.getValue(1, 1) == 1 && !scheme.getModificabile(1, 1));
	}



}
