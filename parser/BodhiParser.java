package parser;
import scanner.*;
import java.util.Map;

import scanner.ScanErrorException;

import java.util.HashMap;

public class BodhiParser
{
   private Scanner sc;
   private String currentToken;
   private Map<String, Integer> variables;
   
   public BodhiParser(Scanner sctakenin) throws ScanErrorException
   {
      sc = sctakenin;
      currentToken = sc.nextToken();
      variables = new HashMap<String, Integer>();
      
   }
   
   public void eat(String expected) throws ScanErrorException
   {
      if(expected.equals(currentToken))
      {
         currentToken = sc.nextToken();
      }
      else
      {
         throw new IllegalArgumentException("Token expected: " + expected + " Token found: " + currentToken);
      }
   }
   
   /**
    * 
    * @return
    */
   private int parseNumber() throws ScanErrorException
   {
      int num = Integer.parseInt(currentToken);
      eat(currentToken);
      return num;
      
   }
   
   public void parseStatement() throws ScanErrorException
   {
      int num = 0;
      while(currentToken.equals("WRITELN"))
      {
         eat(currentToken);
         eat("(");
         num = parseExpression();
         eat(")");
         eat("EOL");
         
      }
      
      if(currentToken.equals("BEGIN"))
      {
         eat(currentToken);
         while(!currentToken.equals("END"))
         {
            parseStatement();
         }
         eat("END");
         eat("EOL");
      }
      
      
      else if (!currentToken.equals("EOF") && !currentToken.equals("EOL") && !currentToken.equals("END"))
      {
         String id = currentToken;
         eat(currentToken);
         eat(":=");
         num = parseExpression();
         eat("EOL");
         variables.put(id, num);
      }
      System.out.println(num);
      
   }
   
   /**
    * 
    * @return
    */
   public int parseFactor() throws ScanErrorException
   {
      int num = 0;
      if(currentToken.equals("("))
      {
         eat(currentToken);
         num = parseExpression();
         eat(")");
         
      }
      else if(currentToken.equals("-"))
      {
         eat("-");
         num = -parseFactor();
      }
      else if (variables.containsKey(currentToken))
      {
         num = variables.get(currentToken);
         eat(currentToken);
      }
      else
      {
         num = parseNumber();
      }
      return num;
   }
   
   private int parseTerm() throws ScanErrorException
   {
      int num = parseFactor();
      while(currentToken.equals("*") || currentToken.equals("/"))
      {
         if(currentToken.equals("*"))
         {
            eat("*");
            num = num * parseFactor();
         } 
         
         if(currentToken.equals("/"))
         {
            eat("/");
            num = num / parseFactor();
         }
      }  
      return num;
   }
   
   private int parseExpression() throws ScanErrorException
   {
       int num = parseTerm();
       
       while(currentToken.equals("+") || currentToken.equals("-"))
       {
          if(currentToken.equals("+"))
          {
             eat("+");
             num = num + parseTerm();
          }
          
          if(currentToken.equals("-"))
          {
             eat("-");
             num = num - parseTerm();
          }
       }
       return num;
   }
}
