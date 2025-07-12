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
package org.allbinary.logic.java.number;

import org.allbinary.logic.string.StringMaker;

public class LongUtil
{
    
    private LongUtil()
    {
    }
    
    public static String fillIn(String end)
    {
        StringMaker frameStringBuffer = new StringMaker();
        if(frameStringBuffer.length() < LongData.MAX_LONG_LENGTH) {
            final int size = LongData.MAX_LONG_LENGTH - frameStringBuffer.length();
            for(int index = 0; index < size; index++) {
                frameStringBuffer.append('0');
            }
        }
        frameStringBuffer.append(end);
        
        return frameStringBuffer.toString();
    }
}
