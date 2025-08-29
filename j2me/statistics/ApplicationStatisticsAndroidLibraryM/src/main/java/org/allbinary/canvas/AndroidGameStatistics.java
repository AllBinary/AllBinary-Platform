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
package org.allbinary.canvas;

import android.view.View;
import org.allbinary.android.view.BaseViewWrapper;
import org.allbinary.android.view.ViewWrapper;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class AndroidGameStatistics extends BaseGameStatistics
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private BaseViewWrapper view = BaseViewWrapper.NULL_VIEW_WRAPPER;

    private long totalOnDraws;

    private final String[] STRING_ARRAY = new String[14];
    
    public AndroidGameStatistics()
    {
        final StringUtil stringUtil = StringUtil.getInstance();
        
        for(int index = 0; index < STRING_ARRAY.length; index++)
        {
            this.STRING_ARRAY[index] = stringUtil.EMPTY_STRING; 
        }
    }
    
    public void init(View view)
    {
        super.init();
        this.view = new ViewWrapper(view);
        this.totalOnDraws = 0;
    }

    @Override
    public void process()
    {
        // logUtil.put(commonStrings.START, "Refresh", commonStrings.PROCESS);
        // timeElapsed = currentTime - timeElapsed;
        // if (timeElapsed > 50)
        // {
        view.postInvalidate();
        // }
    }
    
    public void nextOnDraw()
    {
        this.totalOnDraws++;
    }
    
    private final String TOTAL_ONDRAWS = " Total onDraws: ";
    private final String ONDRAWS_RATE = " onDraws(/10) Sec: ";
    
    @Override
    public String[] toStringArray()
    {
        final String[] stringArray = super.toStringArray();
        
        for(int index = 0; index < stringArray.length; index++)
        {
            STRING_ARRAY[index] = stringArray[index];
        }
        
        long totalTime = getTimeDelayHelper().getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 10000);

        STRING_ARRAY[10] = TOTAL_ONDRAWS;
        STRING_ARRAY[11] = Long.toString(this.totalOnDraws);

        STRING_ARRAY[12] = ONDRAWS_RATE;
        STRING_ARRAY[13] = Long.toString(this.totalOnDraws / totalTime);

        return STRING_ARRAY;
    }

    public String toString()
    {    	
        long totalTime = getTimeDelayHelper().getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 1000);

        if (totalTime > 0)
        {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(super.toString(totalTime));
            
            if(this.totalOnDraws > 0)
            {
                stringBuffer.append(TOTAL_ONDRAWS);
                stringBuffer.append(Long.toString(this.totalOnDraws));

                stringBuffer.append(ONDRAWS_RATE);
                final String drawsOverTime = Long.toString(this.totalOnDraws / totalTime);
                stringBuffer.append(drawsOverTime);
            }
            
            return stringBuffer.toString();
        }
        else
        {
            return NOT;
        }
    }    
    
}
