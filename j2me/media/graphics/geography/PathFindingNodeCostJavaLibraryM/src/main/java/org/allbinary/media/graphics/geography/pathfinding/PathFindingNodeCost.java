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

import org.allbinary.string.CommonSeps;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public class PathFindingNodeCost extends PathFindingNode implements Comparable<PathFindingNodeCost> {

    private final CommonSeps commonSeps = CommonSeps.getInstance();

    public PathFindingNodeCostInfo pathFindingNodeCostInfo;

    public PathFindingNodeCost(final PathFindingNodeCost parent,
        final GeographicMapCellPosition geographicMapCellPosition,
        final PathFindingNodeCostInfo pathFindingNodeCostInfo)
        throws Exception {
        super(parent, geographicMapCellPosition);

        this.pathFindingNodeCostInfo = pathFindingNodeCostInfo;

        /*
      if(this.getParent() == null)
      {
         LogUtil.put(LogFactory.getInstance("No Parent", this, this.commonStrings.CONSTRUCTOR));
      }
         */
        if (this.geographicMapCellPosition == null) {
            throw new Exception("No GeographicMapCellPosition");
        }

        if (this.getPathFindingNodeCostInfo() == null) {
            throw new Exception("No PathFindingNodeCostInfo");
        }
    }

    public PathFindingNodeCostInfo getPathFindingNodeCostInfo() {
        return pathFindingNodeCostInfo;
    }

    public void setPathFindingNodeCostInfo(PathFindingNodeCostInfo pathFindingNodeCostInfo) {
        this.pathFindingNodeCostInfo = pathFindingNodeCostInfo;
    }

    @Override
    public int compareTo(PathFindingNodeCost pathFindingNodeCost) {
        return this.pathFindingNodeCostInfo.compareTo(pathFindingNodeCost.pathFindingNodeCostInfo);
    }
    
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(": ");
        stringBuffer.append(this.getPathFindingNodeCostInfo().toString());
        stringBuffer.append(" Path: ");
        stringBuffer.append(this.geographicMapCellPosition.toString());

        PathFindingNode pathFindingNode = this.parent;
        while (pathFindingNode != null) {
            stringBuffer.append(pathFindingNode.geographicMapCellPosition.toString());
            stringBuffer.append(commonSeps.SPACE);
            pathFindingNode = pathFindingNode.parent;
        }

        return stringBuffer.toString();
    }
}
