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

import org.allbinary.direction.Direction;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;

public class VectorRotationAnimation 
    extends VectorBaseRotationAnimation 
{   
   public VectorRotationAnimation(final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior)
           throws Exception
   {
       //int angleIncrement
       super(AngleInfo.getInstance((short) (AngleFactory.getInstance().TOTAL_ANGLE / currentPoints.length)), currentPoints, basicColor, animationBehavior);

      this.angleInfo.adjustAngle(this.getFrame());
      
      //LogUtil.put(LogFactory.getInstance(this.getAngleInfo().toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
   }
   
   public void setFrame(final Direction direction)
   {
      Angle angle = directionUtil.getFrameAngle(direction);
      this.adjustFrame(angle);
   }

   public void setFrame(final Angle angle)
   {
      this.adjustFrame(angle);
   }
  
   public void setFrame(final int index)
   {
      super.setFrame(index);
      this.angleInfo.adjustAngle(this.getFrame());
   }

   private final FrameUtil frameUtil = FrameUtil.getInstance();

   public void adjustFrame(final short newAngle)
   {
      this.setFrame(frameUtil.getFrameForAngle(newAngle, this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
   }

   public void adjustFrame(final Angle newAngle)
   {
      this.adjustFrame(newAngle.getValue());
   }
}
