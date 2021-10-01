package builder;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

import iterator.OperatorIteratorPost;
import operators.And;
import operators.Condition;
import operators.Div;
import operators.EQ;
import operators.GE;
import operators.GT;
import operators.LE;
import operators.LT;
import operators.Minus;
import operators.Mult;
import operators.NEQ;
import operators.Not;
import operators.Or;
import operators.Plus;
import operators.Power;
import operators.Rem;
import operators.Value;
import operators.Variable;
import visitor.Context;
import visitor.EvaluationVisitor;
import visitor.PrintVisitor;

public class Parser {
	private LexicalAnalyzer lexer;
	private Token token;
	private Condition root;

	public Parser(Reader in) {
		lexer = new LexicalAnalyzer(in);
		condition();
		awaited(Token.EOF);
	}

	private void condition() {
		token = lexer.next();
		termb();
		while (token == Token.OR) {
			Or c = new Or();
			c.setLeft(root);
			token = lexer.next();
			termb();
			c.setRight(root);
			root = c;
		}
	}

	private void termb() {
		factb();
		while (token == Token.AND) {
			And c = new And();
			c.setLeft(root);
			token = lexer.next();
			factb();
			c.setRight(root);
			root = c;
		}
	}

	private void factb() {
		if (token == Token.NOT) {
			Not c = new Not();
			token = lexer.next();
			factb();
			c.setRight(root);
			root = c;
		} else if (token == Token.OPAR) {
			condition();
			awaited(Token.CPAR);
		} else {
			expr();
			if (token != Token.EQ && token != Token.NEQ && token != Token.GE && token != Token.GT && token != Token.LE
					&& token != Token.LT) {
				throw new SyntaxException("Atteso un operatore di confronto dopo una expr");
			}
			Condition c;
			if (token == Token.EQ)
				c = new EQ();
			else if (token == Token.NEQ)
				c = new NEQ();
			else if (token == Token.GT)
				c = new GT();
			else if (token == Token.GE)
				c = new GE();
			else if (token == Token.LT)
				c = new LT();
			else
				c = new LE();
			c.setLeft(root);
			token = lexer.next();
			expr();
			c.setRight(root);
			root = c;
		}
	}

	private void expr() {
		if (token == Token.PLUS) {
			Plus c = new Plus();
			token = lexer.next();
			term();
			c.setRight(root);
			root = c;
		} else if (token == Token.MINUS) {
			Minus c = new Minus();
			token = lexer.next();
			term();
			c.setRight(root);
			root = c;
		} else {
			term();
			while (token == Token.PLUS || token == Token.MINUS) {
				Condition c;
				if (token == Token.PLUS)
					c = new Plus();
				else
					c = new Minus();
				c.setLeft(root);
				token = lexer.next();
				term();
				c.setRight(root);
				root = c;
			}
		}
	}

	private void term() {
		termp();
		while (token == Token.DIV || token == Token.MULT || token == Token.REM) {
			Condition c;
			if (token == Token.DIV)
				c = new Div();
			else if (token == Token.MULT)
				c = new Mult();
			else
				c = new Rem();
			c.setLeft(root);
			token = lexer.next();
			termp();
			c.setRight(root);
			root = c;
		}
	}

	private void termp() {
		fact();
		while (token == Token.POWER) {
			Power c = new Power();
			c.setLeft(root);
			token = lexer.next();
			fact();
			c.setRight(root);
			root = c;
		}
	}

	private void fact() {
		if (token == Token.VARIABLE) {
			Condition c = new Variable(lexer.getString());
			root = c;
			token = lexer.next();
		} else if (token == Token.VALUE) {
			Condition c = new Value(lexer.getString());
			root = c;
			token = lexer.next();
		} else if (token == Token.OPAR1) {
			token = lexer.next();
			expr();
			awaited(Token.CPAR1);
		} else
			throw new SyntaxException("Atteso una variabile, un valore o una parentesi quadra");
	}

	private void awaited(Token t) {
		if (token != t) {
			String msg = " trovato " + token + " mentre si attendeva " + t;
			throw new SyntaxException(msg);
		}
		token = lexer.next();
	}

	public Condition getCondition() {
		return root;
	}

}
