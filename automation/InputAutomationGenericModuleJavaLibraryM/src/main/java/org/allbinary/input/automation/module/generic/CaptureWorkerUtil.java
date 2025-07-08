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
package org.allbinary.input.automation.module.generic;


import java.util.Vector;

import org.allbinary.input.automation.actions.script.condition.ProfileActionScriptConditionInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class CaptureWorkerUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public CaptureWorkerUtil()
    {
    }
    
    public static void processProfileActionConditions(Vector vector, Long frame)
        throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(commonStrings.START, "CaptureWorkerUtil", "processProfileActionConditions");

        final int size = vector.size();
        for(int index = 0; index < size; index++)
        {
            ProfileActionScriptConditionInterface profileActionConditionInterface =
                (ProfileActionScriptConditionInterface) vector.get(index);
            if(profileActionConditionInterface.shouldProcess(frame))
            {
                //logUtil.put("Should Process", this, "processProfileActionConditions");
                profileActionConditionInterface.process(frame);
            }
            else
            {
                //logUtil.put("Should Not Process", this, "processProfileActionConditions");
            }
        }
    }
}
