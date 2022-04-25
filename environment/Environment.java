package environment;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import ast.ProcedureDeclaration;

/**
 * The environment class defines the constructs within
 * which the code is evaluated
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
	 * puts the variable in the endvironment by putting it in a map
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
	
	public void declareVariable(String variable, int value)
	{
		variables.put(variable, value);
	}
	
	private boolean containsVariable(String variable)
    {
        boolean contains = false;
        if (par != null)
            contains = par.containsVariable(variable);

        
        return contains || variables.containsKey(variable);
    }
	
	/**
	 * if the variable exists in the map, returns the value
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
	
	public void setProcedure(String procedure, ProcedureDeclaration dec)
    {
        if (par != null)
        {
            par.setProcedure(procedure, dec);
            return;
        }
        procedures.put(procedure, dec);
    }
	
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
	
	public Environment getPar()
    {
        if (par == null)
        {
            return this;
        }
        return par.getPar();
    }
	

}
