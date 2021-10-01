package test;



import org.junit.Assert;
import org.junit.Test;

import model.ConstrainedMatrixFactory;
import model.Constraint;
import model.Scheme;

public class ConstrainedMatrixSchemeTest {
	Scheme scheme = new ConstrainedMatrixFactory().create(5);

	@Test(expected = IllegalArgumentException.class)
	public void NegativeIndex() {
		scheme.setRightConstraint(Constraint.LESSER, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void UncorrectIndex() {
		scheme.setValue(1, 1, 9);
	}

	@Test(expected = IllegalArgumentException.class)
	public void NegativeValue() {
		scheme.setValue(-1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void UncorrectValue() {
		scheme.setValue(6, 1, 1);
	}

	@Test
	public void correctInsert() {
		scheme.setValue(1, 1, 1);
		Assert.assertTrue(scheme.getValue(1, 1)==1);
	}
	
	@Test
	public void correctCheck(){
		scheme.setCellValue(1, 1, 1);
		Assert.assertTrue(!scheme.checkValuesColumn(1, 1));
	}

}
