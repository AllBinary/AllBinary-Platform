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

import allbinary.input.media.image.capture.CaptureWorkerInterface;
import allbinary.input.media.image.capture.ScreenCaptureImagesWorker;

import allbinary.input.automation.module.generic.configuration.profile.GenericProfile;
import allbinary.input.automation.module.generic.configuration.profile.GenericProfileDataWorkerType;
import allbinary.input.automation.module.generic.configuration.profile.SavedCaptureGenericProfileDataWorkerType;

public class GenericProfileCaptureWorkerFactory
{
    private GenericProfileCaptureWorkerFactory()
    {
    }
 
    public static CaptureWorkerInterface getInstance(
        GenericProfile genericProfile)
    throws Exception
    {
        Vector vector = genericProfile.getGenericProfileDataWorkerTypeVector();
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            GenericProfileDataWorkerType genericProfileDataWorkerType = 
                (GenericProfileDataWorkerType) iterator.next();
            
            //LogUtil.put(new Log("Possible Capture Type: " + genericProfileDataWorkerType, "GenericProfileCaptureWorkerFactory", "getInstance"));
            if(genericProfileDataWorkerType == GenericProfileDataWorkerType.SCREEN_CAPTURE)
            {
                return new ScreenCaptureImagesWorker();
            }
            else
            if(genericProfileDataWorkerType == GenericProfileDataWorkerType.SAVED_CAPTURE)
            {
                return new SavedCaptureImagesWorker(
                    (SavedCaptureGenericProfileDataWorkerType)
                    genericProfileDataWorkerType);
            }
        }
        throw new Exception("No CaptureWorkerInterface specified in GenericProfile");
    }
}
