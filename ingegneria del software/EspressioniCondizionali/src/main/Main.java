package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import builder.Parser;
import builder.SyntaxException;
import iterator.PostIterator;
import iterator.SimmetricIterator;
import operators.Condition;
import visitor.Context;
import visitor.EvaluationVisitor;
import visitor.IteratorVisitor;
import visitor.PrintVisitor;

public class Main {

	public static Parser expr(Scanner sc) {
		System.out.println("Inserire l'espressione condizionale [separare gli operatori e le variabili con lo spazio]: ");
		boolean ok = false;
		Parser p = null;
		while (!ok) {
			String expr = sc.nextLine();
			Reader r = new StringReader(expr);
			try {
				p = new Parser(r);
				ok = true;
			} catch (SyntaxException e) {
				System.out.println(e.getMessage());
				System.out.println("Reinserisci l'espressione");
			}
		}
		System.out.println("La tua espressione è: " + p.getCondition().toString());
		return p;
	}

	public static Context setContext(Scanner sc) {
		System.out.println("Inserisci il file properties dove prendere il contesto:");
		Context c = null;
		boolean ok = false;
		while (!ok) {
			String nomeFile = sc.nextLine();
			try {
				c = new Context(nomeFile);
				ok = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Reinserisci il nome del file");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println("Reinserisci il nome del file");
			}
		}
		return c;
	}

	public static void save(Context c, Scanner sc) {
		System.out.println("Inserire il nuovo file in cui salvare il context");
		boolean ok3 = false;
		while (!ok3) {
			try {
				String nomeFile = sc.nextLine();
				c.save(nomeFile);
				ok3 = true;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				System.out.println("Reinserisci il nome del file");
			}
		}
	}

	public static void ripristina(Context c, Scanner sc) {
		System.out.println("Inserire il nuovo file da cui prendere il context");
		boolean ok2 = false;
		while (!ok2) {
			try {
				String nomeFile = sc.nextLine();
				c.refresh(nomeFile);
				ok2 = true;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				System.out.println("Reinserisci il nome del file");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Reinserisci il nome del file");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Parser p = expr(sc);
		Context c = setContext(sc);
		EvaluationVisitor v = new EvaluationVisitor(c);
		IteratorVisitor iv = new IteratorVisitor(new PostIterator());
		PrintVisitor pv = new PrintVisitor();
		Condition cond = p.getCondition();
		System.out.println("Operazione consentite [ 1:salvaContesto, 2:ripristinaContesto, 3:visitaPost, 4:visitaSimm, 5:modificaExpr, 6:valutaExpr, 7:esci]");
		while (true) {
			String scelta = sc.nextLine();
			if (scelta.equals("esci") || scelta.equals("7")) {
				System.out.println("Goodbye!");
				break;
			} else if (scelta.equals("valutaExpr") || scelta.equals("6")) {
				try {
					cond.accept(v);
					System.out.println("Il risultato dell'espressione è: " + v.getResult());
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}

			} else if (scelta.equals("salvaContesto") || scelta.equals("1")) {
				save(c, sc);
			} else if (scelta.equals("ripristinaContesto") || scelta.equals("2")) {
				ripristina(c, sc);
			} else if (scelta.equals("modificaExpr") || scelta.equals("5")) {
				p = expr(sc);
				cond = p.getCondition();
			} else if (scelta.equals("visitaPost") || scelta.equals("3")) {
				iv.setConditionIterator(new PostIterator());
				cond.accept(iv);
				cond.accept(pv);
				System.out.println();
			} else if (scelta.equals("visitaSimm") || scelta.equals("4")) {
				iv.setConditionIterator(new SimmetricIterator());
				cond.accept(iv);
				cond.accept(pv);
				System.out.println();
			} else
				System.out.println("Comando non valido, reinserire comando");
			System.out.println("Operazione consentite [ 1:salvaContesto, 2:ripristinaContesto, 3:visitaPost, 4:visitaSimm, 5:modificaExpr, 6:valutaExpr, 7:esci]");
		}
		return;

	}

}
