/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package abcs.logic.basic.string;

public class CommonSeps
{
    private static final CommonSeps SINGLETON = new CommonSeps();
    
    public static CommonSeps getInstance()
    {
        return SINGLETON;
    }
    
    public final String NEW_LINE = "\n";

    public final String SEMICOLON = ";";
    public final String EQUALS = "=";
    public final String SPACE = " ";
    public final String COMMA = ",";
    public final String COLON = ":";
    public final String PERIOD = ".";
    public final String UNDERSCORE = "_";
    public final String COLON_SEP = COLON + SPACE;
    public final String COMMA_SEP = COMMA + SPACE;
    public final String PARENTHESIS_OPEN = "(";
    public final String PARENTHESIS_CLOSE = ")";
    public final String BRACE_OPEN = "{";
    public final String BRACE_CLOSE = "}";
    public final String BRACKET_OPEN = "[";
    public final String BRACKET_CLOSE = "]";
}
