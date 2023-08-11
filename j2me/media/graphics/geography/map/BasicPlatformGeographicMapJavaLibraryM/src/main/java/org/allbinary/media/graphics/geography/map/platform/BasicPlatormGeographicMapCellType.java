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
package org.allbinary.media.graphics.geography.map.platform;

import org.allbinary.media.graphics.geography.map.GeographicMapCellType;

public class BasicPlatormGeographicMapCellType extends GeographicMapCellType
{
    private final int[] types;

    public BasicPlatormGeographicMapCellType(int type) {
        super(type);
        this.types = new int[1];
        this.types[0] = type;
    }
    
    public BasicPlatormGeographicMapCellType(int[] types) {
        super(Integer.MIN_VALUE);
        this.types = types;
    }

    public boolean isType(final GeographicMapCellType type) {
        return this.isType(type.getType());
    }

    public boolean isType(final int type) {
        final int size = types.length;
        for(int index = 0; index < size; index++) {
            if(types[index] == type) {
                return true;
            }
        }
        return false;
    }
}
