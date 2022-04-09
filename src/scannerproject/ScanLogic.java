/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerproject;


import java.util.*;







// Scanning logic class
public class ScanLogic
{
	// Tokens list
	private static ArrayList<Token> TokensList = new ArrayList<Token>();

	// Show tokens method for GUI purpose
	public static ArrayList<Map.Entry<String, String>> ShowTokens()
	{
		ArrayList<Map.Entry<String, String>> L = new ArrayList<Map.Entry<String, String>>();

		for (Token t : TokensList)
		{
			if (!(t instanceof Error))
			{
				L.add(t.ShowToken());
			}
		}
		return L;
	}

	// Show Errors method for GUI purpose
	public static ArrayList<Map.Entry<String, String>> ShowErrors()
	{
		ArrayList<Map.Entry<String, String>> E = new ArrayList<Map.Entry<String, String>>();

		for (Token er : TokensList)
		{
			if (er instanceof Error)
			{
				E.add(er.ShowToken());
			}
		}
		return E;
	}



	// Methods for characters recognition

	//
	//
	//

	// Check for whiteSpace
	private static boolean isSpace(char s)
	{
		return (s == ' ' || s == '\t' || s == '\n' || s == '\r');
	}

	// Check for letter
	private static boolean isLetter(char l)
	{
		return (l >= 'a' && l <= 'z' || l >= 'A' && l <= 'Z');
	}

	// Check for digit
	private static boolean isDigit(char d)
	{
		return (d >= '0' && d <= '9');
	}

	// Check for symbol
	private static boolean isSymbol(char c)
	{

   return (c == '+' || c == '-' || c == '*' || c == '/' || c == '=' || c == '<' || c == '>' || c == '(' || c == ')' || c == '{' || c == '}' || c == ',' || c == ';');
	}

	// Assign symbol lexemes to a corresponding token type
	private static TokenType whichSymbol(String s)
	{
		switch (s)
		{
			case "+":
				return TokenType.PLUS;
			case "-":
				return TokenType.MINUS;
			case "*":
				return TokenType.MULTIPLICATION;
			case "/":
				return TokenType.DIVISION;
			case "=":
				return TokenType.ISEQUAL;
			case ":=":
				return TokenType.ASSIGNMENT_OPERATOR;
			case "<":
				return TokenType.LESS;
			case ">":
				return TokenType.GREATER;
  
                        case "<>": 
                                return TokenType.NOTEQUAL;
                        case "&&": 
                                return TokenType.AND;
                        case "||":
                                return TokenType.OR;
                        case "(":
                                return TokenType.OPEN_PARENTHESES;
                        case ")": 
                                return TokenType.CLOSE_PARENTHESES;
                        case "{": 
                                return TokenType.OPEN_BRACES;
                        case "}":
                                return TokenType.CLOSE_BRACES;
                        case ",": 
                                return TokenType.COMMA;
                        case ";": 
                                return TokenType.SIMICOLOCN;
                        default:
                                return TokenType.NULL;
            }
        }
 
     // Assign identifier lexemes to a corresponding token type
    

	private static TokenType IdAssign(String k)
	{
		switch (k)
		{
			case "main":
				return TokenType.RESWORD_MAIN;
			case "read":
				return TokenType.RESWORD_READ;
			case "write":
				return TokenType.RESWORD_WRITE;
			case "repeat":
				return TokenType.RESWORD_REPEAT;
			case "untill":
				return TokenType.RESWORD_UNTILL;
			case "if":
				return TokenType.RESWORD_IF;
			case "elseif":
				return TokenType.RESWORD_ELSEIF;
			case "else":
				return TokenType.RESWORD_ELSE;

                        case "then": 
                                return TokenType.RESWORD_THEN;
                        case "return": 
                                return TokenType.RESWORD_RETURN;
                        case "endl": 
                                return TokenType.RESWORD_ENDL;
                        case "int": 
                                return TokenType.DATATYPE_INT;
                        case "float": 
                                return TokenType.DATATYPE_FLOAT;
                        case "string": 
                                return TokenType.DATATYPE_STRING;

                       default: 
                                return TokenType.IDENTIFIER; // Return IDENTIFIER if the lexeme doesn't match a reserved word
            }
        }

	private enum States
	{
		START,
		NUM,
		IDINTIFIER,
		STRING,
		COMMENT,
		ASSIGNMENT,
		BOOL,
		SYMBOL,
		DONE,
		ERROR;

		public static final int SIZE = java.lang.Integer.SIZE;

		public int getValue()
		{
			return this.ordinal();
		}

		public static States forValue(int value)
		{
			return values()[value];
		}
	}


	// Since the language isn't that big, The Evaluator for each token is combined with the scanner
	// Scanner method
	public static void scanCode(String c)
	{
		States state = States.START; // Current scanner state initially set to start at the beginning of the each scan
		String lexeme; // Current scanning lexeme
		String errorlex = ""; // For lexical errors

               TokenType errortype = TokenType.NULL; // For lexical errors
               int i = 0; // Source code string index

               TokensList.clear(); // Reset the list of tokens before each scan

    try // For safety
            {
                while (c.length() != 0 && state != States.DONE)
                {
                    lexeme = ""; // Reset the scanning lexeme each iteration
                    switch (state)
                    {
                        case START:
                            {
                                if (isSpace(c.charAt(i)))
                                {
                                    i++;
                                    if (i == c.length()) state = States.DONE;
                                    else state = States.START;
                                }

                                else if (isLetter(c.charAt(i)))
                                {
                                    state = States.IDINTIFIER;
                                }

                                else if (isDigit(c.charAt(i)))
                                {
                                    state = States.NUM;
                                }

                                else if (i + 1 < c.length() && c.charAt(i) == ':' && c.charAt(i+1) == '=')
                                {
                                    state = States.ASSIGNMENT;
                                }

                                else if (i + 1 < c.length() && ((c.charAt(i) == '&' && c.charAt(i+1) == '&') || (c.charAt(i) == '|' && c.charAt(i+1) == '|')))
                                {
                                    state = States.BOOL;
                                }

                                else if (c.charAt(i) == '"')
                                {
                                    state = States.STRING;
                                }

                                else if (i + 1 < c.length() && c.charAt(i) == '/' && c.charAt(i+1) == '*')
                                {
                                    state = States.COMMENT;
                                }

                                else if (isSymbol(c.charAt(i)))
                                {
                                    state = States.SYMBOL;
                                }

                                else // Any un recognized char that is out of language character set
                                {
                                    state = States.ERROR;
                                    errortype = TokenType.UN_RECOGNIZED_CHAR;
                                }
                                break;
                            }
                    case IDINTIFIER:
                            {
                                while (i < c.length() && (isLetter(c.charAt(i)) || isDigit(c.charAt(i))))
                                {
                                    lexeme += c.charAt(i);
                                    i++;
                                }
                                

                                TokensList.add(new NonValueToken(lexeme, IdAssign(lexeme)));

                                if (i == c.length()) state = States.DONE;
                                else state = States.START;
                                break;
                            }

                        case NUM:
                            {
                                while (i < c.length() && isDigit(c.charAt(i)))
                                {
                                    lexeme += c.charAt(i);
                                    i++;
                                }

                                if (i < c.length() && c.charAt(i) == '.') // Floating point
                                {
                                    lexeme += c.charAt(i); // Add the floading point to the lexeme
                                    i++; // First digit after the point
                                    if (i < c.length() && isDigit(c.charAt(i))) // Float number
                                    {
                                        while (i < c.length() && isDigit(c.charAt(i)))
                                        {
                                            lexeme += c.charAt(i);
                                            i++;
                                        }

                                        // Float number
                                        try
                                        {
                                            // Real evaluated numeric value included
                                            TokensList.add(new NumToken(lexeme, TokenType.NUMBER,  Float.parseFloat(lexeme)));

                                            if (i == c.length()) state = States.DONE;
                                            else state = States.START;
                                        }
                                        
                                        catch (ArithmeticException e )
                                        {
                                            errorlex = lexeme;
                                            state = States.ERROR;
                                            errortype = TokenType.TYPE_LIMIT_EXCEEDED;
                                        }
                                    }

                                    else // Floating point error ...caused by the char after the floating point ... Either EOF or not a digit
                                    {
                                        errorlex = lexeme;
                                        state = States.ERROR;
                                        errortype = TokenType.FLOATING_POINT_ERROR;
                                    }
                                }

                                else // No floating point
                                {
                                    // Integer number
                                    try
                                    {
                                        // Real evaluated numeric value included
                                        TokensList.add(new NumToken(lexeme, TokenType.NUMBER,  Integer.parseInt(lexeme)));

                                        if (i == c.length()) state = States.DONE;
                                        else state = States.START;
                                    }
                                    catch (ArithmeticException e2)
                                    {
                                        errorlex = lexeme;
                                        state = States.ERROR;
                                        errortype = TokenType.TYPE_LIMIT_EXCEEDED;
                                    }
                                }

                                break;
                            }

                        case ASSIGNMENT:
                            {
                                lexeme += c.charAt(i);
                                lexeme += c.charAt(i+1);
                                TokensList.add(new NonValueToken(lexeme, whichSymbol(lexeme)));

                                i += 2;

                                if (i == c.length()) state = States.DONE;
                                else state = States.START;
                                break;
                            }

                        case BOOL:
                            {
                                lexeme += c.charAt(i);
                                lexeme += c.charAt(i+1);
                                TokensList.add(new NonValueToken(lexeme, whichSymbol(lexeme)));

                                i += 2;

                                if (i == c.length()) state = States.DONE;
                                else state = States.START;
                                break;
                            }

                        case STRING:
                            {
                                lexeme += c.charAt(i); // Starting double quotes
                                i++; // i stands on the first char of the string
                                while (i < c.length() && c.charAt(i) != '"' && c.charAt(i) != '\n' && c.charAt(i) != '\r')
                                {
                                    lexeme += c.charAt(i);
                                    i++;
                                }
                                if (i < c.length() && c.charAt(i) == '"') // String ended successfully
                                {
                                    lexeme += c.charAt(i); // Ending double quotes

                                    // Including string value after removing the double quotes
                                    TokensList.add(new StringToken(lexeme, TokenType.DATATYPE_STRING, lexeme.substring(1, (lexeme.length() - 1))));

                                    i++;
                                    if (i == c.length()) state = States.DONE;
                                    else state = States.START;
                                }
                                else // No closing double quotes found ... Either the line ended or EOF (RUNAWAY_STRING ERROR)
                                {
                                    errorlex = lexeme;
                                    state = States.ERROR;
                                    errortype = TokenType.RUNAWAY_STRING;
                                }

                                break;
                            }

                        case COMMENT:
                            {
                                errorlex += c.charAt(i);
                                errorlex += c.charAt(i+1);
                                i += 2;
                                while (i + 1 < c.length() && !(c.charAt(i) == '*' &&c.charAt(i+1) == '/'))
                                {
                                    errorlex += c.charAt(i); // In case comment failed 
                                    i++; // Skip te comment text
                                }
                                // Comment ended or reached EOF without ending
                                // So we need to check whick case

                                if (i + 1 == c.length()) // Reached EOF without ending (RUNAWAY_COMMENT ERROR)
                                {
                                    errorlex += c.charAt(i);
                                    i++;
                                    state = States.ERROR;
                                    errortype = TokenType.RUNAWAY_COMMENT;
                                }

                                else if (i == c.length())
                                {
                                    state = States.ERROR;
                                    errortype = TokenType.RUNAWAY_COMMENT;
                                }

                                else // Comment ended successfully with the " */ "
                                {
                                    errorlex = ""; // Clear the error lexeme since noo error occured
                                    i += 2; // Skip the ending " */ "
                                    if (i == c.length()) state = States.DONE;
                                    else state = States.START;
                                }

                                // if comment didn't close untill EOF ... the rest of the text is considered as a comment

                                break;
                            }

                        case SYMBOL:
                            {
                                if (c.charAt(i) == '<') // Check for the (is equal) sign
                                {
                                    if (i + 1 < c.length() && c.charAt(i+1) == '>')
                                    {
                                        lexeme += c.charAt(i);
                                        lexeme += c.charAt(i+1);
                                        i += 2;
                                    }
                                    else
                                    {
                                        lexeme += c.charAt(i);
                                        i++;
                                    }
                                }
                                else
                                {
                                    lexeme += c.charAt(i);
                                    i++;
                                }
                                TokensList.add(new NonValueToken(lexeme, whichSymbol(lexeme)));
                                if (i >= c.length()) state = States.DONE;
                                else state = States.START;
                                break;
                            }

                        case ERROR:
                            {
                                switch (errortype)
                                {
                                    case UN_RECOGNIZED_CHAR:
                                        {
                                            errorlex += c.charAt(i);
                                            TokensList.add(new Error(errorlex, TokenType.UN_RECOGNIZED_CHAR));
                                            i++; // Skip the un recognized char
                                            break;
                                        }

                                    case TYPE_LIMIT_EXCEEDED:
                                        {
                                            TokensList.add(new Error(errorlex, TokenType.TYPE_LIMIT_EXCEEDED));
                                            break;
                                        }

                                    case FLOATING_POINT_ERROR:
                                        {
                                            TokensList.add(new Error(errorlex, TokenType.FLOATING_POINT_ERROR));
                                            break;
                                        }

                                    case RUNAWAY_STRING:
                                        {
                                            TokensList.add(new Error(errorlex, TokenType.RUNAWAY_STRING));
                                            break;
                                        }

                                    case RUNAWAY_COMMENT:
                                        {
                                            TokensList.add(new Error(errorlex, TokenType.RUNAWAY_COMMENT));
                                            break;
                                        }
                                }
                                errorlex = ""; // Reset the error lexeme
                                if (i == c.length()) state = States.DONE;
                                else state = States.START;
                                break;
                            }

                        default:
                            {
                                break;
                            }
                    }
                }
            }
            catch (Exception e3)
            {
                // Just for safety
                // Catch any exception that could cause failure
                // Prevent the exe from crashing
            }
        }
    }

