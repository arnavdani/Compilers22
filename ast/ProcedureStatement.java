package ast;

import environment.*;
import java.util.*;

/**
 * ProcedureStatement executes a procedure by followingt the same process
 * 	as calling the procedure. The main difference is that the statement does
 * 	not return a value while a ProcedureCall does
 * @author Arnav Dani
 * @version 4/24/22
 *
 */
public class ProcedureStatement extends Statement
{
	private String name;
	private List<Expression> expressions;
	
	/**
	 * Constructor for ProcedureStatement
	 * @param s the name of the procedure
	 * @param exp a list of expressions to be used as parameters
	 */
	public ProcedureStatement(String s, List<Expression> exp)
	{
		name = s;
		expressions = exp;
	}
	
	/**
	 * Executes the procedure by acessing the declaration
	 * 	from the parent env, evaluating all the parameters,
	 * 	declaring all variables within the proper scope, and 
	 * 	executing the statement block wthin the method to calculate
	 * 		the final return of the procedure.
	 */
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
