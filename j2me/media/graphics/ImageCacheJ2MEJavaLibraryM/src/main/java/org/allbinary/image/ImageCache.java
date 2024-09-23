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
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.system.Memory;

public class ImageCache extends ImageCacheBase
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    protected ImageCache()
    // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
    }
    
    //AllBinaryRendererBase3
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
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Image for: ").append(caller).toString(), this, commonStrings.GET));
                System.gc();
                //System.gc();
                volume = 0;
                //LogUtil.put(LogFactory.getInstance(Memory.getInfo(), this, commonStrings.GET));
            }

            image = this.createImage(caller, width, height);
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Image: ").append(image).toString(), this, commonStrings.GET));

            if(nextIndex > widths.length - 1) {
                if (foundIndex == -1) {
                    foundIndex = nextIndex;

                    widths[nextIndex] = width;
                    heights[nextIndex] = height;

                    nextIndex++;
                }

                listOfList[foundIndex].add(image);

            }
            
        }

        return image;
    }
    
    public Image get(final Object key) throws Exception
    {
        Image image = (Image) this.hashtable.get(key);

        if (image == null)
        {
            final ResourceUtil resourceUtil = ResourceUtil.getInstance();
            final InputStream inputStream = resourceUtil.getResourceAsStream((String) key);
            
            if(inputStream == null) {
                throw new RuntimeException(new StringMaker().append("Image resource is not available for key: ").append(key).toString());
            }

            try
            {
                //LogUtil.put(LogFactory.getInstance(Memory.getInfo(), this, commonStrings.GET));
                image = this.createImage(key, inputStream);
            }
            catch(Exception e)
            {
                LogUtil.put(LogFactory.getInstance("Exception: Trying Again After GC", this, commonStrings.GET, e));
                
                LogUtil.put(LogFactory.getInstance(new StringMaker().append("InputStream: ").append(inputStream).toString(), this, commonStrings.GET));
                System.gc();
                System.gc();
                LogUtil.put(LogFactory.getInstance(Memory.getInfo(), this, commonStrings.GET));
                Thread.sleep(100);
                image = this.createImage(key, inputStream);
            }
            inputStream.close();
            //Put in the name is really only for debugging
//            if(DebugFactory.getInstance() != NoDebug.getInstance())
//            {
//                this.hashtable.put(key, image);
//            }
            this.hashtable.put(key, image);
        }

//        final SpacialStrings spacialStrings = SpacialStrings.getInstance();
//        LogUtil.put(LogFactory.getInstance(spacialStrings.WIDTH_LABEL + image.getWidth() + spacialStrings.HEIGHT_LABEL + image.getHeight(), this, commonStrings.CONSTRUCTOR));
//        LogUtil.put(LogFactory.getInstance(key + " = " + image.toString(), this, commonStrings.CONSTRUCTOR));

        return image;
    }

    public void loadImageForAnimation() throws Exception {
        
    }
    
}
