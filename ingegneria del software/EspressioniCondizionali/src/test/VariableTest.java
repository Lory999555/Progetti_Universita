package test;

import org.junit.Assert;
import org.junit.Test;

import operators.Variable;

public class VariableTest {

	@Test
	public void positiveVariable() {
		String var = "xyz23";
		Variable v = new Variable(var);
		Assert.assertTrue(v != null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeVariable() {
		String var = "0xy";
		Variable v = new Variable(var);
	}
}
