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
package org.allbinary.input.automation.module;


import java.util.Hashtable;
import java.util.Set;

import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;

public class TestInputAutomationActionWorker 
    extends AbstractInputAutomationWorker
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private int sleep;
    
    public TestInputAutomationActionWorker(
        int sleep,
        InputAutomationActionInterface inputAutomationActionInterface,
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface,
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);

        logUtil.put("BasicTestAttackWorder", this, this.commonStrings.CONSTRUCTOR);
        
        this.sleep = sleep;
    }

    public void processBetweenWorkers() throws Exception
    {
        Thread.sleep(2000);
    }
    
    public void process() throws Exception
    {
        logUtil.put(this.commonStrings.START, this, commonStrings.PROCESS);

        //this.startCaptureWorkers();

        final Hashtable hashtable = InputRobotFactory.getInstance().get();
        final Set set = hashtable.keySet();
                
        final Object[] inputTypeNameArray = set.toArray();
        final int size = inputTypeNameArray.length;
        for(int index = 0; index < size; index++)
        {
            String inputTypeNameString = (String) inputTypeNameArray[index];
            
            InputRobotInterface robot = 
                (InputRobotInterface) hashtable.get(inputTypeNameString);
            
            //this.getInputAutomationActionInterface().test();
            
            if(this.sleep > 0)
            {
               Thread.sleep(this.sleep);
            }
        }
    }
}
