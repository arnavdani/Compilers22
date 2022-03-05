package parser;
import scanner.*;
import java.util.Map;
import java.util.HashMap;


public class Parser 
{
	private Scanner sc;
	private String cur;
	private Map<String, Integer> vars;
	
	/**
	 * 
	 * @param scanner
	 * @throws ScanErrorException
	 */
	public Parser(Scanner scanner) throws ScanErrorException
	{
		sc = scanner;
		cur = sc.nextToken();
		vars = new HashMap<String, Integer>();
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
	 * 
	 * @return
	 * @throws ScanErrorException
	 */
	private int parseNumber() throws ScanErrorException
	{	
		int num = Integer.parseInt(cur);
		eat(cur);
		return num;
	}
	
	/**
	 * 
	 * @throws ScanErrorException
	 */
	public void parseStatement() throws ScanErrorException
	{
		int num = 0;
		if (cur.equals("WRITELN"))
		{
			eat(cur);
			eat("(");
			num = parseExpression();
			eat(")");
			eat("EOL");
			System.out.println(num);			
		}
		else if (cur.equals("BEGIN"))
		{
			eat("BEGIN");
			while (!cur.equals("END"))
			{
				parseStatement();
			}
			eat("END");
			eat("EOL");
		}
		else
		{
			String id = cur;
			eat(cur);
			eat(":=");
			num = parseExpression();
			eat("EOL");
			vars.put(id, num);	
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws ScanErrorException
	 */
	public int parseFactor() throws ScanErrorException
	{
		int num; 
		if (cur.equals("("))
		{
			eat(cur);
			num = parseExpression();
			eat(")");
		}
		
		else if (cur.equals("-"))
		{
			eat(cur);
			num = -parseFactor();
		}
		
		else if (vars.containsKey(cur))
		{
			num = vars.get(cur);
			eat(cur);
		}
		
		else
			num = parseNumber();
		
		return num;
	}
	
	/**
	 * 
	 * @return
	 * @throws ScanErrorException
	 */
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
	
	/**
	 * 
	 * @return
	 * @throws ScanErrorException
	 */
	private int parseExpression() throws ScanErrorException
	{
		int num = parseTerm();
		while (cur.equals("+")|| cur.equals("-"))
		{
			if (cur.equals("+"))
			{
				eat("+");
				num = num + parseTerm();
			}
			else if (cur.equals("-"))
			{
				eat("-");
				num = num - parseTerm();
			}
		}
		return num;
	}
}
