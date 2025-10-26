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

import org.allbinary.game.layer.NullPathFindingLayer;
import org.allbinary.game.layer.NullRTSLayer;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.waypoint.WaypointLayer;

/**
 *
 * @author user
 */
public class PrimaryWaypointHelper {

    private static final PrimaryWaypointHelper instance = new PrimaryWaypointHelper();

    /**
     * @return the instance
     */
    public static PrimaryWaypointHelper getInstance()
    {
        return instance;
    }

    private PathFindingLayerInterface waypointLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;

    private PrimaryWaypointHelper()
    {

    }

    /**
     * @return the waypointLayer
     */
    public PathFindingLayerInterface getWaypointLayer()
    {
        return waypointLayer;
    }

    /**
     * @param waypointLayer the waypointLayer to set
     */
    public void setWaypointLayer(WaypointLayer waypointLayer)
    {
        this.waypointLayer = waypointLayer;
    }


}
