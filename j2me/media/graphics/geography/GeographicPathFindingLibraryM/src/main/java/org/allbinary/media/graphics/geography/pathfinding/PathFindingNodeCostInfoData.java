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

public class PathFindingNodeCostInfoData
{

    private static final PathFindingNodeCostInfoData instance = new PathFindingNodeCostInfoData();
    
    /**
     * @return the instance
     */
    public static PathFindingNodeCostInfoData getInstance() {
        return instance;
    }

   private PathFindingNodeCostInfoData()
   {
   }

   public int MAX_TOTAL_COST = Integer.MAX_VALUE;
   public int MAX_NODE_COST = Integer.MAX_VALUE / 10000; //SmallLongFactory.SPECIAL;
}
