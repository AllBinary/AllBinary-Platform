/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import org.allbinary.image.GameFeatureImageCacheFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.HashtableUtil;
import org.microemu.device.playn.PlaynImage;

/**
 *
 * @author User
 */
public class ImageWaitCompleteUtil extends ImageCompleteUtil {
    
    //private final String WAIT = "Waiting to load image";

    private final String METHOD_NAME = "waitForLoad";

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(18000);
    private final TimeDelayHelper allTimeDelayHelper = new TimeDelayHelper(120000);
    
    public ImageWaitCompleteUtil() {
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //LogUtil.put(LogFactory.getInstance(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR));
    }
    
    @Override
    public void waitFor(final Image image, final String name)
            throws Exception
    {
        this.timeDelayHelper.setStartTime();
        this.waitFor(image, name, this.timeDelayHelper);
    }

    public void handleTimeout(final String name) throws Exception {
        if (timeDelayHelper.isTime()) {
            throw new Exception("isReady: Timeout Waiting or GameHtmlHasLoadedResourcesProcessor does not have this Image: " + name);
        }
    }    
    
    private void waitFor(final Image image, final String name, final TimeDelayHelper timeDelayHelper)
            throws Exception
    {
        final PlaynImage playnImage = (PlaynImage) image;
        final playn.core.Image playnCoreImage = (playn.core.Image) playnImage.getImage();
        
        //if(!playnCoreImage.isReady()) PreLogUtil.put("core Image Ready: " + image.getName() + " " + playnCoreImage.width() + playnCoreImage.height(), this, ISREADY);
        while(!playnCoreImage.isReady() || playnCoreImage.width() + playnCoreImage.height() <= 0)
        {   
            /*
            try
            {
                PreLogUtil.put("Waiting for Image Ready: " + image.toString(), this, METHOD_NAME);
                Thread.sleep(120);
            }
            catch(Exception e)
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
            }
            */

            if(timeDelayHelper.isTime())
            {
                throw new Exception("waitFor: Timeout Waiting or GameHtmlHasLoadedResourcesProcessor does not have this Image: " + name);
            }
        }
        
        if(!image.isReady()) {
            image.init(image.getImage());
        }
        
    }

    public void waitForAll()
            throws Exception
    {
        this.allTimeDelayHelper.setStartTime();
        
        final Hashtable hashtable = GameFeatureImageCacheFactory.getInstance().getHashtable();

        final Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        final int size = objectArray.length;
        
        PreLogUtil.put("Image Total: " + size, this, "waitForAll");
        
        for (int index = 0; index < size; index++)
        {
            this.waitFor((Image) hashtable.get(objectArray[index]), (String) objectArray[index], this.allTimeDelayHelper);
        }
    }

}
