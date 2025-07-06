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
package org.allbinary.game.layer.waypoint;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.building.event.BuildingEventListenerInterface;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.layer.unit.UnitWaypointBehavior;
import org.allbinary.game.media.graphics.geography.map.racetrack.PathFindingInfoFactory;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.media.audio.Sound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.racetrack.CustomMapGenerator;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMap;
import org.allbinary.media.graphics.geography.pathfinding.BasicGeographicMapExtractedPathsCacheFactory;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathGenerator;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author user
 */
public class Waypoint extends WaypointBase
    implements BuildingEventListenerInterface
{
    protected final PathFindingLayerInterface ownerLayer;
    
    private BasicArrayList endList;
    private BasicArrayList[][] paths;
    
    //, BasicArrayList endList
    public Waypoint(final PathFindingLayerInterface ownerLayer, final Sound sound)
        throws Exception
    {
        super(sound);
        
        this.ownerLayer = ownerLayer;

    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        
        final GeographicMapCompositeInterface geographicMapCompositeInterface =
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

        if(paths != null) {
            throw new RuntimeException();
        }

        this.endList = this.ownerLayer.getEndGeographicMapCellPositionList();
        
        this.paths = new BasicArrayList[tiledLayer.getColumns()][tiledLayer.getRows()];        

    }
    
    public void releaseCachedPaths()
    {
        for(int columnIndex = paths.length; --columnIndex >= 0;)
        {
            for(int rowIndex = paths.length; --rowIndex >= 0;)
            {
                final BasicArrayList pathsList = this.paths[columnIndex][rowIndex];
                if(pathsList != null)
                {
                    BasicGeographicMapExtractedPathsCacheFactory.getInstance().release(pathsList);
                }
            }
        }
    }

    @Override
    public BasicArrayList getPathsListFromCacheOnly(final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        return this.paths[geographicMapCellPosition.getColumn()][geographicMapCellPosition.getRow()];
    }

    @Override
    public BasicArrayList getPathsList(final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        BasicArrayList pathsList = this.paths[geographicMapCellPosition.getColumn()][geographicMapCellPosition.getRow()];

        if (pathsList == null)
        {
            pathsList = this.createPaths(geographicMapCellPosition);
          
            this.paths[geographicMapCellPosition.getColumn()][geographicMapCellPosition.getRow()] = pathsList;
        }

        return pathsList;
    }

    /*
    private BasicArrayList createPaths(GeographicMapCellPosition geographicMapCellPosition)
    throws Exception
    {
    BasicArrayList pathsList = new BasicArrayList();

    BasicArrayList pathList = new BasicArrayList();

    GeographicMapCellPosition endGeographicMapCellPosition =
    (GeographicMapCellPosition)
    this.geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList().get(0);

    pathList.add(geographicMapCellPosition);
    pathList.add(endGeographicMapCellPosition);

    pathsList.add(pathList);

    return pathsList;
    }
     */

    protected GeographicMapCellPosition
        getEndGeographicMapCellPosition(
        final GeographicMapCellPosition startGeographicMapCellPosition)
    {
        GeographicMapCellPosition endGeographicMapCellPosition =
            (GeographicMapCellPosition) BasicArrayListUtil.getInstance().getRandom(this.endList);

        if (startGeographicMapCellPosition == endGeographicMapCellPosition)
        {
            for (int index = this.endList.size() - 1; index >= 0; index--)
            {
                endGeographicMapCellPosition =
                    (GeographicMapCellPosition) this.endList.get(index);

                if (startGeographicMapCellPosition != endGeographicMapCellPosition)
                {
                    break;
                }
            }
        }
        return endGeographicMapCellPosition;
    }

    private BasicArrayList createPaths(final GeographicMapCellPosition startGeographicMapCellPosition)
        throws Exception
    {
        if(this.endList.size() == 0) {
            //return new BasicArrayList();
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }

        final GeographicMapCellPosition endGeographicMapCellPosition =
            this.getEndGeographicMapCellPosition(startGeographicMapCellPosition);

        if(endGeographicMapCellPosition == null) {
            //return new BasicArrayList();
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
        
        //Most likely not a building
        if (startGeographicMapCellPosition == endGeographicMapCellPosition)
        {
            if (this.ownerLayer.shouldHandleStartSameAsEnd())
            //if (this.endList.size() < 2)
            {
                //return new BasicArrayList();
                return BasicArrayListUtil.getInstance().getImmutableInstance();
            }
            else
            {
                throw new Exception("Start should not be End: " + this.endList.size());
            }
        }

        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) this.allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final RaceTrackGeographicMap raceTrackGeographicMap =
            (RaceTrackGeographicMap) geographicMapInterface;
        final GeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
            raceTrackGeographicMap.getGeographicMapCellTypeFactory();

        //Copy mapArray into graphArray - Note: mapArray may not contain start and end nodes
        final CustomMapGenerator customMapGenerator = (CustomMapGenerator)
            raceTrackGeographicMap.getCustomMapGenerator();
        
        customMapGenerator.copyMapIntoCustomMap();
        final int[][] customMapArray = customMapGenerator.getCustomMapArray();

        //LogUtil.put(LogFactory.getInstance("Start GeographicMapCellPosition: " + startGeographicMapCellPosition.toString(), this, "createPaths"));
        //int originalStartData = 
          //  customMapArray[startGeographicMapCellPosition.getRow()][startGeographicMapCellPosition.getColumn()];
        
        //int originalEndData =
          //  customMapArray[endGeographicMapCellPosition.getRow()][endGeographicMapCellPosition.getColumn()];

        customMapArray[startGeographicMapCellPosition.getRow()][startGeographicMapCellPosition.getColumn()] =
            raceTrackGeographicMapCellTypeFactory.getStartType();

        customMapArray[endGeographicMapCellPosition.getRow()][endGeographicMapCellPosition.getColumn()] =
            raceTrackGeographicMapCellTypeFactory.getEndType();
        
        final PathFindingInfo pathFindingInfo =
            PathFindingInfoFactory.getInstance().getInstance(
            raceTrackGeographicMap,
            customMapArray);
        
        //LogUtil.put(LogFactory.getInstance("geographicMapInterface.getGeographicMapCellTypeFactory().toString(): " + geographicMapInterface.getGeographicMapCellTypeFactory().toString(), this, "createPaths"));

        final BasicArrayList list = 
            PathGenerator.getInstance().getInstanceNoCache(
            geographicMapInterface,
            //RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance(),
            pathFindingInfo, 2);

        //Put map back
        //customMapArray[startGeographicMapCellPosition.getRow()][startGeographicMapCellPosition.getColumn()] = originalStartData;
        //customMapArray[endGeographicMapCellPosition.getRow()][endGeographicMapCellPosition.getColumn()] = originalEndData;

        //Temporary debugging for clearing caching
        if(list.size() < 1)
        {
            throw new Exception("Path may have been cleared by clearing caching");
        }
        
        return list;
    }

    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    //Clear path cache
    public void onBuildingEvent(final RTSLayerEvent event)
        throws Exception
    {
        /*
        BuildingLayer buildingLayer = (BuildingLayer) event.getRtsLayer();

        GeographicMapCellPosition geographicMapCellPosition = this.geographicMapInterface
                .getCellPositionAt(this.x + this.getHalfWidth(), this.y + this.getHalfHeight());

        BasicArrayList list = buildingLayer.geographicMapCellPositionArea
                .getOccupyingGeographicMapCellPositionList();
        */

        //Remove offending paths from cache

        for(int columnIndex = paths.length; --columnIndex >= 0;)
        {
            for(int rowIndex = paths.length; --rowIndex >= 0;)
            {
                this.paths[columnIndex][rowIndex] = null;
            }
        }
    }

    public void reset() {
        this.getConnectedWaypointList().clear();
        this.releaseCachedPaths();
    }
    
    public void visit(final PathFindingLayerInterface unitLayer)
        throws Exception
    {
        //Only visit waypoint if in same group
        if(unitLayer.getGroupInterface()[0] != this.ownerLayer.getGroupInterface()[0])
        {
            return;
        }

        final int size = this.getConnectedWaypointList().size();

        if (size > 0)
        {
            final UnitWaypointBehavior unitWaypointBehavior = 
                (UnitWaypointBehavior) unitLayer.getWaypointBehavior();
            
            while(this.getConnectedWaypointList().size() > 0)
            {
                final CollidableDestroyableDamageableLayer rtsLayer = (CollidableDestroyableDamageableLayer) this.getConnectedWaypointList().get(0);
                
                if(rtsLayer.isDestroyed())
                {
                    this.getConnectedWaypointList().remove(rtsLayer);
                }
                else
                {
                    //if (this.getOwnerLayer() != rtsLayer)
                    //{
                    unitLayer.handleCost(this.ownerLayer);
                    //LogUtil.put(LogFactory.getInstance("Sending: " + unitLayer.getName(), this, "visit"));
                    
                    unitWaypointBehavior.insertWaypoint(0, rtsLayer);
                    break;
                }
            }
            
            //}
            //else
            //{
            //throw new Exception("Can't Waypoint to Sellf");
            //}
        }

        /*
        //Future imple may select from a number of waypoints
        for(int columnIndex = 0; columnIndex < size; columnIndex++)
        {
        this.getWaypointList().add(list.get(columnIndex));
        }
         */
    }

}
