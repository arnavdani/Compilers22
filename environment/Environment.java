package environment;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Environment 
{
	private Map<String, Integer> variables;
	private Environment env;
	
	public Environment(Environment e)
	{
		variables = new HashMap<>();
		env = e;
	}
	
	public void setVariable(String variable, int value)
	{
		if (!variables.containsKey(variable))
		{
			variables.put(variable, value);
		}
		
	}
	
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
