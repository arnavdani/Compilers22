package ast;

import environment.Environment;

public class BinOp extends Expression
{
	private String op;
	private Expression e1;
	private Expression e2;
	
	public BinOp(String operator, Expression exp1, Expression exp2)
	{
		op = operator;
		e1 = exp1;
		e2 = exp2;
	}

	@Override
	public int eval(Environment env) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
