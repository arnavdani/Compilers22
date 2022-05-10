package ast;
import environment.Environment;
import emitter.Emitter;

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
	
	/**
	 * Compiles a writeln statement by
	 * 	writing the MIPS equivalent
	 * 
	 * First, the expression within the WriteLN
	 * is compiled and evaluated,
	 * Then, the final value is moved into $a0
	 * 
	 * Finally, the value is printing using the system commands
	 * 
	 * @param E the emitter that emits the final code
	 */
	@Override
	public void compile(Emitter e)
	{
		exp.compile(e); 
        e.emit("move $a0, $v0 #Move value into arg register");
        e.emit("li $v0, 1 #Prepare to print");
        e.emit("syscall");
        e.newline();
	}
}