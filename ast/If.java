package ast;
import environment.Environment;

/**
 * If class holds the constructs to execute
 * if conditional statements
 * 
 * The class supports both IF and IF .. ELSE
 * statements
 * 
 * @author Arnav Dani
 * @version 3/22/22
 *
 */
public class If extends Statement
{
	private Conditional condo;
	private Statement stmt1;
	private Statement stmt2;
	
	/**
	 * Constructor for If class when implementing
	 * IF THEN ... ELSE ...
	 * @param c the conditional being used
	 * @param s1 the statement to be executed if the condition is true
	 * @param s2 the statement to be executed if the condition is false
	 */
	public If(Conditional c, Statement s1, Statement s2)
	{
		condo = c;
		stmt1 = s1;
		stmt2 = s2;
	}
	
	/**
	 * Constructor for If class when implementing
	 * IF THEN ....
	 * @param c the conditional being used
	 * @param s1 the statement to be executed if the condition is true
	 */
	public If(Conditional c, Statement s1)
	{
		condo = c;
		stmt1 = s1;
	}

	/**
	 * Executes an if statement by first checking the conditional,
	 * 	deciding whether to execute the first statement,
	 * 	and finally executing the else statement if the statements
	 * 	are not null
	 * 
	 * @param env the environment in which the statement is executed
	 */
	@Override
	public void exec(Environment env) 
	{
		int ret = condo.eval(env);
		if (ret == 1)
		{
			if (stmt1 != null)
				stmt1.exec(env);
		}
		else
		{
			if (stmt2 != null)
				stmt2.exec(env);
		}
		
	}

}
