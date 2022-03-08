package parser;
import scanner.*;
import java.util.Map;
import java.util.HashMap;

/**
 * The parser uses the stream of tokens returned by the scanner
 * to execute simple commands and resolve mathematical expressions
 * 
 * Can do + - * /, assign variables, and process BEGIN and END tokens
 * @author Arnav Dani
 * @version 3/5/22
 */
public class Parser 
{
	private Scanner sc;
	private String cur;
	private Map<String, Integer> vars;
	
	/**
	 * Constructor, creates a scanner to scan the input file
	 * and initializes all the instance variables
	 * @param scanner the scanner that scans the input file
	 * @throws ScanErrorException
	 */
	public Parser(Scanner scanner) throws ScanErrorException
	{
		sc = scanner;
		cur = sc.nextToken();
		vars = new HashMap<String, Integer>();
	}
	
	/** 
	 * Tests whether the current token matches its expected value
	 * @param curTok the expected current token
	 * @throws ScanErrorException 
	 * @throws IllegalArgumentException
	 */
	private void eat(String curTok) throws ScanErrorException
	{
		if (curTok.equals(cur))
		{
			cur = sc.nextToken();
		}
		else
			throw new IllegalArgumentException("Illegal Character: expected " + curTok + 
					" and got " + cur);
	}
	
	/**
	 * Parses a number by turning the token to an integer
	 * Number is the most base level unit in the grammar
	 * @return integer of the number in the token
	 * @throws ScanErrorException
	 */
	private int parseNumber() throws ScanErrorException
	{	
		int num = Integer.parseInt(cur);
		eat(cur);
		return num;
	}
	
	/**
	 * Parses a full statement using top down recursive descent
	 * Works with BEGIN and END tokens and parses every expression between the two
	 * Can store variables using a HashMap and evaluate expressions with variables
	 * 		in them
	 * 
	 * 
	 * Works with WRITELN token and parses the statement inside ()
	 * 
	 * Follows the defined grammar
	 * stmt -> WRITELN ( expr ) | BEGIN stmts END | id := expr
	 * stmts -> stmts stmt | e
	 * 
	 * Statement is the highest level in the grammar
	 * @throws ScanErrorException
	 */
	public void parseStatement() throws ScanErrorException
	{
		int num = 0;
		while (cur.equals("WRITELN"))
		{
			eat(cur);
			eat("(");
			num = parseExpression();
			eat(")");
			eat("EOL");
			System.out.println(num);			
		}
		if (cur.equals("BEGIN"))
		{
			eat("BEGIN");
			while (!cur.equals("END"))
			{
				parseStatement();
			}
			eat("END");
			eat("EOL");
		}
		else if (!cur.equals("EOF") && !cur.equals("EOL") && !cur.equals("END"))
		{
			String id = cur;
			eat(cur);
			eat(":=");
			num = parseExpression();
			eat("EOL");
			vars.put(id, num);	
		}
	}
	
	/**
	 * parseFactor parses for all types of factors
	 * A factor is the simplest form of an expression 
	 * 
	 * A factor accounts for - signs in front of numbers
	 * and variable assignment to numbers during computation
	 * 
	 * The factor is the 2nd lowest level in the grammar
	 * 
	 * follows the grammar 
	 * factor -> ( expr ) | - factor | num | id
	 * @return the integer value of the factor
	 * @throws ScanErrorException
	 */
	public int parseFactor() throws ScanErrorException
	{
		int num; 
		if (cur.equals("("))
		{
			eat(cur);
			num = parseExpression();
			eat(")");
		}
		
		else if (cur.equals("-"))
		{
			eat(cur);
			num = -parseFactor();
		}
		
		else if (vars.containsKey(cur))
		{
			num = vars.get(cur);
			eat(cur);
		}
		
		else
			num = parseNumber();
		
		return num;
	}
	
	/**
	 * parseTerm is at an intermediate level in the grammar
	 * and parses multiplication and division operations
	 * @return the term parsed and evaluated
	 * @throws ScanErrorException
	 */
	private int parseTerm() throws ScanErrorException
	{
		int num = parseFactor();
		while (cur.equals("*") || cur.equals("/") )
		{
			if (cur.equals("*"))
			{
				eat("*");
				num = num * parseFactor();
			}
			else if (cur.equals("/"))
			{
				eat("/");
				num = num / parseFactor();
			}
		}		
		return num;
	}
	
	/**
	 * parseExpression is an intermediate process in the grammar
	 * that parses addition and subtraction
	 * 
	 * Since multiplication and division are ahead in the order of operation,
	 * parseExpression calls parseTerm to ensure those operations are done first
	 * @return the expression parsed and evaluated in correct order of OPS
	 * @throws ScanErrorException
	 */
	private int parseExpression() throws ScanErrorException
	{
		int num = parseTerm();
		while (cur.equals("+") || cur.equals("-"))
		{
			if (cur.equals("+"))
			{
				eat("+");
				num = num + parseTerm();
			}
			else if (cur.equals("-"))
			{
				eat("-");
				num = num - parseTerm();
			}
		}
		return num;
	}
}
