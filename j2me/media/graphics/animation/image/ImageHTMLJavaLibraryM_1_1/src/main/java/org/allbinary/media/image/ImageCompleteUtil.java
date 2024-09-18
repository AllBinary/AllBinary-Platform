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
package org.allbinary.media.image;

import javax.microedition.lcdui.Image;
import org.allbinary.time.TimeDelayHelper;

public class ImageCompleteUtil
{
    private static final ImageCompleteUtil instance = 
        new ImageLazyCompleteUtil();
        //new ImageWaitCompleteUtil();

    /**
     * @return the instance
     */
    public static ImageCompleteUtil getInstance()
    {
        return instance;
    }

   public void waitFor(Image image, String name)
            throws Exception
    {
    }

    public boolean isReady(Image image, String name, TimeDelayHelper timeDelayHelper)
            throws Exception
    {
        return true;
    }

    public void waitForAll()
            throws Exception
    {
    }

    public boolean isLazy() {
        return false;
    }
    
}
