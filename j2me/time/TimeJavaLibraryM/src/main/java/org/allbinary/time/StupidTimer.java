package org.allbinary.time;

import org.allbinary.logic.NullUtil;
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
    protected final NullUtil nullUtil = NullUtil.getInstance();
    
    private final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
    
    private boolean visitBool(final Visitor visitorInterface) {
        final Boolean result = (Boolean) visitorInterface.visit(nullUtil.NULL_OBJECT);
        final boolean result2 = result.booleanValue();
        return result2;
    }
    
    public final void visit(final Visitor visitorInterface, final TimeDelayHelper timeDelayHelper)
    throws Exception
    {
        boolean tookTooLong = false;

        final String WAITING_FOR = "Waiting for: ";
        //logUtil.put(WAITING_FOR).append(visitorInterface, this, commonStrings.VISIT);
        PreLogUtil.put(new StringMaker().append(WAITING_FOR).append(StringUtil.getInstance().toString(visitorInterface)).toString(), this, commonStrings.VISIT);

        int index = 0;
        while (this.visitBool(visitorInterface))
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
