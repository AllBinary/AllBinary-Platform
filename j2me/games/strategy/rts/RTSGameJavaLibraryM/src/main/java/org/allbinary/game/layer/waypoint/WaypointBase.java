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

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.media.audio.NoSound;
import org.allbinary.media.audio.Sound;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.pathfinding.MultipassState;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class WaypointBase 
    implements EventListenerInterface {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public static WaypointBase NULL_WAYPOINT_BASE = new WaypointBase(NoSound.getInstance());

    private final BasicArrayList connectedWaypointList = new BasicArrayList();
    
    private final Sound sound;
    
    protected AllBinaryGameLayerManager allBinaryGameLayerManagerP = AllBinaryGameLayerManager.NULL_ALLBINARY_LAYER_MANAGER;
    
    public WaypointBase(final Sound sound) {
        this.sound = sound;
    }

    /**
     * @return the sound
     */
    public Sound getSound()
    {
        return sound;
    }
    
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {
        this.allBinaryGameLayerManagerP = allBinaryGameLayerManager;
    }
    
    /**
     * @return the list
     */
    public BasicArrayList getConnectedWaypointList()
    {
        return connectedWaypointList;
    }
 
    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    public PathFindingInfo getPathFindingInfo(final GeographicMapCellPosition geographicMapCellPosition) throws Exception {
        throw new RuntimeException();
    }

    public BasicArrayList getPathsList(final GeographicMapCellPosition geographicMapCellPosition, final PathFindingInfo pathFindingInfo, final MultipassState multipassState)
        throws Exception {
        throw new RuntimeException();
    }

    public BasicArrayList getPathsList(final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception {
        throw new RuntimeException();
    }
        
    public BasicArrayList getPathsListFromCacheOnly(final GeographicMapCellPosition geographicMapCellPosition) throws Exception {
        throw new RuntimeException();
    }

    public void visit(final PathFindingLayerInterface unitLayer) throws Exception {
        
    }

    public void reset() {
        
    }
    
    public int getType()
    {
        return 0;
    }
    
}
