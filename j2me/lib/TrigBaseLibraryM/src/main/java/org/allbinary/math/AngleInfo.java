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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;


public class AngleInfo
{
   private final AngleIncrementInfo angleIncrementInfo;

   private short angle;
      
   public static AngleInfo getInstance(short angleIncrement)
   {
      return new AngleInfo(AngleIncrementInfoFactory.getInstance().getInstance(angleIncrement));
   }
   
   private AngleInfo(AngleIncrementInfo angleIncrementInfo)
   {
      this.angleIncrementInfo = angleIncrementInfo;
      //LogUtil.put(LogFactory.getInstance(this.angleIncrementInfo.toString(), this, "getAngleIncrementInfo()"));
   }
   
   private final FrameUtil frameUtil = FrameUtil.getInstance();
   
   public void adjustAngle(int frame)
   {
      int newAngle = this.angleIncrementInfo.getAngleIncrement() * frame - 90;
      this.setAngle(frameUtil.adjustAngleToFrameAngle(newAngle));
   }
   
   public short getAngle()
   {
      return angle;
   }
   
   public void setAngle(short angle)
   {
      //LogUtil.put(LogFactory.getInstance("Set Angle: " + angle, this, "setAngle"));
      this.angle = angle;
   }

   public AngleIncrementInfo getAngleIncrementInfo()
   {
	  //LogUtil.put(LogFactory.getInstance(angleIncrementInfo.toString(), this, "getAngleIncrementInfo()"));
      return this.angleIncrementInfo;
   }
   
   private static final String ANGLE = "Angle: ";
   
   public String toString()
   {
      StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append(ANGLE);
      stringBuffer.append(this.angle);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(StringUtil.getInstance().toString(this.getAngleIncrementInfo()));
      
      return stringBuffer.toString();
   }
}
