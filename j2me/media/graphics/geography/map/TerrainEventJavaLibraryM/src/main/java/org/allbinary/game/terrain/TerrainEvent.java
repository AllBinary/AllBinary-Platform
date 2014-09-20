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
package org.allbinary.game.terrain;

import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

public class TerrainEvent extends AllBinaryEventObject
{
    private BasicTerrainInfo basicTerrainInfo;

    public TerrainEvent()
    {
        super((Object) null);
    }

    public TerrainEvent(BasicTerrainInfo basicTerrainInfo)
    {
        super(basicTerrainInfo);
        this.setBasicTerrainInfo(basicTerrainInfo);
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("TerrainEvent: \n");
        stringBuffer.append("LayerInterface: ");
        stringBuffer.append(this.basicTerrainInfo.toString());

        return stringBuffer.toString();
    }

    public BasicTerrainInfo getBasicTerrainInfo()
    {
        return basicTerrainInfo;
    }

    public void setBasicTerrainInfo(BasicTerrainInfo basicTerrainInfo)
    {
        this.basicTerrainInfo = basicTerrainInfo;
    }

    public void setBasicTerrainInfoForCircularStaticPool(
            BasicTerrainInfo basicTerrainInfo)
    {
        this.basicTerrainInfo = basicTerrainInfo;
    }
}
