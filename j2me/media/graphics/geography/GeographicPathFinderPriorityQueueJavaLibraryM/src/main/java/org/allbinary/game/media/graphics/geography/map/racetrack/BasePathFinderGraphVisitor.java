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
package org.allbinary.game.media.graphics.geography.map.racetrack;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorBase;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNode;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
//1.4.2
public class BasePathFinderGraphVisitor
    extends PathFinderGraphVisitorBase
{
    protected BasicGeographicMap geographicMapInterface;

    protected final int edgeMinimum;
    //10
    protected final int minPathWeight;
    //8
    protected final int maxPathWeight;
    //100000

    public BasePathFinderGraphVisitor(
        final BasicGeographicMap geographicMapInterface,
        final int edgeMinimum, final int minPathWeight, final int maxPathWeight)
    {
        this.geographicMapInterface = geographicMapInterface;

        this.edgeMinimum = edgeMinimum;
        this.minPathWeight = minPathWeight;
        this.maxPathWeight = maxPathWeight;
    }

    public void visit(final Object graph, //SimpleWeightedGraph
        final BasicArrayList startPathFindingNodeList,
        final BasicArrayList endPathFindingNodeList)
        throws Exception
    {
    }

    public void fixPath(final BasicArrayList startPathFindingNodeList,
        final BasicArrayList endPathFindingNodeList, final BasicArrayList pathList)
        throws Exception
    {
        PathFindingNode startPathFindingNode;
        for (int index = startPathFindingNodeList.size() - 1; index >= 0; index--)
        {
            startPathFindingNode = (PathFindingNode) startPathFindingNodeList.get(index);
            
            pathList.remove(startPathFindingNode.geographicMapCellPosition);
            pathList.add(0, startPathFindingNode.geographicMapCellPosition);
        }
    }

    public boolean isValid(final Object graphPath) //GraphPath
    {
        return false;
    }

    public String getInvalidReason(final Object graphPath) //GraphPath
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
}
