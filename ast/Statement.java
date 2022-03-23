package ast;
import environment.Environment;

/**
 * Statement class is the abstract shell/framework for all
 * statements that execute code
 * @author Arnav Dani
 * @version 3/22/22
 */
public abstract class Statement 
{
	/**
	 * Provides a framework for all classes that
	 * extend Statement to execute various parts
	 * @param env the environment in which the statements are executed
	 */
	public abstract void exec(Environment env);

}
