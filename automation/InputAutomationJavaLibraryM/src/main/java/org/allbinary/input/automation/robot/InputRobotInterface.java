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

public interface InputRobotInterface
{
   String getName();
   HelpSet getHelpSet();
   
   BufferedImage createScreenCapture(Rectangle screenRect);
   void delay(Integer ms);
   Integer getAutoDelay();
   Color getPixelColor(Integer x, Integer y);
   boolean isAutoWaitForIdle();
   void keyPress(Integer keycode);
   void keyRelease(Integer keycode);

   Point getMousePoint();
   
   void mouseMove(Integer x, Integer y);
   void mousePress(Integer buttons);
   void mouseRelease(Integer buttons);
   void mouseWheel(Integer wheelAmt);
   void setAutoDelay(Integer ms);
   void setAutoWaitForIdle(boolean isOn);
   String toString();
   void waitForIdle();
   
   void mouseMove(Point point);
   //Moves to the middle of the target
   void mouseMoveToTarget(Rectangle rectangle) throws Exception;
   void mouseMoveToTarget(Rectangle rectangle, Integer x, Integer y) throws Exception;
   
   //Toggle the processing on and off
   //public void on();
   //public void off();
}
