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
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;
import org.microemu.device.playn.PlaynImage;

public class ImageCompleteUtil {

    private static final ImageCompleteUtil instance =
        new ImageLazyCompleteUtil();
        //new ImageWaitCompleteUtil();

    /**
     * @return the instance
     */
    public static ImageCompleteUtil getInstance() {
        return instance;
    }
    
    public void waitFor(Image image, String name)
        throws Exception {
    }
    
//    public boolean isReady(Image image, String name, TimeDelayHelper timeDelayHelper)
//            throws Exception
//    {
//        return true;
//    }
    //private final String ISREADY = "isReady";
    private final String COMPLETE = "Image loading complete for: ";
    public boolean isReady(final Image image, final String name, final TimeDelayHelper timeDelayHelper)
        throws Exception {
        final PlaynImage playnImage = (PlaynImage) image;
        final playn.core.Image playnCoreImage = (playn.core.Image) playnImage.getImage();
        //if(playnCoreImage.isReady()) PreLogUtil.put("core Image Ready: " + image.getName() + " " + playnCoreImage.width() + playnCoreImage.height(), this, ISREADY);
        if (!playnCoreImage.isReady() && playnCoreImage.width() + playnCoreImage.height() <= 0)
        {
            /*
            try
            {
                PreLogUtil.put("Waiting for Image: " + image.getName(), this, ISREADY);
                Thread.sleep(120);
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
        
        if(!image.isReady()) {
            image.init(image.getImage());
        }
        
        PreLogUtil.put(COMPLETE + image.getName(), this, CommonStrings.getInstance().SUCCESS);
        return true;
    }

    public void handleTimeout(final String name) throws Exception {

    }

    public void waitForAll()
        throws Exception {
    }

    public boolean isLazy() {
        return false;
    }

}
