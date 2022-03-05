package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import scanner.ScanErrorException;
import scanner.Scanner;

public class ParserTester 
{
	
	public static void main(String [] args) throws FileNotFoundException, ScanErrorException
	{
		Scanner sc = new Scanner(new FileInputStream(new File("./src/ParserTest7.txt")));
		Parser p = new Parser(sc);
		p.parseStatement();
	}

}
