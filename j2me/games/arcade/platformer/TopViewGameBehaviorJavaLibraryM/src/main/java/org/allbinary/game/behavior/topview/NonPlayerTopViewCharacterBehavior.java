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

import org.allbinary.media.graphics.geography.map.MultiGeographicMapBehavior;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.terrain.BasicTerrainInfo;
import org.allbinary.game.terrain.TerrainEvent;
import org.allbinary.game.terrain.TerrainEventCircularStaticPool;
import org.allbinary.game.terrain.TerrainEventHandler;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.math.AngleFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;

/**
 *
 * @author User
 */
public class NonPlayerTopViewCharacterBehavior extends TopViewCharacterBehavior {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private final MultiGeographicMapBehavior geographicMapBehavior = new MultiGeographicMapBehavior();

    private final BasicTerrainInfo CLIFF = new BasicTerrainInfo(AngleFactory.getInstance().DOWN);

    public void terrainEvent(final AllBinaryLayer layer, final Direction direction, final int x, final int y, 
            final BasicGeographicMap[] geographicMapInterfaceArray, 
            final GeographicMapCellType[] geographicMapCellTypeArray, 
            final GeographicMapCellPosition geographicMapCellPosition)
            throws Exception {

        final int maxColumns = geographicMapInterfaceArray[0].getAllBinaryTiledLayer().getColumns();

        if (geographicMapCellPosition.getColumn() > 0 && geographicMapCellPosition.getColumn() < maxColumns) {

            GeographicMapCellPosition nextTerrainGeographicMapCellPosition = null;

            final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory
                    = geographicMapInterfaceArray[0].getGeographicMapCellPositionFactory();

            if (direction == DirectionFactory.getInstance().LEFT) {
                nextTerrainGeographicMapCellPosition
                        = geographicMapCellPositionFactory.getInstance(
                                geographicMapCellPosition.getColumn() - 1,
                                geographicMapCellPosition.getRow());
            } else if (direction == DirectionFactory.getInstance().RIGHT) {
                nextTerrainGeographicMapCellPosition
                        = geographicMapCellPositionFactory.getInstance(
                                geographicMapCellPosition.getColumn() + 1,
                                geographicMapCellPosition.getRow());
            }

            geographicMapBehavior.getCellTypeAt(geographicMapInterfaceArray, geographicMapCellTypeArray, nextTerrainGeographicMapCellPosition);
            final boolean hasSolidBlock = this.hasSolidBlock(geographicMapInterfaceArray, geographicMapCellTypeArray);

            if (!hasSolidBlock) {
                // logUtil.put("Cliff Found: " +
                // nextTerrainGeographicMapCellPosition, this, "terrainEvent");
                final TerrainEvent terrainEvent = TerrainEventCircularStaticPool.getInstance().getInstance(this.CLIFF);
                TerrainEventHandler.getInstance(layer).fireEvent(terrainEvent);
            }
        }

    }
    
    public void terrainMove(final AllBinaryLayer layer, final BasicGeographicMap[] geographicMapInterfaceArray, final int x, final int y) {
        layer.move(x, y);
    }
        
}
