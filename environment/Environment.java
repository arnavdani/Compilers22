package environment;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

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

	/**
	 * Constructor for Environment object
	 */
	public Environment()
	{
		variables = new HashMap<>();
	}
	
	/**
	 * puts the variable in the environment by putting it in a map
	 * @param variable the variable being stored
	 * @param value the value associated with the variable
	 */
	public void setVariable(String variable, int value)
	{
		variables.put(variable, value);
	}
	
	/**
	 * if the variable exists in the map, returns the value
	 * @param variable the string to look for in the map
	 * @return integer of the value of the variable
	 */
	public int getVariable(String variable)
	{
		int val = 0;
		if (variables.containsKey(variable))
        {
            val = variables.get(variable);
        }
		return val;
	}
	

}
