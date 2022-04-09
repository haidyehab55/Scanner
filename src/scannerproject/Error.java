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

// Scanning errors
public class Error extends Token
{
	public Error(String e, TokenType et)
	{
		lexeme = e; // Error string
		tokentype = et; // Error type
	}
}