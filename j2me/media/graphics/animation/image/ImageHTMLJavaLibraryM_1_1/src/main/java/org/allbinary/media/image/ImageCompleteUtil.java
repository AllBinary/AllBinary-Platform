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

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;
import org.microemu.device.playn.PlaynImage;

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

//    public boolean isReady(Image image, String name, TimeDelayHelper timeDelayHelper)
//            throws Exception
//    {
//        return true;
//    }

    public boolean isReady(final Image image, final String name, final TimeDelayHelper timeDelayHelper)
            throws Exception
    {
        final PlaynImage playnImage = (PlaynImage) image;
        final playn.core.Image playnCoreImage = (playn.core.Image) playnImage.getImage();
        if(!playnCoreImage.isReady() && playnCoreImage.width() + playnCoreImage.height() == 0)
        {   
            /*
            try
            {
                Thread.sleep(120);
                PreLogUtil.put(image.toString(), this, METHOD_NAME);
            }
            catch(Exception e)
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
            }
            */

            this.handleTimeout(name);
            return false;
        }
        PreLogUtil.put("Image loading complete for: " + name, this, CommonStrings.getInstance().SUCCESS);
        return true;
    }

    public void handleTimeout(final String name) throws Exception {
    
    }    

    public void waitForAll()
            throws Exception
    {
    }

    public boolean isLazy() {
        return false;
    }
    
}
