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

import java.util.Hashtable;
import org.allbinary.graphics.CellPosition;

public class CachingGeographicMapCellPositionFactory extends
        BasicGeographicMapCellPositionFactory
{
    protected CachingGeographicMapCellPositionFactory(
            BasicGeographicMap geographicMapInterface) throws Exception
    {
        super(geographicMapInterface);
    }

    @Override
    public GeographicMapCellPosition createInstance(final int i_column, final int i_row,
            final int width, final int height) throws Exception
    {
        final Hashtable hashtable = GeographicMapCellPositionFactory.getHashtable();
        
        final String cellPositionKey = CellPosition.toString(i_column, i_row);

        Object cellPositionCanBeNull =  hashtable.get(cellPositionKey);

        if (cellPositionCanBeNull == null)
        {
            cellPositionCanBeNull = this.geographicMapCellPositionFactoryInterface.getInstance(
                this.geographicMapInterface, i_column, i_row,
                            this.getColumns(), this.getRows(), width, height);
            geographicMapCellPositionArray[i_row][i_column] = (GeographicMapCellPosition) cellPositionCanBeNull;

            hashtable.put(cellPositionKey, cellPositionCanBeNull);
        }

        return (GeographicMapCellPosition) cellPositionCanBeNull;
    }
}
