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
package org.allbinary.animation;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

/**
 *
 * @author user
 */
public class TimedVectorAnimation extends VectorAnimation
{
   private TimeDelayHelper timeElapsedHelper = new TimeDelayHelper(200);
   
    public TimedVectorAnimation(final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
       super(currentPoints, basicColor, animationBehavior);
    }

    public TimedVectorAnimation(final int[][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
       super(currentPoints, basicColor, animationBehavior);
    }
    
    @Override
    public void nextFrame()
    {
       if(this.timeElapsedHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
       {
           super.nextFrame();
       }
    }    
}
