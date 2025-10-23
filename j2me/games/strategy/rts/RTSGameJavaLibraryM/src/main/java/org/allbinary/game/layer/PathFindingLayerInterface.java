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
package org.allbinary.game.layer;

import org.allbinary.game.combat.destroy.DestroyableInterface;
import org.allbinary.game.identification.GroupInterfaceCompositeInterface;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.layer.waypoint.WaypointLayerInterface;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public interface PathFindingLayerInterface extends WaypointLayerInterface, GameKeyEventSourceInterface, GroupInterfaceCompositeInterface, DestroyableInterface {
  
    BasicArrayList getEndGeographicMapCellPositionList() throws Exception;
    
    boolean shouldHandleStartSameAsEnd();
    
    GeographicMapCellPositionAreaBase getGeographicMapCellPositionArea();
    
    SelectionHudPaintable getHudPaintable();
 
    void handleCost(PathFindingLayerInterface ownerLayer) throws Exception;
    
    PathFindingLayerInterface getParentLayer();
        
    RTSLayer2LogHelper getRTSLayer2LogHelper();

    void setTarget(final PathFindingLayerInterface targetGameLayer) throws Exception;
    
}
