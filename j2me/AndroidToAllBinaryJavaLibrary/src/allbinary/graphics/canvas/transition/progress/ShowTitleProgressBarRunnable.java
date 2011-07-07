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
package allbinary.graphics.canvas.transition.progress;

import org.allbinary.android.activity.MidletActivity;
import org.allbinary.animation.transition.TransistionTypes;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class ShowTitleProgressBarRunnable extends ProgressRunnable
{
    public ShowTitleProgressBarRunnable(
            MidletActivity midletActivity, ProgressCanvas progressCanvas)
    {
        super(midletActivity, progressCanvas);
    }
    
    private final int[] FADE_IN_HALF =
    { TransistionTypes.getInstance().FADE_IN_TO_HALF_ALPHA,
            TransistionTypes.getInstance().FADE_IN_FROM_HALF_ALPHA, };
    
    public void run()
    {
        try
        {
            this.midletActivity.onShowProgress(false, FADE_IN_HALF);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
        }
    }
}
