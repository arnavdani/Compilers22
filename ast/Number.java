package ast;

import environment.Environment;

public class Number extends Expression 
{
	private int val;
	
	public Number(int v)
	{
		val = v;
	}

	@Override
	public int eval(Environment env) 
	{
		return val;
	}

}
