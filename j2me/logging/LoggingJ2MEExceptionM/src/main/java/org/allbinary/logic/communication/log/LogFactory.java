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
package org.allbinary.logic.communication.log;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;

//ActualPlatform
public class LogFactory
{
    public static final Log LOG = new Log(StringUtil.getInstance().EMPTY_STRING, NullUtil.getInstance().NULL_OBJECT, StringUtil.getInstance().EMPTY_STRING, NullUtil.getInstance().NULL_OBJECT);

    //ActualPlatform
    public static final Log getInstanceD() {
        final StringUtil stringUtil = StringUtil.getInstance();
        final NullUtil nullUtil = NullUtil.getInstance();
        return new Log(stringUtil.EMPTY_STRING, nullUtil.NULL_OBJECT, stringUtil.EMPTY_STRING, nullUtil.NULL_OBJECT);
    }
    
    //ActualPlatform
    public static final Log getInstance(
            String specialMessage,
            Object object,
            String functionName,
            Object exception)
    {
        return new Log(specialMessage, object, functionName, exception);
    }

    //ActualPlatform
    public static final Log getInstanceF(
            String specialMessage,
            Object object,
            String functionName)
    {
        return LOG;
        //return new Log(specialMessage, object, functionName);
    }
}
