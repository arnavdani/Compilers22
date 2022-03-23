package ast;

import environment.Environment;
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
	
}
