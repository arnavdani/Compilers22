package scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Tests to ensure the scanner class is functioning
 * 
 * @author Arnav Dani
 * @version 1-31-22
 *
 */
public class ScannerTester
{
	/**
	 * tests the effectiveness of the methods of the scanner class
	 * by loading a text file to test the methods
	 */
	public static void main(String[] args) throws ScanErrorException, IOException
	{
		Scanner sc = new Scanner(new FileInputStream(new File("./src/temptest1.txt")));
		while (sc.hasNext())
		{
			System.out.println(sc.nextToken());
		}
	}
}
