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

import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListS;

public class BasicPlatormGeographicMapCellType //extends GeographicMapCellType
{
    private final int[] types;

    public static BasicPlatormGeographicMapCellType createType(final int type) {
        //super(type);
        final BasicArrayList types = new BasicArrayListS(1);
        types.add(SmallIntegerSingletonFactory.getInstance().getAt(type));
        final BasicPlatormGeographicMapCellType basicPlatormGeographicMapCellType = new BasicPlatormGeographicMapCellType(types);
        //this.types = new int[1];
        //this.types[0] = type;
        return basicPlatormGeographicMapCellType;
    }
    
//    public BasicPlatormGeographicMapCellType(final int[] types) {
//        //super(Integer.MIN_VALUE);
//
//        final int size = types.length;
//        for(int index = 0; index < size; index++) {
//            new GeographicMapCellType(types[index]);
//        }
//        this.types = types;
//    }

    public BasicPlatormGeographicMapCellType(final BasicArrayList types) {
        //super(Integer.MIN_VALUE);
        
        final int size = types.size();
        final int[] typeArray = new int[size];
        Integer typeAsInteger;
        int type;
        for(int index = 0; index < size; index++) {
            typeAsInteger = ((Integer) types.get(index));
            type = typeAsInteger.intValue();
            new GeographicMapCellType(type, 0);
            typeArray[index] = type;
        }
        
        this.types = typeArray;
    }
    
    public boolean isType(final GeographicMapCellType type) {
        return this.hasType(type.getType());
    }

    public boolean hasType(final int type) {
        final int size = types.length;
        for(int index = 0; index < size; index++) {
            if(this.types[index] == type) {
                return true;
            }
        }
        return false;
    }
}
