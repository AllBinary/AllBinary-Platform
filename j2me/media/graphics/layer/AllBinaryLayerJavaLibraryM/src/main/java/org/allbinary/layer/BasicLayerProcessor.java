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
package org.allbinary.layer;

import jsinterop.annotations.JsType;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class BasicLayerProcessor
{
    @JsProperty
    public static final BasicLayerProcessor NULL_LAYER_PROCESSOR = new BasicLayerProcessor();
    
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayList list = new BasicArrayListD();

    @JsConstructor
    protected BasicLayerProcessor()
    {
    }

    @JsMethod
    public void add(AllBinaryLayer layerInterface)
    {
        if(!this.list.contains(layerInterface))
        {
            //if(layerInterface.getName().indexOf("debrish") >= 0) {
                //this.logUtil.put("Adding: " + layerInterface, this, "add", new Exception());
            //}
            this.list.add(layerInterface);
        }
    }

    @JsMethod
    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    @JsMethod
    public BasicArrayList getList()
    {
        return this.list;
    }
}
