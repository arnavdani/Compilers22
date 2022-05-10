package ast;
import emitter.Emitter;
import environment.Environment;

/**
 * Evaluates all expressions
 * where an binary operator is used
 * to operate on two expressions, resulting
 * in a 3rd value
 * @author Arnav Dani
 * @version 3/22/22
 */
public class BinOp extends Expression
{
	private String op;
	private Expression e1;
	private Expression e2;
	
	/**
	 * Constructor for BinOp class
	 * @param operator the operator being used
	 * @param exp1 the expression left of the operator
	 * @param exp2 the expression right of the operator
	 */
	public BinOp(String operator, Expression exp1, Expression exp2)
	{
		op = operator;
		e1 = exp1;
		e2 = exp2;
	}
	
	/**
	 * Overrides eval from the Expression class to
	 * evaluate an expression that contains a binary operator
	 * 
	 * operators include / * + - %
	 * @return integer value of the calculation
	 */
	@Override
	public int eval(Environment env) 
	{
		if (op.equals("*"))
			return e1.eval(env) * e2.eval(env);
		if (op.equals("/"))
			return e1.eval(env) / e2.eval(env);
		if (op.equals("+"))
			return e1.eval(env) + e2.eval(env);
		if (op.equals("-"))
			return e1.eval(env) - e2.eval(env);
		else
			return e1.eval(env) % e2.eval(env);	
	}
	
	
	/**
	 * Compiles a binary op by compiling the left most operation first,
	 * 	putting it on the stack, and then evaluating the right.
	 * 
	 * Depending on the operator, the correct operation happens
	 * and the value is stored in $t0
	 * @param e the Emitter that writes the MIPS code
	 */
	@Override
	public void compile(Emitter e)
	{
		e1.compile(e);
		e.emitPush("$v0");
		e2.compile(e);
		e.emitPop("$t0");
		
		if(op.equals("+"))
            e.emit("addu $v0, $v0, $t0");
		
        else if(op.equals("-"))
            e.emit("subu $v0, $v0, $t0");
        else
        {
            if(op.equals("*"))
                e.emit("mult $v0, $t0");
            
            else
                e.emit("div $t0, $v0");
            
            e.emit("mflo $v0");
        }
	}

}
