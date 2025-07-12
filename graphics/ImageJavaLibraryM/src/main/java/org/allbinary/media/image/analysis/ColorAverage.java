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

import org.allbinary.logic.string.StringMaker;

public class ColorAverage
{
    private float avgRed;
    private float avgGreen;
    private float avgBlue;
    
    public ColorAverage()
    {
        this.setAvgRed(0);
        this.setAvgGreen(0);
        this.setAvgBlue(0);
    }
        
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
         
        stringBuffer.append("getAvgRed(): ");
        stringBuffer.append(this.getAvgRed());
        stringBuffer.append("\n");
        stringBuffer.append("getAvgRed(): ");
        stringBuffer.append(this.getAvgRed());
        stringBuffer.append("\n");
        stringBuffer.append("getAvgGreen(): ");
        stringBuffer.append(this.getAvgGreen());
        stringBuffer.append("\n");
        
        return stringBuffer.toString();
    }

    public float getAvgRed()
    {
        return avgRed;
    }

    public void setAvgRed(float avgRed)
    {
        this.avgRed = avgRed;
    }

    public float getAvgGreen()
    {
        return avgGreen;
    }

    public void setAvgGreen(float avgGreen)
    {
        this.avgGreen = avgGreen;
    }

    public float getAvgBlue()
    {
        return avgBlue;
    }

    public void setAvgBlue(float avgBlue)
    {
        this.avgBlue = avgBlue;
    }
}
