package ast;
import environment.Environment;
import emitter.Emitter;

/**
 * Number class is used to interpret numbers
 * @author Arnav Dani
 * @version 3/22/22
 */
public class Number extends Expression 
{
	private int val;
	
	/**
	 * Constructor for number class
	 * @param v the number
	 */
	public Number(int v)
	{
		val = v;
	}
	
	/**
	 * Evaluates a number object by returning the number
	 * @return integer of the number
	 */
	@Override
	public int eval(Environment env) 
	{
		return val;
	}
	
	/**
	 * Compiles number by putting the value into $v0
	 * @param e the Emitter that emits the code
	 */
	@Override
	public void compile(Emitter e)
	{
		e.emit("li $v0 " + val);
	}

}
