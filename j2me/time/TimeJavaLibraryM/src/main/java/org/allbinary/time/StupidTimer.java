package org.allbinary.time;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.ThreadObjectUtil;

public class StupidTimer
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
    
    public final void visit(
            Visitor visitorInterface, TimeDelayHelper timeDelayHelper)
    throws Exception
    {
        boolean tookTooLong = false;

        final String WAITING_FOR = "Waiting for: ";
        //logUtil.put(WAITING_FOR).append(visitorInterface, this, commonStrings.VISIT);
        PreLogUtil.put(new StringMaker().append(WAITING_FOR).append(StringUtil.getInstance().toString(visitorInterface)).toString(), this, commonStrings.VISIT);

        int index = 0;
        while (((Boolean) visitorInterface.visit(null)).booleanValue())
        {
            if(index % 10 == 0)
            {
                PreLogUtil.put(new StringMaker().append(WAITING_FOR).append(index).toString(), this, commonStrings.VISIT);
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
            logUtil.put(
                    commonStrings.EXCEPTION, this, commonStrings.VISIT, 
                    new Exception(new StringMaker().append("Took Too Long: ").append(StringUtil.getInstance().toString(visitorInterface)).toString()));
        }
        else
        {
            PreLogUtil.put(timeDelayHelper.toString(), this, commonStrings.VISIT);
        }
    }

    public synchronized void stopWaiting()
    throws Exception
    {
        this.threadObjectUtil.notifyObject(this);
    }
}
