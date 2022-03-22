package ast;

import java.util.List;

import environment.Environment;

public class Block extends Statement
{
	private List<Statement> stmts;
	
	public Block(List<Statement> statements)
	{
		stmts = statements;
	}
	
	public void exec(Environment env)
	{
		for (int i = 0; i < stmts.size(); i++)
		{
			Statement s = stmts.get(i);
			s.exec(env);
		}
	}

}
