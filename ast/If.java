package ast;

import environment.Environment;

public class If extends Statement
{
	private Conditional condo;
	private Statement stmt1;
	private Statement stmt2;
	
	public If(Conditional c, Statement s1, Statement s2)
	{
		condo = c;
		stmt1 = s1;
		stmt2 = s2;
	}
	
	public If(Conditional c, Statement s1)
	{
		condo = c;
		stmt1 = s1;
	}

	@Override
	public void exec(Environment env) 
	{
		int ret = condo.eval(env);
		if (ret == 1)
		{
			if (stmt1 != null)
				stmt1.exec(env);
		}
		else
		{
			if (stmt2 != null)
				stmt2.exec(env);
		}
		
	}

}
