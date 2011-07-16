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
package allbinary.input.automation.module.generic;

import java.util.Iterator;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.ProfileActionScriptConditionInterface;

public class CaptureWorkerUtil
{
    
    public CaptureWorkerUtil()
    {
    }
    
    public static void processProfileActionConditions(Vector vector, Long frame)
        throws Exception
    {
        LogUtil.put(new Log("Start", "CaptureWorkerUtil", "processProfileActionConditions"));

        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            ProfileActionScriptConditionInterface profileActionConditionInterface =
                (ProfileActionScriptConditionInterface) iterator.next();
            if(profileActionConditionInterface.shouldProcess(frame))
            {
                //LogUtil.put(new Log("Should Process", this, "processProfileActionConditions"));
                profileActionConditionInterface.process(frame);
            }
            else
            {
                //LogUtil.put(new Log("Should Not Process", this, "processProfileActionConditions"));
            }
        }
    }
}
