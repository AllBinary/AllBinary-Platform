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
package org.allbinary.logic.math;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class PrimitiveLongUtil
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private int powerOfTen;
    private int maxValue;
    
    private int maxDigits;

    private final char[] charArray;
    private int currentTotalDigits;

    private final PrimitiveLongSingleton primitiveLongSingleton = PrimitiveLongSingleton.getInstance();
    
    public PrimitiveLongUtil(int powerOfTen)
    {
        this(powerOfTen, false);
        
        if(powerOfTen % 10 != 0)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR,
                    new Exception("Max must be power of 10")));
        }
    }

    public PrimitiveLongUtil(int powerOfTen, boolean throwException)
    {
        this.maxDigits = MathUtil.getInstance().getTotalDigits(powerOfTen);
        
        this.powerOfTen = powerOfTen;
        this.maxValue = (powerOfTen * 10) - 1;
        
        this.charArray = new char[this.maxDigits];
    }
    
    /*
    private final StringMaker stringBuffer = new StringMaker();
    public String getString(int value)
    {
        if(value == 0)
        {
            return NUMBER_STRING_ARRAY[0];
        }
                            
        if(value > maxValue)
        {
            return PrimitiveLongUtil.UNK;
        }
        
        stringBuffer.delete(0, stringBuffer.length());
        int div = powerOfTen;
        int lastValue = 0;
        while(div >= 1)
        {
            int digit = (int) (value - lastValue) / div;
            if(digit >= 0 && digit < 10)
            {
                if(digit == 0 && stringBuffer.length() == 0)
                {
                 
                }
                else
                {
                    stringBuffer.append(NUMBER_STRING_ARRAY[digit]);
                }
                lastValue += digit * div;
            }
            div = div / 10;
        }
        return stringBuffer.toString();
    }
    */

    public char[] getCharArray(int value)
    {
        if(value < 10)
        {
            this.setCurrentTotalDigits(1);
            return this.primitiveLongSingleton.NUMBER_CHAR_ARRAYS[value];
        }
                            
        if(value > maxValue)
        {
            this.setCurrentTotalDigits(3);
            return this.primitiveLongSingleton.UNK;
        }
        
        int index = 0;
        int div = powerOfTen;
        int lastValue = 0;
        while(div >= 1)
        {
            int digit = (value - lastValue) / div;
            if(digit >= 0 && digit < 10)
            {
                if(digit == 0 && index == 0)
                {
                 
                }
                else
                {
                    this.charArray[index++] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[digit];
                }
                lastValue += digit * div;
            }
            div = div / 10;
        }
        
        this.setCurrentTotalDigits(index);
        
        /*
        while(index < this.maxDigits)
        {
            this.charArray[index] = ' ';
            index++;
        }
        */
        
        return this.charArray;
    }

    private void setCurrentTotalDigits(int currentTotalDigits)
    {
        this.currentTotalDigits = currentTotalDigits;
    }

    public int getCurrentTotalDigits()
    {
        return currentTotalDigits;
    }
    
    public int getMaxDigits()
    {
        return maxDigits;
    }
}
