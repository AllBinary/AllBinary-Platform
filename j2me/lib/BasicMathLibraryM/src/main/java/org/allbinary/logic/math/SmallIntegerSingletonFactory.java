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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

import org.allbinary.AndroidUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;


@JsType
public class SmallIntegerSingletonFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final SmallIntegerSingletonFactory instance = new SmallIntegerSingletonFactory();

    @JsMethod
    public static SmallIntegerSingletonFactory getInstance()
    {
        return SmallIntegerSingletonFactory.instance;
    }
    
    @JsProperty
    public final int NEGATIVE_MAX = 500;
    @JsProperty
    public final int POSITIVE_MAX = 0x2D1; //500;

    private final Integer[] INTEGER_ARRAY = new Integer[this.NEGATIVE_MAX + this.POSITIVE_MAX];
    private final String[] STRING_ARRAY = new String[this.NEGATIVE_MAX + this.POSITIVE_MAX];

    private int MIN = 0;
    private int lastMin = 0;
    private int lastNegativeMin = 0;

    @JsMethod
    public int getMin() {
        final int minAllowed = this.getMinAllowed();

        //J2ME only needs 23, Androids starts with 0x101, but needs larger for Input, PC needs 0x2D0 for input
        if(this.MIN <= minAllowed) {
            if (J2MEUtil.isJ2ME()) {
            } else if (AndroidUtil.isAndroid()) {
                final LogUtil logUtil = LogUtil.getInstance();
                logUtil.putF(new StringMaker().append("Android InputFactory was initialized before GameMidlet: ").appendint(this.MIN).toString(), this, "getMin");
                this.initWithRange(0x291, 6);
            } else {
                final LogUtil logUtil = LogUtil.getInstance();
                logUtil.putF(new StringMaker().append("InputFactory was initialized before GameMidlet or KeyFactoryInitializer - Currently this is occurs on JS build by TouchMotionGestureFactory constructor: ").appendint(this.MIN).toString(), this, "getMin");
                this.initWithRange(0x2D0, 6);
            }
        }

        if(this.MIN <= minAllowed) {
            final LogUtil logUtil = LogUtil.getInstance();
            logUtil.put("This means you loaded the InputFactory before determining the platform input size requirements.", this, "getMin", new Exception());
        }
        return this.MIN;
    }
    
    private int getMinAllowed() {
        if(J2MEUtil.isJ2ME()) {
            return 0;
        } else {
            if(AndroidUtil.isAndroid()) {
                return 0x101;
            } else {
                return 23;
            }
                    
        }
    }
    
    @JsMethod
    public void initWithRange(int value, int negativeValue)
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

        //final LogUtil logUtil = LogUtil.getInstance();
        //logUtil.putF("? " + this.INTEGER_ARRAY[5], this, "?????????");
    }

    @JsMethod
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
            
            this.lastMin = this.POSITIVE_MAX;
            this.MIN = this.lastMin;
            this.lastNegativeMin = this.NEGATIVE_MAX;

            //final LogUtil logUtil = LogUtil.getInstance();
            //logUtil.putF("? " + this.INTEGER_ARRAY[5], this, "?????????");            
        }
    }

    @JsConstructor
    private SmallIntegerSingletonFactory()
    {
        this.initWithRange(23, 0);
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

    @JsMethod
    public Integer getAt(int index)
    {
//        if(index >= 0 && index < 24) {
//            this.checkMe();
//        }
        //this.updateStats(index);

        return this.INTEGER_ARRAY[index + this.NEGATIVE_MAX];
    }

    @JsMethod
    public Integer getAtNoThrow(int index)
    {
//        if(index >= 0 && index < 24) {
//            this.checkMe();
//        }
        //this.updateStats(index);

        if(index + this.NEGATIVE_MAX > this.INTEGER_ARRAY.length - 1) {
            return this.INTEGER_ARRAY[-1 + this.NEGATIVE_MAX];
        }
        
        return this.INTEGER_ARRAY[index + this.NEGATIVE_MAX];
    }
    
    @JsMethod
    public Integer createInstance(int index)
    {
        //this.updateStats(index);

        Integer integer = this.getAt(index);
        
        if(integer == null)
        {
            integer = new Integer(index);
        }
        return integer;
    }
    
    @JsMethod
    public String getString(int index) {
         final int i = index + this.NEGATIVE_MAX;
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
     * this, this.commonStrings.GET_INSTANCE, e); } } return integer;
     * }else { Integer integer = NEGATIVE_INTEGER_ARRAY[-index]; if(integer ==
     * null) { try { throw new Exception("Out of Range: " + index); }
     * catch(Exception e) { this.logUtil.put(commonStrings.EXCEPTION,
     * this, this.commonStrings.GET_INSTANCE, e); } } return integer; }
     * }
     */
}
