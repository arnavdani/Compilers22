package ast;
import environment.*;
import java.util.*;

/**
 * fill out
 * @author arnav
 * @version
 *
 */
public class ProcedureDeclaration 
{
	private String name;
	private Statement s;
	private List<String> params;
	
	public ProcedureDeclaration(String n, Statement st, List<String> p)
	{
		name = n;
		s = st;
		params = p;
	}
	
	public Statement getStatement()
	{
		return s;
	}
	
	public List<String> getParams()
	{
		return params;
	}
	
	public void exec(Environment e)
	{
		e.setProcedure(name, this);
	}
}
