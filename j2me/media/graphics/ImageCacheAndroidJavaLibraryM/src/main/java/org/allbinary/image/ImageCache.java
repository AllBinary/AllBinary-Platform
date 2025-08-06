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
package org.allbinary.image;

import java.io.InputStream;

import javax.microedition.lcdui.Image;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.system.Memory;

public class ImageCache extends ImageCacheBase
{
    public static final ImageCache NULL_IMAGE_CACHE = new ImageCache();
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    protected ImageCache()
    // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
    }

    public void addListener(Object renderer) {
        
    }
    
    public Image get(final String caller, final int width, final int height)
    throws Exception
    {        
        int foundIndex = this.getIndex(width, height);
        Image image = this.getFromAvailable(foundIndex, width, height);

        if (image == null)
        {
            volume += width * height;
            if (volume > 32000)
            {
                //logUtil.put(new StringMaker().append("Image for: ").append(caller).toString(), this, commonStrings.GET);
                System.gc();
                //System.gc();
                volume = 0;
                //logUtil.put(Memory.getInfo(), this, commonStrings.GET);
            }

            image = this.createImage(caller, width, height);
            //if(logit) logUtil.put(new StringMaker().append(caller).append(" Image: ").append(image).toString(), this, commonStrings.GET, new Exception());
            //else logUtil.put(new StringMaker().append(caller).append(" Image: ").append(image).toString(), this, commonStrings.GET);

            //if(nextIndex < widths.length) {
                if (foundIndex == -1) {
                    foundIndex = nextIndex;

                    widths[nextIndex] = width;
                    heights[nextIndex] = height;

                    nextIndex++;
                }

                listOfList[foundIndex].add(image);
            //}
            
        }

        return image;
    }
    
    public Image get(final Object key) throws Exception
    {
        final ResourceUtil resourceUtil = ResourceUtil.getInstance();
        final Integer resourceId = resourceUtil.getResourceId((String) key);
        Image image = (Image) this.hashtable.get(resourceId);

        if (image == null)
        {
            final InputStream inputStream = resourceUtil.getResourceAsStream((String) key);
            try
            {
                logUtil.put(Memory.getInfo(), this, commonStrings.GET);
                image = this.createImage(key, inputStream);
            }
            catch(Exception e)
            {
                logUtil.put("Exception: Trying Again After GC", this, commonStrings.GET, e);
                
                logUtil.put(new StringMaker().append("InputStream: ").append(StringUtil.getInstance().toString(inputStream)).toString(), this, commonStrings.GET);
                System.gc();
                System.gc();
                logUtil.put(Memory.getInfo(), this, commonStrings.GET);
                Thread.sleep(100);
                image = this.createImage(key, inputStream);
            }
            inputStream.close();
            //Put in the name is really only for debugging
//            if(DebugFactory.getInstance() != NoDebug.getInstance())
//            {
//                this.hashtable.put(key, image);
//            }
            this.hashtable.put(resourceId, image);
        }

        return image;
    }

    public void runTask() {
        
    }

    public boolean isLazy() {
        return false;
    }
    
}
