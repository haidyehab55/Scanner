/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerproject;

/**
 *
 * @author AnooD
 */
public class NumToken extends Token
{
	private int inumvalue; // Real integer token numeric value evaluated by the "Evaluator"
	private float fnumvalue; // Real float token numeric value evaluated by the "Evaluator"

	public NumToken(String lx, TokenType t, int n) // For integers
	{
		lexeme = lx;
		tokentype = t;
		inumvalue = n;
	}

	public NumToken(String lx, TokenType t, float f) // For floats
	{
		lexeme = lx;
		tokentype = t;
		fnumvalue = f;
	}
}