package org.allbinary.animation.image;

import abcs.logic.communication.log.PreLogUtil;
import allbinary.time.TimeDelayHelper;
import java.util.Hashtable;
import javax.microedition.lcdui.Image;
import org.allbinary.image.GameFeatureImageCacheFactory;
import org.allbinary.util.HashtableUtil;
import org.microemu.device.playn.PlaynImage;

/**
 *
 * @author user
 */
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

    private final String METHOD_NAME = "waitForLoad";

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(18000);
    private final TimeDelayHelper allTimeDelayHelper = new TimeDelayHelper(120000);
    
    public void waitFor(Image image)
            throws Exception
    {
        this.timeDelayHelper.setStartTime();
        this.waitFor(image, this.timeDelayHelper);
    }

    public boolean isReady(Image image, TimeDelayHelper timeDelayHelper)
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
                throw new Exception("Timeout Waiting for Images");
            }
            return false;
        }
        return true;
    }

    private void waitFor(Image image, TimeDelayHelper timeDelayHelper)
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
                throw new Exception("Timeout Waiting for Images");
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
            this.waitFor((Image) hashtable.get(objectArray[index]), this.allTimeDelayHelper);
        }
    }
}
