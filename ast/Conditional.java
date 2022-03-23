package ast;
import environment.Environment;

/**
 * Conditional is a class that evaluates conditional
 * 	expressions that use relative operators
 * 
 * @author Arnav Dani
 * @version 3/22/22
 */
public class Conditional extends Expression
{
	private String op;
	private Expression e1;
	private Expression e2;
	
	/**
	 * Constructor for Conditional class
	 * @param operator the relative operator in question
	 * @param exp1 the expression left of the operator
	 * @param exp2 the expression right of the operator
	 */
	public Conditional(String operator, Expression exp1, Expression exp2)
	{
		op = operator;
		e1 = exp1;
		e2 = exp2;
	}
	
	/**
	 * Evaluates relative operations using boolean logic
	 * works with: == > < >= <= <>
	 * @param env the environment in which the expression is being evaluated
	 * @returns an integer 0 or 1 with 0 representing false and 1 true
	 */
	public int eval(Environment env)
	{
		int val1 = e1.eval(env);
		int val2 = e2.eval(env);
		
		int var = 0;
		
		if (op.equals("=="))
		{
			if (val1 == val2)
			{
				var =  1;
			}		
		}
		
		else if (op.equals("<>"))
		{
			if (val1 != val2)
			{
				var =  1;
			}
		}
		
		else if (op.equals(">"))
		{
			if (val1 > val2)
				var = 1;
		}
		
		else if (op.equals("<"))
		{
			if (val1 < val2)
				var = 1;
		}
		
		else if (op.equals("<="))
		{
			if (val1 <= val2)
				var = 1;
		}
		
		else if (op.equals(">="))
		{
			if (val1 >= val2)
				var = 1;
		}
		return var;
	}

}
