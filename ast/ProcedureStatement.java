package ast;


import environment.*;
import java.util.*;

public class ProcedureStatement extends Statement
{
	private String name;
	private List<Expression> expressions;
	
	public ProcedureStatement(String s, List<Expression> exp)
	{
		name = s;
		expressions = exp;
	}
	
	@Override
	public void exec(Environment e)
	{
		Environment par = e.getPar();
		Environment env = new Environment(par);
		ProcedureDeclaration pdec = env.getProcedure(name);
		List<String> params = pdec.getParams();
		
		if (expressions.size() != params.size())
			throw new IllegalArgumentException("Expected " + params.size() + 
										" parameters - found " + expressions.size());
		
		for (int i = 0; i < params.size(); i++)
		{
			env.declareVariable(params.get(i), expressions.get(i).eval(e));
		}
		
		env.declareVariable(name, 0);
		Statement s = pdec.getStatement();
		s.exec(env);
	}
}
