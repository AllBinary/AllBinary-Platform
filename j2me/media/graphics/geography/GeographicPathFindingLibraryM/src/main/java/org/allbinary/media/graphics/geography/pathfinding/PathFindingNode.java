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

import org.allbinary.logic.NullUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.SimpleGeographicMapCellPositionFactory;
import org.allbinary.string.CommonLabels;

public class PathFindingNode {

    public Object parent = NullUtil.getInstance().NULL_OBJECT;

    public GeographicMapCellPosition geographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;

    public PathFindingNode(final Object parent,
        final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception {
        this.parent = parent;
        this.setGeographicMapCellPositionP(geographicMapCellPosition);

        /*
      if(this.getParent() == null)
      {
         logUtil.put("No Parent", this, commonStrings.CONSTRUCTOR);
      }
         */
        if (this.geographicMapCellPosition == null) {
            throw new Exception("No GeographicMapCellPosition");
        }

    }

    public PathFindingNode getParentP() {
        return (PathFindingNode) parent;
    }

    public void setGeographicMapCellPositionP(final GeographicMapCellPosition geographicMapCellPosition) {
        this.geographicMapCellPosition = geographicMapCellPosition;
    }

    public String toString() {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(CommonLabels.getInstance().COLON_SEP);
        stringBuffer.append(" Path: ");
        stringBuffer.append(this.geographicMapCellPosition.toString());

        PathFindingNode pathFindingNode = this.getParentP();
        while (pathFindingNode != null) {
            stringBuffer.append(pathFindingNode.geographicMapCellPosition.toString());
            stringBuffer.append(CommonSeps.getInstance().SPACE);
            pathFindingNode = pathFindingNode.getParentP();
        }

        return stringBuffer.toString();
    }
}
