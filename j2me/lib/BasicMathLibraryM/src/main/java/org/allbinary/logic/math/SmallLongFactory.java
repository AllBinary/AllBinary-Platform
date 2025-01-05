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

public class SmallLongFactory
{
    private static final SmallLongFactory instance = new SmallLongFactory();

    public static SmallLongFactory getInstance()
    {
        return instance;
    }
    
    public final int NEGATIVE_MAX = 500;
    public final int POSITIVE_MAX = 0x2D1; //500;

    private final Long[] INTEGER_ARRAY = new Long[NEGATIVE_MAX + POSITIVE_MAX];
    private final String[] STRING_ARRAY = new String[NEGATIVE_MAX + POSITIVE_MAX];

    public int MIN = 0;
    public int lastMin = 0;
    public int lastNegativeMin = 0;

    public void init(int value, int negativeValue)
    {
        for (int index = value - 1; index >= lastMin; index--)
        {
            INTEGER_ARRAY[index + NEGATIVE_MAX] = new Long(index);
        }

        for (int index = negativeValue - 1; index >= lastNegativeMin; index--)
        {
            INTEGER_ARRAY[index] = new Long(-index);
        }

        if (lastMin < value)
        {
            lastMin = value;
            MIN = value;
        }

        if (lastNegativeMin < negativeValue)
        {
            lastNegativeMin = negativeValue;
            //MIN = negativeValue;
        }
        //LogUtil.put(LogFactory.getInstance("? " + NEGATIVE_INTEGER_ARRAY[5], "", "?????????"));
    }

    public void init()
    {
        if(INTEGER_ARRAY[0] == null) {
            if (lastMin < POSITIVE_MAX || lastNegativeMin < NEGATIVE_MAX) {
                for (int index = POSITIVE_MAX - 1; index >= lastMin; index--) {
                    INTEGER_ARRAY[index + NEGATIVE_MAX] = new Long(index);
                }

                for (int index = NEGATIVE_MAX - 1; index >= lastNegativeMin; index--) {
                    INTEGER_ARRAY[index] = new Long(-index);
                }

                //LogUtil.put(LogFactory.getInstance("? " + NEGATIVE_INTEGER_ARRAY[5], "2", "?????????"));
                lastMin = POSITIVE_MAX;
                lastNegativeMin = NEGATIVE_MAX;
            }
        }
    }

    private SmallLongFactory()
    {
    }

    public Long getInstanceNoThrow(long index)
    {
        //this.updateStats(index);

        if(index + NEGATIVE_MAX > this.INTEGER_ARRAY.length - 1) {
            return INTEGER_ARRAY[-1 + NEGATIVE_MAX];
        }
        
        return INTEGER_ARRAY[((int) index) + NEGATIVE_MAX];
    }
        
    public String getString(int index) {
        final int i = index + NEGATIVE_MAX;
        if(STRING_ARRAY[i] == null) {
            STRING_ARRAY[i] = this.INTEGER_ARRAY[i].toString();
        }
        return STRING_ARRAY[i];
    }
    
}
