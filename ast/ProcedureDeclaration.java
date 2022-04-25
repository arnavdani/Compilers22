package ast;
import environment.*;
import java.util.*;

/**
 * A ProcedureDeclaration is an object that stores the name and
 * 	parameters of a procedure for future use and execution
 * @author Arnav Dani 
 * @version 4/23/2022
 *
 */
public class ProcedureDeclaration 
{
	private String name;
	private Statement s;
	private List<String> params;
	
	/**
	 * Constructor for ProcedureDeclaration
	 * @param n the name of the procedure
	 * @param st the statements within the procedure
	 * @param p the parameters of the procedure
	 */
	public ProcedureDeclaration(String n, Statement st, List<String> p)
	{
		name = n;
		s = st;
		params = p;
	}
	
	/**
	 * @return the statement s of the body of the procedure
	 */
	public Statement getStatement()
	{
		return s;
	}
	
	/**
	 * @return the parameters used in the procedure
	 */
	public List<String> getParams()
	{
		return params;
	}
	
	/**
	 * Execute for the ProcedureDeclaration is storing the 
	 * procedure in the map for future use
	 * 
	 * The name is the pointer to the declaration in the map
	 * in the environment
	 * @param e The environment where the procedure is being stored
	 */
	public void exec(Environment e)
	{
		e.setProcedure(name, this);
	}
}
