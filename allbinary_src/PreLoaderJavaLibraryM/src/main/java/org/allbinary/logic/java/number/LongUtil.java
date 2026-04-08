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
import org.allbinary.string.CommonPhoneStrings;

public class LongUtil
{
    
    private LongUtil()
    {
    }
    
    public static String fillIn(final String end)
    {
        final StringMaker frameStringBuffer = new StringMaker();
        if(frameStringBuffer.length() < LongData.MAX_LONG_LENGTH) {
            final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
            final int size = LongData.MAX_LONG_LENGTH - frameStringBuffer.length();
            for(int index = 0; index < size; index++) {
                frameStringBuffer.append(commonPhoneStrings.ZERO);
            }
        }
        frameStringBuffer.append(end);
        
        return frameStringBuffer.toString();
    }
}
