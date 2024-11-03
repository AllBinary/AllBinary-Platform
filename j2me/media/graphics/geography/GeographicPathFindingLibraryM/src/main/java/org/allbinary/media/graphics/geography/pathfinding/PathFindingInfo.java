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
    private GeographicPathFinderInterface pathFinder;
    private final BasicArrayList startPathFindingNode;
    private final BasicArrayList endPathFindingNode;
    
    private final BasePathFindingNodeCostInfoFactoryInterface pathFindingNodeCostInfoFactoryInterface;
    
    public PathFindingInfo(
            BasePathFindingNodeCostInfoFactoryInterface pathFindingNodeCostInfoFactoryInterface)
    {
       this.pathFindingNodeCostInfoFactoryInterface =
           pathFindingNodeCostInfoFactoryInterface;

       this.startPathFindingNode = new BasicArrayList(1);
       this.endPathFindingNode = new BasicArrayList(1);
    }

    public void init()
    {
        this.startPathFindingNode.clear();
        this.endPathFindingNode.clear();
    }

    public BasicArrayList getStartPathFindingNodeList()
    {
        return this.startPathFindingNode;
    }
    
    public BasicArrayList getEndPathFindingNodeList()
    {
        return this.endPathFindingNode;
    }

    public GeographicPathFinderInterface getPathFinder()
    {        
        return this.pathFinder;
    }

    public void addStartPathFindingNode(PathFindingNode startPathFindingNode)
    {
        this.startPathFindingNode.add(startPathFindingNode);
    }

    public void addEndPathFindingNode(PathFindingNode endPathFindingNode)
    {
        this.endPathFindingNode.add(endPathFindingNode);
    }

    public void setPathFinder(GeographicPathFinderInterface pathFinder)
    {
        this.pathFinder = pathFinder;
    }

   public BasePathFindingNodeCostInfoFactoryInterface getPathFindingNodeCostInfoFactoryInterface()
   {
      return pathFindingNodeCostInfoFactoryInterface;
   }
}
