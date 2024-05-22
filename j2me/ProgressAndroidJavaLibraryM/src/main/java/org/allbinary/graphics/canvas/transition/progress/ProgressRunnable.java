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

import android.app.Activity;
import org.allbinary.android.activity.SimpleProgressActivityInterface;

public class ProgressRunnable  implements Runnable
{
    protected final SimpleProgressActivityInterface midletActivity;
    protected final ProgressCanvas progressCanvas;
    
    public ProgressRunnable(
            Activity midletActivity, ProgressCanvas progressCanvas)
    {
        this.midletActivity = (SimpleProgressActivityInterface) midletActivity;
        this.progressCanvas = progressCanvas;
    }
    
    public void run()
    {
    }
}
