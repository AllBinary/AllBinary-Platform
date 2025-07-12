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
package org.allbinary.media.audio;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AllBinaryMediaManagerShutdown
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final AllBinaryMediaManagerShutdown instance =
            new AllBinaryMediaManagerShutdown();

    public static void shutdown(SoundsFactoryInterface soundsFactoryInterface)
        throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(commonStrings.START, instance, "shutdown");
        AllBinaryMediaManager.shutdown(soundsFactoryInterface);
    }
    
}
