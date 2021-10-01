package test;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import builder.Parser;
import builder.SyntaxException;

public class ParserTest {
	
	@Test
	public void positiveParser(){
		String expr=" a % [ - b ] == 0 && (a > 2 || b - 12 > 0) ";
		Parser p=new Parser(new StringReader(expr));
		Assert.assertTrue(p.getCondition()!=null);
	}
	
	@Test(expected=SyntaxException.class)
	public void negativeParser(){
		String expr=" a % b == 0 && (a > 2 || b - 12 > 0  ";
		Parser p=new Parser(new StringReader(expr));
	}
	
	@Test(expected=SyntaxException.class)
	public void negativeParser2(){
		String expr=" a c % b == 0 && (a > 2 || b - 12 > 0 ) ";
		Parser p=new Parser(new StringReader(expr));
	}

}
