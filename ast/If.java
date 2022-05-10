package ast;
import emitter.Emitter;
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
	
	/**
	 * Compiles if by compiling the conditional
	 * If the conditional is met, statement 1 is compiled
	 * and the program jumps to the end of the if
	 * 
	 * otherwise, the program jumps to the elseif label as a part
	 * 	of the conditional and compiles statement 2 if it is not null
	 * 
	 * Finally, the endif label is displayed and the program continues
	 * @param e The emitter writing the code
	 */
	public void compile(Emitter e)
	{
		int i = e.nextLabelID();
		condo.compile(e, "elseif" + i);
		stmt1.compile(e);
		e.emit("j endif" + i);
		e.emit("elseif" + i + ":   #jump for else");
		if (stmt2 != null)
		{
			stmt2.compile(e);
		}
		e.emit("endif" + i + ":	   #jump for the if");
		
	}

}
