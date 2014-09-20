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
package org.allbinary.system;

import org.allbinary.logic.basic.string.StringUtil;

public class Memory
{
    public static String getInfo()
    {
        /*
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Memory: Free: ");
        stringBuffer.append(Runtime.getRuntime().freeMemory());

        //stringBuffer.append(" Max: ");
        //stringBuffer.append(Runtime.getRuntime().maxMemory());

        stringBuffer.append(" Total: ");
        stringBuffer.append(Runtime.getRuntime().totalMemory());

        return stringBuffer.toString();
         */
        return StringUtil.getInstance().EMPTY_STRING;
    }
}
