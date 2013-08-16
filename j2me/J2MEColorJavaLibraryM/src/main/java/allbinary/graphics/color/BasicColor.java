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
package allbinary.graphics.color;

import abcs.logic.basic.string.StringMaker;
import abcs.logic.basic.string.StringUtil;

public class BasicColor
{
    // private int id;
    private final String name;
    private final int value;

    private final float redComponent;
    private final float greenComponent;
    private final float blueComponent;
    private final float alphaComponent;

    public final short red;
    public final short green;
    public final short blue;
    public final short alpha;
    
    private final BasicColorUtil basicColorUtil = BasicColorUtil.getInstance();
    /*
     * BasicColor.init(true, true);
     * 
     * public static void setAlpha(boolean alpha) { BasicColor.alpha = alpha; }
     * 
     * public static void init(boolean alpha, boolean ffOpaque) {
     * BasicColor.setAlpha(alpha); BasicColor.ffOpaque = ffOpaque; }
     */

    public BasicColor(int value)
    {
        this(value, StringUtil.getInstance().EMPTY_STRING);
        // this.id = index++;

    }

    BasicColor(int value, String name)
    {
        this(BasicColorUtil.getInstance().ALPHA, value, name);
    }
    
    BasicColor(int alphaValue, int value, String name)
    {

        this.name = name;

        int tempValue;

        if (this.basicColorUtil.isAlpha)
        {
            if (this.basicColorUtil.ffOpaque)
            {
                tempValue = alphaValue | value;
            }
            else
            {
                tempValue = 0x00000000 | value;
            }
        }
        else
        {
            tempValue = value;
        }

        int alphaInt = ((tempValue >> 24) & 255);
        this.alpha = (short) alphaInt;
        this.alphaComponent = ((float) alphaInt) / 255;

        int redInt = ((tempValue >> 16) & 255);
        this.red = (short) redInt;
        this.redComponent = ((float) redInt) / 255;

        int greenInt = ((tempValue >> 8 ) & 255);
        this.green = (short) greenInt;
        this.greenComponent = ((float) greenInt) / 255;

        int blueInt = ((tempValue) & 255);
        this.blue = (short) blueInt;
        this.blueComponent = ((float) blueInt) / 255;

        this.value = tempValue;

        /*
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Alpha: ");
        stringBuffer.append(alpha);
        stringBuffer.append(" ffOpaque: ");
        stringBuffer.append(ffOpaque);
        stringBuffer.append(" Value: ");
        stringBuffer.append(Integer.toHexString(this.intValue()));
        stringBuffer.append(" a: ");
        stringBuffer.append(this.alphaComponent);
        stringBuffer.append(" r: ");
        stringBuffer.append(this.redComponent);
        stringBuffer.append(" g: ");
        stringBuffer.append(this.greenComponent);
        stringBuffer.append(" b: ");
        stringBuffer.append(this.blueComponent);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(),this, CommonStrings.getInstance().CONSTRUCTOR));
        */
    }

    public int intValue()
    {
        return value;
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("BasicColor: ");
        stringBuffer.append("Red: ");
        stringBuffer.append(this.red);
        stringBuffer.append(" Green: ");
        stringBuffer.append(this.green);
        stringBuffer.append(" Blue: ");
        stringBuffer.append(this.blue);

        return stringBuffer.toString();
    }
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    public float getRedComponent()
    {
        return redComponent;
    }

    public float getGreenComponent()
    {
        return greenComponent;
    }

    public float getBlueComponent()
    {
        return blueComponent;
    }

    public float getAlphaComponent()
    {
        return alphaComponent;
    }

    //public int getId() { return id; }
}
