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

import java.io.File;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import allbinary.time.TimeHelper;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.number.LongUtil;
import abcs.logic.visual.media.MediaData;
import allbinary.media.image.cache.BufferedImageFrameCacheable;
import allbinary.input.media.image.capture.CaptureWorkerInterface;
import allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import allbinary.input.media.image.capture.CapturedImageWorkerResultsEvent;
import allbinary.input.media.image.capture.ProcessingFrameIndexFactory;
import allbinary.input.media.image.capture.ScreenScavangerRobot;
import allbinary.input.automation.module.generic.configuration.profile.SavedCaptureGenericProfileDataWorkerType;
    
import allbinary.logic.basic.util.event.handler.BasicEventHandlerAbstract;

public class SavedCaptureImagesWorker
    extends BasicEventHandlerAbstract
    implements CaptureWorkerInterface
{
    private static long index;
    
    private boolean running;
    
    private ScreenScavangerRobot screenScavangerRobot;
    
    private SavedCaptureGenericProfileDataWorkerType savedCaptureGenericProfileDataWorkerType;
    
    public SavedCaptureImagesWorker(
        SavedCaptureGenericProfileDataWorkerType savedCaptureGenericProfileDataWorkerType) 
        throws Exception
    {
        this.savedCaptureGenericProfileDataWorkerType = 
            savedCaptureGenericProfileDataWorkerType;
        this.screenScavangerRobot = new ScreenScavangerRobot();
        index = ProcessingFrameIndexFactory.next();
    }
    
    public synchronized boolean isRunning()
    {
        return running;
    }
    
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(new Log("Start", this, "run"));
            
            this.setRunning(true);
            
            TimeHelper timeHelper = new TimeHelper(1000);
            
            while(this.isRunning())
            {
                timeHelper.setStartTime();
                
                Long frame = new Long(index);
                
                StringBuffer filePathStringBuffer = new StringBuffer();
                
                filePathStringBuffer.append(
                    this.savedCaptureGenericProfileDataWorkerType.getPath());
                filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
                filePathStringBuffer.append(MediaData.JPG.getExtension());
                
                String filePath = filePathStringBuffer.toString();
                
                LogUtil.put(new Log("Loading Image File Path: " + filePath, this, "run"));
                
                File file = new File(filePath);
                if(file.isFile())
                {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    
                    index++;
                    
                    CapturedBufferedImagesCacheSingleton.getInstance().add(
                        new BufferedImageFrameCacheable(bufferedImage, frame));
                    
                    CapturedImageWorkerResultsEvent capturedImageEvent =
                        new CapturedImageWorkerResultsEvent(
                        this, frame, bufferedImage);
                    
                    this.fireEvent(capturedImageEvent);
                }
                else
                {
                    LogUtil.put(new Log(
                        "Could Not Load File: " + filePath, this, "run"));
                }
                
                LogUtil.put(new Log(
                    "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
                
                this.setRunning(false);
            }
            
            LogUtil.put(new Log("End", this, "run"));
            
        }
        catch (Exception e)
        {
            LogUtil.put(new Log("Exception", this, "run", e));
        }
    }
}
