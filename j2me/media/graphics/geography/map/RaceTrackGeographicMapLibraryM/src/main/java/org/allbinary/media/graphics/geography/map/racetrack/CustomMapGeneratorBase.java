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
package org.allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.logic.NullUtil;

/**
 *
 * @author User
 */
public class CustomMapGeneratorBase {

    public void copyMapIntoCustomMap() throws Exception {
        
    }

    public int[][] getCustomMapArray() {
        return NullUtil.getInstance().NULL_INT_ARRAY_ARRAY;
    }

    public int getCustomType(final int column, final int row, final int currentType) throws Exception {
        return -1;
    }
    
}
