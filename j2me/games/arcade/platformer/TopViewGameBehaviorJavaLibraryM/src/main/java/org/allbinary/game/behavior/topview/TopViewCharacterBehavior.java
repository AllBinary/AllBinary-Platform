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
package org.allbinary.game.behavior.topview;

import org.allbinary.direction.Direction;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.topview.BasicTopViewGeographicMapCellTypeFactory;

/**
 *
 * @author User
 */
public class TopViewCharacterBehavior {
    
    public void terrainEvent(final AllBinaryLayer layer, final Direction direction, final int x, final int y, final BasicGeographicMap[] geographicMapInterfaceArray,
            final GeographicMapCellPosition geographicMapCellPosition)
            throws Exception {
    }
    
    public void terrainMove(final AllBinaryLayer layer, final BasicGeographicMap[] geographicMapInterfaceArray, final int x, final int y) {
    
    }

    public boolean hasSolidBlock(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray) {
        
        final int size = geographicMapInterfaceArray.length;
        
        BasicTopViewGeographicMapCellTypeFactory basicTopViewGeographicMapCellTypeFactory;
        for(int index = 0; index < size; index++) {
            basicTopViewGeographicMapCellTypeFactory = (BasicTopViewGeographicMapCellTypeFactory) geographicMapInterfaceArray[index].getGeographicMapCellTypeFactory();
            if(basicTopViewGeographicMapCellTypeFactory.BLOCK_CELL_TYPE.isType(geographicMapCellTypeArray[index])) {
                return true;
            }
        }

        return false;
    }
    
}
