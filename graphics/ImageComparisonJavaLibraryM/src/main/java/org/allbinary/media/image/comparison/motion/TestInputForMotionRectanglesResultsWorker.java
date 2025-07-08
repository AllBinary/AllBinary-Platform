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

import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.TempInputRobotNames;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class TestInputForMotionRectanglesResultsWorker
   implements MotionRectanglesResultsListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
         logUtil.put(this.commonStrings.START, this, this.commonStrings.RUN);
         
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
         final Object[] inputTypeNameArray = robotHashtable.keySet().toArray();
         final int size = inputTypeNameArray.length;
         for(int index = 0; index < size; index++)
         {
            String inputTypeNameString = (String) inputTypeNameArray[index];
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

         logUtil.put(
            CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(), this, this.commonStrings.RUN);
         
         logUtil.put(this.commonStrings.END, this, this.commonStrings.RUN);
      }
      catch (Exception e)
      {
         logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
      }
   }
   
}
