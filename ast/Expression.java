package ast;

import environment.Environment;
import emitter.Emitter;
/*
 * Expression class is the abstract descriptor
 * for all logical and arithmetic calculations and operations
 */
public abstract class Expression 
{
	/**
	 * provides a framework for all classes that extend Expression
	 * to calculate/evaluate all its various parts
	 * @param env the environment within which the operations happen
	 * @return integer of the evaluated expression
	 */
	public abstract int eval(Environment env);
	
	/**
	 * Returns error if sublcass does not compile
	 * @param e Emitter that writes to file
	 */
	public void compile(Emitter e)
	{
		throw new RuntimeException("Implement me!!!!!");
	}
	
}
