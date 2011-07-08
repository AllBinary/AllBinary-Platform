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
package abcs.logic.java.character;

public class CharArrayFactory
{
    private static final CharArrayFactory instance = new CharArrayFactory();
    
    public static CharArrayFactory getInstance()
    {
        return instance;
    }
    
    private final char[] zeroCharArray = new char[0];

    public char[] getZeroCharArray()
    {
        return zeroCharArray;
    }
}
