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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

public class ImageCacheBase
{
    protected final Hashtable hashtable = new Hashtable();

    protected final int SIZE = 50;
    protected final int[] widths = new int[SIZE];
    protected final int[] heights = new int[SIZE];
    
    protected final BasicArrayList[] listOfList = 
        new BasicArrayList[SIZE];
    protected final BasicArrayList[] availableListOfList = 
        new BasicArrayList[SIZE];

    protected int volume = 0;
    protected int nextIndex = 0;

    protected ImageCacheBase()
    // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
        // super(cacheableInterfaceFactoryInterface);

        for (int index = listOfList.length - 1; index >= 0; index--)
        {
            listOfList[index] = new BasicArrayList();
        }

        for (int index = availableListOfList.length - 1; index >= 0; index--)
        {
            availableListOfList[index] = new BasicArrayList();
        }
    }
    
    public void releaseAll()
    {
        for (int index = listOfList.length - 1; index >= 0; index--)
        {
            availableListOfList[index].clear();
            availableListOfList[index].addAll(listOfList[index]);            
        }
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("ImageCache: ").append(this.toString()).toString(), this, "releaseAll"));
    }

    protected int getIndex(final int width, final int height)
    {
        int foundIndex = -1;
        
        int size = widths.length;
        
        for (int index = 0; index < size; index++)
        {
            if (widths[index] == width && heights[index] == height)
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
            if (availableListOfList[foundIndex].size() > 0)
            {
                //.log("Returning Image From Cache");
                BasicArrayList list = availableListOfList[foundIndex];
                return (Image) list.remove(list.size() - 1);
            }
        }
        return null;
    }
    
    public Image get(final Object object, final int width, final int height)
    throws Exception
    {
        return this.get(object.getClass().getName(), width, height);
    }
    
    public Image get(final String caller, final int width, final int height)
    throws Exception
    {        
        throw new RuntimeException();
    }

    public Image get(final Object key) throws Exception
    {
        throw new RuntimeException();
    }

    protected Image createImage(final String caller, final int width, final int height)
    throws Exception
    {
        return Image.createImage(width, height);
    }
    
    protected Image createImage(final Object key, final InputStream inputStream)
    throws Exception
    {
        return Image.createImage(inputStream);
    }
    
    public String toString()
    {   
        final StringMaker stringBuffer = new StringMaker(); 
        for (int index = this.nextIndex - 1; index >= 0; index--)
        {
            final int width = widths[index];
            final int height = heights[index];

            final int total = listOfList[index].size();

            final int totalAvailable = availableListOfList[index].size();

            stringBuffer.append(" w: ");
            stringBuffer.append(width);
            stringBuffer.append(" h: ");
            stringBuffer.append(height);
            stringBuffer.append(CommonSeps.getInstance().SPACE);
            stringBuffer.append(CommonLabels.getInstance().TOTAL_LABEL);
            stringBuffer.append(total);
            stringBuffer.append(" available: ");
            stringBuffer.append(totalAvailable);
        }
        return stringBuffer.toString();
    }

    public Hashtable getHashtable()
    {
        return hashtable;
    }

    //OpenGL
    public void init(Image image) {
        
    }
    
}
