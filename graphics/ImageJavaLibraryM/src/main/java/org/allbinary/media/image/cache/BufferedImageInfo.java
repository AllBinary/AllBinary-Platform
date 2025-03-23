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
package org.allbinary.media.image.cache;

import org.allbinary.string.CommonLabels;

public class BufferedImageInfo
{
    private int width;
    private int height;
    private int type;
    
    public BufferedImageInfo(int width, int height, int type)
    {
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
    
    public String toString()
    {
        final CommonLabels commonLabels = CommonLabels.getInstance();
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BufferedImageInfo -");
        stringBuffer.append(commonLabels.WIDTH_LABEL);
        stringBuffer.append(this.getWidth());
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.append(this.getHeight());
        stringBuffer.append(" Type: ");
        stringBuffer.append(this.getType());
        return stringBuffer.toString();
    }
}
