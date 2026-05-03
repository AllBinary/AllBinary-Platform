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

    private final String[] ANDROID_STRING_ARRAY = new String[14];
    
    public AndroidGameStatistics()
    {
        final StringUtil stringUtil = StringUtil.getInstance();
        
        for(int index = 0; index < this.ANDROID_STRING_ARRAY.length; index++)
        {
            this.ANDROID_STRING_ARRAY[index] = stringUtil.EMPTY_STRING; 
        }
    }
    
    public void initView(View view)
    {
        super.init();
        this.view = new ViewWrapper(view);
        this.totalOnDraws = 0;
    }

    @Override
    public void process()
    {
        // this.logUtil.putF(commonStrings.START, "Refresh", commonStrings.PROCESS);
        // timeElapsed = currentTime - timeElapsed;
        // if (timeElapsed > 50)
        // {
        this.view.postInvalidate();
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
            this.ANDROID_STRING_ARRAY[index] = stringArray[index];
        }
        
        long totalTime = this.getTimeDelayHelper().getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 10000);

        this.ANDROID_STRING_ARRAY[10] = this.TOTAL_ONDRAWS;
        this.ANDROID_STRING_ARRAY[11] = Long.toString(this.totalOnDraws);

        this.ANDROID_STRING_ARRAY[12] = this.ONDRAWS_RATE;
        this.ANDROID_STRING_ARRAY[13] = Long.toString(this.totalOnDraws / totalTime);

        return this.ANDROID_STRING_ARRAY;
    }

    public String toString()
    {    	
        long totalTime = this.getTimeDelayHelper().getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 1000);

        if (totalTime > 0)
        {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(super.toStringAt(totalTime));
            
            if(this.totalOnDraws > 0)
            {
                stringBuffer.append(this.TOTAL_ONDRAWS);
                stringBuffer.append(Long.toString(this.totalOnDraws));

                stringBuffer.append(this.ONDRAWS_RATE);
                final String drawsOverTime = Long.toString(this.totalOnDraws / totalTime);
                stringBuffer.append(drawsOverTime);
            }
            
            return stringBuffer.toString();
        }
        else
        {
            return this.NOT;
        }
    }    
    
}
