package parser;
import scanner.*;
import java.util.Map;
import ast.*;
import ast.Number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The parser uses the stream of tokens returned by the scanner
 * to execute simple commands and resolve mathematical expressions
 * 
 * Can do + - * /, assign variables, and process BEGIN and END tokens
 * @author Arnav Dani
 * @version 3/5/22
 */
public class ParserAST 
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
	public ParserAST(Scanner scanner) throws ScanErrorException
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
	private Number parseNumber() throws ScanErrorException
	{	
		int num = Integer.parseInt(cur);
		eat(cur);
		return new Number(num);
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
	public Statement parseStatement() throws ScanErrorException
	{
		while (cur.equals("WRITELN"))
		{
			eat(cur);
			eat("(");
			Expression exp = parseExpression();
			eat(")");
			eat("EOL");
			return new Writeln(exp);
		}
		if (cur.equals("BEGIN"))
		{
			eat("BEGIN");
			List<Statement> statements = new ArrayList<Statement>();
			while (!cur.equals("END"))
			{
				statements.add(parseStatement());
			}
			eat("END");
			eat("EOL");
			return new Block(statements);
		}
		
		if (cur.equals("IF"))
		{
			eat(cur);
			Expression exp1 = parseExpression();
			String relop = cur;
			eat(cur);
			Conditional condo = new Conditional(relop, exp1, parseExpression());
			eat("THEN");
			Statement s1 = parseStatement();
			if(cur.equals("ELSE"))
			{
				eat(cur);
				return new If(condo, s1, parseStatement());
			}
			else
				return new If(condo, s1);
		}
		
		if (cur.equals("WHILE"))
		{
			eat(cur);
			Expression exp1 = parseExpression();
			String relop = cur;
			eat(cur);
			Conditional condo = new Conditional(relop, exp1, parseExpression());
			eat("DO");
			return new While(condo, parseStatement());
		}
		
		else if (!cur.equals("EOF") && !cur.equals("EOL") && !cur.equals("END"))
		{
			String id = cur;
			eat(cur);
			eat(":=");
			Assignment varAssign = new Assignment(id, parseExpression());
			eat("EOL");
			return  varAssign;
		}
		return null;
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
	public Expression parseFactor() throws ScanErrorException
	{
		Expression exp;
		if (cur.equals("("))
		{
			eat(cur);
			exp = parseExpression();
			eat(")");
		}
		
		else if (cur.equals("-"))
		{
			eat(cur);
			exp = new BinOp("-", new Number(0),parseFactor());
		}
		
		else
		{
			try
			{
				exp = parseNumber();
			}
			catch (NumberFormatException n)
			{
				exp = new Variable(cur);
				eat(cur);
			}
			
		}
		
		return exp;
	}
	
	/**
	 * parseTerm is at an intermediate level in the grammar
	 * and parses multiplication and division operations
	 * @return the term parsed and evaluated
	 * @throws ScanErrorException
	 */
	private Expression parseTerm() throws ScanErrorException
	{
		Expression e = parseFactor();
		while (cur.equals("*") || cur.equals("/") )
		{
			if (cur.equals("*"))
			{
				eat("*");
				e = new BinOp("*", e, parseFactor());
			}
			else if (cur.equals("/"))
			{
				eat("/");
				e = new BinOp("/", e, parseFactor());
			}
		}		
		return e;
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
	private Expression parseExpression() throws ScanErrorException
	{
		Expression exp = parseTerm();
		while (cur.equals("+") || cur.equals("-"))
		{
			if (cur.equals("+"))
			{
				eat("+");
				return new BinOp("+", exp, parseTerm());
			}
			else if (cur.equals("-"))
			{
				eat("-");
				return new BinOp("-", exp, parseTerm());
			}
		}
		return exp;
	}
}