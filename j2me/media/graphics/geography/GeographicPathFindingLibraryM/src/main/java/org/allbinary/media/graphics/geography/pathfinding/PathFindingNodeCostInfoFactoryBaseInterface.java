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
package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public interface PathFindingNodeCostInfoFactoryBaseInterface {

    void create(
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition comingFromGeographicMapCellPosition,
        //GeographicMapCellPosition startGeographicMapCellPosition, 
        //GeographicMapCellPosition endGeographicMapCellPosition, 
        final GeographicMapCellPosition geographicMapCellPosition,
        final int costFromStart,
        final int costToEnd)
        throws Exception;

    int getTotalCost(
        final BasicGeographicMap geographicMapInterface,
        final GeographicMapCellPosition comingFromGeographicMapCellPosition,
        final GeographicMapCellPosition geographicMapCellPosition) throws Exception;
}
