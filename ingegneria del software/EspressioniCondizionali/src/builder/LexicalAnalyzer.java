package builder;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class LexicalAnalyzer {
	private StreamTokenizer input;
	private Token token;
	private final String REGEXWORD = "[a-zA-Z][a-zA-Z0-9]*";
	private final String REGEXNUMBER = "[0-9]+";

	public LexicalAnalyzer(Reader in) {

		input = new StreamTokenizer(in);
		input.resetSyntax();
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('0', '9');
		input.wordChars('!', '!');
		input.wordChars('=', '=');
		input.wordChars('<', '<');
		input.wordChars('>', '>');
		input.wordChars('|', '|');
		input.wordChars('&', '&');
		input.whitespaceChars('\u0000', ' ');
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar('[');
		input.ordinaryChar(']');
		input.ordinaryChar('+');
		input.ordinaryChar('-');
		input.ordinaryChar('*');
		input.ordinaryChar('/');
		input.ordinaryChar('%');
		input.ordinaryChar('^');
	}

	public String getString() {
		return input.sval;
	}

	public Token next() {
		try {
			switch (input.nextToken()) {
			case StreamTokenizer.TT_EOF:
				token = Token.EOF;
				break;
			case StreamTokenizer.TT_WORD:
				if (input.sval.equalsIgnoreCase("or") || input.sval.equals("||"))
					token = Token.OR;
				else if (input.sval.equalsIgnoreCase("and") || input.sval.equals("&&"))
					token = Token.AND;
				else if (input.sval.equalsIgnoreCase("not") || input.sval.equals("!"))
					token = Token.NOT;
				else if (input.sval.matches(REGEXWORD))
					token = Token.VARIABLE;
				else if (input.sval.matches(REGEXNUMBER))
					token = Token.VALUE;
				else if (input.sval.equals("<"))
					token = Token.LT;
				else if (input.sval.equals("<="))
					token = Token.LE;
				else if (input.sval.equals(">"))
					token = Token.GT;
				else if (input.sval.equals(">="))
					token = Token.GE;
				else if (input.sval.equals("!="))
					token = Token.NEQ;
				else
					token = Token.EQ;
				break;
			case '(':
				token = Token.OPAR;
				break;
			case ')':
				token = Token.CPAR;
				break;
			case '[':
				token = Token.OPAR1;
				break;
			case ']':
				token = Token.CPAR1;
				break;
			case '+':
				token = Token.PLUS;
				break;
			case '-':
				token = Token.MINUS;
				break;
			case '*':
				token = Token.MULT;
				break;
			case '/':
				token = Token.DIV;
				break;
			case '%':
				token = Token.REM;
				break;
			case '^':
				token = Token.POWER;
				break;
			default:
				token = Token.INVALID_CHAR;
			}
		} catch (IOException e) {
			token = Token.EOF;
		}
		return token;
	}

}
