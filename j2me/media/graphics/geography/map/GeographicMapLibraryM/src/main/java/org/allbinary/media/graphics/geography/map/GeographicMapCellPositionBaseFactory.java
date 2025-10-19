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


public class GeographicMapCellPositionBaseFactory
{
    //No Cache Version
    //For the Non Caching version - Turning off caching here requires turning of caching of paths as well
    public BasicGeographicMapCellPositionFactory getInstance(
            final BasicGeographicMap geographicMapInterface) throws Exception
    {
        return new BasicGeographicMapCellPositionFactory(geographicMapInterface);
    }
}
