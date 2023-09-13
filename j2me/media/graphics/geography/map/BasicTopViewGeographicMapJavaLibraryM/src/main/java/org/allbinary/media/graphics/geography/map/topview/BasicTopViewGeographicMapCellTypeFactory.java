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

import java.util.Map;
import java.util.Set;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class BasicTopViewGeographicMapCellTypeFactory {

    private static final BasicTopViewGeographicMapCellTypeFactory instance = new BasicTopViewGeographicMapCellTypeFactory();

    /**
     * @return the instance
     */
    public static BasicTopViewGeographicMapCellTypeFactory getInstance() {
        return instance;
    }

    public BasicTopViewGeographicMapCellType BLOCK_CELL_TYPE;
    public BasicTopViewGeographicMapCellType DOOR_CELL_TYPE;
    public BasicTopViewGeographicMapCellType STAIRS_UP_CELL_TYPE;
    public BasicTopViewGeographicMapCellType STAIRS_DOWN_CELL_TYPE;

    public void init() {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().INIT));

        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        new GeographicMapCellType(0);
        BLOCK_CELL_TYPE = new BasicTopViewGeographicMapCellType(1);
        DOOR_CELL_TYPE = BLOCK_CELL_TYPE;
        STAIRS_UP_CELL_TYPE = BLOCK_CELL_TYPE;
        STAIRS_DOWN_CELL_TYPE = BLOCK_CELL_TYPE;
    }

    public void init(final Map tileTypeToTileIdsMap) {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.INIT));

        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        //new GeographicMapCellType(0);
        //new GeographicMapCellType(1);
        
        //final String OTHER = "Other";
        final String WALL = "Wall";
        final String DOOR = "Door";
        final String STAIRS_UP = "StairsUp";
        final String STAIRS_DOWN = "StairsDown";
        
        final Set set = tileTypeToTileIdsMap.keySet();
        final String[] keyArray = (String[]) set.toArray(new String[set.size()]);
        final int size = keyArray.length;
        BasicArrayList idsWithTypeList;
        String key;
        BasicTopViewGeographicMapCellType basicPlatormGeographicMapCellType;
        for(int index = 0; index < size; index++) {
            key = keyArray[index];
            
            LogUtil.put(LogFactory.getInstance(key, this, commonStrings.INIT));
            
            idsWithTypeList = (BasicArrayList) tileTypeToTileIdsMap.get(key);

            basicPlatormGeographicMapCellType = new BasicTopViewGeographicMapCellType(idsWithTypeList);
            if(key.equals(WALL)) {
                //LogUtil.put(LogFactory.getInstance(idsWithTypeList.toString(), this, commonStrings.INIT));
                this.BLOCK_CELL_TYPE = basicPlatormGeographicMapCellType;
            }
            if(key.equals(DOOR)) {
                this.DOOR_CELL_TYPE = basicPlatormGeographicMapCellType;
            }
            if(key.equals(STAIRS_UP)) {
                this.STAIRS_UP_CELL_TYPE = basicPlatormGeographicMapCellType;
            }
            if(key.equals(STAIRS_DOWN)) {
                this.STAIRS_DOWN_CELL_TYPE = basicPlatormGeographicMapCellType;
            }

        }
    }
    
}
