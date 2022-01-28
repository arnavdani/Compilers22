package scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Tests to ensure the scanner class is functioning
 * 
 * @author arnav
 * @version 1-26-22
 *
 */
public class ScannerTester 
{
	/**
	 * tests the effectiveness of the methods of the scanner class
	 */
	public static void main(String[] args) throws ScanErrorException, IOException
	{
		Scanner sc = new Scanner(new FileInputStream(new File("C:" + 
								"Users/arnav/eclipse-workspace/ATCS CnI"
								+ "/src/scanner/ScannerTest.txt")));
		while (sc.hasNext())
		{
			System.out.println(sc.nextToken());
		}
	}
}
