package ast;
import environment.Environment;

/**
 * Describes a statement where a variable is assigned
 * to an expression within an environment
 * @author Arnav Dani
 * @version 3/22/22
 */
public class Assignment extends Statement
{
	private String var;
	private Expression e;
	
	/**
	 * Constructor for the assignment class
	 * @param variable the variable being assigned
	 * @param e1 the expression being evaluated for a value to assign to the variable
	 */
	public Assignment(String variable, Expression e1)
	{
		var = variable;
		e = e1;
	}
	
	/**
	 * Executes an assignment by setting the variable to the
	 * evaluated expression within the environment
	 */
	public void exec(Environment env)
	{
		env.setVariable(var, e.eval(env));
	}
}
