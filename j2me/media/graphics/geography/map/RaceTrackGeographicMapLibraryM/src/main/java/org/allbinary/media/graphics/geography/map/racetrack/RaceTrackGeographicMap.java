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

import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionBaseFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;

public class RaceTrackGeographicMap extends BaseRaceTrackGeographicMap
{
   public RaceTrackGeographicMap(
      final RaceTrackInfo raceTrackInfo,
      final RaceTrackData raceTrackData,
      final AllBinaryTiledLayerFactoryInterface tiledLayerFactoryInterface,
      final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface,
      final GeographicMapCellPositionBaseFactory geographicMapCellPositionBaseFactory,
      final GeographicMapCellTypeFactory geographicMapCellTypeFactory)
      throws Exception
   {
      super(raceTrackInfo, raceTrackData, 
              tiledLayerFactoryInterface.getInstance(raceTrackInfo, raceTrackData),
              new BasicGeographicMapFactory().getInstance(
                 raceTrackInfo, raceTrackData, tiledLayerFactoryInterface, geographicMapCellPositionFactoryInterface, geographicMapCellTypeFactory),
              geographicMapCellPositionFactoryInterface,
              geographicMapCellPositionBaseFactory,
              geographicMapCellTypeFactory);
   }   
}
