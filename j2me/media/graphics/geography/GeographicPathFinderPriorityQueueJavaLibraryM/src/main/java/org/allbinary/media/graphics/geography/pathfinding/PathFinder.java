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

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.util.BasicArrayListUtil;

public class PathFinder extends GeographicPathFinderBase {
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    private final MathUtil mathUtil = MathUtil.getInstance();
    
    private final PriorityQueue<PathFindingNodeCost> openPriorityQueue = new PriorityQueue<PathFindingNodeCost>();
    private final HashSet<PathFindingNodeCost> closedSet = new HashSet<PathFindingNodeCost>();
    
    //BasicGeographicMap
    private Object geographicMapInterface = NullUtil.getInstance().NULL_OBJECT;
    private PathFindingNodeCost[][] costArray = PathFindingNodeCost.NULL_PATH_FINDING_NODE_COST_ARRAY_ARRAY;

    public void init(final BasicGeographicMap geographicMapInterface) throws Exception
    {
        this.geographicMapInterface = geographicMapInterface;
        
        final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        this.costArray = new PathFindingNodeCost[tiledLayer.getColumns()][tiledLayer.getRows()];

        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory = geographicMapInterface.getGeographicMapCellPositionFactory();
        
        PathFindingNodeCost node;

        final int sizeX = this.costArray.length;
        final int sizeY = this.costArray[0].length;
        for(int column = 0; column < sizeX; column++) {
            for(int row = 0; row < sizeY; row++) {
                
                final GeographicMapCellType geographicMapCellType = 
                    geographicMapInterface.getCellTypeAt(basicGeographicMapCellPositionFactory.getAt(column, row));

                final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType = (RaceTrackGeographicMapCellType) geographicMapCellType;
                
                node = new PathFindingNodeCost(NullUtil.getInstance().NULL_OBJECT, basicGeographicMapCellPositionFactory.getAt(column, row), new PathFindingNodeCostInfo((long) raceTrackGeographicMapCellType.getTravelCost(), (long) -1));

                this.costArray[column][row] = node;
            }
        }
        
    }
    
    @Override
    public BasicArrayList searchTotalPath(
        final BasicArrayList startPathFindingNodeList,
        final BasicArrayList endPathFindingNodeList, final int totalPaths) {
        
        try {
            return this.search((PathFindingNode) startPathFindingNodeList.get(0), (PathFindingNode) endPathFindingNodeList.get(0));
        } catch(Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, "search", e);            
            return this.basicArrayListUtil.getImmutableInstance();
        }
    }
   
    @Override
    public BasicArrayList searchTotalPathN(
        final BasicArrayList startPathFindingNodeList,
        final BasicArrayList endPathFindingNodeList, final int totalPaths, final MultipassState multipassState)
        throws Exception {
        
        if(multipassState.step == 0) {
            multipassState.step++;
        } else if(multipassState.step == 1) {
            //this.logUtil.putF(new StringMaker().append("step 1: ").append(multipassState.iteration).toString(), this, "search");
            this.searchStart((PathFindingNode) startPathFindingNodeList.get(0), (PathFindingNode) endPathFindingNodeList.get(0), multipassState);
        } else if(multipassState.step == 2) {
            //this.logUtil.putF(new StringMaker().append("step 2: ").append(multipassState.iteration).toString(), this, "search");
            //this.logUtil.putF("step 2", this, "search");
            return this.searchN((PathFindingNode) startPathFindingNodeList.get(0), (PathFindingNode) endPathFindingNodeList.get(0), multipassState);
        }
        return this.basicArrayListUtil.getImmutableInstance();
    }

    public BasicArrayList search(
        final PathFindingNode startPathFindingNode,
        final PathFindingNode endPathFindingNode)
        throws Exception {

        final BasicArrayList list = this.findPath(startPathFindingNode.geographicMapCellPosition, endPathFindingNode.geographicMapCellPosition);
        
//        if(list == null) { 
//            return BasicArrayListUtil.getInstance().getImmutableInstance();
//        }
        
        final BasicArrayList pathList = new BasicArrayListD();
        pathList.add(list);

        //this.logUtil.putF(new StringMaker().append("finish A* ").append(list.size()).append(CommonSeps.getInstance().SPACE).append(pathList.size()).append(CommonSeps.getInstance().SPACE).append(list.toString()).toString(), this, "search");
        
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
            return this.basicArrayListUtil.getImmutableInstance();
        }
                
        final BasicArrayList pathList = new BasicArrayListD();
        pathList.add(list);

        //this.logUtil.putF(new StringMaker().append("finish A* ").append(list.size()).append(CommonSeps.getInstance().SPACE).append(pathList.size()).append(CommonSeps.getInstance().SPACE).append(list.toString()).toString(), this, "search");
        
        multipassState.iteration = 0;
        multipassState.step = 0;
        
        return pathList;

    }
    
    public BasicArrayList findPath(final GeographicMapCellPosition start, final GeographicMapCellPosition target) 
        throws Exception 
    {
        
        this.openPriorityQueue.clear();
        this.closedSet.clear();

        long discoveryCalculation;
        PathFindingNodeCost node;

        final int targetColumn = target.getColumn();
        final int targetRow = target.getRow();
        final int sizeX = this.costArray.length;
        final int sizeY = this.costArray[0].length;
        for(int column = 0; column < sizeX; column++) {
            for(int row = 0; row < sizeY; row++) {
                discoveryCalculation = (long) this.mathUtil.abs(column - targetColumn) + this.mathUtil.abs(row - targetRow);
                node = this.costArray[column][row];
                node.pathFindingNodeCostInfoP.totalCostP = 0;
                node.pathFindingNodeCostInfoP.costToEndP = discoveryCalculation;
                
                //if(!this.geographicMapInterface.isAvailable(node.geographicMapCellPosition))) {
                    //closedSet.add(node);
                //}

            }
        }

        final PathFindingNodeCost startNode = this.costArray[start.getColumn()][start.getRow()];
        this.openPriorityQueue.add(startNode);

        final BasicGeographicMap geographicMapInterface = (BasicGeographicMap) this.geographicMapInterface;
        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory = geographicMapInterface.getGeographicMapCellPositionFactory();
        final AllBinaryTiledLayer allBinaryTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        
        final PathFindingNodeCost targetNode = this.costArray[target.getColumn()][target.getRow()];

        PathFindingNodeCost current;
        do {
            current = this.openPriorityQueue.poll();
            this.closedSet.add(current);

            if(current.equals(targetNode)) {
                return this.extractPath(start, current);
            }

            PathFindingNodeCost neighbor;
            PathFindingNodeCostInfo neighborInfo;
            long calculatedCost;
            for(int column = current.geographicMapCellPosition.getColumn() - 1; column < current.geographicMapCellPosition.getColumn() + 2; column++) {

                for(int row = current.geographicMapCellPosition.getRow() - 1; row < current.geographicMapCellPosition.getRow() + 2; row++) {

                    if(column > 0 && row > 0 && column < allBinaryTiledLayer.getColumns() && row < allBinaryTiledLayer.getRows() && 
                        geographicMapInterface.isOnMap(basicGeographicMapCellPositionFactory.getAt(column, row))) {
                        neighbor = this.costArray[column][row];

                        if(this.closedSet.contains(neighbor)) {
                            continue;
                        }

                        neighborInfo = neighbor.pathFindingNodeCostInfoP;
                        calculatedCost = neighborInfo.costToEndP + neighborInfo.costFromStartP + current.pathFindingNodeCostInfoP.totalCostP;

                        if(calculatedCost < neighborInfo.totalCostP || !this.openPriorityQueue.contains(neighbor)) {
                            neighborInfo.totalCostP = calculatedCost;
                            neighbor.parent = current;

                            if(!this.openPriorityQueue.contains(neighbor)) {
                                if(geographicMapInterface.isOfFourDirections(current.geographicMapCellPosition, neighbor.geographicMapCellPosition)) {
                                    this.openPriorityQueue.add(neighbor);
                                }
                            }
                        }
                    }
                    
                }
            }
        } while(!this.openPriorityQueue.isEmpty());

        //return new BasicArrayListD()add(start);
        throw new RuntimeException();
        //return null;
    }

    public void findPathStart(final GeographicMapCellPosition start, final GeographicMapCellPosition target, final MultipassState multipassState) 
        throws Exception 
    {
        this.openPriorityQueue.clear();
        this.closedSet.clear();

        long discoveryCalculation;
        PathFindingNodeCost node;

        final int targetColumn = target.getColumn();
        final int targetRow = target.getRow();
        final int sizeX = this.costArray.length;
        final int sizeY = this.costArray[0].length;
        //int total = 0;
        //multipassState.iteration
        for(int column = 0; column < sizeX; column++) {
            for(int row = 0; row < sizeY; row++) {
                discoveryCalculation = (long) this.mathUtil.abs(column - targetColumn) + this.mathUtil.abs(row - targetRow);
                node = this.costArray[column][row];
                node.pathFindingNodeCostInfoP.totalCostP = 0;
                node.pathFindingNodeCostInfoP.costToEndP = discoveryCalculation;
                
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

        final PathFindingNodeCost startNode = this.costArray[start.getColumn()][start.getRow()];
        this.openPriorityQueue.add(startNode);
        
        //multipassState.iteration = 0;
        multipassState.step++;
    }
    
    public BasicArrayList findPathEnd(final GeographicMapCellPosition start, final GeographicMapCellPosition target, final MultipassState multipassState) 
        throws Exception 
    {
        final BasicGeographicMap geographicMapInterface = (BasicGeographicMap) this.geographicMapInterface;
        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory = geographicMapInterface.getGeographicMapCellPositionFactory();
        final AllBinaryTiledLayer allBinaryTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        final PathFindingNodeCost targetNode = this.costArray[target.getColumn()][target.getRow()];

        PathFindingNodeCost current;
        int total = 0;
        do {
            current = this.openPriorityQueue.poll();
            this.closedSet.add(current);

            if(current.equals(targetNode)) {
                return this.extractPath(start, current);
            }

            PathFindingNodeCost neighbor;
            PathFindingNodeCostInfo neighborInfo;
            long calculatedCost;
            for(int column = current.geographicMapCellPosition.getColumn() - 1; column < current.geographicMapCellPosition.getColumn() + 2; column++) {

                for(int row = current.geographicMapCellPosition.getRow() - 1; row < current.geographicMapCellPosition.getRow() + 2; row++) {

                    if(column > 0 && row > 0 && column < allBinaryTiledLayer.getColumns() && row < allBinaryTiledLayer.getRows() && 
                        geographicMapInterface.isOnMap(basicGeographicMapCellPositionFactory.getAt(column, row))) {
                        neighbor = this.costArray[column][row];

                        if(this.closedSet.contains(neighbor)) {
                            continue;
                        }

                        neighborInfo = neighbor.pathFindingNodeCostInfoP;
                        calculatedCost = neighborInfo.costToEndP + neighborInfo.costFromStartP + current.pathFindingNodeCostInfoP.totalCostP;

                        if(calculatedCost < neighborInfo.totalCostP || !this.openPriorityQueue.contains(neighbor)) {
                            neighborInfo.totalCostP = calculatedCost;
                            neighbor.parent = current;

                            if(!this.openPriorityQueue.contains(neighbor)) {
                                if(geographicMapInterface.isOfFourDirections(current.geographicMapCellPosition, neighbor.geographicMapCellPosition)) {
                                    this.openPriorityQueue.add(neighbor);
                                }
                            }
                        }
                    }
                    
                }
            }
          
            total++;
            if(total > 10) {
                return this.basicArrayListUtil.getImmutableInstance();
            }
            
        } while(!this.openPriorityQueue.isEmpty());

        //return new BasicArrayListD()add(start);
        throw new RuntimeException();
        //return null;
    }
    
    private BasicArrayList extractPath(final GeographicMapCellPosition start, PathFindingNodeCost current) {
        final BasicArrayList path = new BasicArrayListD();
        while(current.parent != NullUtil.getInstance().NULL_OBJECT) {
            path.add(current.geographicMapCellPosition);
            current = (PathFindingNodeCost) current.parent;
        }
        path.add(start);

        this.basicArrayListUtil.reverse(path);
        return path;
    }

}
