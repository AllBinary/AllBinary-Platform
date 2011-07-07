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
package org.allbinary.game.ai.path;

import javax.microedition.lcdui.Canvas;

import allbinary.direction.Direction;
import allbinary.direction.DirectionFactory;
import allbinary.math.AngleIncrementInfo;
import allbinary.math.AngleInfo;

public class BasicDirectionAIHelper
{
   private String name;
   
   private AngleInfo angleInfo;
   
   public BasicDirectionAIHelper(String name, AngleInfo angleInfo)
   {
      this.setName(name);
      this.setAngleInfo(angleInfo);
   }
   
   private int turnAI(int frame)
   {
      int keyDirection = -1;
      
      //Find out it left or right is closest
      //0-360 - 0-360 = -360-360
      int directionAngle = this.getAngleInfo().getAngleIncrementInfo().getFrameAngle(frame);
      int degrees = Math.abs(directionAngle - this.getAngleInfo().getAngle());

      //LogUtil.put(LogFactory.getInstance(this.getName() + " Direction Angle: " + directionAngle + " Angle: " + this.getAngleInfo().getAngle() + " degrees: " + degrees, this, "turnAI"));
      
      if(degrees < 180)
      {
         if(this.getAngleInfo().getAngle() > directionAngle)
         {
            keyDirection = Canvas.LEFT;
         }
         else
            if(this.getAngleInfo().getAngle() < directionAngle)
            {
            keyDirection = Canvas.RIGHT;
            }
      }
      else
      {
         if(this.getAngleInfo().getAngle() > directionAngle)
         {
            keyDirection = Canvas.RIGHT;
         }
         else
            if(this.getAngleInfo().getAngle() < directionAngle)
            {
            keyDirection = Canvas.LEFT;
            }
      }
      return keyDirection;
   }
   
   private int getAIKeyPressed(Integer frame)
   {
      //LogUtil.put(LogFactory.getInstance("this.getAngle(): " + this.getAngleInfo().getAngle(), this, "getAIKeyPressed"));
      //LogUtil.put(LogFactory.getInstance("this.getFrameAngle(frame): " + this.getAngleInfo().getFrameAngle(frame), this, "getAIKeyPressed"));
      
      if(this.getAngleInfo().getAngle() != this.getAngleInfo().getAngleIncrementInfo().getFrameAngle(frame.intValue()))
      {
         return this.turnAI(frame.intValue());
      }
      else
      {
         //LogUtil.put(LogFactory.getInstance("Accelerate", this, "getAIKeyPressed"));
         return Canvas.UP;
      }
   }
   
   public int getAIKeyPressedFromDirection(Direction geographicMapDirectionData)
   {
      int keyDirection = -1;

      //LogUtil.put(LogFactory.getInstance(this.getName() + " geographicMapDirectionData: " + geographicMapDirectionData, this, "getAIKeyPressedFromDirection"));
      
      AngleIncrementInfo angleIncrementInfo = 
         this.getAngleInfo().getAngleIncrementInfo();
      
      DirectionFactory directionFactory = 
          DirectionFactory.getInstance();
      
      if(geographicMapDirectionData == directionFactory.DOWN)
      {
         keyDirection = this.getAIKeyPressed(angleIncrementInfo.DOWN_FRAME);
      }
      else
         if(geographicMapDirectionData == directionFactory.UP)
         {
         keyDirection = this.getAIKeyPressed(angleIncrementInfo.UP_FRAME);
         }
         else
            if(geographicMapDirectionData == directionFactory.LEFT)
            {
         keyDirection = this.getAIKeyPressed(angleIncrementInfo.LEFT_FRAME);
            }
            else
               if(geographicMapDirectionData == directionFactory.RIGHT)
               {
         keyDirection = this.getAIKeyPressed(angleIncrementInfo.RIGHT_FRAME);
               }
      return keyDirection;
   }

   protected AngleInfo getAngleInfo()
   {
      return angleInfo;
   }

   protected void setAngleInfo(AngleInfo angleInfo)
   {
      this.angleInfo = angleInfo;
   }
   
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
   
}
