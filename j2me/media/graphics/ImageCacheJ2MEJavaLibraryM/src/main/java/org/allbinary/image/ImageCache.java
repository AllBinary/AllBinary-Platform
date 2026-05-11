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
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.game.gd.resource.GDResources;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.system.Memory;

public class ImageCache extends ImageCacheBase
{
    public static final ImageCache NULL_IMAGE_CACHE = new ImageCache();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    public ImageCache()
    // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
    }
    
    //AllBinaryRendererBase3
    public void addListener(Object renderer) {
        
    }

    public int getIndex(final Object key) {
        final GDResources gdResources = GDResources.getInstance();
        final String[] resourceStringArray = gdResources.resourceStringArray;
        final int size = resourceStringArray.length;
        for (int index = 0; index < size; index++) {
            if (resourceStringArray[index] == key) {
                return index;
            }
        }
        this.logUtil.putF(new StringMaker().append("unable to find key: ").append(StringUtil.getInstance().toString(key)).toString(), this, this.commonStrings.RUN);
        throw new RuntimeException();
    }
    
    @Override
    public Image get(final String caller, final int width, final int height)
    throws Exception
    {        
        int foundIndex = this.getIndexWH(width, height);
        Image image = this.getFromAvailable(foundIndex, width, height);

        if (image == NullCanvas.NULL_IMAGE)
        {
            this.volume += width * height;
            if (this.volume > 32000)
            {
                //this.logUtil.putF(new StringMaker().append("Image for: ").append(caller).toString(), this, this.commonStrings.GET);
                System.gc();
                //System.gc();
                this.volume = 0;
                //this.logUtil.putF(Memory.getInfo(), this, this.commonStrings.GET);
            }

            image = this.createImage(caller, width, height);
            //this.logUtil.putF(new StringMaker().append("Image: ").append(image).toString(), this, this.commonStrings.GET);

            final int[] widths = this.widths;
            
            if(this.nextIndex > widths.length - 1) {
                if (foundIndex == -1) {
                    foundIndex = this.nextIndex;

                    widths[this.nextIndex] = width;
                    this.heights[this.nextIndex] = height;

                    this.nextIndex++;
                }

                this.listOfList[foundIndex].add(image);

            }
            
        }

        return image;
    }
    
    @Override    
    public Image getWithKey(final Object key) throws Exception
    {
        Image image = this.getImage(key);

        if (image == NullCanvas.NULL_IMAGE)
        {
            final ResourceUtil resourceUtil = ResourceUtil.getInstance();
            final InputStream inputStream = resourceUtil.getResourceAsStream((String) key);
            
            if(inputStream == null) {
                throw new RuntimeException(new StringMaker().append("Image resource is not available for key: ").append(StringUtil.getInstance().toString(key)).toString());
            }

            try
            {
                //this.logUtil.putF(Memory.getInfo(), this, this.commonStrings.GET);
                image = this.createImageFromInputStream(key, inputStream);
            }
            catch(Exception e)
            {
                this.logUtil.put("Exception: Trying Again After GC", this, this.commonStrings.GET, e);
                
                this.logUtil.putF(new StringMaker().append("InputStream: ").append(StringUtil.getInstance().toString(inputStream)).toString(), this, this.commonStrings.GET);
                System.gc();
                System.gc();
                this.logUtil.putF(Memory.getInfo(), this, this.commonStrings.GET);
                Thread.sleep(100);
                image = this.createImageFromInputStream(key, inputStream);
            }
            inputStream.close();
            //Put in the name is really only for debugging
//            if(DebugFactory.getInstance() != NoDebug.getInstance())
//            {
//                this.hashtable.put(key, image);
//            }
            this.hashtable.put(key, image);
        }

//        final CommonLabels commonLabels = CommonLabels.getInstance();
//        this.logUtil.putF(this.commonLabels.WIDTH_LABEL + image.getWidth() + commonLabels.HEIGHT_LABEL + image.getHeight(), this, this.commonStrings.CONSTRUCTOR);
//        this.logUtil.putF(key + " = " + image.toString(), this, this.commonStrings.CONSTRUCTOR);

        return image;
    }

    public void loadImageForAnimation() throws Exception {
        
    }

    public void progressEnded() {
    
    }    
    
    public void runTask() {
    
    }    

    public boolean isLazy() {
        return false;
    }
    
}
