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

import allbinary.media.graphics.geography.map.GeographicMapInterface;

public interface RaceTrackGeographicMapInterface 
   extends GeographicMapInterface
{
   public RaceTrackInfo getRaceTrackInfo();

   public void setRaceTrackInfo(RaceTrackInfo aRaceTrackInfo);

   RaceTrackData getRaceTrackData();

   void setRaceTrackData(RaceTrackData raceTrackData);
}
