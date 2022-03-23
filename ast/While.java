package ast;
import environment.Environment;

/**
 * While defines the execution of a While loop construct
 * @author Arnav Dani
 * @version 3/22/22
 */
public class While extends Statement
{
	private Conditional condo;
	private Statement stmt1;
	
	/**
	 * Constructor for While Object
	 * @param c conditional object for while
	 * @param s1 the statement to execute inside the while
	 */
	public While(Conditional c, Statement s1)
	{
		condo = c;
		stmt1 = s1;
	}
	
	/**
	 * Executes the while loop by checking
	 * the conditional every time and then
	 * executes the statement while the condition is met
	 * 
	 * @param env environment in which statement is executed
	 */
	@Override
	public void exec(Environment env) 
	{
		int ret = condo.eval(env);
		while (ret == 1)
		{
			if (stmt1 != null)
				stmt1.exec(env);
			ret = condo.eval(env);
			
		}
		
	}

}
