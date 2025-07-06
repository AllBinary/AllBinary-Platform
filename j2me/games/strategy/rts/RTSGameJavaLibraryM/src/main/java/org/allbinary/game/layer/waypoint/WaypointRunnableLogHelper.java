/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.game.layer.waypoint;

import org.allbinary.game.layer.PathFindingLayerInterface;

/**
 *
 * @author User
 */
public class WaypointRunnableLogHelper {
    
    protected static final WaypointRunnableLogHelper instance = new WaypointRunnableLogHelper();

    /**
     * @return the instance
     */
    public static WaypointRunnableLogHelper getInstance() {
        return instance;
    }

    public void start(final PathFindingLayerInterface pathFindingLayerInterface) {

    }

    public void end(final PathFindingLayerInterface pathFindingLayerInterface) {

    }
    
}
