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
package org.allbinary.input.automation.actions.script.condition.processors.input;

import org.allbinary.input.automation.actions.script.condition.processors.ProfileActionScriptProcessorInterface;
import org.allbinary.input.automation.robot.InputRobotInterface;

public interface ProfileActionScriptInputInterface
      extends ProfileActionScriptProcessorInterface
{
   void setInputRobotInterface(InputRobotInterface inputRobotInterface);
   InputRobotInterface getInputRobotInterface();
   
   int getTime();
   void setTime(int time);
}
