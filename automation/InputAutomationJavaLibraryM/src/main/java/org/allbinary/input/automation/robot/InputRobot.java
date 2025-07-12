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

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.help.HelpSet;

import org.allbinary.input.automation.PointHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class InputRobot
   implements InputRobotInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private Robot robot;
   
   public final static String NAME = "Java Robot";

   public InputRobot(final GraphicsDevice graphicsDevice) throws Exception
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
   
   public Point getMousePoint() {
       final Point point = MouseInfo.getPointerInfo().getLocation();
       return point;
       //return PointFactory.getInstance(point.x, point.y);
   }

   public void mouseMoveToTarget(final Rectangle rectangle, final Integer x, final Integer y) throws Exception
   {
      final Point point = PointHelper.getCenterPoint(rectangle);

      this.robot.mouseMove(point.x + x, point.y + y);

      final String message = new StringMaker().append("Moved Mouse To: x: ").append(point.x).append(" y: ").append(point.y).append(" in the middle of: ").append(StringUtil.getInstance().toString(rectangle)).toString();
      logUtil.put(message, this, "moveMouseToTarget");
   }

   public void mouseMove(Point point) 
   {
       this.mouseMove(point.x, point.y);
   }

   public void mouseMove(final int x, final int y) 
   {
      this.robot.mouseMove(x, y);

      final String message = new StringMaker().append("Moved Mouse To: x: ").append(x).append(" y: ").append(y).toString();
      logUtil.put(message,this, "moveMouse");
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
