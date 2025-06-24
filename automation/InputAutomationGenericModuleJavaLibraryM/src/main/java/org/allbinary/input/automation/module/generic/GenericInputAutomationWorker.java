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
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.util.cache.J2SECacheInterface;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import org.allbinary.media.image.comparison.ImageComparisonWorker;
import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;
import org.allbinary.media.image.comparison.motion.MotionRectanglesWorker;

public class GenericInputAutomationWorker
    extends AbstractInputAutomationWorker
{

    protected final String PROCESS_DATA_WORKER_RESULTS = "processDataWorkerResults";

    //private final Rectangle rectangle = new Rectangle(0, 0, 1024, 768);
    //private TimeHelper timeHelper;
    private Long lastFrame = new Long(-1);
    private Long frame;
    
    private GenericProfile genericProfile;
    
    public GenericInputAutomationWorker(
        final InputAutomationActionInterface inputAutomationActionInterface,
        final GenericProfile genericProfile,
        final MotionRectangleConstraintsInterface motionRectangleConstraintsInterface,
        final ImageComparatorConstraintsInterface imageComparatorConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);

        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR));
        
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

        final Vector vector = this.getGenericProfile().getGenericProfileDataWorkerTypeVector();
        final int size = vector.size();
        for(int index = 0; index < size; index++)
        {
            final GenericProfileDataWorkerType genericProfileDataWorkerType = 
                (GenericProfileDataWorkerType) vector.get(index);

            LogUtil.put(LogFactory.getInstance("Adding Listener: " + genericProfileDataWorkerType, this, this.commonStrings.CONSTRUCTOR));
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
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.PROCESS_DATA_WORKER_RESULTS));

        this.waitForDataWorkers();
        
        final J2SECacheInterface cacheInterface = (J2SECacheInterface)
            CapturedBufferedImagesCacheSingleton.getInstance();
        
        final Object[] keyArray = cacheInterface.keySet().toArray();
        if(keyArray.length > 0)
        {
            LogUtil.put(LogFactory.getInstance("Image Available", this, this.PROCESS_DATA_WORKER_RESULTS));
            setFrame((Long) keyArray[keyArray.length - 1]);

            if(getFrame() > lastFrame)
            {
                LogUtil.put(LogFactory.getInstance("Processing new frame: " + getFrame(), this, this.PROCESS_DATA_WORKER_RESULTS));
                
                final HashMap hashMap = this.getGenericProfile().getGenericProfileActions().getHashMap();
                final Set set = hashMap.keySet();
                
                LogUtil.put(LogFactory.getInstance("Processing " + set.size() + "Actions", this, this.PROCESS_DATA_WORKER_RESULTS));

                final Object[] actionNameArray = set.toArray();
                final int size = actionNameArray.length;
                for(int index = 0; index < size; index++)
                {
                    String actionNameString = (String) actionNameArray[index];
                    LogUtil.put(LogFactory.getInstance("Processing Action: " + actionNameString, this, this.PROCESS_DATA_WORKER_RESULTS));
                    final GenericProfileAction genericProfileAction =
                        (GenericProfileAction) hashMap.get(actionNameString);
                    final GenericProfileActionScript genericProfileActionScript =
                        genericProfileAction.getGenericProfileActionScript();
                    final Vector vector = genericProfileActionScript.getProfileActionConditionInterfaceVector();
                    CaptureWorkerUtil.processProfileActionConditions(vector, getFrame());
                }
                lastFrame = getFrame();
            }
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Image Not Available", this, this.PROCESS_DATA_WORKER_RESULTS));
        }
    }

    public void process() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.PROCESS));
        this.startDataWorkers();
        this.processDataWorkerResults();
    }
    
    public GenericProfile getGenericProfile()
    {
        return genericProfile;
    }
    
    public void setGenericProfile(final GenericProfile genericProfile)
    {
        this.genericProfile = genericProfile;
    }

    protected Long getFrame()
    {
        return frame;
    }

    protected void setFrame(final Long frame)
    {
        this.frame = frame;
    }
}
