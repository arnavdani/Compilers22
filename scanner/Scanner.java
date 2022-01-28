package scanner;
import java.io.IOException;
import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Arnav Dani
 *  
 * A basic simplified scanner that can read letters, numbers, and elemtary operators
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    
    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
	        in = new BufferedReader(new StringReader(inString));
	        eof = false;
	        getNextChar();
    }
    
    /**
     * gets the next character from the reader
     * @throws exception if character can not be found
     */
    private void getNextChar()
    {
    	try
    	{
    		int i = in.read();
    		if (i == -1)
    			eof = true;
    		
    		currentChar = (char) i;
    		
    	}
    	catch (IOException e)
    	{
                System.out.println("IOException Error");
                e.printStackTrace();            
    	}
    }
    
    /**
     * Checks if the input string has a next character
     * @return true if not at end of file; otherwise, false
     */
    public boolean hasNext()
    {
    	return !eof;
    }
    
    /**
     * Progresses through the characters of the string
     * and calls getNext on all of them to ensure progression
     * @param expected - expected character in the curret position
     */
    private void eat(char expected) throws ScanErrorException
    {
        if (currentChar == expected)
        	getNextChar();
        else
        {
        	throw new ScanErrorException("Illegal Character: expected " + expected + " and got " + currentChar);
        }
    }
    
    /**
     * checks if the character is a digit
     * @param s the character being evaluated
     * @return true if character is between 1-9; otherwise, false
     */
    public static boolean isDigit(char s)
    {
    	return s >= '0' && s <= '9';	
    }
    
    /**
     * checks if the character is a letter uppercase or lowercase
     * @param s the character being evaluated
     * @return true if the character is  letter; otherwise, false
     */
    public static boolean isLetter(char s)
    {
    	return (s >= 'a' &&  s <= 'z') || (s >= 'A' || s <= 'Z');
    }
    
    /**
     * checks if the inputted character is a space
     * @param s the character being evaluated
     * @return true if character is a space, tab, new line; otherwise, return false
     */
    public static boolean isWhiteSpace(char s)
    {
    	return s == ' ' || s == '\t' || s == '\n' || s == '\r';
    }
    
    /**
     * checks if the inputted character is an operator
     * @param s the character being evaluated
     * @return true if it is an operator; otherwise, false
     */
    public static boolean isOperator(char s)
    {
        return s == '=' || s == '+' || s == '-' || s == '*' || s == '/' || s == '%' 
            || s == '(' || s == ')';
    }
    
    /**
     * Scans a number by iterating through all the inputs.
     * Reg ex: (digit)(digit)*
     * 
     * @throws ScanErrorException if not a digit
     */
    private String scanNumber() throws ScanErrorException
    {
    	//test for first number
    	String num = "";
    	if (isDigit(currentChar))
    	{
    		num += currentChar;   	
    		if (hasNext())
    			eat(currentChar);
    		else
    			return num;
    	}
    	else
    		throw new ScanErrorException("Can't find a number");
    	
    	//read the rest of the digits
    	while (isDigit(currentChar))
    	{
    		num += currentChar;
    		if (hasNext())
    			eat(currentChar);
    		else
    			return num;
    	}
    	return num;
    }
    
    /**
     * Scans identifier by iterating through inputs
     * reg ex: (letter)(letter | digit)*
     * 
     * @throws ScanErrorException if not a digit
     */
    private String scanIdentifier() throws ScanErrorException
    {
    	//first letter
    	String id = "";
    	if (isLetter(currentChar))
    	{
    		id += currentChar;
    		if (hasNext())
    			eat(currentChar);
    		else
    			return id;
    	}
    	else
    		throw new ScanErrorException("Not valid identifier - must start with letter");
    	
    	//rest of the identifier
    	while (isLetter(currentChar))
    	{
    		id += currentChar;
    		if (hasNext())
    			eat(currentChar);
    		else
    			return id;
    	}
    	return id;
    }
    
    /**
     * Scans operators by iterating through inputs
     * reg ex: (OP)*
     * 
     * @throws ScanErrorException if not an operator
     */
    public String scanOperator() throws ScanErrorException
    {
    	//first op
    	String op = "";
    	if (isOperator(currentChar))
    	{
    		op += currentChar;
    		if (hasNext())
    			eat(currentChar);
    		else
    			return op;
    	}
    	else
    		throw new ScanErrorException("Not valid operator");
    	
    	//rest of the operator
    	while (isOperator(currentChar))
    	{
    		op += currentChar;
    		if (hasNext())							
    			eat(currentChar);
    		else
    			return op;
    	}
    	return op;
    }
    
    /**
     * Method: nextToken
     * @return
     */
    public String nextToken() throws ScanErrorException
    {
    	String token = "";
    	if(hasNext())
    	{
    		if (isLetter(currentChar))
    			token = scanIdentifier();
    		else if(isDigit(currentChar))
    			token = scanNumber();
    		else if(isOperator(currentChar))
    			token = scanOperator();
    		else if(isWhiteSpace(currentChar))
    		{
    			return token;
    		}
    		else
    			throw new ScanErrorException("No characters recogized");
    	}
    	
    	
    	return token;
    }    
}
