package ast;
import environment.*;
import java.util.*;

import emitter.Emitter;

/**
 * The Program class defines a program object, which encompasses 
 * the entire code for evaluation separately
 * @author Arnav Dani
 * @version 04/24/22
 */
public class Program
{
	private List<String> varNames;
	private List<ProcedureDeclaration> pdecs;
	private Statement s;
	
	/**
	 * Constructor for Program Object
	 * @param names the name of all the declares variables
	 * @param p list of procedure declarations
	 * @param st the final statement being executex
	 */
	public Program(List<String> names, List<ProcedureDeclaration> p, Statement st)
	{
		varNames = names;
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
	
	/**
	 * Emits the full program into the MIPS File
	 * 
	 * First, a newline variable is defined for future use
	 * Then, all the variables declared t the top of the PASCAL file
	 * 	are initialized to 0
	 * 
	 * Finally, after writing the .text and .main sections,
	 * the statement/body of the code is compiled and written to the file
	 * @param filename the output file where the code is saved to
	 */
	public void compile(String filename)
	{
		Emitter e = new Emitter(filename);
		e.emit(".data");
		e.emit("newline: .asciiz \"\\n\" #defining a newline variable");
		
		for (int i = 0; i < varNames.size(); i++)
		{
			String str = varNames.get(i);
			e.emit("var" + str + ": .word " + 0 + " #initializing to 0");
		}
		
		e.emit(".text");
		e.emit(".globl main");
		e.emit("main: #QTSPIM will automatically look for main");
		s.compile(e);
		e.emit("li $v0 10");
		e.emit("syscall #terminate");
	}

}
