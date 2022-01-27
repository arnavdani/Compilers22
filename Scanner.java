
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
    	catch
    	{
    		if (e instanceof IOException) {
                System.out.println("IOException Error");
                e.printStackTrace();
    	}
    	finally
    	{
    		System.out.println("abort program");
    		eof = true;
    	}
    }
    
    /**
     * Checks if the input string has a next character
     * 
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
        if (currentChar = expected)
        	getNextChar();
        else
        {
        	Throw new ScanErrorException("Illegal Character: expected " + expected + " and got " + currentChar);
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
     * 
     */
    public static boolean isOperator(char s)
    {
        return s == '=' || s == '+' || s == '-' || s == '*' || s == '/' || s == '%' 
            || s == '(' || s == ')';
    }
    
    /**
     * Method: nextToken
     * @return
     */
    public String nextToken() throws ScanErrorException
    {

        
    }    
}
