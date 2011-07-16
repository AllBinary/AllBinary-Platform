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
package allbinary.media.graphics.geography.map;

public class GeographicMapCellTypeFactory
{
    private static final GeographicMapCellTypeFactory instance = new GeographicMapCellTypeFactory();
    
    public static GeographicMapCellTypeFactory getInstance()
    {
        return instance;
    }

    private final GeographicMapCellType[] geographicMapCellTypeArray = new GeographicMapCellType[20];
    
    public GeographicMapCellType EMPTY_CELL_TYPE;
    public GeographicMapCellType EASY_CELL_TYPE;
    //new GeographicMapCellType(SmallIntegerSingletonFactory.getInstance(0));
    
    public GeographicMapCellType getInstance(int type)
    {
       return getGeographicMapCellTypeArray()[type];
    }

    public GeographicMapCellType[] getGeographicMapCellTypeArray()
    {
        return geographicMapCellTypeArray;
    }

}
