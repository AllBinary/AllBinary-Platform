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
package org.allbinary.input;

import jsinterop.annotations.JsType;


import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class AllBinarySensor
implements AllBinarySensorListener
{
    @JsProperty
    public static final AllBinarySensor NULL_ALLBINARY_SENSOR = new AllBinarySensor();
    
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private static int max = 100;
    
    @JsConstructor
    protected AllBinarySensor()
    {
        
    }
    
    @JsMethod
    public void init()
    {
    }

    @JsMethod
    public void update()
    throws Exception
    {
        
    }
    
    @JsMethod
    public static void setMax(int max)
    {
        AllBinarySensor.max = max;
    }
    
    @JsMethod
    public static int getMax()
    {
        return AllBinarySensor.max;
    }
    
    @JsMethod
    public int getId()
    {
        return AllBinarySensor.getMax();
    }
    
    @JsMethod
    public void shutdown()
    {
        this.logUtil.putF(this.commonStrings.START, this, "shutdown");
    }

    @Override    
    @JsMethod
    public void onSensorChange()
    {
        ForcedLogUtil.log(this.commonStrings.NOT_IMPLEMENTED, this);
    }
}
