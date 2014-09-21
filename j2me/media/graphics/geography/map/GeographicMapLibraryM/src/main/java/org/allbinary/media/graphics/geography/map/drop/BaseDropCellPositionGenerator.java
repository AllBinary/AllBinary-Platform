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
package org.allbinary.media.graphics.geography.map.drop;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;

public class BaseDropCellPositionGenerator
implements DropCellPositionGeneratorInterface
{
    /* (non-Javadoc)
     * @see allbinary.media.graphics.geography.map.racetrack.drop.DropCellPositionGeneratorInterface#getName()
     */
    public String getName()
    {
        return this.getClass().getName();
    }

    public void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
            BasicGeographicMap geographicMapInterface) throws Exception
    {
        
    }

    public boolean isDropAllowedAt(
            GeographicMapCellPosition geographicMapCellPosition)
            throws Exception
    {
        return true;
    }

    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        
    }
}
