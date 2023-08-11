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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;

/**
 *
 * @author User
 */
public class BasicPlatormGeographicMapCellTypeFactory {

    private static final BasicPlatormGeographicMapCellTypeFactory instance = new BasicPlatormGeographicMapCellTypeFactory();

    /**
     * @return the instance
     */
    public static BasicPlatormGeographicMapCellTypeFactory getInstance() {
        return instance;
    }

    public BasicPlatormGeographicMapCellType BLOCK_CELL_TYPE;
    public BasicPlatormGeographicMapCellType LADDER_CELL_TYPE;

    public void init() {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().INIT));

        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        new GeographicMapCellType(0);
        BLOCK_CELL_TYPE = new BasicPlatormGeographicMapCellType(1);
    }

    public void init(final int[][] tileMapTypeArray) {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().INIT));

        //GeographicMapCellTypeFactory.getInstance().EMPTY_CELL_TYPE = 
        new GeographicMapCellType(0);
        BLOCK_CELL_TYPE = new BasicPlatormGeographicMapCellType(1);
    }
    
}
