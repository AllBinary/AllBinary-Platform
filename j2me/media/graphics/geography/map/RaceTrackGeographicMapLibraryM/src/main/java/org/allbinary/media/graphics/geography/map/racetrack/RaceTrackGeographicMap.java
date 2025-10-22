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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionBaseFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionFactoryInterface;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;

public class RaceTrackGeographicMap extends BaseRaceTrackGeographicMap {

    private final CustomMapGeneratorBase customMapGenerator;

    public RaceTrackGeographicMap(
        final RaceTrackInfo raceTrackInfo,
        final RaceTrackData raceTrackData,
        final AllBinaryTiledLayerFactoryInterface tiledLayerFactoryInterface,
        final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface,
        final GeographicMapCellPositionBaseFactory geographicMapCellPositionBaseFactory,
        final GeographicMapCellTypeFactory geographicMapCellTypeFactory,
        final CustomMapGeneratorBaseFactory customMapGeneratorBaseFactory)
        throws Exception {
        super(raceTrackInfo, raceTrackData,
            tiledLayerFactoryInterface.getInstance(raceTrackInfo, raceTrackData),
            new BasicGeographicMapFactory().getInstance(
                raceTrackInfo, raceTrackData, tiledLayerFactoryInterface, geographicMapCellPositionFactoryInterface, geographicMapCellTypeFactory),
            geographicMapCellPositionFactoryInterface,
            geographicMapCellPositionBaseFactory,
            geographicMapCellTypeFactory);

        this.customMapGenerator = customMapGeneratorBaseFactory.getInstance(this);
    }

    public RaceTrackGeographicMap(
        final RaceTrackInfo raceTrackInfo,
        final RaceTrackData raceTrackData,
        final AllBinaryTiledLayerFactoryInterface tiledLayerFactoryInterface,
        final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface,
        final GeographicMapCellTypeFactory geographicMapCellTypeFactory,
        final CustomMapGeneratorBaseFactory customMapGeneratorBaseFactory)
        throws Exception {
        this(raceTrackInfo, raceTrackData,
            tiledLayerFactoryInterface,
            geographicMapCellPositionFactoryInterface,
            new GeographicMapCellPositionBaseFactory(),
            geographicMapCellTypeFactory,
            customMapGeneratorBaseFactory);

    }

    public CustomMapGeneratorBase getCustomMapGenerator() {
        return customMapGenerator;
    }

    //This should never happen remove when bug is found
    public boolean isValid(final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception {
        
        final int[][] customMapArray = this.customMapGenerator.getCustomMapArray();
        if (geographicMapCellPosition.getColumn() >= customMapArray[0].length) {
            final StringMaker stringBuffer = new StringMaker();
            stringBuffer.append("Column: ");
            stringBuffer.append(geographicMapCellPosition.getColumn());
            stringBuffer.append(" not in: ");
            stringBuffer.append(customMapArray[0].length);

            logUtil.put(stringBuffer.toString(), this, commonStrings.IS_VALID);

            if (geographicMapCellPosition.getColumn() == customMapArray[0].length) {
                return true;
            } else {
                return false;
            }
        } else if (geographicMapCellPosition.getRow() > customMapArray.length) {
            final StringMaker stringBuffer = new StringMaker();
            stringBuffer.append("Row: ");
            stringBuffer.append(geographicMapCellPosition.getRow());
            stringBuffer.append(" not in: ");
            stringBuffer.append(customMapArray.length);

            logUtil.put(stringBuffer.toString(), this, commonStrings.IS_VALID);

            if (geographicMapCellPosition.getRow() == customMapArray.length) {
                return true;
            } else {
                return false;
            }

        }
        return true;
    }

}
