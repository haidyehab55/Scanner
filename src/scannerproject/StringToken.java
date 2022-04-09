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
public class StringToken extends Token
{
	private String stringval; // Real string token value after removing the double quotes

	public StringToken(String lx, TokenType t, String s)
	{
		lexeme = lx;
		tokentype = t;
		stringval = s;
	}
}