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
// Non evaluated value tokens with no real evaluated value like identifiers, reserved words & symbols
public class NonValueToken extends Token
{
	public NonValueToken(String lx, TokenType t)
	{
		lexeme = lx;
		tokentype = t;
	}
}