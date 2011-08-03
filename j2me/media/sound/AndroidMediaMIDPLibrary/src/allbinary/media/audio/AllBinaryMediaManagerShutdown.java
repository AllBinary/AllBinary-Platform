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
package allbinary.media.audio;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class AllBinaryMediaManagerShutdown
{
    private static final AllBinaryMediaManagerShutdown instance =
            new AllBinaryMediaManagerShutdown();

    public static void shutdown(SoundsFactoryInterface soundsFactoryInterface)
        throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
                "No Media Shutdown for Android Process", instance, "shutdown"));
        //AllBinaryMediaManager.shutdown(soundsFactoryInterface);
    }
    
}
