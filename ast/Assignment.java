package ast;

import environment.Environment;

public class Assignment extends Statement
{
	private String var;
	private Expression e;
	
	public Assignment(String variable, Expression e1)
	{
		var = variable;
		e = e1;
	}
	
	public Expression getExpression()
	{
		return e;
	}
	
	public void setExpression(Expression ex)
	{
		e = ex;
	}
	
	public void exec(Environment env)
	{
		env.setVariable(var, e.eval(env));
	}
	

}
