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
import org.allbinary.logic.string.StringMaker;
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

    private final int maxTileId;
    
    private BasicTopViewGeographicMapCellTypeFactory() {

        this.maxTileId = 9;

        final BasicTopViewGeographicMapStrings basicTopViewGeographicMapStrings =
            BasicTopViewGeographicMapStrings.getInstance();
        
        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        new RaceTrackGeographicMapCellType(0, 999);
        BasicTopViewGeographicMapCellType BLOCK_CELL_TYPE = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.DEFAULT, 1, 1);
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
    
    public BasicTopViewGeographicMapCellTypeFactory(final Hashtable tileTypeToTileIdsMap, final int maxTileId) {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));

        this.maxTileId = maxTileId;

        final BasicTopViewGeographicMapStrings basicTopViewGeographicMapStrings =
            BasicTopViewGeographicMapStrings.getInstance();
        
        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        
        final GeographicMapCellTypeFactory geographicMapCellTypeFactory = GeographicMapCellTypeFactory.getInstance();
        final GeographicMapCellType[] geographicMapCellTypeArray = geographicMapCellTypeFactory.getGeographicMapCellTypeArray();
        
        int type = 0;
        if(geographicMapCellTypeArray[type] == null) {
            new RaceTrackGeographicMapCellType(type, 999);
        } else {
            //LogUtil.put(LogFactory.getInstance(basicTopViewGeographicMapStrings.ALREADY_EXISTS + type, this, commonStrings.CONSTRUCTOR));
        }
        
        BasicTopViewGeographicMapCellType BLOCK_CELL_TYPE = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.DEFAULT, 1, 1);
        BasicTopViewGeographicMapCellType OFF_MAP_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType FLOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType DOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType STAIRS_UP_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType STAIRS_DOWN_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicTopViewGeographicMapCellType OTHER_CELL_TYPE = BLOCK_CELL_TYPE;
        
        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        //new GeographicMapCellType(1);
                
        final Object[] keyArray = HashtableUtil.getInstance().getKeysAsArray(tileTypeToTileIdsMap);
        //final Set set = tileTypeToTileIdsMap.keySet();
        //final String[] keyArray = (String[]) set.toArray(new String[set.size()]);
        final int size = keyArray.length;
        BasicArrayList idsWithTypeList;
        String key;
        BasicTopViewGeographicMapCellType basicPlatormGeographicMapCellType;
        for(int index = 0; index < size; index++) {
            key = (String) keyArray[index];
            
            //LogUtil.put(LogFactory.getInstance(key, this, commonStrings.INIT));
            
            idsWithTypeList = (BasicArrayList) tileTypeToTileIdsMap.get(key);

            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("key: ").append(key).append(": ").append(idsWithTypeList.toString()).toString(), this, commonStrings.INIT));
            if(key.equals(basicTopViewGeographicMapStrings.WALL)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.WALL, idsWithTypeList, 1000);
                BLOCK_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(basicTopViewGeographicMapStrings.OFF_MAP)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.OFF_MAP, idsWithTypeList, 1001);
                OFF_MAP_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(basicTopViewGeographicMapStrings.FLOOR)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.FLOOR, idsWithTypeList, 1);
                FLOOR_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(basicTopViewGeographicMapStrings.DOOR)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.DOOR, idsWithTypeList, 1);
                DOOR_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(basicTopViewGeographicMapStrings.STAIRS_UP)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.STAIRS_UP, idsWithTypeList, 1);
                STAIRS_UP_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(basicTopViewGeographicMapStrings.STAIRS_DOWN)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.STAIRS_DOWN, idsWithTypeList, 1);
                STAIRS_DOWN_CELL_TYPE = basicPlatormGeographicMapCellType;
            } else if(key.equals(basicTopViewGeographicMapStrings.OTHER)) {
                basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(basicTopViewGeographicMapStrings.OTHER, idsWithTypeList, 1);
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
        
        type = this.maxTileId - 1;
        if(geographicMapCellTypeArray[type] == null) {
            new RaceTrackGeographicMapCellType(CommonStrings.getInstance().START, type, 1);
        } else {
            //LogUtil.put(LogFactory.getInstance(basicTopViewGeographicMapStrings.ALREADY_EXISTS + type, this, commonStrings.CONSTRUCTOR));
        }

        type = this.maxTileId - 2;
        if(geographicMapCellTypeArray[type] == null) {
            new RaceTrackGeographicMapCellType(CommonStrings.getInstance().START, type, 1);
        } else {
            //LogUtil.put(LogFactory.getInstance(basicTopViewGeographicMapStrings.ALREADY_EXISTS + type, this, commonStrings.CONSTRUCTOR));
        }
        
    }
    
    public int getStartType() {
        return this.maxTileId - 1; //7
    }

    public int getEndType() {
        return this.maxTileId - 2; //8
    }
    
    public int getEmptyType() {
        return this.FLOOR_CELL_TYPE.getTypes()[0];
    }

    public boolean isPath(GeographicMapCellType cellType) {
        if(this.FLOOR_CELL_TYPE.isType(cellType)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return new StringMaker()
            .append("key: WALL/BLOCK_CELL_TYPE: ").append(this.BLOCK_CELL_TYPE.toString())
            .append("key: FLOOR_CELL_TYPE: ").append(this.FLOOR_CELL_TYPE.toString())
            .append("key: OTHER_CELL_TYPE: ").append(this.OTHER_CELL_TYPE.toString())
            .append("key: OFF_MAP_CELL_TYPE: ").append(this.OFF_MAP_CELL_TYPE.toString())
            .append("key: DOOR_CELL_TYPE: ").append(this.DOOR_CELL_TYPE.toString())
            .append("key: STAIRS_DOWN_CELL_TYPE: ").append(this.STAIRS_DOWN_CELL_TYPE.toString())
            .append("key: STAIRS_UP_CELL_TYPE: ").append(this.STAIRS_UP_CELL_TYPE.toString())
            .toString();
    }    
}
