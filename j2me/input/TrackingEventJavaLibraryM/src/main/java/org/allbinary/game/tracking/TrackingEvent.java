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
package org.allbinary.game.tracking;

import jsinterop.annotations.JsType;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

@JsType
public class TrackingEvent extends AllBinaryEventObject
{
    private AllBinaryLayer layerInterface = AllBinaryLayer.NULL_ALLBINARY_LAYER;

    @JsConstructor
    public TrackingEvent(Object allBinaryLayerInterface)
    {
        super(allBinaryLayerInterface);

        if(allBinaryLayerInterface != TrackingEventHandler.getInstance()) {
            this.setLayerInterface((AllBinaryLayer) allBinaryLayerInterface);
        }
    }

    @JsMethod
    public void init(Object object)
    {
       this.setSource(object);
    }

    @JsMethod
    public AllBinaryLayer getLayerInterface()
    {
        return this.layerInterface;
    }

    @JsMethod
    private void setLayerInterface(AllBinaryLayer layerInterface)
    {
        this.layerInterface = layerInterface;
    }

    @JsMethod
    public void setLayerInterfaceForCircularStaticPool(AllBinaryLayer layerInterface)
    {
        this.layerInterface = layerInterface;
    }

    @JsMethod
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("TrackingEvent: \n");
        stringBuffer.append("LayerInterface: ");
        stringBuffer.append(this.layerInterface.toString());

        return stringBuffer.toString();
    }
}
