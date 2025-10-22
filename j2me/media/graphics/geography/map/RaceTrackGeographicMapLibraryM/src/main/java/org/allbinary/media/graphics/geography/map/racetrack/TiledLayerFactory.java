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

import javax.microedition.lcdui.game.TiledLayer;

/**
 *
 * @author user
 */
public class TiledLayerFactory {

    public TiledLayer getInstance(RaceTrackData raceTrackData)
    {
        final int[][] mapArray = raceTrackData.getMapArray();
        return new TiledLayer(
                mapArray[0].length,
                mapArray.length,
                raceTrackData.getTileSetImage(),
                raceTrackData.getCellWidth(), raceTrackData.getCellHeight());
    }

    public TiledLayer getMiniInstance(RaceTrackData raceTrackData)
    {
        final int[][] mapArray = raceTrackData.getMapArray();
        return new TiledLayer(
                mapArray[0].length,
                mapArray.length,
                raceTrackData.getMiniTileSetImage(),
                raceTrackData.getMiniCellWidth(),
                raceTrackData.getMiniCellHeight());
    }
}