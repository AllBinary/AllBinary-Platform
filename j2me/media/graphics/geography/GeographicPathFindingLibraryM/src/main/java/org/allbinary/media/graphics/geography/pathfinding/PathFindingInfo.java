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

import org.allbinary.util.BasicArrayList;

public class PathFindingInfo implements PathFindingInfoInterface
{
    private GeographicPathFinderBase pathFinder;
    private final BasicArrayList startPathFindingNodeList;
    private final BasicArrayList endPathFindingNodeList;
    
    private final PathFindingNodeCostInfoFactoryBase pathFindingNodeCostInfoFactoryInterface;
    
    public PathFindingInfo(
            final PathFindingNodeCostInfoFactoryBase pathFindingNodeCostInfoFactoryInterface)
    {
       this.pathFindingNodeCostInfoFactoryInterface =
           pathFindingNodeCostInfoFactoryInterface;

       this.startPathFindingNodeList = new BasicArrayList(1);
       this.endPathFindingNodeList = new BasicArrayList(1);
    }

    public BasicArrayList getStartPathFindingNodeList()
    {
        return this.startPathFindingNodeList;
    }
    
    public BasicArrayList getEndPathFindingNodeList()
    {
        return this.endPathFindingNodeList;
    }

    public GeographicPathFinderBase getPathFinder()
    {        
        return this.pathFinder;
    }

    public void addStartPathFindingNode(PathFindingNode startPathFindingNode)
    {
        this.startPathFindingNodeList.add(startPathFindingNode);
    }

    public void addEndPathFindingNode(PathFindingNode endPathFindingNode)
    {
        this.endPathFindingNodeList.add(endPathFindingNode);
    }

    public void setPathFinder(GeographicPathFinderBase pathFinder)
    {
        this.pathFinder = pathFinder;
    }

   public PathFindingNodeCostInfoFactoryBase getPathFindingNodeCostInfoFactoryInterface()
   {
      return pathFindingNodeCostInfoFactoryInterface;
   }
}
