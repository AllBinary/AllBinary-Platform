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

import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionBaseFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;

/**
 *
 * @author user
 */
public class BasicGeographicMapFactory 
{
    public BasicGeographicMap getInstance(
            RaceTrackInfo raceTrackInfo,
            RaceTrackData raceTrackData,
            AllBinaryTiledLayerFactoryInterface tiledLayerFactoryInterface,
            GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface)
            throws Exception
    {
        return new BasicGeographicMap(
                SmallIntegerSingletonFactory.getInstance().getInstance(raceTrackInfo.getId().intValue() + 100 + 1),
                raceTrackInfo.getName(),
                raceTrackData.getCellTypeIdToGeographicMapCellTypeArray(),
                tiledLayerFactoryInterface.getMiniInstance(raceTrackData),
                BasicColorFactory.getInstance().CLEAR_COLOR, 
                BasicColorFactory.getInstance().CLEAR_COLOR,
                geographicMapCellPositionFactoryInterface,
                new GeographicMapCellPositionBaseFactory());
    }
}
