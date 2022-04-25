package ast;
import environment.*;
import java.util.*;

/**
 * The Program class defines a program object, which encompasses 
 * the entire code for evaluation separately
 * @author Arnav Dani
 * @version 04/24/22
 */
public class Program
{
	private List<ProcedureDeclaration> pdecs;
	private Statement s;
	
	/**
	 * Constructor for Program Object
	 * @param p list of procedure declarations
	 * @param st the final statement being executex
	 */
	public Program(List<ProcedureDeclaration> p, Statement st)
	{
		pdecs = p;
		s = st;
	}
	
	/**
	 * Executes a Program by calling execute on all the objects
	 * 	within the syntax tree
	 * @param e The environment in which the Program is being executed
	 */
	public void exec(Environment e)
	{
		for (int i = 0; i < pdecs.size(); i++)
		{
			ProcedureDeclaration pdec = pdecs.get(i);
			pdec.exec(e);
		}
		s.exec(e);
	}

}
