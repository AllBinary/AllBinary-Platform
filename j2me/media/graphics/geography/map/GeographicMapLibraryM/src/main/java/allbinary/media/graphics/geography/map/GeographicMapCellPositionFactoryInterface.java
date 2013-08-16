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

/**
 *
 * @author user
 */
public interface GeographicMapCellPositionFactoryInterface {

    GeographicMapCellPosition getInstance(
        final BasicGeographicMap geographicMapInterface,
        int i_column, int i_row, int columns, int rows, int width, int height)
        throws Exception;
}
