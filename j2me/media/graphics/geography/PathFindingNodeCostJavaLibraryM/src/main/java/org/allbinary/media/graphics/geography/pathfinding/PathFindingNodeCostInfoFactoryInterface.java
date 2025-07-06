/*
* AllBinary Open License Version 1
* Copyright (c) 2006 AllBinary
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

import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public interface PathFindingNodeCostInfoFactoryInterface extends
    PathFindingNodeCostInfoFactoryBaseInterface
{
   public PathFindingNodeCostInfo getInstance(
      GeographicMapCellPosition comingFromGeographicMapCellPosition,
      //GeographicMapCellPosition startGeographicMapCellPosition, 
      //GeographicMapCellPosition endGeographicMapCellPosition, 
      GeographicMapCellPosition geographicMapCellPosition, 
      long costFromStart,
      long costToEnd)
      throws Exception;
      
   public PathFindingNodeCostInfo getInstance(
      GeographicMapCellPosition comingFromGeographicMapCellPosition,
      GeographicMapCellPosition geographicMapCellPosition);

   public long getTotalCost(
      GeographicMapCellPosition comingFromGeographicMapCellPosition,
      GeographicMapCellPosition geographicMapCellPosition);
}
