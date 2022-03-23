package ast;
import java.util.List;

import environment.Environment;

/**
 * Block object helps group sets of statements
 * and evaluate them together
 * @author Arnav Dani
 * @version 3/22/22
 */
public class Block extends Statement
{
	private List<Statement> stmts;
	
	/**
	 * Constructor for Block Object
	 * @param statements list of statements containing all
	 * 			the statements in the block
	 */
	public Block(List<Statement> statements)
	{
		stmts = statements;
	}
	
	/**
	 * Executes the block by iterating through
	 * 	and executing each statement
	 * 
	 * @param env the environment in which the statements 
	 * 	are being executed
	 */
	@Override
	public void exec(Environment env)
	{
		for (int i = 0; i < stmts.size(); i++)
		{
			Statement s = stmts.get(i);
			s.exec(env);
		}
	}

}
