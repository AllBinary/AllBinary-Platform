package org.allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.game.layer.AllBinaryTiledLayer;

/**
 *
 * @author user
 */
public interface AllBinaryTiledLayerFactoryInterface
{
    AllBinaryTiledLayer getInstance(RaceTrackInfo raceTrackInfo, RaceTrackData raceTrackData) 
            throws Exception;
    AllBinaryTiledLayer getMiniInstance(RaceTrackData raceTrackData) 
            throws Exception;
}
