package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import scanner.*;

/**
 * Tests the Parser class against a passed in
 * text file to ensure functionality
 * @author Arnav Dani
 * @version 3/6/22
 *
 */
public class ParserTester
{
	/**
	 * Tests the parser by making it parse a file
	 * @param args not used
	 * @throws FileNotFoundException
	 * @throws ScanErrorException
	 */
	public static void main(String [] args) throws FileNotFoundException, ScanErrorException
	{
		Scanner sc = new Scanner(new FileInputStream(new File("./src/parserTest3.txt")));
		Parser p = new Parser(sc);
		p.parseStatement();
	}

}
