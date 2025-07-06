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

import org.allbinary.util.BasicArrayList;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;

/**
 *
 * @author user
 */
//1.4.2
public class PathFinderGraphHackVisitor extends BasePathFinderGraphVisitor
{

    //RaceTrackGeographicMap raceTrackGeographicMap
    public PathFinderGraphHackVisitor(
        final BasicGeographicMap geographicMapInterface,
       int edgeMinimum, int minPathWeight, int maxPathWeight)
    {
        super(geographicMapInterface, edgeMinimum, minPathWeight, maxPathWeight);
    }

    public void visit(Object graph, //SimpleWeightedGraph
       BasicArrayList startPathFindingNodeList,
       BasicArrayList endPathFindingNodeList)
       throws Exception
    {
        this.fixStart(graph, startPathFindingNodeList);

        this.fixEnd(graph, endPathFindingNodeList);

        this.fixOverPassEdges(graph);
    }

    private void fixStart(Object graph, //SimpleWeightedGraph
       BasicArrayList startPathFindingNodeList)
       throws Exception
    {
    }

    private void fixEnd(Object graph, //SimpleWeightedGraph
       BasicArrayList endPathFindingNodeList)
       throws Exception
    {
    }

    private void fixOverPassEdges(
       Object graph) //SimpleWeightedGraph
       throws Exception
    {
    }

    public void fixPath(BasicArrayList startPathFindingNodeList,
       BasicArrayList endPathFindingNodeList, BasicArrayList pathList)
       throws Exception
    {
    }

    //Fix Over pass Hack
    private void removeOverPassEdges(BasicArrayList pathList)
       throws Exception
    {
    }

    public boolean isValid(Object graphPath) //GraphPath
    {
        return false;
    }
    
}
