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
package org.allbinary.input.automation.module.game.test;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.allbinary.input.automation.module.AbstractInputAutomationWorker;
import org.allbinary.input.automation.module.InputAutomationActionInterface;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileAction;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActions;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScript;
import org.allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.cache.J2SECacheInterface;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;

public class TestInputAutomationCaptureWorker
    extends AbstractInputAutomationWorker
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private final Rectangle rectangle = new Rectangle(0, 0, 1024, 768);
    //private TimeHelper timeHelper;
    
    private GenericProfileActions genericProfileActions;

    private final String MESSAGE = "GenericInputAutomationCaptureWorker";
    private final String PROCESS_DATA_WORKER_RESULTS = "processDataWorkerResults";
    private final String IMAGE_AVAILABLE_SO_PROCESSING = "Image Available so processing";
    private final String IMAGE_NOT_AVAILABLE = "Image Not Available";

    public TestInputAutomationCaptureWorker(
        InputAutomationActionInterface inputAutomationActionInterface,
        GenericProfileActions genericProfileActions,
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface,
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);
        
        logUtil.put(MESSAGE, this, commonStrings.CONSTRUCTOR);
        
        this.setGenericProfileActions(genericProfileActions);
    }

    public void processDataWorkerResults()
        throws Exception
    {
        final J2SECacheInterface cacheInterface = (J2SECacheInterface)
            CapturedBufferedImagesCacheSingleton.getInstance();

        if(cacheInterface.keySet().size() > 0)
        {
            logUtil.put(IMAGE_AVAILABLE_SO_PROCESSING, this, PROCESS_DATA_WORKER_RESULTS);
            
            Object object = cacheInterface.keySet().toArray()[0];
            //BufferedImageFrameCacheable capturedBufferedImageCacheable = 
                //(BufferedImageFrameCacheable) cacheInterface.get(object);
            
            HashMap hashMap = this.getGenericProfileActions().getHashMap();
            Set set = hashMap.keySet();

            final Object[] actionNameArray = set.toArray();
            final int size = actionNameArray.length;
            for(int index = 0; index < size; index++)
            {                
                String actionNameString = (String) actionNameArray[index];
                GenericProfileAction genericProfileAction =
                    (GenericProfileAction) hashMap.get(actionNameString);
                GenericProfileActionScript genericProfileActionScript =
                    genericProfileAction.getGenericProfileActionScript();
                Vector vector = genericProfileActionScript.getProfileActionConditionInterfaceVector();
                //CaptureWorkerUtil.processProfileActionConditions(vector,
                  //  capturedBufferedImageCacheable);
            }
            
            //was remove before
            cacheInterface.get(object);
        }
        else
        {
            logUtil.put(IMAGE_NOT_AVAILABLE, this, PROCESS_DATA_WORKER_RESULTS);
        }
    }
    
    public void process() throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.PROCESS);
        
        this.startDataWorkers();
        this.processDataWorkerResults();
    }

    public GenericProfileActions getGenericProfileActions()
    {
        return genericProfileActions;
    }
    
    public void setGenericProfileActions(GenericProfileActions genericProfileActions)
    {
        this.genericProfileActions = genericProfileActions;
    }
}
