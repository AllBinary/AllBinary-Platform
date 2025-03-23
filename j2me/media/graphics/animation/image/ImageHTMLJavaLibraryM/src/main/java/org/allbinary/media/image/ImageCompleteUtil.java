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

import java.util.Hashtable;
import javax.microedition.lcdui.Image;
import org.allbinary.image.GameFeatureImageCacheFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.HashtableUtil;
import org.microemu.device.playn.PlaynImage;

public class ImageCompleteUtil
{
    private static final ImageCompleteUtil instance = new ImageCompleteUtil();

    /**
     * @return the instance
     */
    public static ImageCompleteUtil getInstance()
    {
        return instance;
    }

    //private final String WAIT = "Waiting to load image";

    //private final String METHOD_NAME = "waitForLoad";

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(18000);
    private final TimeDelayHelper allTimeDelayHelper = new TimeDelayHelper(120000);
    
    public void waitFor(Image image, String name)
            throws Exception
    {
        this.timeDelayHelper.setStartTime();
        this.waitFor(image, name, this.timeDelayHelper);
    }

    public boolean isReady(Image image, String name, TimeDelayHelper timeDelayHelper)
            throws Exception
    {
        PlaynImage playnImage = (PlaynImage) image;

        if(!playnImage.getImage().isReady() && playnImage.getImage().width() + playnImage.getImage().height() == 0)
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

            if(timeDelayHelper.isTime())
            {
                throw new Exception("isReady: Timeout Waiting or GameHtmlHasLoadedResourcesProcessor does not have this Image: " + name);
            }
            return false;
        }
        //PreLogUtil.put("Image loading complete for: " + name, this, CommonStrings.getInstance().SUCCESS);
        return true;
    }

    private void waitFor(Image image, String name, TimeDelayHelper timeDelayHelper)
            throws Exception
    {
        PlaynImage playnImage = (PlaynImage) image;

        while(!playnImage.getImage().isReady() && playnImage.getImage().width() + playnImage.getImage().height() == 0)
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

            if(timeDelayHelper.isTime())
            {
                throw new Exception("waitFor: Timeout Waiting or GameHtmlHasLoadedResourcesProcessor does not have this Image: " + name);
            }
        }
    }

    public void waitForAll()
            throws Exception
    {
        this.allTimeDelayHelper.setStartTime();
        
        Hashtable hashtable = 
                GameFeatureImageCacheFactory.getInstance().getHashtable();

        Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        int size = objectArray.length;
        
        PreLogUtil.put("Total: " + size, this, "waitForAll");
        
        for (int index = 0; index < size; index++)
        {
            this.waitFor((Image) hashtable.get(objectArray[index]), (String) objectArray[index], this.allTimeDelayHelper);
        }
    }
}
