package ast;

import environment.*;
import java.util.*;

public class ProcedureCall extends Expression
{
	String name;
	List<Expression> expressions;
	
	public ProcedureCall(String n, List<Expression> e)
	{
		name = n;
		expressions = e;
	}
	
	@Override
	public int eval(Environment e)
	{
		Environment par = e.getPar();
		Environment env = new Environment(par);
		ProcedureDeclaration pdec = e.getProcedure(name);
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
		return env.getVariable(name);
	}

}
