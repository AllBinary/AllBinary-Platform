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
import java.util.Hashtable;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class ImageCacheBase
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected final ImageFactory imageFactory = ImageFactory.getInstance();

    protected final Hashtable hashtable = new Hashtable();

    protected final int SIZE = 128;
    protected final int[] widths = new int[this.SIZE];
    protected final int[] heights = new int[this.SIZE];
    
    protected final BasicArrayList[] listOfList = new BasicArrayList[this.SIZE];
    protected final BasicArrayList[] availableListOfList = new BasicArrayList[this.SIZE];

    protected int volume = 0;
    protected int nextIndex = 0;

    //protected
    public ImageCacheBase()
    // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
        // super(cacheableInterfaceFactoryInterface);

        for (int index = this.listOfList.length - 1; index >= 0; index--)
        {
            this.listOfList[index] = new BasicArrayListD();
        }

        for (int index = this.availableListOfList.length - 1; index >= 0; index--)
        {
            this.availableListOfList[index] = new BasicArrayListD();
        }
    }
    
    protected Image getImage(final Object resourceId) {
       final Object imageCanBeNull =  this.hashtable.get(resourceId);
       if(imageCanBeNull == null) {
           return NullCanvas.NULL_IMAGE;
       }
           
       return (Image) imageCanBeNull;
    }
    
    public void releaseAll()
    {
        for (int index = this.listOfList.length - 1; index >= 0; index--)
        {
            this.availableListOfList[index].clear();
            this.availableListOfList[index].addAllList(this.listOfList[index]);
        }
        this.logUtil.putF(new StringMaker().append("ImageCache: ").append(this.toString()).toString(), this, "releaseAll");
    }

    protected int getIndex(final int width, final int height)
    {
        int foundIndex = -1;
        
        int size = this.widths.length;
        
        for (int index = 0; index < size; index++)
        {
            if (this.widths[index] == width && this.heights[index] == height)
            {
                foundIndex = index;
                return foundIndex;
            }
        }
        
        return foundIndex;
    }
    
    protected Image getFromAvailable(final int foundIndex, final int width, final int height)
    {
        if (foundIndex != -1)
        {
            if (this.availableListOfList[foundIndex].size() > 0)
            {
                //.log("Returning Image From Cache");
                BasicArrayList list = this.availableListOfList[foundIndex];
                return (Image) list.removeAt(list.size() - 1);
            }
        }
        return NullCanvas.NULL_IMAGE;
    }
    
//    public Image get(final Object object, final int width, final int height)
//    throws Exception
//    {
//        return this.get(object.getClass().getName(), width, height);
//    }
    
    public Image get(final String caller, final int width, final int height)
    throws Exception
    {        
        throw new RuntimeException();
    }

    public Image getWithKey(final Object key) throws Exception
    {
        throw new RuntimeException();
    }

    protected Image createImage(final String caller, final int width, final int height)
    throws Exception
    {
        return this.imageFactory.createImage(caller, width, height);
    }
    
    protected Image createImageFromInputStream(final Object key, final InputStream inputStream)
    throws Exception
    {
        return this.imageFactory.createImageFromInputStream(key, inputStream);
    }
    
    public String toString()
    {   
        final StringMaker stringBuffer = new StringMaker(); 
        for (int index = this.nextIndex - 1; index >= 0; index--)
        {
            final int width = this.widths[index];
            final int height = this.heights[index];

            final int total = this.listOfList[index].size();

            final int totalAvailable = this.availableListOfList[index].size();

            stringBuffer.append(" w: ");
            stringBuffer.appendint(width);
            stringBuffer.append(" h: ");
            stringBuffer.appendint(height);
            stringBuffer.append(CommonSeps.getInstance().SPACE);
            stringBuffer.append(CommonLabels.getInstance().TOTAL_LABEL);
            stringBuffer.appendint(total);
            stringBuffer.append(" available: ");
            stringBuffer.appendint(totalAvailable);
        }
        return stringBuffer.toString();
    }

    public Hashtable getHashtableP()
    {
        return this.hashtable;
    }

    //OpenGL
    public void init(Image image) {
        
    }
    
    public void initProgress() {
        
    }

}
