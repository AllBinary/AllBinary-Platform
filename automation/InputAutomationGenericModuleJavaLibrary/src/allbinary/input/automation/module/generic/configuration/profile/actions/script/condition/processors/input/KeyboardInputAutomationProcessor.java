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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.input;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.automation.robot.InputRobotInterface;
import allbinary.time.TimeHelper;

public class KeyboardInputAutomationProcessor
{
   private KeyboardInputAutomationProcessor()
   {
   }
   
   public static void process(KeyboardActionScriptInputInterface keyActionScriptInputInterface)
   throws Exception
   {
      LogUtil.put(new Log("Start: " + keyActionScriptInputInterface.toString(), 
            "KeyInputAutomationProcessor", "process"));
      
      InputRobotInterface inputRobotInterface = 
            keyActionScriptInputInterface.getInputRobotInterface();
      
      Integer[] integer = keyActionScriptInputInterface.getKeyArray();
      
      //Simultaneious
      if(keyActionScriptInputInterface.getDelayBetweenKeys() == 0)
      {
         for (int index = 0; index < keyActionScriptInputInterface.getKeyArray().length; index++)
         {
            if (keyActionScriptInputInterface.isPress())
            {
               inputRobotInterface.keyPress(integer[index]);
            }
         }
         
         Thread.sleep(keyActionScriptInputInterface.getTime());
         
         for (int index = keyActionScriptInputInterface.getKeyArray().length - 1; index >= 0; index--)
         {
            if (keyActionScriptInputInterface.isRelease())
            {
               inputRobotInterface.keyRelease(integer[index]);
            }
         }
         
      }
      else
      {
         for (int index = 0; index < keyActionScriptInputInterface.getKeyArray().length; index++)
         {
            if (keyActionScriptInputInterface.isPress())
            {
               inputRobotInterface.keyPress(integer[index]);
            }
            
            Thread.sleep(keyActionScriptInputInterface.getTime());
            
            if (keyActionScriptInputInterface.isRelease())
            {
               inputRobotInterface.keyRelease(integer[index]);
            }
            
            TimeHelper timeHelper = new TimeHelper(
                  keyActionScriptInputInterface.getDelayBetweenKeys());
            
            int toLong = 0;
            while (!timeHelper.isTime() || toLong > 6000)
            {
               toLong++;
               Thread.sleep(50);
            }
         }
         
      }
   }
}