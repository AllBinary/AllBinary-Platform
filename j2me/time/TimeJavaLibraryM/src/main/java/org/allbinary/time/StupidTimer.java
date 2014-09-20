package org.allbinary.time;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.basic.util.visitor.Visitor;
import org.allbinary.thread.ThreadObjectUtil;

public class StupidTimer
{
    public final void visit(
            Visitor visitorInterface, TimeDelayHelper timeDelayHelper)
    throws Exception
    {
        boolean tookTooLong = false;

        final String WAITING_FOR = "Waiting for: ";
        //LogUtil.put(LogFactory.getInstance(WAITING_FOR + visitorInterface, this, CommonStrings.getInstance().VISIT));
        PreLogUtil.put(WAITING_FOR + visitorInterface, this, CommonStrings.getInstance().VISIT);

        int index = 0;
        while (((Boolean) visitorInterface.visit(null)).booleanValue())
        {
            if(index % 10 == 0)
            {
                PreLogUtil.put(WAITING_FOR + index, this, CommonStrings.getInstance().VISIT);
            }
            
            index++;

            synchronized (this)
            {
                ThreadObjectUtil.getInstance().waitObject(this, 1800);
            }

            if (timeDelayHelper.isTime())
            {
                tookTooLong = true;
                break;
            }
        }

        if (tookTooLong)
        {
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().VISIT, 
                    new Exception("Took Too Long: " + visitorInterface)));
        }
        else
        {
            PreLogUtil.put(timeDelayHelper.toString(), this, CommonStrings.getInstance().VISIT);
        }
    }

    public synchronized void stopWaiting()
    throws Exception
    {
        ThreadObjectUtil.getInstance().notifyObject(this);
    }
}
