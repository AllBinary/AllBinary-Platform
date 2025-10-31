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
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.building.event.BuildingEventListenerInterface;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.layer.unit.UnitWaypointBehavior;
import org.allbinary.game.media.graphics.geography.map.racetrack.PathFindingInfoFactory;
import org.allbinary.logic.communication.log.ForcedLogUtil;
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
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathGenerator;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author user
 */
public class NoCacheWaypoint extends WaypointBase
    implements BuildingEventListenerInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final PathFindingLayerInterface ownerLayer;
    
    public NoCacheWaypoint(final PathFindingLayerInterface ownerLayer, final Sound sound)
        throws Exception
    {
        super(sound);
        
        this.ownerLayer = ownerLayer;

    }

    @Override
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        
//        final GeographicMapCompositeInterface geographicMapCompositeInterface =
//            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
//        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
//        
//        final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

    }
    
    public void releaseCachedPaths()
    {
    }

    @Override
    public BasicArrayList getPathsListFromCacheOnly(final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        return BasicArrayListUtil.getInstance().getImmutableInstance();
    }

    @Override
    public BasicArrayList getPathsList(final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        return this.createPaths(geographicMapCellPosition);
    }

    protected GeographicMapCellPosition
        getEndGeographicMapCellPosition(
        final GeographicMapCellPosition startGeographicMapCellPosition) throws Exception
    {
        final BasicArrayList endList = this.ownerLayer.getEndGeographicMapCellPositionList();
        
        //logUtil.put(new StringMaker().append(this.ownerLayer.getName()).append(" c: ").append(this.ownerLayer.getCurrentGeographicMapCellPosition()).append(' ').append(this.ownerLayer.getTopLeftGeographicMapCellPosition()).append(" endList: ").append(endList).toString(), this, "visit");
        
        GeographicMapCellPosition endGeographicMapCellPosition =
            (GeographicMapCellPosition) BasicArrayListUtil.getInstance().getRandom(endList);

        if (startGeographicMapCellPosition == endGeographicMapCellPosition)
        {
            for (int index = endList.size() - 1; index >= 0; index--)
            {
                endGeographicMapCellPosition =
                    (GeographicMapCellPosition) endList.get(index);

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
        //logUtil.put("", this, "createPaths");
        final GeographicMapCellPosition endGeographicMapCellPosition =
            this.getEndGeographicMapCellPosition(startGeographicMapCellPosition);

        if(endGeographicMapCellPosition == null) {
            //return new BasicArrayList();
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
        
        //logUtil.put("", this, "createPaths");
        
        //Most likely not a building
        if (startGeographicMapCellPosition == endGeographicMapCellPosition)
        {
            //logUtil.put("", this, "createPaths");
            if (this.ownerLayer.shouldHandleStartSameAsEnd())
            //if (this.endList.size() < 2)
            {
                //return new BasicArrayList();
                return BasicArrayListUtil.getInstance().getImmutableInstance();
            }
            else
            {
                throw new Exception("Start should not be End: " + endGeographicMapCellPosition);
            }
        }

        //logUtil.put("", this, "createPaths");

        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) this.allBinaryGameLayerManagerP;
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

        //logUtil.put("Start GeographicMapCellPosition: " + startGeographicMapCellPosition.toString(), this, "createPaths");
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
        
        //logUtil.put("geographicMapInterface.getGeographicMapCellTypeFactory().toString(): " + geographicMapInterface.getGeographicMapCellTypeFactory().toString(), this, "createPaths");

        final BasicArrayList list = 
            PathGenerator.getInstance().getInstanceNoCache(
            geographicMapInterface,
            //RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance(),
            pathFindingInfo, 2);

        //logUtil.put("" + list.size(), this, "createPaths");
        
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

    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    //Clear path cache
    @Override
    public void onBuildingEvent(final RTSLayerEvent event)
        throws Exception
    {
    }

    @Override
    public void reset() {
        this.getConnectedWaypointList().clear();
    }
    
    @Override
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
                    //logUtil.put("Sending: " + unitLayer.getName(), this, "visit");
                    
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
