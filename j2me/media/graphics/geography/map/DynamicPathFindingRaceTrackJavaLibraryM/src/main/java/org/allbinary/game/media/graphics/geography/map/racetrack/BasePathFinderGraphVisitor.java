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

import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorBase;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNode;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class BasePathFinderGraphVisitor<V, E>
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
            BasicGeographicMap geographicMapInterface,
        int edgeMinimum, int minPathWeight, int maxPathWeight)
    {
        this.geographicMapInterface = geographicMapInterface;

        this.edgeMinimum = edgeMinimum;
        this.minPathWeight = minPathWeight;
        this.maxPathWeight = maxPathWeight;
    }

    public void visit(SimpleWeightedGraph graph,
        BasicArrayList startPathFindingNodeList,
        BasicArrayList endPathFindingNodeList)
        throws Exception
    {
    }

    public void fixPath(BasicArrayList startPathFindingNodeList,
        BasicArrayList endPathFindingNodeList, BasicArrayList pathList)
        throws Exception
    {
        for (int index = startPathFindingNodeList.size() - 1; index >= 0; index--)
        {
            PathFindingNode startPathFindingNode = 
                (PathFindingNode) startPathFindingNodeList.get(index);
            
            pathList.remove(startPathFindingNode.geographicMapCellPosition);
            pathList.add(0, startPathFindingNode.geographicMapCellPosition);
        }
    }

    public boolean isValid(GraphPath graphPath)
    {
        if (graphPath.getEdgeList().size() > edgeMinimum &&
            graphPath.getWeight() < maxPathWeight &&
            graphPath.getWeight() > minPathWeight)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getInvalidReason(GraphPath graphPath)
    {
        final StringMaker stringBuffer = new StringMaker();
        
        if(graphPath.getEdgeList().size() < edgeMinimum)
        {
            stringBuffer.append("Ignoring Small: " + graphPath.getEdgeList().size());
        }

        if(graphPath.getWeight() <= minPathWeight)
        {
            stringBuffer.append(" Low Weighted Path: " + graphPath.getWeight());
        }

        if(graphPath.getWeight() >= maxPathWeight)
        {
            stringBuffer.append(" To High Weighted Path: " + graphPath.getWeight());
        }

        return stringBuffer.toString();
    }
}
