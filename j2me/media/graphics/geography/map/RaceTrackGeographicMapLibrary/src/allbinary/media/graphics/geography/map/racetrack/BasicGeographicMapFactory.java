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

import allbinary.graphics.color.BasicColorFactory;
import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.map.GeographicMapCellPositionBaseFactory;
import allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;

/**
 *
 * @author user
 */
public class BasicGeographicMapFactory 
{
    public BasicGeographicMap getInstance(
            RaceTrackInfo raceTrackInfo,
            RaceTrackData raceTrackData,
            GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface)
            throws Exception
    {
        return new BasicGeographicMap(
                SmallIntegerSingletonFactory.getInstance().getInstance(raceTrackInfo.getId().intValue() + 100 + 1),
                raceTrackInfo.getName(),
                raceTrackData.getCellTypeIdToGeographicMapCellTypeArray(),
                new AllBinaryTiledLayerFactory().getMiniInstance(raceTrackData),
                BasicColorFactory.getInstance().CLEAR_COLOR, 
                BasicColorFactory.getInstance().CLEAR_COLOR,
                geographicMapCellPositionFactoryInterface,
                new GeographicMapCellPositionBaseFactory());
    }
}
