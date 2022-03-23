package ast;

import environment.Environment;

public class Conditional extends Expression
{
	private String op;
	private Expression e1;
	private Expression e2;
	
	public Conditional(String operator, Expression exp1, Expression exp2)
	{
		op = operator;
		e1 = exp1;
		e2 = exp2;
	}
	
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

}
