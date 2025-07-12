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

import java.awt.*;

import org.allbinary.logic.string.StringMaker;

public class ColorRange
    implements ColorRangeInterface
{
    private int minRed;
    private int maxRed;
    private int minGreen;
    private int maxGreen;
    private int minBlue;
    private int maxBlue;
    
    public ColorRange()
    {
        this.minRed = 0;
        this.maxRed = 0;
        this.minGreen = 0;
        this.maxGreen = 0;
        this.minBlue = 0;
        this.maxBlue = 0;
    }
    
    public int getMinRed()
    {
        return minRed;
    }
    
    public void setMinRed(int minRed)
    {
        this.minRed = minRed;
    }
    
    public int getMaxRed()
    {
        return maxRed;
    }
    
    public void setMaxRed(int maxRed)
    {
        this.maxRed = maxRed;
    }
    
    public int getMinGreen()
    {
        return minGreen;
    }
    
    public void setMinGreen(int minGreen)
    {
        this.minGreen = minGreen;
    }
    
    public int getMaxGreen()
    {
        return maxGreen;
    }
    
    public void setMaxGreen(int maxGreen)
    {
        this.maxGreen = maxGreen;
    }
    
    public int getMinBlue()
    {
        return minBlue;
    }
    
    public void setMinBlue(int minBlue)
    {
        this.minBlue = minBlue;
    }
    
    public int getMaxBlue()
    {
        return maxBlue;
    }
    
    public void setMaxBlue(int maxBlue)
    {
        this.maxBlue = maxBlue;
    }
    
    public boolean isInRange(Color color)
    {
        if(color.getRed() >= this.getMinRed() &&
           color.getRed() <= this.getMaxRed() &&
           color.getGreen() >= this.getMinGreen() &&
           color.getGreen() <= this.getMaxGreen() &&            
           color.getBlue() >= this.getMinBlue() &&
           color.getBlue() <= this.getMaxBlue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
         
        stringBuffer.append("getMinRed(): ");
        stringBuffer.append(this.getMinRed());
        stringBuffer.append("\n");
        stringBuffer.append("getMaxRed(): ");
        stringBuffer.append(this.getMaxRed());
        stringBuffer.append("\n");
        stringBuffer.append("getMinGreen(): ");
        stringBuffer.append(this.getMinGreen());
        stringBuffer.append("\n");
        stringBuffer.append("getMaxGreen(): ");
        stringBuffer.append(this.getMaxGreen());
        stringBuffer.append("\n");
        stringBuffer.append("getMinBlue(): ");
        stringBuffer.append(this.getMinGreen());
        stringBuffer.append("\n");
        stringBuffer.append("getMaxBlue(): ");
        stringBuffer.append(this.getMaxBlue());
        
        return stringBuffer.toString();
    }
}
