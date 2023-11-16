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
package org.allbinary.util;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

public class ArrayUtil
{
    private static final ArrayUtil instance = new ArrayUtil();

    public static ArrayUtil getInstance()
    {
        return instance;
    }

    public final Object[] ZERO_OBJECT_ARRAY = new Object[0];
    
    public Object[] copyOf(Object[] original, int newLength)
    {
        return copyOf(original, newLength, original.getClass());
    }

    public Object[] copyOf(Object[] original, int newLength, Class newType)
    {
        Object[] copy = new Object[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    public String toString(int[][] twoDimensionalIntArray)
    {
        StringMaker stringBuffer = new StringMaker();

        CommonSeps commonSeps = CommonSeps.getInstance();
                
        int endIndex = twoDimensionalIntArray.length;
        int endIndex2 = twoDimensionalIntArray[0].length;
        for (int index = 0; index < endIndex; index++)
        {
            stringBuffer.append(commonSeps.BRACE_OPEN);
            for (int index2 = 0; index2 < endIndex2; index2++)
            {
                stringBuffer.append(twoDimensionalIntArray[index][index2]);
                stringBuffer.append(commonSeps.COMMA_SEP);
            }
            stringBuffer.append(commonSeps.BRACE_CLOSE);
            stringBuffer.append(commonSeps.NEW_LINE);
        }

        return stringBuffer.toString();
    }
    
    public String toString(Object[] objectArray)
    {
        StringMaker stringBuffer = new StringMaker();

        CommonSeps commonSeps = CommonSeps.getInstance();

        for (int index = 0; index < objectArray.length; index++)
        {
                stringBuffer.append(objectArray[index]);
                stringBuffer.append(commonSeps.COMMA_SEP);
        }

        return stringBuffer.toString();
    }
}
