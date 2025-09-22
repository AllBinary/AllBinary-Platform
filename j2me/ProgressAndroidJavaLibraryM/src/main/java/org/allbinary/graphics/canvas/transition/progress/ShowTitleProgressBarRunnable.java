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
package org.allbinary.graphics.canvas.transition.progress;

import org.allbinary.animation.transition.TransistionTypes;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;
import android.app.Activity;

public class ShowTitleProgressBarRunnable extends ProgressRunnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public ShowTitleProgressBarRunnable(
            Activity midletActivity, ProgressCanvas progressCanvas)
    {
        super(midletActivity, progressCanvas);
    }
    
    private final int[] FADE_IN_HALF =
    { TransistionTypes.getInstance().FADE_IN_TO_HALF_ALPHA,
            TransistionTypes.getInstance().FADE_IN_FROM_HALF_ALPHA, };
    
    @Override
    public void run()
    {
        try
        {
            this.midletActivity.onShowProgress(false, FADE_IN_HALF);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
        }
    }
}
