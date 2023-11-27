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

import java.util.Iterator;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;
import java.util.Hashtable;
import java.util.Set;
import org.allbinary.logic.communication.log.LogFactory;

public class TestInputAutomationActionWorker 
    extends AbstractInputAutomationWorker
{
    private int sleep;
    
    public TestInputAutomationActionWorker(
        int sleep,
        InputAutomationActionInterface inputAutomationActionInterface,
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface,
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);

        LogUtil.put(LogFactory.getInstance("BasicTestAttackWorder", this, this.commonStrings.CONSTRUCTOR));
        
        this.sleep = sleep;
    }

    public void processBetweenWorkers() throws Exception
    {
        Thread.sleep(2000);
    }
    
    public void process() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "process"));

        //this.startCaptureWorkers();

        Hashtable hashtable = InputRobotFactory.getInstance().get();
        Set set = hashtable.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            String inputTypeNameString = (String) iterator.next();
            
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
