/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class GeographicMapCellPositionAreaBase implements GeographicMapCellPositionAreaInterface {
    
    public static final GeographicMapCellPositionAreaBase NULL_GEOGRPAHIC_MAP_POSITION_AREA_BASE = new GeographicMapCellPositionAreaBase();
    
    @Override
    public GeographicMapCellPosition getNextSurroundingGeographicMapCellPosition() {
        throw new RuntimeException();
    }

    @Override
    public BasicArrayList getOccupyingGeographicMapCellPositionList() {
        throw new RuntimeException();
    }

    @Override
    public BasicArrayList getSurroundingGeographicMapCellPositionList() {
        throw new RuntimeException();
    }

    @Override
    public void update(final BasicGeographicMap geographicMapInterface) throws Exception {
        throw new RuntimeException();
    }
    
}
