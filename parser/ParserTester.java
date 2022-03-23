package parser;

import ast.Statement;
import environment.Environment;

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
		Scanner sc = new Scanner(new FileInputStream(new File("./src/parserTest6.txt")));
		ParserAST p = new ParserAST(sc);
		Statement s = p.parseStatement();
		Environment env = new Environment();
		s.exec(env);
	}

}
