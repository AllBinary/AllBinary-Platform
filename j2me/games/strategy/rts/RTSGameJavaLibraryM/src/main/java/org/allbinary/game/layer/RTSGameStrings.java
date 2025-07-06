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

package org.allbinary.game.layer;

public class RTSGameStrings
{
    private static final RTSGameStrings instance = new RTSGameStrings();
    
    public static RTSGameStrings getInstance()
    {
        return instance;
    }
    
    public final String DRAG_TO_SPOT = "Please Drag";
    public final String NOT_YOURS = "Not Yours";
    public final String NEW_UNIT = "New Unit";
    public final String NEW_WAYPOINT = "New Waypoint";
    public final String BUILDING = "Building";
    public final String UPGRADE = "Upgrading";
    public final String DOWNGRADE = "Downgrading";
    public final String NO_MONEY = "Not Enough Money";
    public final String SPOT_TAKEN = "Spot Taken";
    public final String BUILDING_COLLISION = "Building Collision";
    public final String STRUCTURE_TO_CLOSE = "Building To Close";
    public final String MAP_EDGE = "Map Edge";
    public final String ROAD_COLLISION = "Road Collision";
    public final String SELECT_BUILD_SPOT = "Select Build Spot";
    public final String BUILD_ON_PATH = "Not On Path";
    public final String DRAGGABLE = "Draggable";
}
