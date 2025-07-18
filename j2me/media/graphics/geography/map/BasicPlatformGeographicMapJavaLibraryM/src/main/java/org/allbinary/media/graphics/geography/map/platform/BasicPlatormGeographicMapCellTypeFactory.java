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
package org.allbinary.media.graphics.geography.map.platform;

import java.util.Enumeration;
import java.util.Hashtable;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class BasicPlatormGeographicMapCellTypeFactory extends GeographicMapCellTypeFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    public final BasicPlatormGeographicMapCellType BLOCK_CELL_TYPE;
    public final BasicPlatormGeographicMapCellType JUMP_THRU_CELL_TYPE;
    public final BasicPlatormGeographicMapCellType LADDER_CELL_TYPE;

    private final int maxTileId;
    
    public BasicPlatormGeographicMapCellTypeFactory(final Hashtable tileTypeToTileIdsMap, final int maxTileId) {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(commonStrings.START, this, commonStrings.INIT);

        this.maxTileId = maxTileId;
        
        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        new GeographicMapCellType(0);
        //new GeographicMapCellType(1);
        BasicPlatormGeographicMapCellType BLOCK_CELL_TYPE = new BasicPlatormGeographicMapCellType(1);
        BasicPlatormGeographicMapCellType JUMP_THRU_CELL_TYPE = BLOCK_CELL_TYPE;
        BasicPlatormGeographicMapCellType LADDER_CELL_TYPE = BLOCK_CELL_TYPE;
        
        //final String OTHER = "Other";
        final String PLATFORM = "Platform";
        final String JUMP_TRHU = "JumpThru";
        final String LADDER = "Ladder";
        
        final Enumeration enumeration = tileTypeToTileIdsMap.keys();
        BasicArrayList idsWithTypeList;
        String key;
        BasicPlatormGeographicMapCellType basicPlatormGeographicMapCellType;
        while(enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            
            logUtil.put(key, this, commonStrings.INIT);
            
            idsWithTypeList = (BasicArrayList) tileTypeToTileIdsMap.get(key);

            basicPlatormGeographicMapCellType = new BasicPlatormGeographicMapCellType(idsWithTypeList);
            if(key.equals(PLATFORM)) {
                BLOCK_CELL_TYPE = basicPlatormGeographicMapCellType;
            }
            if(key.equals(JUMP_TRHU)) {
                JUMP_THRU_CELL_TYPE = basicPlatormGeographicMapCellType;
            }
            if(key.equals(LADDER)) {
                LADDER_CELL_TYPE = basicPlatormGeographicMapCellType;
            }

        }
        
        this.BLOCK_CELL_TYPE = BLOCK_CELL_TYPE;
        this.JUMP_THRU_CELL_TYPE = JUMP_THRU_CELL_TYPE;
        this.LADDER_CELL_TYPE = LADDER_CELL_TYPE;
        
        new GeographicMapCellType(this.maxTileId - 1);
        new GeographicMapCellType(this.maxTileId - 2);
        
    }

    public int getStartType() {
        return this.maxTileId - 1; //7
    }

    public int getEndType() {
        return this.maxTileId - 2; //8
    }
        
    public int getEmptyType() {
        return 0;
    }
 
    public boolean isPath(final GeographicMapCellType cellType) {
        if(cellType.getType() == 0) {
            return true;
        }
        return false;
    }
    
}
