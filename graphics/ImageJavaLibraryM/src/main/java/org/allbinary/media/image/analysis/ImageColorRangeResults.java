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
package org.allbinary.media.image.analysis;

public class ImageColorRangeResults
{
    private ColorRangeInterface colorRangeInterface;
    
    private long matchingPixelsChecked;
    private long totalPixelsChecked;
    
    public ImageColorRangeResults()
    {
        this.setColorRangeInterface((ColorRangeInterface) new ColorRange());
    }

    public ColorRangeInterface getColorRangeInterface()
    {
        return colorRangeInterface;
    }

    public void setColorRangeInterface(ColorRangeInterface colorRangeInterface)
    {
        this.colorRangeInterface = colorRangeInterface;
    }

    public long getMatchingPixelsChecked()
    {
        return matchingPixelsChecked;
    }

    public void setMatchingPixelsChecked(long matchingPixelsChecked)
    {
        this.matchingPixelsChecked = matchingPixelsChecked;
    }

    public void addMatchingPixelsChecked()
    {
        this.matchingPixelsChecked++;
    }
    
    public long getTotalPixelsChecked()
    {
        return totalPixelsChecked;
    }

    public void addTotalPixelsChecked()
    {
        totalPixelsChecked++;
    }
    
    public void setTotalPixelsChecked(long totalPixelsChecked)
    {
        this.totalPixelsChecked = totalPixelsChecked;
    }
    
    public float getPercent()
    {
        return ((float) this.getMatchingPixelsChecked()/this.getTotalPixelsChecked());
    }
}
