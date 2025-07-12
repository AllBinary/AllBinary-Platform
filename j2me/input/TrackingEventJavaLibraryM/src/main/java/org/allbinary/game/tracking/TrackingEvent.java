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

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class TrackingEvent extends AllBinaryEventObject
{
    private AllBinaryLayer layerInterface;

    public TrackingEvent()
    {
        super(TrackingEventHandler.getInstance());
    }

    public TrackingEvent(AllBinaryLayer allBinaryLayerInterface)
    {
        super(allBinaryLayerInterface);

        this.setLayerInterface(allBinaryLayerInterface);
    }

    public void init(Object object)
    {
       this.setSource(object);
    }

    public AllBinaryLayer getLayerInterface()
    {
        return layerInterface;
    }

    private void setLayerInterface(AllBinaryLayer layerInterface)
    {
        this.layerInterface = layerInterface;
    }

    public void setLayerInterfaceForCircularStaticPool(AllBinaryLayer layerInterface)
    {
        this.layerInterface = layerInterface;
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("TrackingEvent: \n");
        stringBuffer.append("LayerInterface: ");
        stringBuffer.append(this.layerInterface.toString());

        return stringBuffer.toString();
    }
}
