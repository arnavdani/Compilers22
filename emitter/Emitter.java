package emitter;
import java.io.*;

public class Emitter
{
	private PrintWriter out;
	private int labelcounter;

	//creates an emitter for writing to a new file with given name
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
	
	public void newline()
	{
		emit("la $a0 newline");
		emit("li $v0 4");
		emit("syscall #print newline");
	}
	
	public void emitPush(String reg)
	{
		emit("subu $sp $sp 4");
		emit("sw " + reg +  " ($sp) #push on stack");
	}
	
	public void emitPop(String reg)
	{
		emit("lw " + reg + " ($sp)");
		emit("addu $sp $sp 4 #pop off stack");
	}
	
	public int nextLabelID()
	{
		labelcounter++;
		return labelcounter;
	}
}