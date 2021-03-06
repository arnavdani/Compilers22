package scanner;
import java.io.IOException;
import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters 
 * @author Arnav Dani
 * @version 2-1-22
 *  
 * The scanner takes in an input stream of characters and
 * classifies them into tokens of digits, letters, and operators
 * 
 * The main method finally outputs those individual tokens that are used later
 * in the compiling process.
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * 
     * @precondition inStream is a real file
     * @postcondition the entire file is parsed through
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
     * 
     * @precondition inString is not null
     * @postcondition inString is fully parsed through
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
     * 
     * @precondition reader file is not null
     * @postcondition currentChar has the current char stored
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
     * 
     * @precondition input string is non-null
     * @postcondition returns whether there is a next character
     * @return true if not at end of file; otherwise, false
     */
    public boolean hasNext()
    {
    	return !eof;
    }
    
    /**
     * Progresses through the characters of the string
     * and calls getNext on all of them to ensure progression
     * 
     * @precondition currentChar is not null
     * @postcondition current = expected is verified or error is thrown
     * @param expected - expected character in the current position
     * @exception ScanErrorException if expected does not match current
     */
    private void eat(char expected) throws ScanErrorException
    {
        if (currentChar == expected)
        	getNextChar();
        else
        {
        	throw new ScanErrorException("Illegal Character: expected " + expected + 
        								" and got " + currentChar);
        }
    }
    
    /**
     * checks if the character is a digit
     * 
     * @precondition s is not null
     * @postcondition s it determined to be a digit or not
     * @param s the character being evaluated
     * @return true if character is between 1-9; otherwise, false
     */
    public static boolean isDigit(char s)
    {
    	return s >= '0' && s <= '9';	
    }
    
    /**
     * checks if the character is a letter uppercase or lowercase
     * 
     * @precondition s is not null
     * @postcondition returns whether character is a letter
     * @param s the character being evaluated
     * @return true if the character is  letter; otherwise, false
     */
    public static boolean isLetter(char s)
    {
    	return (s >= 'a' &&  s <= 'z') || (s >= 'A' && s <= 'Z');
    }
    
    /**
     * checks if the inputted character is a space
     * 
     * @precondition s is not null
     * @postcondition a boolean is returned describing the input char
     * @param s the character being evaluated
     * @return true if character is a space, tab, new line; otherwise, return false
     */
    public static boolean isWhiteSpace(char s)
    {
    	return s == ' ' || s == '\t' || s == '\n' || s == '\r';
    }
    
    /**
     * checks if the inputted character is an operator
     * 
     * @precondition s is not null
     * @postcondition a boolean is returned describing the input char
     * @param s the character being evaluated
     * @return true if it is an operator; otherwise, false
     */
    public static boolean isOperator(char s)
    {
        return s == '=' || s == '+' || s == '-' || s == '*' || s == '/' || s == '%' 
            || s == '(' || s == ')' || s == ':' || s == '<' || s == '>' || s == ',';
    }
    
    /**
     * checks if the inputted operator is an operator
     * that can be a 2 character operator
     * 
     * :=, <=, >=, ==, <>
     * @precondition s is not null
     * @postcondition a boolean is returned describing the input char
     * @param s the character being evaluated
     * @return true if is an operator that can be used consecutively;
     * 			otherwise, false
     */
    public static boolean isDuplOperator(char s)
    {
    	return s == '=' || s == ':' || s == '<' || s == '>';
    }
    
    /**
     * checks if at the end of the file
     * by checking if current char is a period
     * 
     * @precondition s is not null
     * @postcondition a boolean is returned describing whether at end of file
     * @param s the character being evaluated
     * @return true if it is a period; otherwise false
     */
    public static boolean isAtEndofFile(char s)
    {
    	return s == '.';
    }
    
    /**
     * checks if at the end of a statement
     * currently a semicolon signifies the end of a statement
     * 
     * @precondition s is not null
     * @postcondition
     * @param s the character being evaluated
     * @return true if s is a semicolon; otherwise, false
     */
    public static boolean isAtEndOfLine(char s)
    {
    	return s == ';';
    }
    
    /**
     * Scans a number by iterating through all the inputs.
     * Reg ex: (digit)(digit)*
     * 
     * @precondition the file is loaded and is being iterated through
     * @postcondition returns string and parses n characters in the file
     * @exception ScanErrorException if not a digit
     */
    private String scanNumber() throws ScanErrorException
    {
    	//test for first number
    	String num = "";
    	if (isDigit(currentChar) && !isWhiteSpace(currentChar))
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
    	while (isDigit(currentChar) && !isWhiteSpace(currentChar))
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
     * @precondition the file is loaded and being parsed through
     * @postcondition returns identifier and parses through more characters in the file
     * @exception ScanErrorException if not a digit
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
    	while ((isLetter(currentChar) || isDigit(currentChar)) && !isWhiteSpace(currentChar))
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
     * reg ex: (OP)(DuplOp)*
     * 
     * @precondition file is loaded and being parsed through
     * @postcondition returns operator and parses characters through the file
     * @exception ScanErrorException if not an operator
     */
    private String scanOperator() throws ScanErrorException
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
    	
    	while (isDuplOperator(currentChar))
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
     * Scans an in line comment by eating every character for the rest of the line
     * 
     * @precondition token before was "//" to start a comment
     * @postcondition the rest of the line is eaten and reading resumes on the next line
     * @exception ScanErrorException
     */
    private void scanILComments() throws ScanErrorException
    {
    	while ((currentChar != '\n') && hasNext())
    	{
    		eat(currentChar);
    	}
    	eat(currentChar);
    }
    
    /**
     * Parses through the file, determines the type of token, and returns the token
     * Removes in line comments in the code and denotes end of line and end of file
     * 
     * @precondition the file is loaded
     * @postcondition the full file is parsed through and all the tokens are returns
     * @return String of the next token
     */
    public String nextToken() throws ScanErrorException
    {
    	String token = "";
    	if(hasNext())
    	{	
    		while (isWhiteSpace(currentChar))
    		{
    			eat(currentChar);
    		}
    		
    		if(isOperator(currentChar))
    		{
    			//comment support
    			if (currentChar == '/')
    			{
    				if (hasNext())
    					eat(currentChar);
    				if (currentChar == '/')
    				{
    					scanILComments(); //removes comments
    				}
    				else   				
    					return "/"; //division sign	
    			}
    			
    			else if (isOperator(currentChar))
    			{
    				token = scanOperator();
    				return token;
    			}
    		}
    		
    		if (isLetter(currentChar))
    		{
    			token = scanIdentifier();
    			return token;
    		}
    			
    		else if (isDigit(currentChar))
    		{
    			token = scanNumber();
    			return token;
    		}
    		
    		else if (isAtEndofFile(currentChar))
    		{
    			token = "EOF";
    			eof = true;
    		}
    		
    		else if (isAtEndOfLine(currentChar))
    		{
    			token = "EOL";
    			eat(currentChar);	
    		}
    		
    		if (!token.equals("") && token.compareTo("//") != 0)
        	{
        		return token;
        	}
        	else
        	{
        		System.out.println("errchar:" + currentChar);
        		throw new ScanErrorException(currentChar + " not recogized"); 	
        	}
    	}
    	else
    		return "";
			
    }    
}
