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
package allbinary.input.automation.module.generic.configuration.profile;

import allbinary.input.media.image.InputImageType;

public class SavedCaptureGenericProfileDataWorkerType
    extends GenericProfileDataWorkerType
{
    public static SavedCaptureGenericProfileDataWorkerType SAVED_CAPTURE = 
        new SavedCaptureGenericProfileDataWorkerType(
        "Saved " + InputImageType.CAPTURE.getName(), 
        "c:\\share\\captures\\2\\", 100);

    private String path;
    private int lastFrame;
    
    public SavedCaptureGenericProfileDataWorkerType(
        String name, String path, int lastFrame)
    {
        super(name);
        
        this.setPath(path);
        this.setLastFrame(lastFrame);
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public int getLastFrame()
    {
        return lastFrame;
    }

    public void setLastFrame(int lastFrame)
    {
        this.lastFrame = lastFrame;
    }

}
