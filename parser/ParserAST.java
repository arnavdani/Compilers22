package parser;
import scanner.*;
import ast.*;
import ast.Number;
import java.util.ArrayList;
import java.util.List;

/**
 * The parser uses the stream of tokens returned by the scanner
 * and an abstract syntax tree to execute blocks of code
 * 
 * Can do + - * /, assign variables, BEGIN END blocks, if and while loops, 
 * 	and procedure declaration, evaluatin, execution
 * @author Arnav Dani
 * @version 4/24/22
 */
public class ParserAST 
{
	private Scanner sc;
	private String cur;
	
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
	 * @return Number of the number in the token
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
	 * Works with (), BEGIN END blocks, assignment, while and if conditionals
	 * 
	 * Follows the defined grammar
	 * stmt -> WRITELN ( expr ) ; | BEGIN stmts END ; | id := expr ;| 
	 * IF cond THEN stmt | WHILE cond DO stmt
	 * 
	 * stmts -> stmts stmt | e
	 * 
	 * Statement is the 2nd highest level in the grammar
	 * 
	 * @return Statement object with the final parsed statement
	 * @throws ScanErrorException
	 */
	public Statement parseStatement() throws ScanErrorException
	{
		Statement returnStatement;
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
			if (cur.equals(":="))
			{
				eat(":=");
				Assignment varAssign = new Assignment(id, parseExpression());
				returnStatement = varAssign;
			}
			else
			{
				eat("(");
				List<Expression> variables = new ArrayList<>();
				if (!cur.equals(")"))
					variables.add(parseExpression());
				
				while (!cur.equals(")"))
				{
					eat(",");
					variables.add(parseExpression());
				}
				eat(")");
				returnStatement =  new ProcedureStatement(id, variables);

			}
			eat("EOL");
			return returnStatement;
			
		}
		else
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
	 * factor ->  ( expr ) | - factor | num | id ( maybeargs) | 
	 * 			id maybeargs -> args | eps
	 * 
	 * args -> args , expr | expr
	 * @return parsed Expression object evaluating the factor
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
				String c = cur;
				
				eat(cur);
				
				if (cur.equals("("))
				{
					eat("(");
					List<Expression> variables = new ArrayList<>();
					while (!cur.equals(")"))
					{
						variables.add(parseExpression());
						if (cur.equals(","))
							eat(",");
					}
					eat(")");
					exp = new ProcedureCall(c, variables);
				}
				else
					exp = new Variable(c);
			}
		}
		return exp;
	}
	
	/**
	 * parseTerm is at an intermediate level in the grammar
	 * and parses multiplication and division operations
	 * @return parsed Expression object evaluating the term
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
	 * @return an Expression object parsed and evaluated in correct order of OPS
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
	
	/**
	 * ParseProgram reads all the variables procedures and then the following statements
	 * and uses the program object to store and execute the section of code
	 * 
	 * A Program represents all the code comprised of the lower level objects
	 * Program is the highest level object in the grammar
	 * 
	 * Program -> VAR (id,)* id;| PROCEDURE id ( maybeparms ) ; stmt program | 
	 * 		stmt . maybeparms -> parms | eps
	 * 
	 * @return Program object to be executed/evaluated
	 * @throws ScanErrorException
	 */
	public Program parseProgram() throws ScanErrorException
	{
		List<String> varList = new ArrayList<String>();
		while(cur.equals("VAR"))
		{
			eat("VAR");
			varList.add(cur);
			eat(cur);
			while (!cur.equals("EOL"))
			{
				eat(",");
				varList.add(cur);
				eat(cur);
			}
			eat("EOL");
		}
		
		
		List<ProcedureDeclaration> procedures = new ArrayList<>();
		while (cur.equals("PROCEDURE"))
		{
			eat("PROCEDURE");
			String c = cur;
			eat(cur);
			eat("(");
			List<String> params = new ArrayList<>();
			while (!cur.equals(")"))
			{
				params.add(cur);
				eat(cur);
				if (cur.equals(","))
					eat(",");
			}
			eat(")");
			eat("EOL");
			Statement statement = parseStatement();
			procedures.add(new ProcedureDeclaration(c, statement, params));
		}
		Statement statement = parseStatement();
		return new Program(varList, procedures, statement);
	}
}

