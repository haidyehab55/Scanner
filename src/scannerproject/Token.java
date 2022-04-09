/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerproject;

// Token classes

import java.util.Map;

// Abstract representing token records
public abstract class Token
{
	protected String lexeme; // Actual lexeme string extracted from source code by the "Scanner"
	protected TokenType tokentype = TokenType.values()[0]; // The tokenType to be passed to the "Parser"

	public Map.Entry<String, String> ShowToken()
	{
            
		return new java.util.AbstractMap.SimpleEntry<String,String>(lexeme,tokentype.toString());
         
	}
}