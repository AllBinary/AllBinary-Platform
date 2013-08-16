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
package abcs.logic.java.number;

public class LongUtil
{
    
    private LongUtil()
    {
    }
    
    public static String fillIn(String end)
    {
        StringBuffer frameStringBuffer = new StringBuffer();
        frameStringBuffer.append(end);
        while(frameStringBuffer.length() < LongData.MAX_LONG_LENGTH)
        {
            frameStringBuffer.insert(0, '0');
        }
        
        return frameStringBuffer.toString();
    }
}
