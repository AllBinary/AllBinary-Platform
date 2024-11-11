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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GeographicMapCellTypeFactory
{
    private static final GeographicMapCellTypeFactory instance = new GeographicMapCellTypeFactory();
    
    public static GeographicMapCellTypeFactory getInstance()
    {
        return instance;
    }

    private final GeographicMapCellType[] geographicMapCellTypeArray = new GeographicMapCellType[300];
        
    public GeographicMapCellType getInstance(int type)
    {
//        final GeographicMapCellType geographicMapCellType = geographicMapCellTypeArray[type];
//        if(geographicMapCellType == null) {
//            LogUtil.put(LogFactory.getInstance("missing type: " + type, this, CommonStrings.getInstance().PROCESS));
//            throw new RuntimeException();
//        }
//        return geographicMapCellType;
        return geographicMapCellTypeArray[type];
    }

    public GeographicMapCellType[] getGeographicMapCellTypeArray()
    {
        return geographicMapCellTypeArray;
    }

    public int getStartType() {
        throw new RuntimeException();
    }

    public int getEndType() {
        throw new RuntimeException();
    }
    
    public int getEmptyType() {
        throw new RuntimeException();
    }

    public boolean isPath(GeographicMapCellType cellType) {
        throw new RuntimeException();
    }

}
