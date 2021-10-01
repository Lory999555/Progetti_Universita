package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import builder.Parser;
import operators.Condition;
import visitor.Context;
import visitor.EvaluationVisitor;

public class EvaluationTest {
	
	static Context c;
	static EvaluationVisitor v;
	
	@BeforeClass
	public static void buildEvaluation() throws IOException{
		String fileP=System.getProperty("user.home")+ "/" + "var.properties";
		Properties p=new Properties();
		FileWriter file=new FileWriter(fileP);
		p.store(file, "ec");
		file.close();
		c=new Context(fileP);
		v=new EvaluationVisitor(c);
	}
	
	@Test
	public void positiveEvaluation(){
		Parser p=new Parser(new StringReader("a < 5 || b - 3 == 0"));
		Condition cond=p.getCondition();
		cond.accept(v);
		Assert.assertTrue(v.getResult());
	}
	
	@Test
	public void CortoCircuitEvaluation(){
		Parser p=new Parser(new StringReader("a < 5 || 2 / b == 1"));
		Condition cond=p.getCondition();
		cond.accept(v);
		Assert.assertTrue(v.getResult());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void negativeEvaluation(){
		Parser p=new Parser(new StringReader("a < 5 && 2 / b == 1"));
		Condition cond=p.getCondition();
		cond.accept(v);
	}

}
