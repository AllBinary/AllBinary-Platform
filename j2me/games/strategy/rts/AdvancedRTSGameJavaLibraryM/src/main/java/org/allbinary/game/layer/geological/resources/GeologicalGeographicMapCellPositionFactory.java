/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.game.layer.geological.resources;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;

/**
 *
 * @author user
 */
public class GeologicalGeographicMapCellPositionFactory
implements GeographicMapCellPositionFactoryInterface
{
    private final int[] resourcePerMapCellType;

    public GeologicalGeographicMapCellPositionFactory(int[] resourcePerMapCellType)
    {
        this.resourcePerMapCellType = resourcePerMapCellType;
    }

    public GeographicMapCellPosition getInstance(
        final BasicGeographicMap geographicMapInterface,
        int i_column, int i_row, int columns, int rows, int width, int height)
        throws Exception
    {
        AllBinaryTiledLayer tiledLayer =
            geographicMapInterface.getAllBinaryTiledLayer();

        int resources =
            this.resourcePerMapCellType[tiledLayer.getCell(i_column, i_row)] +
            MyRandomFactory.getInstance().getAbsoluteNextInt(1000);

        return new GeologicalGeographicMapCellPosition(
                    i_column, i_row, columns, rows, width, height,
                    new GeologicalResource(resources));
    }
}
