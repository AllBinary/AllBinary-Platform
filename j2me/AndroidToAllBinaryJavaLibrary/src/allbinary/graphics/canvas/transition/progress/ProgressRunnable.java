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

public class ProgressRunnable  implements Runnable
{
    protected final MidletActivity midletActivity;
    protected final ProgressCanvas progressCanvas;
    
    public ProgressRunnable(
            MidletActivity midletActivity, ProgressCanvas progressCanvas)
    {
        this.midletActivity = midletActivity;
        this.progressCanvas = progressCanvas;
    }
    
    public void run()
    {
    }
}
