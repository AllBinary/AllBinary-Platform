/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.media.graphics.geography.map;

/**
 *
 * @author user
 */
public class SimpleGeographicMapCellPositionFactory
implements GeographicMapCellPositionFactoryInterface
{
    public static final GeographicMapCellPosition NULL_GEOGRAPHIC_MAP_CELL_POSITION = new GeographicMapCellPosition(-1, -1, -1, -1, -1, -1);

    @Override
    public GeographicMapCellPosition getInstance(
            final BasicGeographicMap geographicMapInterface,
        final int i_column, final int i_row, final int columns, final int rows, final int width, final int height)
    {
        return new GeographicMapCellPosition(i_column, i_row, columns, rows, width, height);
    }
}
