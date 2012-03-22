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
package allbinary.animation;

import allbinary.direction.Direction;
import allbinary.graphics.color.BasicColor;
import allbinary.math.Angle;
import allbinary.math.AngleFactory;
import allbinary.math.AngleInfo;
import allbinary.math.FrameUtil;

public class VectorRotationAnimation 
    extends VectorBaseRotationAnimation 
{   
   public VectorRotationAnimation(int currentPoints[][][], BasicColor basicColor)
           throws Exception
   {
       //int angleIncrement
       super(AngleInfo.getInstance(AngleFactory.getInstance().TOTAL_ANGLE/currentPoints.length), currentPoints, basicColor);

      this.angleInfo.adjustAngle(this.getFrame());
      
      //LogUtil.put(LogFactory.getInstance(this.getAngleInfo().toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
   }
   
   public void setFrame(Direction direction)
   {
      Angle angle = directionUtil.getFrameAngle(direction);
      this.adjustFrame(angle);
   }

   public void setFrame(Angle angle)
   {
      this.adjustFrame(angle);
   }
  
   public void setFrame(int index)
   {
      super.setFrame(index);
      this.angleInfo.adjustAngle(this.getFrame());
   }

   private final FrameUtil frameUtil = FrameUtil.getInstance();

   public void adjustFrame(short newAngle)
   {
      this.setFrame(frameUtil.getFrameForAngle(newAngle, this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
   }

   public void adjustFrame(Angle newAngle)
   {
      this.adjustFrame(newAngle.getValue());
   }
}
