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
package org.allbinary.media.graphics.geography.map.racetrack.drop;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.drop.BaseDropCellPositionGenerator;
import org.allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.NullRaceTrackGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapInterface;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackRoadsGeographicMapCellHistoryFactory;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

/**
 * 
 * @author user
 */
public class RaceTrackDropCellPositionGenerator
    extends BaseDropCellPositionGenerator
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final BasicArrayList list = new BasicArrayList();
    // private BasicArrayList typeList = new BasicArrayList();
    private final TimeDelayHelper timeDelayHelper;
    private final int STRAIGHTAWAY = 4;
    protected RaceTrackGeographicMapInterface raceTrackGeographicMap = NullRaceTrackGeographicMap.NULL_RACE_TRACK_GEOGRAPHIC_MAP;
    protected GeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = GeographicMapCellTypeFactory.getInstance();

    protected RaceTrackDropCellPositionGenerator()
    {
        timeDelayHelper = new TimeDelayHelper(10000);
    }

    private void init()
    {
        this.list.clear();
        // this.typeList.clear();
    }

    //isStraightAwayRoad
    @Override
    public boolean isDropAllowedAt(
            final GeographicMapCellPosition geographicMapCellPosition)
            throws Exception
        {            
            final RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
                (RaceTrackGeographicMapCellType) raceTrackGeographicMap.getCellTypeAt(geographicMapCellPosition);

            final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = (RaceTrackGeographicMapCellTypeFactory) this.raceTrackGeographicMapCellTypeFactory;
            if (raceTrackGeographicMapCellType == raceTrackGeographicMapCellTypeFactory.HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE ||
                raceTrackGeographicMapCellType == raceTrackGeographicMapCellTypeFactory.VERTICAL_STRAIGHT_ROAD_CELL_TYPE)
            {
                return true;
            }
            return false;
        }

    /* (non-Javadoc)
     * @see allbinary.media.graphics.geography.map.racetrack.drop.DropCellPositionGeneratorInterface#update(allbinary.game.layer.AllBinaryGameLayerManager, allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap)
     */
    @Override
    public void update(final AllBinaryGameLayerManager allBinaryGameLayerManager,
            final BasicGeographicMap geographicMapInterface) throws Exception
    {
        //PreLogUtil.put(commonStrings.START, this, commonStrings.UPDATE);
        
        this.init();

        final BaseRaceTrackGeographicMap baseRaceTrackGeographicMap = (BaseRaceTrackGeographicMap) geographicMapInterface;
        this.raceTrackGeographicMap = baseRaceTrackGeographicMap;
        this.raceTrackGeographicMapCellTypeFactory = (RaceTrackGeographicMapCellTypeFactory) 
            baseRaceTrackGeographicMap.getGeographicMapCellTypeFactory();


        //AllBinaryTiledLayer tiledLayer =
        //raceTrackGeographicMap.getAllBinaryTiledLayer();

        final GeographicMapCellHistory roadGeographicMapCellHistory =
            RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance();

        final BasicArrayList trackedList = roadGeographicMapCellHistory.getTracked();

        GeographicMapCellPosition geographicMapCellPosition;
        
        final int lastIndex = trackedList.size() - 1;
        
        int total;
        int ahead;
        
        for (int index = lastIndex; index > 0; index--)
        {
            geographicMapCellPosition = (GeographicMapCellPosition) trackedList.get(index);

            total = 0;
            // int behind = index - 1;
            ahead = index;

            // this.isStraighRoad(raceTrackGeographicMap,
            // (GeographicMapCellPosition) trackedList.get(behind)) &&
            while(this.isDropAllowedAt((GeographicMapCellPosition) trackedList.get(ahead)))
            {
                total++;
                /*
                 * behind--; if(behind < 0) behind = lastIndex;
                 */

                // Both a straight away and not close to turn
                if (total > STRAIGHTAWAY)
                {
                    this.list.add(geographicMapCellPosition);
                    break;
                }

                ahead++;
                if (ahead > lastIndex)
                {
                    break;
                }
            }
        }

        int size = this.list.size();
        //PreLogUtil.put("Initial Dropping: " + size, this, commonStrings.UPDATE);
        //logUtil.put("Initial Dropping: " + size, this, commonStrings.UPDATE);

        //this.drop(allBinaryLayerManager, (size >> 1));

        for (int index = size - 1; index >= 0; index--)
        {
            this.drop(allBinaryGameLayerManager, index);
        }
    }

    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    
    /* (non-Javadoc)
     * @see allbinary.media.graphics.geography.map.racetrack.drop.DropCellPositionGeneratorInterface#processTick(allbinary.layer.AllBinaryLayerManager)
     */
    @Override
    public void processTick(final AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        if (timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
        {
            int index = basicArrayListUtil.getRandomIndex(this.list);
            this.drop(allBinaryLayerManager, index);
        }
    }

    protected void drop(final AllBinaryLayerManager allBinaryLayerManager, final int index) throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
}
