package test;

import org.junit.Assert;
import org.junit.Test;

import operators.Value;

public class ValueTest {
	
	@Test
	public void positiveValue(){
		String var="222";
		Value v = new Value(var);
		Assert.assertTrue(v!=null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void negativeValue(){
		String var="c";
		Value v = new Value(var);
	}

}
