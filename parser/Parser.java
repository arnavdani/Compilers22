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
	
	
	

}
