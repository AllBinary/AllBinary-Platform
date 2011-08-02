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
package allbinary.input.automation.module.game.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.automation.module.AbstractInputAutomationWorker;
import allbinary.input.automation.module.InputAutomationActionInterface;
import allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileAction;
import allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActions;
import allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScript;
import allbinary.media.image.cache.BufferedImageFrameCacheable;
import allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import allbinary.input.media.image.capture.CapturedImageWorkerResultsListener;
import allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;
import com.abcs.logic.util.cache.CacheInterface;

public class TestInputAutomationCaptureWorker
    extends AbstractInputAutomationWorker
{
    //private final Rectangle rectangle = new Rectangle(0, 0, 1024, 768);
    //private TimeHelper timeHelper;
    
    private GenericProfileActions genericProfileActions;
    
    public TestInputAutomationCaptureWorker(
        InputAutomationActionInterface inputAutomationActionInterface,
        GenericProfileActions genericProfileActions,
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface,
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);
        
        LogUtil.put(new Log("GenericInputAutomationCaptureWorker", this, "Constructor"));
        
        this.setGenericProfileActions(genericProfileActions);
    }

    public void processDataWorkerResults()
        throws Exception
    {
        CacheInterface cacheInterface = 
            CapturedBufferedImagesCacheSingleton.getInstance();

        if(cacheInterface.keySet().size() > 0)
        {
            LogUtil.put(new Log("Image Available so processing", this, "processDataWorkerResults"));
            
            Object object = cacheInterface.keySet().toArray()[0];
            BufferedImageFrameCacheable capturedBufferedImageCacheable = 
                (BufferedImageFrameCacheable) cacheInterface.get(object);
            
            HashMap hashMap = this.getGenericProfileActions().getHashMap();
            Set set = hashMap.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext())
            {                
                String actionNameString = (String) iterator.next();
                GenericProfileAction genericProfileAction =
                    (GenericProfileAction) hashMap.get(actionNameString);
                GenericProfileActionScript genericProfileActionScript =
                    genericProfileAction.getGenericProfileActionScript();
                Vector vector = genericProfileActionScript.getProfileActionConditionInterfaceVector();
                //CaptureWorkerUtil.processProfileActionConditions(vector,
                  //  capturedBufferedImageCacheable);
            }
            
            cacheInterface.remove(object);
        }
        else
        {
            LogUtil.put(new Log("Image Not Available", this, "processDataWorkerResults"));
        }
    }
    
    public void process() throws Exception
    {
        LogUtil.put(new Log("Start", this, "process"));
        
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
