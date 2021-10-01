package test;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import backtracking.Futoshiki;
import entity.ConcreteFactorySchema;
import entity.Constraint;
import entity.Scheme;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FutoshikiTest {

	static Scheme scheme;

	@BeforeClass
	public static void buildScheme() {
		scheme = new ConcreteFactorySchema().create(5);
		scheme.setValue(3, 1, 4, false);
		scheme.setValue(2, 2, 2, false);
		scheme.setValue(4, 3, 4, false);
		scheme.setDown(Constraint.Greater, 0, 2);
		scheme.setRight(Constraint.Greater, 1, 0);
		scheme.setRight(Constraint.Lower, 2, 1);
		scheme.setDown(Constraint.Greater, 2, 2);
	}

	@Test
	public void correctScheme() {
		Futoshiki futo = new Futoshiki(10, scheme);
		futo.risolvi();
		for (Scheme s : futo.getSoluzioni()) {
			Assert.assertTrue(s.checkSyntax());

		}
	}

	@Test
	public void exampleScheme() {
		scheme.setDown(Constraint.Lower, 3, 0);
		scheme.setDown(Constraint.Lower, 3, 1);
		Futoshiki futo = new Futoshiki(10, scheme);
		futo.risolvi();
		Assert.assertTrue(futo.getSoluzioni().size() == 1);
	}

	@Test
	public void uncorrectScheme() {
		scheme.setValue(3, 1, 1, false);
		Futoshiki futo = new Futoshiki(4, scheme);
		futo.risolvi();
		Assert.assertTrue(futo.getSoluzioni().size() == 0);
	}

}
