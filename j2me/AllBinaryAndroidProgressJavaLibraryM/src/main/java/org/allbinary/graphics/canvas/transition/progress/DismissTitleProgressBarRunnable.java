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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import android.app.Activity;

public class DismissTitleProgressBarRunnable extends ProgressRunnable
{
    public DismissTitleProgressBarRunnable(Activity midletActivity,
            ProgressCanvas progressCanvas)
    {
        super(midletActivity, progressCanvas);
    }

    private final int[] ZOOM_OUT_AND_IN =
    { TransistionTypes.getInstance().ZOOM_OUT,
            TransistionTypes.getInstance().ZOOM_IN };

    public void run()
    {
        try
        {
            this.midletActivity.onDismissProgress(ZOOM_OUT_AND_IN);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION, this,
                    CommonStrings.getInstance().RUN, e));
        }
    }
}
