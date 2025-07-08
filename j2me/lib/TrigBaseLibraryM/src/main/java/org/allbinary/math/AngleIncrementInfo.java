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
package org.allbinary.math;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class AngleIncrementInfo
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private final short angleIncrement;
   
   public final Integer DOWN_FRAME;
   public final Integer UP_FRAME;
   public final Integer LEFT_FRAME;
   public final Integer RIGHT_FRAME;
      
   protected AngleIncrementInfo(short angleIncrement)
   {
      this.angleIncrement = angleIncrement;

      final AngleFactory angleFactory = AngleFactory.getInstance();
      
      final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
      
      this.DOWN_FRAME = smallIntegerSingletonFactory.getInstance(angleFactory.DOWN.getValue() / this.angleIncrement);
      this.UP_FRAME = smallIntegerSingletonFactory.getInstance(angleFactory.UP.getValue());
      this.LEFT_FRAME = smallIntegerSingletonFactory.getInstance(angleFactory.LEFT.getValue() / this.angleIncrement);
      this.RIGHT_FRAME = smallIntegerSingletonFactory.getInstance(angleFactory.RIGHT.getValue() / this.angleIncrement);

      /*
      String message = "DOWN_FRAME = " + this.DOWN_FRAME + " Angle: " + this.getFrameAngle(this.DOWN_FRAME);
      logUtil.put(message, this, commonStrings.CONSTRUCTOR);
      message = "UP_FRAME = " + this.UP_FRAME + " Angle: " + this.getFrameAngle(this.UP_FRAME);
      logUtil.put(message, this, commonStrings.CONSTRUCTOR);
      message = "LEFT_FRAME = " + this.LEFT_FRAME + " Angle: " + this.getFrameAngle(this.LEFT_FRAME);
      logUtil.put(message, this, commonStrings.CONSTRUCTOR);
      message = "RIGHT_FRAME = " + this.RIGHT_FRAME + " Angle: " + this.getFrameAngle(this.RIGHT_FRAME);
      logUtil.put(message, this, commonStrings.CONSTRUCTOR);
       */
   }
   
   private final FrameUtil frameUtil = FrameUtil.getInstance();
   
   public int getFrameAngle(int frame)
   {
      return frameUtil.getFrameAngle(frame, this.angleIncrement);
   }
      
   //public Integer getAngleIncrement()
   public short getAngleIncrement()
   {
      return angleIncrement;
   }

   private static final String INCREMENT = "Inc: ";
   
   public String toString()
   {
      return new StringMaker().append(INCREMENT).append(this.angleIncrement).toString();
   }
   
   public int getClosestGeneralDirection(short angle)
   {
        Integer closestDirection = SmallIntegerSingletonFactory.getInstance().getInstance(360);

        if (UP_FRAME.intValue() - angle < closestDirection.intValue() - angle)
        {
            closestDirection = UP_FRAME;
        }

        if (DOWN_FRAME.intValue() - angle < closestDirection.intValue() - angle)
        {
            closestDirection = DOWN_FRAME;
        }

        if (LEFT_FRAME.intValue() - angle < closestDirection.intValue() - angle)
        {
            closestDirection = LEFT_FRAME;
        }

        if (RIGHT_FRAME.intValue() - angle < closestDirection.intValue() - angle)
        {
            closestDirection = RIGHT_FRAME;
        }

        return closestDirection.intValue();
   }
   
}
