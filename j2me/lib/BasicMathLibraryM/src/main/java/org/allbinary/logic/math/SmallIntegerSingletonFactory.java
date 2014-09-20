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

public class SmallIntegerSingletonFactory
{
    private static final SmallIntegerSingletonFactory instance = new SmallIntegerSingletonFactory();

    public static SmallIntegerSingletonFactory getInstance()
    {
        return instance;
    }
    
    public final int NEGATIVE_MAX = 500;
    public final int POSITIVE_MAX = 0x2D1; //500;

    private final Integer[] INTEGER_ARRAY = new Integer[NEGATIVE_MAX + POSITIVE_MAX];

    public int MIN = 0;
    public int lastMin = 0;
    public int lastNegativeMin = 0;

    public void init(int value, int negativeValue)
    {
        for (int index = value - 1; index >= lastMin; index--)
        {
            INTEGER_ARRAY[index + NEGATIVE_MAX] = new Integer(index);
        }

        for (int index = negativeValue - 1; index >= lastNegativeMin; index--)
        {
            INTEGER_ARRAY[index] = new Integer(-index);
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
        if (lastMin < POSITIVE_MAX || lastNegativeMin < NEGATIVE_MAX)
        {
            for (int index = POSITIVE_MAX - 1; index >= lastMin; index--)
            {
                INTEGER_ARRAY[index + NEGATIVE_MAX] = new Integer(index);
            }

            for (int index = NEGATIVE_MAX - 1; index >= lastNegativeMin; index--)
            {
                INTEGER_ARRAY[index] = new Integer(-index);
            }

            //LogUtil.put(LogFactory.getInstance("? " + NEGATIVE_INTEGER_ARRAY[5], "2", "?????????"));

            lastMin = POSITIVE_MAX;
            lastNegativeMin = NEGATIVE_MAX;
        }
    }

    private SmallIntegerSingletonFactory()
    {
    }

    /*
    public int highest = 0;
    public void updateStats(int index)
    {
            if(index > highest)
            {
                highest = index;
            }
    }
    */

    public Integer getInstance(int index)
    {
        //this.updateStats(index);

        return INTEGER_ARRAY[index + NEGATIVE_MAX];
    }

    public Integer createInstance(int index)
    {
        //this.updateStats(index);

        Integer integer = getInstance(index);
        
        if(integer == null)
        {
            integer = new Integer(index);
        }
        return integer;
    }
    
    /*
     * public Integer getInstance(int index) { if(index >= 0) { Integer
     * integer = INTEGER_ARRAY[index]; if(integer == null) { try { throw new
     * Exception("Out of Range: " + index); } catch(Exception e) {
     * LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION,
     * "SmallIntegerSingletonFactory", CommonStrings.getInstance().GET_INSTANCE, e)); } } return integer;
     * }else { Integer integer = NEGATIVE_INTEGER_ARRAY[-index]; if(integer ==
     * null) { try { throw new Exception("Out of Range: " + index); }
     * catch(Exception e) { LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION,
     * "SmallIntegerSingletonFactory", CommonStrings.getInstance().GET_INSTANCE, e)); } } return integer; }
     * }
     */
}
