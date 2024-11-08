/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.media.graphics.geography.map.topview;

import java.util.Hashtable;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

/**
 *
 * @author User
 */
public class BasicTopViewGeographicMapCellTypeFactory extends GeographicMapCellTypeFactory {

    public final BasicTopViewGeographicMapCellType BLOCK_CELL_TYPE;
    public final BasicTopViewGeographicMapCellType OFF_MAP_CELL_TYPE;
    public final BasicTopViewGeographicMapCellType FLOOR_CELL_TYPE;
    public final BasicTopViewGeographicMapCellType DOOR_CELL_TYPE;
    public final BasicTopViewGeographicMapCellType STAIRS_UP_CELL_TYPE;
    public final BasicTopViewGeographicMapCellType STAIRS_DOWN_CELL_TYPE;
    public final BasicTopViewGeographicMapCellType OTHER_CELL_TYPE;

    private BasicTopViewGeographicMapCellTypeFactory() {

        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        new RaceTrackGeographicMapCellType(0, 999);
        BasicTopViewGeographicMapCellType BLOCK_CELL_TYPE = new BasicTopViewGeographicMapCellType(1, 1);
        BasicTopViewGeographicMapCellType OFF_MAP_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType FLOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType DOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType STAIRS_UP_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType STAIRS_DOWN_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType OTHER_CELL_TYPE = BLOCK_CELL_TYPE;

        this.BLOCK_CELL_TYPE = BLOCK_CELL_TYPE;
        this.OFF_MAP_CELL_TYPE = OFF_MAP_CELL_TYPE;
        this.FLOOR_CELL_TYPE = FLOOR_CELL_TYPE;
        this.DOOR_CELL_TYPE = DOOR_CELL_TYPE;
        this.STAIRS_UP_CELL_TYPE = STAIRS_UP_CELL_TYPE;
        this.STAIRS_DOWN_CELL_TYPE = STAIRS_DOWN_CELL_TYPE;
        this.OTHER_CELL_TYPE = OTHER_CELL_TYPE;
        
    }
    
    public BasicTopViewGeographicMapCellTypeFactory(final Hashtable tileTypeToTileIdsMap) {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.INIT));

        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        new RaceTrackGeographicMapCellType(0, 999);
        BasicTopViewGeographicMapCellType BLOCK_CELL_TYPE = new BasicTopViewGeographicMapCellType(1, 1);
        BasicTopViewGeographicMapCellType OFF_MAP_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType FLOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType DOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType STAIRS_UP_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType STAIRS_DOWN_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType OTHER_CELL_TYPE = BLOCK_CELL_TYPE;
        
        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        //new GeographicMapCellType(1);
        
        final String OTHER = "Other";
        final String WALL = "Wall";
        final String OFF_MAP = "OffMap";
        final String FLOOR = "Floor";
        final String DOOR = "Door";
        final String STAIRS_UP = "StairsUp";
        final String STAIRS_DOWN = "StairsDown";
        
        final Object[] keyArray = HashtableUtil.getInstance().getKeysAsArray(tileTypeToTileIdsMap);
        //final Set set = tileTypeToTileIdsMap.keySet();
        //final String[] keyArray = (String[]) set.toArray(new String[set.size()]);
        final int size = keyArray.length;
        BasicArrayList idsWithTypeList;
        String key;
        BasicTopViewGeographicMapCellType basicPlatormGeographicMapCellType;
        for(int index = 0; index < size; index++) {
            key = (String) keyArray[index];
            
            LogUtil.put(LogFactory.getInstance(key, this, commonStrings.INIT));
            
            idsWithTypeList = (BasicArrayList) tileTypeToTileIdsMap.get(key);

            if(key.equals(WALL)) {
                //LogUtil.put(LogFactory.getInstance(idsWithTypeList.toString(), this, commonStrings.INIT));
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1000);
                BLOCK_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(OFF_MAP)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1001);
                OFF_MAP_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(FLOOR)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1);
                FLOOR_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(DOOR)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1);
                DOOR_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(STAIRS_UP)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1);
                STAIRS_UP_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(STAIRS_DOWN)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1);
                STAIRS_DOWN_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(OTHER)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList, 1);
                OTHER_CELL_TYPE = basicPlatormGeographicMapCellType;
            }

        }

        this.BLOCK_CELL_TYPE = BLOCK_CELL_TYPE;
        this.OFF_MAP_CELL_TYPE = OFF_MAP_CELL_TYPE;
        this.FLOOR_CELL_TYPE = FLOOR_CELL_TYPE;
        this.DOOR_CELL_TYPE = DOOR_CELL_TYPE;
        this.STAIRS_UP_CELL_TYPE = STAIRS_UP_CELL_TYPE;
        this.STAIRS_DOWN_CELL_TYPE = STAIRS_DOWN_CELL_TYPE;
        this.OTHER_CELL_TYPE = OTHER_CELL_TYPE;
        
    }
    
}
