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

import org.allbinary.logic.string.CommonStrings;
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

    public final BasicPlatormGeographicMapCellType BLOCK_CELL_TYPE;
    public final BasicPlatormGeographicMapCellType JUMP_THRU_CELL_TYPE;
    public final BasicPlatormGeographicMapCellType LADDER_CELL_TYPE;

    public BasicPlatormGeographicMapCellTypeFactory(final Hashtable tileTypeToTileIdsMap) {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.INIT));

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
            
            LogUtil.put(LogFactory.getInstance(key, this, commonStrings.INIT));
            
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
    }
    
    public int getEmptyType() {
        return 0;
    }
    
}
