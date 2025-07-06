/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.input;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;

/**
 *
 * @author user
 */
public class RTSGameLayerPositionFinder
implements LayerPositionFinderInterface
{
    private static final RTSGameLayerPositionFinder instance =
        new RTSGameLayerPositionFinder();

    /**
     * @return the instance
     */
    public static RTSGameLayerPositionFinder getInstance()
    {
        return instance;
    }

    private RTSGameLayerPositionFinder()
    {

    }

    public AllBinaryLayer getLayerInterface(GeographicMapCellPosition geographicMapCellPosition)
    {
        return DropCellPositionHistory.getInstance().getLayerInterface(geographicMapCellPosition);
    }
}
