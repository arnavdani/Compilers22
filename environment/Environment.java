package environment;
import java.util.Map;
import java.util.HashMap;

import ast.ProcedureDeclaration;

/**
 * The environment class defines the constructs within
 * which the code is evaluated
 * 
 * Added support for parent environments to ensure procedures can be
 * 	declared and executed
 * @author Arnav Dani
 * @version 3/22/22
 *
 */
public class Environment 
{
	private Map<String, Integer> variables;
	private Map<String, ProcedureDeclaration> procedures;
	Environment par;

	/**
	 * Constructor for Environment object
	 */
	public Environment(Environment p)
	{
		variables = new HashMap<String, Integer>();
		procedures = new HashMap<String, ProcedureDeclaration>();
		par = p;
	}
	
	/**
	 * If the variable doesn't already exist in the child and the parent,
	 * 	the variable is added to the environment using a map
	 * @param variable the variable being stored
	 * @param value the value associated with the variable
	 */
	public void setVariable(String variable, int value)
    {
        if (!variables.containsKey(variable) && par != null && par.containsVariable(variable))
        {
            par.setVariable(variable, value);
            return;
        }
        variables.put(variable, value);
    }
	
	/**
	 * Declares a variable in 'this' environment by putting it in the map
	 * @param variable the variable being stored
	 * @param value the value of the variable
	 */
	public void declareVariable(String variable, int value)
	{
		variables.put(variable, value);
	}
	
	/**
	 * Checks whether the current or parent environment contains the variable
	 * @param variable the variable being searched for
	 * @return true if the variable is in 'this' or the parent env;
	 * 			otherwise, false
	 */
	private boolean containsVariable(String variable)
    {
        boolean contains = false;
        if (par != null)
            contains = par.containsVariable(variable);

        
        return contains || variables.containsKey(variable);
    }
	
	/**
	 * if the variable exists in the child or parent, returns the value
	 * 
	 * If the variable does not exist, it is created and assigned to 0
	 * @param variable the string to look for in the map
	 * @return integer of the value of the variable
	 */
	public int getVariable(String variable)
	{
		if (variables.containsKey(variable))
        {
            return variables.get(variable);
        }
		else if (par != null)
		{
			return par.getVariable(variable);
		}
		
		variables.put(variable, 0);
		return 0;
	}
	
	/**
	 * Stores procedures in the procedure map by using the name of the
	 * 	procedure as a reference for the declaration
	 * @param procedure the name of the procedure being stored
	 * @param dec the declaration of the same procedure
	 */
	public void setProcedure(String procedure, ProcedureDeclaration dec)
    {
        if (par != null)
        {
            par.setProcedure(procedure, dec);
            return;
        }
        procedures.put(procedure, dec);
    }
	
	/**
	 * Finds procedures in the envs by identifying declarations by name
	 * @param procedure the name of the procedure being searched for
	 * @return a ProcedureDeclaration of the procedure being looked for
	 * @throws IllegalArgumentException if procedure cannot be found in parent
	 * 	or child env
	 */
	public ProcedureDeclaration getProcedure(String procedure)
    {
        if (par != null)
            return par.getProcedure(procedure);

        ProcedureDeclaration returndec;
        
        if (procedures.containsKey(procedure))
            returndec = procedures.get(procedure);

        else
            throw new IllegalArgumentException("Procedure " + procedure + " is undefined");
        
        return returndec;
    }
	
	/**
	 * @return the parent env if it exists; otherwise, null
	 */
	public Environment getPar()
    {
        if (par == null)
        {
            return this;
        }
        return par.getPar();
    }
	

}
