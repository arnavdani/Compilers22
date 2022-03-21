package scanner;


/**
 * subclass of exception used to throw error for the scanner
 *
 * @author Arnav Dani
 * @version 1-31-22
 */

@SuppressWarnings("serial")
public class ScanErrorException extends Exception
{
    /**
     * default constructor for ScanErrorObjects
     */
    public ScanErrorException()
    {
        super();
    }
    /**
     * Constructor for ScanErrorObjects that includes a reason for the error
     * @param reason the reason that the exception is being thrown
     */
    public ScanErrorException(String reason)
    {
        super(reason);
    }
}