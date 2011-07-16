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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.automation.module.AbstractInputAutomationWorker;
import allbinary.input.automation.module.InputAutomationActionInterface;
import allbinary.input.automation.module.generic.configuration.profile.GenericProfile;
import allbinary.input.automation.module.generic.configuration.profile.GenericProfileDataWorkerType;
import allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileAction;
import allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScript;
import allbinary.input.automation.module.generic.GenericProfileCaptureWorkerFactory;
import allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import allbinary.media.image.comparison.ImageComparisonWorker;
import allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;
import allbinary.media.image.comparison.motion.MotionRectanglesWorker;

import com.abcs.logic.util.cache.CacheInterface;

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

        LogUtil.put(new Log("GenericInputAutomationCaptureWorker", this, "Constructor"));
        
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

            LogUtil.put(new Log("Adding Listener: " + genericProfileDataWorkerType, this, "Contructor"));
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
        LogUtil.put(new Log("Start", this, "processDataWorkerResults"));

        this.waitForDataWorkers();
        
        CacheInterface cacheInterface =
            CapturedBufferedImagesCacheSingleton.getInstance();
        
        Object keyArray[] = cacheInterface.keySet().toArray();
        if(keyArray.length > 0)
        {
            LogUtil.put(new Log("Image Available", this, "processDataWorkerResults"));
            setFrame((Long) keyArray[keyArray.length - 1]);

            if(getFrame() > lastFrame)
            {
                LogUtil.put(new Log("Processing new frame: " + getFrame(), this, "processDataWorkerResults"));
                
                HashMap hashMap = this.getGenericProfile().getGenericProfileActions().getHashMap();
                Set set = hashMap.keySet();
                Iterator iterator = set.iterator();
                
                LogUtil.put(new Log("Processing " + set.size() + "Actions", this, "processDataWorkerResults"));
                while(iterator.hasNext())
                {
                    String actionNameString = (String) iterator.next();
                    LogUtil.put(new Log("Processing Action: " + actionNameString, this, "processDataWorkerResults"));
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
            LogUtil.put(new Log("Image Not Available", this, "processDataWorkerResults"));
        }
    }

    public void process() throws Exception
    {
        LogUtil.put(new Log("Start", this, "process"));
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
