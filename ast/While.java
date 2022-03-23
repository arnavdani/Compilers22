package ast;

import environment.Environment;

public class While extends Statement
{
	private Conditional condo;
	private Statement stmt1;
	
	public While(Conditional c, Statement s1)
	{
		condo = c;
		stmt1 = s1;
	}

	@Override
	public void exec(Environment env) 
	{
		int ret = condo.eval(env);
		while (ret == 1)
		{
			if (stmt1 != null)
				stmt1.exec(env);
			ret = condo.eval(env);
			
		}
		
	}

}
