package org.allbinary.time;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.thread.ThreadObjectUtil;

public class StupidTimer
{
    private final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
    
    public final void visit(
            Visitor visitorInterface, TimeDelayHelper timeDelayHelper)
    throws Exception
    {
        boolean tookTooLong = false;

        final String WAITING_FOR = "Waiting for: ";
        //LogUtil.put(LogFactory.getInstance(WAITING_FOR).append(visitorInterface, this, CommonStrings.getInstance().VISIT));
        PreLogUtil.put(new StringMaker().append(WAITING_FOR).append(StringUtil.getInstance().toString(visitorInterface)).toString(), this, CommonStrings.getInstance().VISIT);

        int index = 0;
        while (((Boolean) visitorInterface.visit(null)).booleanValue())
        {
            if(index % 10 == 0)
            {
                PreLogUtil.put(new StringMaker().append(WAITING_FOR).append(index).toString(), this, CommonStrings.getInstance().VISIT);
            }
            
            index++;

            synchronized (this)
            {
                this.threadObjectUtil.waitObject(this, 1800);
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
                    new Exception(new StringMaker().append("Took Too Long: ").append(StringUtil.getInstance().toString(visitorInterface)).toString())));
        }
        else
        {
            PreLogUtil.put(timeDelayHelper.toString(), this, CommonStrings.getInstance().VISIT);
        }
    }

    public synchronized void stopWaiting()
    throws Exception
    {
        this.threadObjectUtil.notifyObject(this);
    }
}
