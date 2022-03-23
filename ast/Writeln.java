package ast;
import environment.Environment;

/**
 * Writeln class simulates a writeln function
 * where the input expression is evaluated and printed
 * @author Arnav Dani
 * @version 3/22/22
 *
 */
public class Writeln extends Statement
{
	private Expression exp;
	
	/**
	 * Constructor for Writeln object
	 * @param exp the expression to evaluate then write
	 */
	public Writeln(Expression exp)
	{
			this.exp = exp;
	}
	
	/**
	 * Evaluates and then prints the expression
	 * @param env the environment where the expression is evaluated
	 */
	@Override
	public void exec(Environment env)
	{
		System.out.println(exp.eval(env));	
	}
}