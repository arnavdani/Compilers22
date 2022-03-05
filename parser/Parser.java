package parser;
import scanner.*;


public class Parser 
{
	private Scanner sc;
	String cur;
	
	public Parser(Scanner scanner) throws ScanErrorException
	{
		sc = scanner;
		cur = sc.nextToken();
	}
	
	/** 
	 * 
	 * @param curTok
	 * @throws ScanErrorException 
	 * @throws IllegalArgumentException
	 */
	private void eat(String curTok) throws ScanErrorException
	{
		if (curTok.equals(cur))
		{
			cur = sc.nextToken();
		}
		else
			throw new IllegalArgumentException("Illegal Character: expected " + curTok + 
					" and got " + cur);
	}
	
	/**
	* Your comments documenting this method according to the
	* style guide
	* precondition: current token is an integer
	* postcondition: number token has been eaten
	* @return the value of the parsed integer
	 * @throws ScanErrorException 
	*/
	private int parseNumber() throws ScanErrorException
	{
		
		int num = Integer.parseInt(cur);
		eat(cur);
		return num;
	}
	
	public void parseStatement() throws ScanErrorException
	{
		int num = 0;
		if (cur.equals("WRITELN"))
		{
			eat(cur);
			eat("(");
			num = parseTerm();
			eat(")");
			eat("EOL");
			
		}
		System.out.println(num);
	}
	
	public int parseFactor() throws ScanErrorException
	{
		int num; 
		if (cur.equals("("))
		{
			eat(cur);
			num = parseTerm();
			eat(")");
		}
		
		else if (cur.equals("-"))
		{
			eat(cur);
			
			num =  -parseFactor();
		}
		
		else
			num = parseNumber();
		
		return num;
	}
	
	private int parseTerm() throws ScanErrorException
	{
		int num = parseFactor();
		while (cur.equals("*") || cur.equals("/") )
		{
			if (cur.equals("*"))
			{
				eat("*");
				num = num * parseFactor();
			}
			else if (cur.equals("/"))
			{
				eat("/");
				num = num / parseFactor();
			}
		}
	return num;
	}
	
	

}
