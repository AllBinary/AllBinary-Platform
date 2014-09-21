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
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public interface DropCellPositionGeneratorInterface
{
    String getName();

    void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
            BasicGeographicMap geographicMapInterface) throws Exception;

    void processTick(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception;

    boolean isDropAllowedAt(
            GeographicMapCellPosition geographicMapCellPosition)
            throws Exception;

    //protected void drop(AllBinaryLayerManager allBinaryLayerManager, int index) throws Exception;
}