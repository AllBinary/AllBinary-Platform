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
package org.allbinary.media.graphics.geography.map;

/**
 *
 * @author User
 */
public class MultiGeographicMapBehavior //extends GeographicMapBehavior 
{
    
    public GeographicMapCellType[] getCellTypeAt(
            final BasicGeographicMap[] geographicMapInterfaceArray,
            final GeographicMapCellType[] geographicMapCellTypeArray,
            final GeographicMapCellPosition geographicMapCellPosition) throws Exception {

        final int size = geographicMapInterfaceArray.length;
        GeographicMapCellType cellType = null;
        for (int index = size; --index >= 0;) {
            cellType = geographicMapInterfaceArray[index].getCellTypeAt(geographicMapCellPosition);
            geographicMapCellTypeArray[index] = cellType;
        }

        return geographicMapCellTypeArray;
    }
    
}
