package ast;

import environment.*;
import java.util.*;

/**
 * ProcedureCall calls the procedure and executes the statement within it
 * @author Arnav Dani
 * @version 4/23/22
 *
 */
public class ProcedureCall extends Expression
{
	String name;
	List<Expression> expressions;
	
	/**
	 * Constructor for ProcedureCall
	 * @param n the name of the procedure
	 * @param e expressions that represent parameters
	 */
	public ProcedureCall(String n, List<Expression> e)
	{
		name = n;
		expressions = e;
	}
	
	/**
	 * Evaluates the procedure by acessing the declaration
	 * 	from the parent env, evaluating all the parameters,
	 * 	declaring all variables within the proper scope, and 
	 * 	executing the statement block wthin the method to calculate
	 * 		the final return of the procedure.
	 * @return integer of the final return of the procedure
	 */
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
