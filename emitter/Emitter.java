package emitter;
import java.io.*;

/**
 * Emitter writes the MIPS version of the code to a specified file
 * I used methods to simplify creating a new line, using the stack,
 * and identifing conditionals
 * @author Arnav Dani
 * @version 5/10/22
 *
 */
public class Emitter
{
	private PrintWriter out;
	private int labelcounter;

	/**
	 * Constructor for Emitter to write MIPS code to file
	 * Initializes instance variable labelcounter to seperate conditionals
	 * @param outputFileName the output file where code will be written to
	 */
	public Emitter(String outputFileName)
	{	
		try
		{
			out = new PrintWriter(new FileWriter(outputFileName), true);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
		labelcounter = 0;
	}

	//prints one line of code to file (with non-labels indented)
	public void emit(String code)
	{
		if (!code.endsWith(":"))
			code = "\t" + code;
		out.println(code);
	}

	//closes the file.  should be called after all calls to emit.
	public void close()
	{
		out.close();
	}
	
	/**
	 * writes the code to print a new line in the 
	 * 	output of the mips file
	 */
	public void newline()
	{
		emit("la $a0 newline");
		emit("li $v0 4");
		emit("syscall #print newline");
	}
	
	/**
	 * pushes the value in reg onto the stack
	 * @param reg the register to extract the value from
	 */
	public void emitPush(String reg)
	{
		emit("subu $sp $sp 4");
		emit("sw " + reg +  " ($sp) #push on stack");
	}
	
	/**
	 * Pops the value on the top of the stack into reg
	 * @param reg the register to put the value at the top of the stack
	 */
	public void emitPop(String reg)
	{
		emit("lw " + reg + " ($sp)");
		emit("addu $sp $sp 4 #pop off stack");
	}
	
	/**
	 * Returns a unique label id for each conditional
	 * 	to prevent two conditionals from having the same name
	 * 
	 * This is done by adding 1 to the counter and adding the counter
	 * to the name
	 * 
	 * eg. If1, while2, If3, While4, While5
	 * 
	 * @return a label id to add to the name of each conditional
	 */
	public int nextLabelID()
	{
		labelcounter++;
		return labelcounter;
	}
}