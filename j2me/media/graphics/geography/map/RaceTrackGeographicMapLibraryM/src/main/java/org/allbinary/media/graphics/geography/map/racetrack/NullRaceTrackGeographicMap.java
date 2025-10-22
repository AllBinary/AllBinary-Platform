/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.layer.Layer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;

/**
 *
 * @author User
 */
public class NullRaceTrackGeographicMap implements RaceTrackGeographicMapInterface {

    public static final NullRaceTrackGeographicMap NULL_RACE_TRACK_GEOGRAPHIC_MAP = new NullRaceTrackGeographicMap();

    @Override
    public Integer getId() {
        throw new RuntimeException();
    }

    @Override
    public String getName() {
        throw new RuntimeException();
    }

    @Override
    public void reset() {
        throw new RuntimeException();
    }

    @Override
    public AllBinaryTiledLayer getAllBinaryTiledLayer() {
        throw new RuntimeException();
    }

    @Override
    public boolean getCellPositionsAt(
        Layer layer,
        GeographicMapCellPosition[][] currentCellPositionArray,
        GeographicMapCellPosition[][] cellPositionArray)
        throws Exception {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellPosition getCellPositionAt(int x, int y)
        throws Exception {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellPosition getCellPositionAtNoThrow(int x, int y)
        throws Exception {
        throw new RuntimeException();
    }

    @Override
    public int getCellTypeFromMapCellTypeInt(int cellTypeId) {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellType getCellTypeAt(int x, int y)
        throws Exception {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellType getCellTypeAt(GeographicMapCellPosition geographicMapCellPosition)
        throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public BasicGeographicMapCellPositionFactory getGeographicMapCellPositionFactory() {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellPositionFactoryInterface getGeographicMapCellPositionFactoryInterface() {
        throw new RuntimeException();
    }

    @Override
    public BasicColor getForegroundBasicColor() {
        throw new RuntimeException();
    }

    @Override
    public BasicColor getBackgroundBasicColor() {
        throw new RuntimeException();
    }

    @Override
    public RaceTrackInfo getRaceTrackInfo() {
        throw new RuntimeException();
    }

    @Override
    public void setRaceTrackInfo(RaceTrackInfo aRaceTrackInfo) {
        throw new RuntimeException();
    }

    @Override
    public RaceTrackData getRaceTrackData() {
        throw new RuntimeException();
    }

    @Override
    public void setRaceTrackData(RaceTrackData raceTrackData) {
        throw new RuntimeException();
    }

}
