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
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final SmallIntegerSingletonFactory instance = new SmallIntegerSingletonFactory();

    public static SmallIntegerSingletonFactory getInstance()
    {
        return instance;
    }
    
    public final int NEGATIVE_MAX = 500;
    public final int POSITIVE_MAX = 0x2D1; //500;

    private final Integer[] INTEGER_ARRAY = new Integer[NEGATIVE_MAX + POSITIVE_MAX];
    private final String[] STRING_ARRAY = new String[NEGATIVE_MAX + POSITIVE_MAX];

    public int MIN = 0;
    public int lastMin = 0;
    public int lastNegativeMin = 0;

    public void init(int value, int negativeValue)
    {
        for (int index = value - 1; index >= this.lastMin; index--)
        {
            this.INTEGER_ARRAY[index + this.NEGATIVE_MAX] = new Integer(index);
        }

        for (int index = negativeValue - 1; index >= this.lastNegativeMin; index--)
        {
            this.INTEGER_ARRAY[index] = new Integer(-index);
        }

        if (this.lastMin < value)
        {
            this.lastMin = value;
            this.MIN = value;
        }

        if (this.lastNegativeMin < negativeValue)
        {
            this.lastNegativeMin = negativeValue;
            //MIN = negativeValue;
        }
        //this.logUtil.putF("? " + NEGATIVE_INTEGER_ARRAY[5], stringUtil.EMPTY_STRING, "?????????");
    }

    public void init()
    {
        if (this.lastMin < this.POSITIVE_MAX || this.lastNegativeMin < this.NEGATIVE_MAX)
        {
            for (int index = this.POSITIVE_MAX - 1; index >= this.lastMin; index--)
            {
                this.INTEGER_ARRAY[index + this.NEGATIVE_MAX] = new Integer(index);
            }

            for (int index = this.NEGATIVE_MAX - 1; index >= this.lastNegativeMin; index--)
            {
                this.INTEGER_ARRAY[index] = new Integer(-index);
            }

            //this.logUtil.putF("? " + NEGATIVE_INTEGER_ARRAY[5], CommonPhoneStrings.getInstance().TWO, "?????????");

            this.lastMin = this.POSITIVE_MAX;
            this.lastNegativeMin = this.NEGATIVE_MAX;
        }
    }

    private SmallIntegerSingletonFactory()
    {
    }

    /*
    public int highest = 0;
    public void updateStats(int index)
    {
            if(index > this.highest)
            {
                this.highest = index;
            }
    }
    */

    public Integer getInstance(int index)
    {
        //this.updateStats(index);

        return this.INTEGER_ARRAY[index + this.NEGATIVE_MAX];
    }

    public Integer getInstanceNoThrow(int index)
    {
        //this.updateStats(index);

        if(index + this.NEGATIVE_MAX > this.INTEGER_ARRAY.length - 1) {
            return this.INTEGER_ARRAY[-1 + this.NEGATIVE_MAX];
        }
        
        return this.INTEGER_ARRAY[index + this.NEGATIVE_MAX];
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
    
    public String getString(int index) {
        final int i = index + NEGATIVE_MAX;
        if(this.STRING_ARRAY[i] == null) {
            this.STRING_ARRAY[i] = this.INTEGER_ARRAY[i].toString();
        }
        return this.STRING_ARRAY[i];
    }
    
    /*
     * public Integer getInstance(int index) { if(index >= 0) { Integer
     * integer = INTEGER_ARRAY[index]; if(integer == null) { try { throw new
     * Exception("Out of Range: " + index); } catch(Exception e) {
     * this.logUtil.put(commonStrings.EXCEPTION,
     * this, commonStrings.GET_INSTANCE, e); } } return integer;
     * }else { Integer integer = NEGATIVE_INTEGER_ARRAY[-index]; if(integer ==
     * null) { try { throw new Exception("Out of Range: " + index); }
     * catch(Exception e) { this.logUtil.put(commonStrings.EXCEPTION,
     * this, commonStrings.GET_INSTANCE, e); } } return integer; }
     * }
     */
}
