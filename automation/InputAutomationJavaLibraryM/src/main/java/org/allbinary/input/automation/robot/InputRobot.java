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
package org.allbinary.input.automation.robot;

import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.automation.PointHelper;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.help.HelpSet;

public class InputRobot
   implements InputRobotInterface
{
   private Robot robot;
   
   public final static String NAME = "Java Robot";

   public InputRobot(GraphicsDevice graphicsDevice) throws Exception
   {
      this.robot = new Robot(graphicsDevice);
   }
   
   public String getName()
   {
       return InputRobot.NAME;
   }

   public HelpSet getHelpSet()
   {
       return null;
   }
   
   public void mouseMoveToTarget(Rectangle rectangle, Integer x, Integer y) throws Exception
   {
      Point point = PointHelper.getCenterPoint(rectangle);

      this.robot.mouseMove(point.x + x, point.y + y);

      LogUtil.put(new Log(
            "Moved Mouse To: x: " + point.x + " y: " + point.y + " in the middle of: " + rectangle, 
            this, "moveMouseToTarget"));
   }

   public void mouseMove(Point point) 
   {
      this.robot.mouseMove(point.x, point.y);

      LogUtil.put(new Log(
            "Moved Mouse To: x: " + point.x + " y: " + point.y,
            this, "moveMouse"));
   }
   
   public void mouseMoveToTarget(Rectangle rectangle) throws Exception
   {
       this.mouseMoveToTarget(rectangle, 0, 0);
   }
      
   public BufferedImage createScreenCapture(Rectangle screenRect)
   {
      return this.robot.createScreenCapture(screenRect);
   }
   
   public void delay(Integer ms)
   {
      this.robot.delay(ms);
   }
   
   public Integer getAutoDelay()
   {
      return this.robot.getAutoDelay();
   }
   
   public Color getPixelColor(Integer x, Integer y)
   {
      return this.robot.getPixelColor(x,y);
   }
   
   public boolean isAutoWaitForIdle()
   {
      return this.robot.isAutoWaitForIdle();
   }
   
   public void keyPress(Integer keycode)
   {
      this.robot.keyPress(keycode);
   }
   
   public void keyRelease(Integer keycode)
   {
      this.robot.keyRelease(keycode);
   }

   public void mouseMove(Integer x, Integer y)
   {
      this.robot.mouseMove(x,y);
   }
   
   public void mousePress(Integer buttons)
   {
      this.robot.mousePress(buttons);
   }
   
   public void mouseRelease(Integer buttons)
   {
      this.robot.mouseRelease(buttons);
   }
   
   public void mouseWheel(Integer wheelAmt)
   {
      this.robot.mouseWheel(wheelAmt);
   }
   
   public void setAutoDelay(Integer ms)
   {
      this.robot.setAutoDelay(ms);
   }
   
   public void setAutoWaitForIdle(boolean isOn)
   {
      this.robot.setAutoWaitForIdle(isOn);
   }
   
   public String toString()
   {
      return this.robot.toString();
   }
   
   public void waitForIdle()
   {
      this.robot.waitForIdle();
   }
      
   public void on()
   {

   }
   
   public void off()
   {
       
   }

}
