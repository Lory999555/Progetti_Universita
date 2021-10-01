package main;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Properties;

import builder.Parser;
import iterator.PostIterator;
import iterator.SimmetricIterator;
import operators.Condition;
import visitor.Context;
import visitor.EvaluationVisitor;
import visitor.IteratorVisitor;
import visitor.PrintVisitor;


public class ECMain {
	
	public static void main(String[]args) throws IOException{
		String fileP=System.getProperty("user.home")+ "/" + "var.properties";
		String fileN=System.getProperty("user.home")+ "/" + "var.txt";
		String fileS=System.getProperty("user.home")+ "/" + "context.txt";
		Properties p=new Properties();
		p.setProperty("a", "5");
        p.setProperty("b", "7");
	    p.setProperty("c", "9");
	    FileWriter file=new FileWriter(fileP);
		p.store(file, "ec");
		file.close();
		FileOutputStream prova = new FileOutputStream(fileN);
		PrintStream pm = new PrintStream(prova);
		pm.println("a=2");
		pm.println("b=2");
		pm.println("c=15");
		prova.close();
		
		String expr=" a % b == 0 && (a > 2 || c - 12 > 0) ";
		Parser parser=new Parser(new StringReader(expr));
		System.out.println("La tua espressione è: "+ parser.getCondition().toString());
		System.out.println("Contesto inizializzato con i valori contenuti nel file"+fileP);
		Context c=new Context(fileP);
		EvaluationVisitor v=new EvaluationVisitor(c);
		Condition cond=parser.getCondition();
		cond.accept(v);
		System.out.println("Il risultato dell'espressione è: "+v.getResult());
		System.out.println("Contesto modificato con i valori contenuti nel file"+fileN);
		c.refresh(fileN);
		cond.accept(v);
		System.out.println("Il risultato dell'espressione ora è: "+v.getResult());
		System.out.println("Contesto salvato nel file"+fileS);
		c.save(fileS);
		
		IteratorVisitor iv=new IteratorVisitor(new PostIterator());
		System.out.println("La visita postfissa della condizione è");
		//PostIteratorVisitor piv=new PostIteratorVisitor();
		cond.accept(iv);
		PrintVisitor pv=new PrintVisitor();
		cond.accept(pv);
		System.out.println();
		
		System.out.println("La visita simmetrica della condizione è");
		//SimmetricIteratorVisitor siv=new SimmetricIteratorVisitor();
		iv.setConditionIterator(new SimmetricIterator());
		cond.accept(iv);
		cond.accept(pv);
		System.out.println();
		
		
	}

}
