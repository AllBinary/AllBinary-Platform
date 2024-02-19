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
package org.allbinary.media.image.comparison.motion;

import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.TempInputRobotNames;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class TestInputForMotionRectanglesResultsWorker
   implements MotionRectanglesResultsListener
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private long index;
   
   private boolean running;
   
   private Vector motionRectanglesVector;

   public TestInputForMotionRectanglesResultsWorker() throws Exception
   {
      this.motionRectanglesVector = new Vector();
   }
   
   public Vector getMotionRectanglesVector()
   {
      return this.motionRectanglesVector;
   }
   
   public void onMotionRectanglesImageComparisonResultsEvent(
      MotionRectanglesResultsEvent
      motionRectanglesImageComparisonResultsEvent)
   {
      this.getMotionRectanglesVector().add(
         motionRectanglesImageComparisonResultsEvent.getMotionRectangles());
      
      this.run();
   }
   
   public void onEvent(AllBinaryEventObject allBinaryEventObject)
   {
      this.onMotionRectanglesImageComparisonResultsEvent(
         (MotionRectanglesResultsEvent)
         allBinaryEventObject);
   }
   
   public synchronized boolean isRunning()
   {
      return running;
   }
   
   public synchronized void setRunning(boolean running)
   {
      this.running = running;
   }
   
   public void run()
   {
      try
      {
         LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.RUN));
         
         this.setRunning(true);
         
         TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
         
         timeHelper.setStartTime();

         MotionRectangles motionRectangles = (MotionRectangles)
            this.getMotionRectanglesVector().get(0);

         Vector motionRectangleVector = motionRectangles.getVector();

         if(motionRectangleVector.size() > 0)
         {

         Rectangle rectangle = (Rectangle) motionRectangleVector.get(0);
         
         Hashtable robotHashtable = InputRobotFactory.getInstance().get();
         Iterator iterator = robotHashtable.keySet().iterator();
         while(iterator.hasNext())
         {
            String inputTypeNameString = (String) iterator.next();
            InputRobotInterface inputRobotInterface = 
                (InputRobotInterface) robotHashtable.get(inputTypeNameString);

            inputRobotInterface.mouseMoveToTarget(rectangle);
            
            if(inputRobotInterface.getName().compareTo(TempInputRobotNames.DX_NAME) == 0)
            {
               inputRobotInterface.mousePress(InputEvent.BUTTON1_MASK);
               Thread.sleep(300);
               inputRobotInterface.mouseRelease(InputEvent.BUTTON1_MASK);

               inputRobotInterface.mousePress(InputEvent.BUTTON2_MASK);
               Thread.sleep(300);
               inputRobotInterface.mouseRelease(InputEvent.BUTTON2_MASK);

               inputRobotInterface.mousePress(InputEvent.BUTTON3_MASK);
               Thread.sleep(300);
               inputRobotInterface.mouseRelease(InputEvent.BUTTON3_MASK);
               
               inputRobotInterface.keyPress(KeyEvent.VK_1);
               Thread.sleep(300);
               inputRobotInterface.keyRelease(KeyEvent.VK_1);

               inputRobotInterface.keyPress(KeyEvent.VK_2);
               Thread.sleep(300);
               inputRobotInterface.keyRelease(KeyEvent.VK_2);

               inputRobotInterface.keyPress(KeyEvent.VK_3);
               Thread.sleep(300);
               inputRobotInterface.keyRelease(KeyEvent.VK_3);

               inputRobotInterface.keyPress(KeyEvent.VK_4);
               Thread.sleep(300);
               inputRobotInterface.keyRelease(KeyEvent.VK_4);

               inputRobotInterface.keyPress(KeyEvent.VK_F1);
               Thread.sleep(300);
               inputRobotInterface.keyRelease(KeyEvent.VK_F1);
            }
         }

         }

         this.index++;
         
         this.getMotionRectanglesVector().remove(motionRectangles);

         LogUtil.put(LogFactory.getInstance(
            CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(), this, this.commonStrings.RUN));
         
         LogUtil.put(LogFactory.getInstance(this.commonStrings.END, this, this.commonStrings.RUN));
      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e));
      }
   }
   
}
