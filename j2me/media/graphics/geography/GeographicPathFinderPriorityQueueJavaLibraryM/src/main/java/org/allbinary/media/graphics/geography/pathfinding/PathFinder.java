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

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class PathFinder extends GeographicPathFinderBase {

    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    private final MathUtil mathUtil = MathUtil.getInstance();
    
    private final PriorityQueue<PathFindingNodeCost> openPriorityQueue = new PriorityQueue<>();
    private final Set<PathFindingNodeCost> closedSet = new HashSet<>();
    
    private BasicGeographicMap geographicMapInterface;
    private PathFindingNodeCost[][] costArray;

    public void init(final BasicGeographicMap geographicMapInterface) throws Exception
    {
        this.geographicMapInterface = geographicMapInterface;
        
        final AllBinaryTiledLayer tiledLayer = this.geographicMapInterface.getAllBinaryTiledLayer();
        this.costArray = new PathFindingNodeCost[tiledLayer.getColumns()][tiledLayer.getRows()];

        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory = geographicMapInterface.getGeographicMapCellPositionFactory();
        
        PathFindingNodeCost node;

        final int sizeX = costArray.length;
        final int sizeY = costArray[0].length;
        for(int column = 0; column < sizeX; column++) {
            for(int row = 0; row < sizeY; row++) {
                
                final GeographicMapCellType geographicMapCellType = 
                    this.geographicMapInterface.getCellTypeAt(basicGeographicMapCellPositionFactory.getInstance(column, row));

                final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType = (RaceTrackGeographicMapCellType) geographicMapCellType;
                
                node = new PathFindingNodeCost(null, basicGeographicMapCellPositionFactory.getInstance(column, row), new PathFindingNodeCostInfo(raceTrackGeographicMapCellType.getTravelCost(), -1));

                costArray[column][row] = node;
            }
        }
        
    }
    
    public BasicArrayList search(
        BasicArrayList startPathFindingNodeList,
        BasicArrayList endPathFindingNodeList, int totalPaths) {
        
        try {
            return this.search((PathFindingNode) startPathFindingNodeList.get(0), (PathFindingNode) endPathFindingNodeList.get(0));
        } catch(Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "search", e));            
            return null;
        }
    }
   
    public BasicArrayList searchN(
        BasicArrayList startPathFindingNodeList,
        BasicArrayList endPathFindingNodeList, int totalPaths, final MultipassState multipassState)
        throws Exception {
        
        if(multipassState.step == 0) {
            multipassState.step++;
        } else if(multipassState.step == 1) {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("step 1: ").append(multipassState.iteration).toString(), this, "search"));
            this.searchStart((PathFindingNode) startPathFindingNodeList.get(0), (PathFindingNode) endPathFindingNodeList.get(0), multipassState);
        } else if(multipassState.step == 2) {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("step 2: ").append(multipassState.iteration).toString(), this, "search"));
            //LogUtil.put(LogFactory.getInstance("step 2", this, "search"));
            return this.searchN((PathFindingNode) startPathFindingNodeList.get(0), (PathFindingNode) endPathFindingNodeList.get(0), multipassState);
        }
        return null;
    }

    public BasicArrayList search(
        final PathFindingNode startPathFindingNode,
        final PathFindingNode endPathFindingNode)
        throws Exception {

        final BasicArrayList list = this.findPath(startPathFindingNode.geographicMapCellPosition, endPathFindingNode.geographicMapCellPosition);
        
//        if(list == null) { 
//            return BasicArrayListUtil.getInstance().getImmutableInstance();
//        }
        
        final BasicArrayList pathList = new BasicArrayList();
        pathList.add(list);

        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("finish A* ").append(list.size()).append(' ').append(pathList.size()).append(' ').append(list.toString()).toString(), this, "search"));
        
        return pathList;

    }

    public void searchStart(
        final PathFindingNode startPathFindingNode,
        final PathFindingNode endPathFindingNode,
        final MultipassState multipassState)
        throws Exception {

        this.findPathStart(startPathFindingNode.geographicMapCellPosition, endPathFindingNode.geographicMapCellPosition, multipassState);
        
    }

    public BasicArrayList searchN(
        final PathFindingNode startPathFindingNode,
        final PathFindingNode endPathFindingNode,
        final MultipassState multipassState)
        throws Exception {

        final BasicArrayList list = this.findPathEnd(startPathFindingNode.geographicMapCellPosition, endPathFindingNode.geographicMapCellPosition, multipassState);

        if(list == null) { 
            return null;
        }
                
        final BasicArrayList pathList = new BasicArrayList();
        pathList.add(list);

        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("finish A* ").append(list.size()).append(' ').append(pathList.size()).append(' ').append(list.toString()).toString(), this, "search"));
        
        multipassState.iteration = 0;
        multipassState.step = 0;
        
        return pathList;

    }
    
    public BasicArrayList findPath(final GeographicMapCellPosition start, final GeographicMapCellPosition target) 
        throws Exception 
    {
        
        this.openPriorityQueue.clear();
        this.closedSet.clear();

        int discoveryCalculation;
        PathFindingNodeCost node;

        final int targetColumn = target.getColumn();
        final int targetRow = target.getRow();
        final int sizeX = costArray.length;
        final int sizeY = costArray[0].length;
        for(int column = 0; column < sizeX; column++) {
            for(int row = 0; row < sizeY; row++) {
                discoveryCalculation = mathUtil.abs(column - targetColumn) + mathUtil.abs(row - targetRow);
                node = costArray[column][row];
                node.pathFindingNodeCostInfo.totalCost = 0;
                node.pathFindingNodeCostInfo.costToEnd = discoveryCalculation;
                
                //if(!this.geographicMapInterface.isAvailable(node.geographicMapCellPosition))) {
                    //closedSet.add(node);
                //}

            }
        }

        final PathFindingNodeCost startNode = costArray[start.getColumn()][start.getRow()];
        openPriorityQueue.add(startNode);

        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory = geographicMapInterface.getGeographicMapCellPositionFactory();
        final AllBinaryTiledLayer allBinaryTiledLayer = this.geographicMapInterface.getAllBinaryTiledLayer();
        
        final PathFindingNodeCost targetNode = costArray[target.getColumn()][target.getRow()];

        PathFindingNodeCost current;
        do {
            current = openPriorityQueue.poll();
            closedSet.add(current);

            if(current.equals(targetNode)) {
                return this.extractPath(start, current);
            }

            PathFindingNodeCost neighbor;
            PathFindingNodeCostInfo neighborInfo;
            long calculatedCost;
            for(int column = current.geographicMapCellPosition.getColumn() - 1; column < current.geographicMapCellPosition.getColumn() + 2; column++) {

                for(int row = current.geographicMapCellPosition.getRow() - 1; row < current.geographicMapCellPosition.getRow() + 2; row++) {

                    if(column > 0 && row > 0 && column < allBinaryTiledLayer.getColumns() && row < allBinaryTiledLayer.getRows() && 
                        this.geographicMapInterface.isOnMap(basicGeographicMapCellPositionFactory.getInstance(column, row))) {
                        neighbor = costArray[column][row];

                        if(closedSet.contains(neighbor)) {
                            continue;
                        }

                        neighborInfo = neighbor.pathFindingNodeCostInfo;
                        calculatedCost = neighborInfo.costToEnd + neighborInfo.costFromStart + current.pathFindingNodeCostInfo.totalCost;

                        if(calculatedCost < neighborInfo.totalCost || !openPriorityQueue.contains(neighbor)) {
                            neighborInfo.totalCost = calculatedCost;
                            neighbor.parent = current;

                            if(!openPriorityQueue.contains(neighbor)) {
                                if(this.geographicMapInterface.isOfFourDirections(current.geographicMapCellPosition, neighbor.geographicMapCellPosition)) {
                                    openPriorityQueue.add(neighbor);
                                }
                            }
                        }
                    }
                    
                }
            }
        } while(!openPriorityQueue.isEmpty());

        //return new BasicArrayList()add(start);
        throw new RuntimeException();
        //return null;
    }

    public void findPathStart(final GeographicMapCellPosition start, final GeographicMapCellPosition target, final MultipassState multipassState) 
        throws Exception 
    {
        this.openPriorityQueue.clear();
        this.closedSet.clear();

        int discoveryCalculation;
        PathFindingNodeCost node;

        final int targetColumn = target.getColumn();
        final int targetRow = target.getRow();
        final int sizeX = costArray.length;
        final int sizeY = costArray[0].length;
        //int total = 0;
        //multipassState.iteration
        for(int column = 0; column < sizeX; column++) {
            for(int row = 0; row < sizeY; row++) {
                discoveryCalculation = mathUtil.abs(column - targetColumn) + mathUtil.abs(row - targetRow);
                node = costArray[column][row];
                node.pathFindingNodeCostInfo.totalCost = 0;
                node.pathFindingNodeCostInfo.costToEnd = discoveryCalculation;
                
                //if(!this.geographicMapInterface.isAvailable(node.geographicMapCellPosition))) {
                    //closedSet.add(node);
                //}

            }
//            multipassState.iteration++;
//            total++;
//            if(total > 10) {
//                return;
//            }
        }

        final PathFindingNodeCost startNode = costArray[start.getColumn()][start.getRow()];
        openPriorityQueue.add(startNode);
        
        //multipassState.iteration = 0;
        multipassState.step++;
    }
    
    public BasicArrayList findPathEnd(final GeographicMapCellPosition start, final GeographicMapCellPosition target, final MultipassState multipassState) 
        throws Exception 
    {
        
        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory = geographicMapInterface.getGeographicMapCellPositionFactory();
        final AllBinaryTiledLayer allBinaryTiledLayer = this.geographicMapInterface.getAllBinaryTiledLayer();
        final PathFindingNodeCost targetNode = costArray[target.getColumn()][target.getRow()];

        PathFindingNodeCost current;
        int total = 0;
        do {
            current = openPriorityQueue.poll();
            closedSet.add(current);

            if(current.equals(targetNode)) {
                return this.extractPath(start, current);
            }

            PathFindingNodeCost neighbor;
            PathFindingNodeCostInfo neighborInfo;
            long calculatedCost;
            for(int column = current.geographicMapCellPosition.getColumn() - 1; column < current.geographicMapCellPosition.getColumn() + 2; column++) {

                for(int row = current.geographicMapCellPosition.getRow() - 1; row < current.geographicMapCellPosition.getRow() + 2; row++) {

                    if(column > 0 && row > 0 && column < allBinaryTiledLayer.getColumns() && row < allBinaryTiledLayer.getRows() && 
                        this.geographicMapInterface.isOnMap(basicGeographicMapCellPositionFactory.getInstance(column, row))) {
                        neighbor = costArray[column][row];

                        if(closedSet.contains(neighbor)) {
                            continue;
                        }

                        neighborInfo = neighbor.pathFindingNodeCostInfo;
                        calculatedCost = neighborInfo.costToEnd + neighborInfo.costFromStart + current.pathFindingNodeCostInfo.totalCost;

                        if(calculatedCost < neighborInfo.totalCost || !openPriorityQueue.contains(neighbor)) {
                            neighborInfo.totalCost = calculatedCost;
                            neighbor.parent = current;

                            if(!openPriorityQueue.contains(neighbor)) {
                                if(this.geographicMapInterface.isOfFourDirections(current.geographicMapCellPosition, neighbor.geographicMapCellPosition)) {
                                    openPriorityQueue.add(neighbor);
                                }
                            }
                        }
                    }
                    
                }
            }
          
            total++;
            if(total > 10) {
                return null;
            }
            
        } while(!openPriorityQueue.isEmpty());

        //return new BasicArrayList()add(start);
        throw new RuntimeException();
        //return null;
    }
    
    private BasicArrayList extractPath(final GeographicMapCellPosition start, PathFindingNodeCost current) {
        final BasicArrayList path = new BasicArrayList();
        while(current.parent != null) {
            path.add(current.geographicMapCellPosition);
            current = (PathFindingNodeCost) current.parent;
        }
        path.add(start);

        basicArrayListUtil.reverse(path);
        return path;
    }

}
