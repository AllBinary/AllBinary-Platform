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
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.CellPosition;

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMap;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingNode;

/**
 *
 * @author user
 */
public class PathFinderGraphHackVisitor<V, E> extends BasePathFinderGraphVisitor<V, E>
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    //RaceTrackGeographicMap raceTrackGeographicMap
    public PathFinderGraphHackVisitor(
        final BasicGeographicMap geographicMapInterface,
       int edgeMinimum, int minPathWeight, int maxPathWeight)
    {
        super(geographicMapInterface, edgeMinimum, minPathWeight, maxPathWeight);
    }

    public void visit(SimpleWeightedGraph graph,
       BasicArrayList startPathFindingNodeList,
       BasicArrayList endPathFindingNodeList)
       throws Exception
    {
        this.fixStart(graph, startPathFindingNodeList);

        this.fixEnd(graph, endPathFindingNodeList);

        this.fixOverPassEdges(graph);
    }

    private void fixStart(SimpleWeightedGraph<CellPosition, DefaultWeightedEdge> graph,
       BasicArrayList startPathFindingNodeList)
       throws Exception
    {
        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
           this.geographicMapInterface.getGeographicMapCellPositionFactory();

        AllBinaryTiledLayer tiledLayer =
            this.geographicMapInterface.getAllBinaryTiledLayer();

        for (int index = startPathFindingNodeList.size() - 1; index >= 0; index--)
        {
            PathFindingNode startPathFindingNode =
               (PathFindingNode) startPathFindingNodeList.get(index);

            GeographicMapCellPosition geographicMapCellPosition =
               startPathFindingNode.geographicMapCellPosition;

            int column = geographicMapCellPosition.getColumn();
            int row = geographicMapCellPosition.getRow();

            //If going beyond the tile layer then it is not a closed loop
            int nextRow = row + 1;
            if (tiledLayer.isOnTileLayer(column, nextRow))
            {
                GeographicMapCellPosition geographicMapCellPositionNeighbor =
                   geographicMapCellPositionFactory.getInstance(
                   column, nextRow);

                //logUtil.put("geographicMapCellPositionNeighbor: " + geographicMapCellPositionNeighbor.toString() , this, "fixStart");

                graph.removeEdge(
                   geographicMapCellPosition,
                   geographicMapCellPositionNeighbor);
            }
        }

    }

    private void fixEnd(SimpleWeightedGraph<CellPosition, DefaultWeightedEdge> graph,
       BasicArrayList endPathFindingNodeList)
       throws Exception
    {
        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
           this.geographicMapInterface.getGeographicMapCellPositionFactory();

        AllBinaryTiledLayer tiledLayer =
            this.geographicMapInterface.getAllBinaryTiledLayer();

        for (int index = endPathFindingNodeList.size() - 1; index >= 0; index--)
        {
            PathFindingNode endPathFindingNode =
               (PathFindingNode) endPathFindingNodeList.get(index);

            GeographicMapCellPosition geographicMapCellPosition =
               endPathFindingNode.geographicMapCellPosition;

            graph.addVertex(geographicMapCellPosition);

            int column = geographicMapCellPosition.getColumn();
            int row = geographicMapCellPosition.getRow();

            //If going beyond the tile layer then it is not a closed loop
            int nextRow = row + 1;
            if (tiledLayer.isOnTileLayer(column, nextRow))
            {
                GeographicMapCellPosition geographicMapCellPositionNeighbor =
                   geographicMapCellPositionFactory.getInstance(
                   column, nextRow);

                graph.addEdge(
                   geographicMapCellPosition,
                   geographicMapCellPositionNeighbor);
            }
        }

    }

    private void fixOverPassEdges(
       SimpleWeightedGraph<CellPosition, DefaultWeightedEdge> graph)
       throws Exception
    {
        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
           this.geographicMapInterface.getGeographicMapCellPositionFactory();

        RaceTrackGeographicMap raceTrackGeographicMap =
            (RaceTrackGeographicMap) this.geographicMapInterface;

        //Add Over Passes
        CellPosition[] CellPositionArray =
           raceTrackGeographicMap.getRaceTrackData().getOverPassGeographicMapCellPositionArray();

        for (int index = CellPositionArray.length - 1; index >= 0; index--)
        {
            CellPosition overPassGeographicMapCellPosition =
               CellPositionArray[index];

            logUtil.put("Fixing Over Pass: " + overPassGeographicMapCellPosition.getColumn() + ", " + overPassGeographicMapCellPosition.getRow(), this, "fixOverPassEdges");

            GeographicMapCellPosition underPassGeographicMapCellPosition =
               geographicMapCellPositionFactory.getInstance(
               overPassGeographicMapCellPosition.getColumn(),
               overPassGeographicMapCellPosition.getRow());

            GeographicMapCellPosition rightUnderPassGeographicMapCellPosition =
               geographicMapCellPositionFactory.getInstance(
               overPassGeographicMapCellPosition.getColumn() + 1,
               overPassGeographicMapCellPosition.getRow());

            GeographicMapCellPosition leftUnderPassGeographicMapCellPosition =
               geographicMapCellPositionFactory.getInstance(
               overPassGeographicMapCellPosition.getColumn() - 1,
               overPassGeographicMapCellPosition.getRow());

            //Remove under pass cross connections
            graph.removeEdge(
               underPassGeographicMapCellPosition,
               rightUnderPassGeographicMapCellPosition);
            graph.removeEdge(
               underPassGeographicMapCellPosition,
               leftUnderPassGeographicMapCellPosition);

            graph.addVertex(overPassGeographicMapCellPosition);
            graph.addEdge(overPassGeographicMapCellPosition,
               rightUnderPassGeographicMapCellPosition);
            graph.addEdge(overPassGeographicMapCellPosition,
               leftUnderPassGeographicMapCellPosition);
        }
    }

    public void fixPath(BasicArrayList startPathFindingNodeList,
       BasicArrayList endPathFindingNodeList, BasicArrayList pathList)
       throws Exception
    {
        //logUtil.put("Path: " + pathList, this, "fixPath");

        for (int index = startPathFindingNodeList.size() - 1; index >= 0; index--)
        {
            PathFindingNode endPathFindingNode = (PathFindingNode) endPathFindingNodeList.get(index);
            PathFindingNode startPathFindingNode = (PathFindingNode) startPathFindingNodeList.get(index);

            //Fix Start/End Hack
            if(BasicGeographicMapUtil.getInstance().isSameCellPosition(
               startPathFindingNode.geographicMapCellPosition,
               endPathFindingNode.geographicMapCellPosition))
            //if (startPathFindingNode.geographicMapCellPosition ==
              // endPathFindingNode.geographicMapCellPosition)
            {
                if (pathList.remove(endPathFindingNode.geographicMapCellPosition))
                {
                    pathList.remove(startPathFindingNode.geographicMapCellPosition);
                    pathList.add(0, startPathFindingNode.geographicMapCellPosition);
                }
            }

        }
        this.removeOverPassEdges(pathList);
    }

    //Fix Over pass Hack
    private void removeOverPassEdges(BasicArrayList pathList)
       throws Exception
    {
        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
           this.geographicMapInterface.getGeographicMapCellPositionFactory();

        RaceTrackGeographicMap raceTrackGeographicMap =
            (RaceTrackGeographicMap) this.geographicMapInterface;

        CellPosition[] CellPositionArray =
           raceTrackGeographicMap.getRaceTrackData().getOverPassGeographicMapCellPositionArray();

        for (int index = CellPositionArray.length - 1; index >= 0; index--)
        {
            CellPosition overPassGeographicMapCellPosition =
               CellPositionArray[index];

            GeographicMapCellPosition underPassGeographicMapCellPosition =
               geographicMapCellPositionFactory.getInstance(
               overPassGeographicMapCellPosition.getColumn(),
               overPassGeographicMapCellPosition.getRow());

            int indexOf = pathList.indexOf(overPassGeographicMapCellPosition);
            if (indexOf != -1)
            {
                pathList.set(indexOf, underPassGeographicMapCellPosition);
            }
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
}
