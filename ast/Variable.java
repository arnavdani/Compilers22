package ast;
import emitter.Emitter;
import environment.Environment;

/**
 * Variable class helps create and define variables
 * @author Arnav Dani
 * @version 3/22/22
 */
public class Variable extends Expression
{
	private String vName;
	
	/**
	 * Constructor for variable class
	 * @param v name of the variable
	 */
	public Variable(String v)
	{
		vName = v;
	}
	
	/**
	 * evaluates the variable by returning its true value
	 * based on the environment of execution/eval
	 * @param env environment of execution/evaluation
	 */
	@Override
	public int eval(Environment env) 
	{
		return env.getVariable(vName);
	}
	
	public void compile(Emitter e)
	{
		e.emit("la $t1 var" + vName);
		e.emit("lw $v0 ($t1)");
	}
}
