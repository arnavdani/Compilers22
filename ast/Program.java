package ast;
import environment.*;
import java.util.*;

public class Program
{
	private List<ProcedureDeclaration> pdecs;
	private Statement s;
	
	public Program(List<ProcedureDeclaration> p, Statement st)
	{
		pdecs = p;
		s = st;
	}
	
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
