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
package allbinary.media.image.comparison.motion;

import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.automation.robot.InputRobotInterface;
import allbinary.input.automation.robot.InputRobotFactory;
import allbinary.input.automation.robot.TempInputRobotNames;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

import allbinary.time.TimeHelper;

public class TestInputForMotionRectanglesResultsWorker
   implements MotionRectanglesResultsListener
{
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
         LogUtil.put(new Log("Start", this, "run"));
         
         this.setRunning(true);
         
         TimeHelper timeHelper = new TimeHelper(1000);
         
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

         LogUtil.put(new Log(
            "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
         
         LogUtil.put(new Log("End", this, "run"));
      }
      catch (Exception e)
      {
         LogUtil.put(new Log("Exception", this, "run", e));
      }
   }
   
}
