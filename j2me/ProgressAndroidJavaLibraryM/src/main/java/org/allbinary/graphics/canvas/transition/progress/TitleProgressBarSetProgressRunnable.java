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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import android.app.Activity;

public class TitleProgressBarSetProgressRunnable extends ProgressRunnable
{
    public TitleProgressBarSetProgressRunnable(
            Activity midletActivity, ProgressCanvas progressCanvas)
    {
        super(midletActivity, progressCanvas);
    }
    
    public void run()
    {
        try
        {
            this.midletActivity.onSetProgress(
                    (int) this.progressCanvas.getValue(), this.progressCanvas.getText());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }
    }
}
