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
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;


public class AngleInfo
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private final AngleIncrementInfo angleIncrementInfo;

   private short angle = 0;
      
   public static AngleInfo getInstance(final short angleIncrement)
   {
      return new AngleInfo(AngleIncrementInfoFactory.getInstance().getAt(angleIncrement));
   }
   
   private AngleInfo(final AngleIncrementInfo angleIncrementInfo)
   {
      this.angleIncrementInfo = angleIncrementInfo;
      //this.logUtil.putF(this.angleIncrementInfo.toString(), this, "getAngleIncrementInfo()");
   }
   
   private final FrameUtil frameUtil = FrameUtil.getInstance();
   
   public void adjustAngle(final int frame)
   {
      final int newAngle = this.angleIncrementInfo.getAngleIncrement() * frame - 90;
      this.setAngle((short) this.frameUtil.adjustAngleToFrameAngle(newAngle));
   }
   
   public short getAngle()
   {
      return this.angle;
   }
   
   public void setAngle(final short angle)
   {
      //this.logUtil.putF("Set Angle: " + angle, this, "setAngle");
      this.angle = angle;
   }

   public AngleIncrementInfo getAngleIncrementInfo()
   {
	  //this.logUtil.putF(angleIncrementInfo.toString(), this, "getAngleIncrementInfo()");
      return this.angleIncrementInfo;
   }
   
   private static final String ANGLE = "Angle: ";
   
   public String toString()
   {
      final StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append(AngleInfo.ANGLE);
      stringBuffer.appendshort(this.angle);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(StringUtil.getInstance().toString(this.getAngleIncrementInfo()));
      
      return stringBuffer.toString();
   }
}
