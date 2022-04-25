package parser;
import environment.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import scanner.*;

/**
 * Tests the Parser class against a passed in
 * text file to ensure functionality
 * @author Arnav Dani
 * @version 4/24/22
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
		Scanner sc = new Scanner(new FileInputStream(new File("./src/test8.txt")));
		ParserAST p = new ParserAST(sc);
		Environment env = new Environment(null);
		p.parseProgram().exec(env);
	}

}
