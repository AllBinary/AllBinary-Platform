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

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;

/**
 *
 * @author User
 */
public interface TopViewCharacterInterface {
 
    void terrainMove(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final int x, final int y);

    void terrainEvent(final int x, final int y, 
            final BasicGeographicMap[] geographicMapInterfaceArray,
            final GeographicMapCellType[] geographicMapCellTypeArray,
            final GeographicMapCellPosition geographicMapCellPosition) throws Exception;

    void move();
    void upp();
    void leftp();
    void rightp();

    void reset() throws Exception;
}
