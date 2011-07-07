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
package allbinary.time;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.logic.basic.util.visitor.Visitor;

public class StupidTimer
{
    private static final StupidTimer instance = new StupidTimer();
	
    public final static void visit(
            Visitor visitorInterface, TimeDelayHelper timeDelayHelper)
    throws Exception
    {
        boolean tookTooLong = false;

        final String WAITING_FOR = "Waiting for: ";
        LogUtil.put(LogFactory.getInstance(WAITING_FOR + visitorInterface, instance, CommonStrings.getInstance().VISIT));

        int index = 0;
        while (((Boolean) visitorInterface.visit(null)).booleanValue())
        {
            if(index % 10 == 0)
            {
                PreLogUtil.put(WAITING_FOR + index, instance, CommonStrings.getInstance().VISIT);
            }
            
            index++;
            
            Thread.sleep(1200);

            if (timeDelayHelper.isTime())
            {
                tookTooLong = true;
                break;
            }
        }

        if (tookTooLong)
        {
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION, instance, CommonStrings.getInstance().VISIT, 
                    new Exception("Took Too Long: " + visitorInterface)));
        }
    }
}
