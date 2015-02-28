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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;


import org.allbinary.input.automation.module.AbstractInputAutomationWorker;
import org.allbinary.input.automation.module.generic.configuration.profile.GenericProfile;
import org.allbinary.input.automation.module.generic.configuration.profile.GenericProfileDataWorkerType;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileAction;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScript;
import org.allbinary.input.automation.module.InputAutomationActionInterface;
import org.allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.cache.J2SEAutomaticCacheInterface;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import org.allbinary.media.image.comparison.ImageComparisonWorker;
import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;
import org.allbinary.media.image.comparison.motion.MotionRectanglesWorker;

public class GenericInputAutomationWorker
    extends AbstractInputAutomationWorker
{
    //private final Rectangle rectangle = new Rectangle(0, 0, 1024, 768);
    //private TimeHelper timeHelper;
    private Long lastFrame = new Long(-1);
    private Long frame;
    
    private GenericProfile genericProfile;
    
    public GenericInputAutomationWorker(
        InputAutomationActionInterface inputAutomationActionInterface,
        GenericProfile genericProfile,
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface,
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);

        LogUtil.put(LogFactory.getInstance("GenericInputAutomationCaptureWorker", this, "Constructor"));
        
        this.setCaptureWorker(
            GenericProfileCaptureWorkerFactory.getInstance(
            genericProfile));
        
        this.setInputAutomationActionInterface(inputAutomationActionInterface);
     
        this.setImageComparisonWorker(
            new ImageComparisonWorker(imageComparatorConstraintsInterface));
        this.setMotionRectanglesWorker(
            new MotionRectanglesWorker(motionRectangleConstraintsInterface));
        
        //this.getCaptureWorker().addListener(this);
        
        this.setGenericProfile(genericProfile);

        Vector vector = this.getGenericProfile().getGenericProfileDataWorkerTypeVector();
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            GenericProfileDataWorkerType genericProfileDataWorkerType = 
                (GenericProfileDataWorkerType) iterator.next();

            LogUtil.put(LogFactory.getInstance("Adding Listener: " + genericProfileDataWorkerType, this, "Contructor"));
            if(genericProfileDataWorkerType == GenericProfileDataWorkerType.COMPARISON)
            {
                this.getCaptureWorker().addListener(
                    this.getImageComparisonWorker());
            }
            else
            if(genericProfileDataWorkerType == GenericProfileDataWorkerType.MOTION)
            {
                this.getImageComparisonWorker().addListener(
                    this.getMotionRectanglesWorker());
            }
        }
    }
    
    public void processDataWorkerResults()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "processDataWorkerResults"));

        this.waitForDataWorkers();
        
        J2SEAutomaticCacheInterface cacheInterface = (J2SEAutomaticCacheInterface)
            CapturedBufferedImagesCacheSingleton.getInstance();
        
        Object keyArray[] = cacheInterface.keySet().toArray();
        if(keyArray.length > 0)
        {
            LogUtil.put(LogFactory.getInstance("Image Available", this, "processDataWorkerResults"));
            setFrame((Long) keyArray[keyArray.length - 1]);

            if(getFrame() > lastFrame)
            {
                LogUtil.put(LogFactory.getInstance("Processing new frame: " + getFrame(), this, "processDataWorkerResults"));
                
                HashMap hashMap = this.getGenericProfile().getGenericProfileActions().getHashMap();
                Set set = hashMap.keySet();
                Iterator iterator = set.iterator();
                
                LogUtil.put(LogFactory.getInstance("Processing " + set.size() + "Actions", this, "processDataWorkerResults"));
                while(iterator.hasNext())
                {
                    String actionNameString = (String) iterator.next();
                    LogUtil.put(LogFactory.getInstance("Processing Action: " + actionNameString, this, "processDataWorkerResults"));
                    GenericProfileAction genericProfileAction =
                        (GenericProfileAction) hashMap.get(actionNameString);
                    GenericProfileActionScript genericProfileActionScript =
                        genericProfileAction.getGenericProfileActionScript();
                    Vector vector = genericProfileActionScript.getProfileActionConditionInterfaceVector();
                    CaptureWorkerUtil.processProfileActionConditions(vector, getFrame());
                }
                lastFrame = getFrame();
            }
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Image Not Available", this, "processDataWorkerResults"));
        }
    }

    public void process() throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "process"));
        this.startDataWorkers();
        this.processDataWorkerResults();
    }
    
    public GenericProfile getGenericProfile()
    {
        return genericProfile;
    }
    
    public void setGenericProfile(GenericProfile genericProfile)
    {
        this.genericProfile = genericProfile;
    }

    protected Long getFrame()
    {
        return frame;
    }

    protected void setFrame(Long frame)
    {
        this.frame = frame;
    }
}
