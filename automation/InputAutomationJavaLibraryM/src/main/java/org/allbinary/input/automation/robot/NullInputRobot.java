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

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.AbstractInputRobot;
import javax.help.HelpSet;

public class NullInputRobot extends AbstractInputRobot
   implements InputRobotInterface
{
   public final static String LIBRARY_NAME = "null";
   public final static String NAME = "NULL";

   public NullInputRobot() throws Exception
   {
       super(null);
   }
   
   public String getName()
   {
       return NullInputRobot.NAME;
   }
   
   public HelpSet getHelpSet()
   {
       return null;
   }
   
   public void keyPress(Integer keycode)
   {
       LogUtil.put(new Log("KeyCode: " + keycode, this, "keyPress"));
   }
   
   public void keyRelease(Integer keycode)
   {
      LogUtil.put(new Log("KeyCode: " + keycode, this, "keyRelease"));
   }
   
   public void mouseMove(Integer x, Integer y)
   {
      LogUtil.put(new Log("X: " + x + " Y: " + y, this, "mouseMove"));
   }

   public void mousePress(Integer buttons)
   {
       LogUtil.put(new Log("Buttons: " + buttons, this, "mousePress"));
   }
   
   public void mouseRelease(Integer buttons)
   {
      LogUtil.put(new Log("Buttons: " + buttons, this, "mouseRelease"));
   }

   public BufferedImage createScreenCapture(Rectangle screenRect)
   {
       return null;
   }

   public void delay(Integer ms)
   {
       
   }
   
   public Integer getAutoDelay()
   {
       return -1;
   }
   
   public Color getPixelColor(Integer x, Integer y)
   {
       return null;
   }
   
   public boolean isAutoWaitForIdle()
   {
       return false;
   }

   public void mouseWheel(Integer wheelAmt)
   {
       
   }
   
   public void setAutoDelay(Integer ms)
   {
       
   }
   
   public void setAutoWaitForIdle(boolean isOn)
   {
       
   }
   
   public String toString()
   {
       return "DirectXWindowsInputRobot";
   }
   
   public void waitForIdle()
   {
       
   }
}
    