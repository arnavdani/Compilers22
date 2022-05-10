package ast;
import emitter.Emitter;
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
	
	/**
	 * Compiles while loop by first generating a unique label name
	 * then follows the common assembly loop structure of label, content, and jump
	 * compiles the conditional and statement and jumps back to the top of the while
	 * 	to reevaluate the condition
	 * 
	 * If true, the loop continues; if the condition is false, the program jumps to endwhile
	 * 	and continues with the rest of the code
	 */
	public void compile(Emitter e)
	{
		int i = e.nextLabelID();
		e.emit("while" + i + ":   #jump for while" + i);
		condo.compile(e, "endwhile" + i);
		stmt1.compile(e);
		e.emit("j while" + i + " #loop back to top of while");
		e.emit("endwhile" + i + ": #end of while - continue");
		
	}

}
