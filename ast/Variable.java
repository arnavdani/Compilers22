package ast;

import environment.Environment;

public class Variable extends Expression
{
	private String vName;
	
	public Variable(String v)
	{
		vName = v;
	}

	@Override
	public int eval(Environment env) 
	{
		return env.getVariable(vName);
	}
	
	public String getName()
	{
		return vName;
	}
	
	public void setName(String name)
	{
		vName = name;
	}

}
