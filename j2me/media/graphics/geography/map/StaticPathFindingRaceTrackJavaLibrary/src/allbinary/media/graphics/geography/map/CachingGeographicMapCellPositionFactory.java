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
package allbinary.media.graphics.geography.map;

public class CachingGeographicMapCellPositionFactory extends
        BasicGeographicMapCellPositionFactory
{
    protected CachingGeographicMapCellPositionFactory(
            BasicGeographicMap geographicMapInterface) throws Exception
    {
        super(geographicMapInterface);
    }

    public GeographicMapCellPosition createInstance(int i_column, int i_row,
            int width, int height) throws Exception
    {
        String cellPositionKey = GeographicMapCellPosition.toString(i_column, i_row);

        GeographicMapCellPosition cellPosition = 
            (GeographicMapCellPosition) GeographicMapCellPositionFactory.getHashtable().get(cellPositionKey);

        if (cellPosition == null)
        {
            cellPosition = this.geographicMapCellPositionFactoryInterface
                    .getInstance(this.geographicMapInterface, i_column, i_row,
                            columns, rows, width, height);
            geographicMapCellPositionArray[i_row][i_column] = cellPosition;

            GeographicMapCellPositionFactory.getHashtable().put(cellPositionKey, cellPosition);
        }

        return cellPosition;
    }
}
