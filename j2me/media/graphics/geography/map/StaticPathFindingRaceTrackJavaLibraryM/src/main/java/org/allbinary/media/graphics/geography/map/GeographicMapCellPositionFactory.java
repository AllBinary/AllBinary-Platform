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

import java.util.Hashtable;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.string.StringMaker;

public class GeographicMapCellPositionFactory extends GeographicMapCellPositionBaseFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final static Hashtable hashtable = new Hashtable();
    
  //For the Non Caching version - Turning off caching here requires turning of caching of paths as well
    public BasicGeographicMapCellPositionFactory getInstance(
            final BasicGeographicMap geographicMapInterface) 
        throws Exception
    {
        final AllBinaryTiledLayer allBinaryTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

        BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory = 
            (BasicGeographicMapCellPositionFactory) hashtable.get(allBinaryTiledLayer.getDataId());

        if (geographicMapCellPositionFactory != null)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(
                new StringMaker().append("Reusing GeographicMapCellPositionFactory for TileLayer: ").append(allBinaryTiledLayer.getDataId()).toString(), 
                this, commonStrings.GET_INSTANCE);

            return geographicMapCellPositionFactory;
        }
        else
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(
                new StringMaker().append("Creating GeographicMapCellPositionFactory for TileLayer: ").append(allBinaryTiledLayer.getDataId()).toString(), 
                    this,commonStrings.GET_INSTANCE);

            geographicMapCellPositionFactory = new BasicGeographicMapCellPositionFactory(
                geographicMapInterface);

            hashtable.put(allBinaryTiledLayer.getDataId(), geographicMapCellPositionFactory);
            return geographicMapCellPositionFactory;
        }
    }

    public static Hashtable getHashtable()
    {
        return hashtable;
    }
}
