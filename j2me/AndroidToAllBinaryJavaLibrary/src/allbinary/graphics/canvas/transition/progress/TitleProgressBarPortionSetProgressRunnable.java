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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class TitleProgressBarPortionSetProgressRunnable extends
        ProgressRunnable
{
    public TitleProgressBarPortionSetProgressRunnable(
            MidletActivity midletActivity, ProgressCanvas progressCanvas)
    {
        super(midletActivity, progressCanvas);
    }

    public void run()
    {
        try
        {
            AndroidBasicTitleProgressBar progressCanvas = (AndroidBasicTitleProgressBar) this.progressCanvas;
            
            this.midletActivity.onSetProgress(
                    (int) (this.progressCanvas.getValue() + 
                    this.progressCanvas.getMaxValue() / progressCanvas.getPortion()),
                    this.progressCanvas.getText());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
        }
    }

}
