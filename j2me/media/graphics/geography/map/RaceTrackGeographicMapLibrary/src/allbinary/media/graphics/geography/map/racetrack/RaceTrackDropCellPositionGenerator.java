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
package allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.tick.TickableInterface;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;

/**
 * 
 * @author user
 */
public class RaceTrackDropCellPositionGenerator
    implements TickableInterface
{
    protected final BasicArrayList list = new BasicArrayList();
    // private BasicArrayList typeList = new BasicArrayList();
    private final TimeDelayHelper timeDelayHelper;
    private final int STRAIGHTAWAY = 4;
    protected BaseRaceTrackGeographicMap raceTrackGeographicMap;

    protected RaceTrackDropCellPositionGenerator()
    {
        timeDelayHelper = new TimeDelayHelper(10000);
    }

    public String getName()
    {
        return this.getClass().getName();
    }

    private void init()
    {
        this.list.clear();
        // this.typeList.clear();
    }

    private boolean isStraightAwayRoad(
        BaseRaceTrackGeographicMap raceTrackGeographicMap,
        GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
            RaceTrackGeographicMapCellTypeFactory.getInstance();        
        
        RaceTrackGeographicMapCellType raceTrackGeographicMapCellType =
            (RaceTrackGeographicMapCellType)
            raceTrackGeographicMap.getCellTypeAt(
            geographicMapCellPosition);

        if (raceTrackGeographicMapCellType == raceTrackGeographicMapCellTypeFactory.HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE ||
            raceTrackGeographicMapCellType == raceTrackGeographicMapCellTypeFactory.VERTICAL_STRAIGHT_ROAD_CELL_TYPE)
        {
            return true;
        }
        return false;
    }

    public void update(
            AllBinaryGameLayerManager allBinaryGameLayerManager,
        BaseRaceTrackGeographicMap raceTrackGeographicMap) throws Exception
    {
        this.init();

        this.raceTrackGeographicMap = raceTrackGeographicMap;

        //AllBinaryTiledLayer tiledLayer =
        //raceTrackGeographicMap.getAllBinaryTiledLayer();

        GeographicMapCellHistory roadGeographicMapCellHistory =
            RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance();

        BasicArrayList trackedList = roadGeographicMapCellHistory.getTracked();

        GeographicMapCellPosition geographicMapCellPosition;
        
        int lastIndex = trackedList.size() - 1;
        
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
            while(this.isStraightAwayRoad(raceTrackGeographicMap, (GeographicMapCellPosition) trackedList.get(ahead)))
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
        LogUtil.put(LogFactory.getInstance("Initial Dropping: " + size, this, CommonStrings.getInstance().UPDATE));

        //this.drop(allBinaryLayerManager, (size >> 1));

        for (int index = size - 1; index >= 0; index--)
        {
            this.drop(allBinaryGameLayerManager, index);
        }
    }

    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    
    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        if (timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().getStartTime()))
        {
            int index = basicArrayListUtil.getRandomIndex(this.list);
            this.drop(allBinaryLayerManager, index);
        }
    }

    protected void drop(AllBinaryLayerManager allBinaryLayerManager, int index) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
}
