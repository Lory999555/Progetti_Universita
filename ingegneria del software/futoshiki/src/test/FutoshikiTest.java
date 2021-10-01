package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import backtracking.Futoshiki;
import model.ConstrainedMatrixFactory;
import model.Constraint;
import model.Scheme;

public class FutoshikiTest {

	static Scheme scheme;
	
	@BeforeClass
	public static void buildScheme() {
		scheme = new ConstrainedMatrixFactory().create(5);
		scheme.setValue(3, 0, 1);
		scheme.setValue(5, 3, 4);
		scheme.setDownConstraint(Constraint.LESSER, 0, 1);
	}
	
	@Test
	public void correctScheme() {
		Futoshiki futo = new Futoshiki(4,5, scheme);
		futo.risolvi();
		for (Scheme s : futo.getSolutions()) {
			Assert.assertTrue(s.check() );
		}
	}
	
	@Test
	public void uncorrectScheme() {
		scheme.setValue(1, 1, 1);
		Futoshiki futo = new Futoshiki(4,5, scheme);
		futo.risolvi();
		Assert.assertTrue(futo.getSolutions().size() == 0);
	}

}
