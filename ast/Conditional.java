package ast;
import emitter.Emitter;
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
	 * @return an integer 0 or 1 with 0 representing false and 1 true
	 */
	@Override
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
	
	/**
	 * Compiles a conditional by evaluating the first expression,
	 * pushing it onto the stack, evaluating the second, then storing the first in a temp
	 * 
	 * Since MIPS is purely top down, the opposite of the true condition being checked
	 * is used to ensure that the statement after the conditional is the one that should be
	 * executed if the conditional is true
	 * 
	 * For ex, when checking for =, the assembly code checks for bne (not equal)
	 * 
	 * If the statement is false, the program should jump to the label
	 * @param e The emitter that writes the code
	 * @param tlabel the target label to jump to if the expression is false
	 */
	public void compile(Emitter e, String tlabel)
	{
		e1.compile(e);
		e.emitPush("$v0");
		e2.compile(e);
		e.emitPop("$t1");
		
		if (op.equals("="))
        {
            e.emit("bne $t1, $v0, " + tlabel + "#Conditional Statement");
        }
        else if (op.equals("<>"))
        {
            e.emit("beq $t1, $v0, " + tlabel + "#Conditional Statement");
        }
        else if (op.equals("<"))
        {
            e.emit("bge $t1, $v0, " + tlabel + "#Conditional Statement");
        }
        else if (op.equals(">"))
        {
            e.emit("ble $t1, $v0, " + tlabel + "#Conditional Statement");
        }
        else if (op.equals("<="))
        {
            e.emit("bgt $t1, $v0, " + tlabel + "#Conditional Statement");
        }
        else if (op.equals(">="))
        {
            e.emit("blt $t1, $v0, " + tlabel + "#Conditional Statement");
        }
        else
        {
            throw new IllegalArgumentException("Operator not recognized.");
        }
        e.emit("#Conditional statement");
		
	}

}
