/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.input.form;

import org.allbinary.game.input.LayerPositionFinderInterface;
import org.allbinary.game.layer.waypoint.WaypointCellPositionHistory;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;

/**
 *
 * @author user
 */
public class AdvancedRTSGameLayerPositionFinder
implements LayerPositionFinderInterface
{
    private static final AdvancedRTSGameLayerPositionFinder
        instance = new AdvancedRTSGameLayerPositionFinder();

    /**
     * @return the instance
     */
    public static AdvancedRTSGameLayerPositionFinder getInstance()
    {
        return instance;
    }

    private AdvancedRTSGameLayerPositionFinder()
    {

    }

    public AllBinaryLayer getLayerInterface(GeographicMapCellPosition geographicMapCellPosition)
    {
        AllBinaryLayer layerInterface =
            DropCellPositionHistory.getInstance().getLayerInterface(geographicMapCellPosition);

        if(layerInterface == AllBinaryLayer.NULL_ALLBINARY_LAYER)
        {
            layerInterface =
                WaypointCellPositionHistory.getInstance().getLayerInterface(geographicMapCellPosition);
        }
        
        return layerInterface;
    }
}
