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
package org.allbinary.media.graphics.geography.map.racetrack.threed;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackData;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackInfo;

public class AllBinaryThreedTiledLayerFactory
{
    private static final AllBinaryThreedTiledLayerFactory instance = new AllBinaryThreedTiledLayerFactory();

    public static AllBinaryThreedTiledLayerFactory getInstance()
    {
        return instance;
    }

    public AllBinaryTiledLayer getInstance(
            RaceTrackInfo raceTrackInfo,
            RaceTrackData raceTrackData)
            throws Exception
    {
        throw new Exception("No Threed");
    }
}
