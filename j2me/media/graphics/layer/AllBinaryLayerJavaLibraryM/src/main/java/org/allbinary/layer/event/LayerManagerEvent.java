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
package org.allbinary.layer.event;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.LayerManager;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class LayerManagerEvent extends AllBinaryEventObject
{
    private AllBinaryLayer layerInterface;
    private final String crud;

    public LayerManagerEvent(LayerManager layerManager, String crud)
    {
        super(layerManager);
        
        this.crud = crud;
    }

    /**
     * @return the layerInterface
     */
    public AllBinaryLayer getLayerInterface()
    {
        return layerInterface;
    }

    /**
     * @param layerInterface
     *            the layerInterface to set
     */
    public void setLayerInterface(AllBinaryLayer layerInterface)
    {
        this.layerInterface = layerInterface;
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("LayerManagerEvent: \n");
        stringBuffer.append("LayerInterface: ");
        stringBuffer.append(this.getLayerInterface().toString());
        stringBuffer.append("\nCRUD: ");
        stringBuffer.append(this.crud.toString());

        return stringBuffer.toString();
    }
}
