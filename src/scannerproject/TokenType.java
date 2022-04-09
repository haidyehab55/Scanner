/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerproject;

import static scannerproject.TokenType.values;

/**
 *
 * @author AnooD
 */
// All language tokens
public enum TokenType
{
	NULL,
	IDENTIFIER,
	NUMBER,

	// Reserved Words
	RESWORD_MAIN,
	RESWORD_READ,
	RESWORD_WRITE,
	RESWORD_REPEAT,
	RESWORD_UNTILL,
	RESWORD_IF,
	RESWORD_ELSEIF,
	RESWORD_ELSE,
	RESWORD_THEN,
	RESWORD_RETURN,
	RESWORD_ENDL,

	// Datatypes
	DATATYPE_INT,
	DATATYPE_FLOAT,
	DATATYPE_STRING,

	// Arith. operators
	PLUS,
	MINUS,
	NEGATIVE,
	MULTIPLICATION,
	DIVISION,

	// Assg opearator
	ASSIGNMENT_OPERATOR,

	// Condition operators
	LESS,
	GREATER,
	ISEQUAL,
	NOTEQUAL,

	// Separators
	OPEN_PARENTHESES,
	CLOSE_PARENTHESES,
	OPEN_BRACES,
	CLOSE_BRACES,
	COMMA,
	SIMICOLOCN,

	// Boolean operators
	AND,
	OR,

	// Errors
	UN_RECOGNIZED_CHAR,
	TYPE_LIMIT_EXCEEDED,
	FLOATING_POINT_ERROR,
	RUNAWAY_STRING,
	RUNAWAY_COMMENT,

	// End of file
	END_OF_FILE;

	public static final int SIZE = java.lang.Integer.SIZE;

	public int getValue()
	{
		return this.ordinal();
	}

	public static TokenType forValue(int value)
	{
		return values()[value];
	}
}
