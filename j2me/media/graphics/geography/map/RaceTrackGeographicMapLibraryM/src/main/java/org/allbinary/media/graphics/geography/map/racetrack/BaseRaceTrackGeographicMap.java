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
package org.allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionBaseFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;

public class BaseRaceTrackGeographicMap extends BasicGeographicMap 
    implements RaceTrackGeographicMapInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //public static final String NAME = "RACE_TRACK_MAP";

    private RaceTrackInfo raceTrackInfo;
    private RaceTrackData raceTrackData;
    
    private BasicGeographicMap miniGeographicMap;

    public BaseRaceTrackGeographicMap(
       final RaceTrackInfo raceTrackInfo,
       final RaceTrackData raceTrackData,
       final AllBinaryTiledLayer tiledLayer,
       final BasicGeographicMap miniGeographicMap,
       final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface,
       final GeographicMapCellPositionBaseFactory geographicMapCellPositionBaseFactory,
       final GeographicMapCellTypeFactory geographicMapCellTypeFactory)
       throws Exception
    {
        super(raceTrackInfo.getId(),
           raceTrackInfo.getName(),
           raceTrackData.getCellTypeIdToGeographicMapCellTypeArray(),
           tiledLayer,
           raceTrackInfo.getForegroundBasicColor(),
           raceTrackInfo.getBackgroundBasicColor(),
           geographicMapCellPositionFactoryInterface,
           geographicMapCellPositionBaseFactory,
           geographicMapCellTypeFactory);

        this.raceTrackInfo = raceTrackInfo;

        this.raceTrackData = raceTrackData;

        this.miniGeographicMap = miniGeographicMap;

        AllBinaryTiledLayer miniTiledLayer =
           this.miniGeographicMap.getAllBinaryTiledLayer();

        if (miniTiledLayer.getColumns() !=
           this.getGeographicMapCellPositionFactory().getColumns())
        {
            final String error = new StringMaker().append("RaceTrackMap has incorrect Mini Map columns: ").append(miniTiledLayer.getColumns()).append(" != ")
                    .append(this.getGeographicMapCellPositionFactory().getColumns()).toString();
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR);
            throw new Exception(error);
        }

        if (miniTiledLayer.getRows() !=
           this.getGeographicMapCellPositionFactory().getRows())
        {
            String error = "RaceTrackMap has incorrect Mini Map rows";
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR);
            throw new Exception(error);
        }
    }

    @Override
    public RaceTrackInfo getRaceTrackInfo()
    {
        return raceTrackInfo;
    }

    @Override
    public void setRaceTrackInfo(RaceTrackInfo aRaceTrackInfo)
    {
        raceTrackInfo = aRaceTrackInfo;
    }

    public void setMiniBasicGeographicMap(BasicGeographicMap miniBasicGeographicMap)
    {
        this.miniGeographicMap = miniBasicGeographicMap;
    }

    public BasicGeographicMap getMiniBasicGeographicMap()
    {
        return miniGeographicMap;
    }

    @Override
    public RaceTrackData getRaceTrackData()
    {
        return raceTrackData;
    }

    @Override
    public void setRaceTrackData(RaceTrackData raceTrackData)
    {
        this.raceTrackData = raceTrackData;
    }
}