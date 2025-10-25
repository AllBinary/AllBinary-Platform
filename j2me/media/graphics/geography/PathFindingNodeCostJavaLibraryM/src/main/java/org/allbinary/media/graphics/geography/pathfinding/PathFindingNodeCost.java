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
import org.allbinary.logic.string.StringMaker;


import org.allbinary.string.CommonSeps;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.string.CommonLabels;

public class PathFindingNodeCost extends PathFindingNode implements Comparable<PathFindingNodeCost> {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public static final PathFindingNodeCost[][] NULL_PATH_FINDING_NODE_COST_ARRAY_ARRAY = new PathFindingNodeCost[0][0];

    private final CommonSeps commonSeps = CommonSeps.getInstance();

    public PathFindingNodeCostInfo pathFindingNodeCostInfoP;

    public PathFindingNodeCost(final Object parent,
        final GeographicMapCellPosition geographicMapCellPosition,
        final PathFindingNodeCostInfo pathFindingNodeCostInfo)
        throws Exception {
        super(parent, geographicMapCellPosition);

        this.pathFindingNodeCostInfoP = pathFindingNodeCostInfo;

        /*
      if(this.getParent() == null)
      {
         logUtil.put("No Parent", this, this.commonStrings.CONSTRUCTOR);
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
        return pathFindingNodeCostInfoP;
    }

    public void setPathFindingNodeCostInfo(PathFindingNodeCostInfo pathFindingNodeCostInfo) {
        this.pathFindingNodeCostInfoP = pathFindingNodeCostInfo;
    }

    @Override
    public int compareTo(PathFindingNodeCost pathFindingNodeCost) {
        return this.pathFindingNodeCostInfoP.compareTo(pathFindingNodeCost.pathFindingNodeCostInfoP);
    }
    
    public String toString() {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(CommonLabels.getInstance().COLON_SEP);
        stringBuffer.append(this.getPathFindingNodeCostInfo().toString());
        stringBuffer.append(" Path: ");
        stringBuffer.append(this.geographicMapCellPosition.toString());

        PathFindingNode pathFindingNode = (PathFindingNode) this.parent;
        while (pathFindingNode != null) {
            stringBuffer.append(pathFindingNode.geographicMapCellPosition.toString());
            stringBuffer.append(commonSeps.SPACE);
            pathFindingNode = (PathFindingNode) pathFindingNode.parent;
        }

        return stringBuffer.toString();
    }
}
