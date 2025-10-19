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
package org.allbinary.media.graphics.geography.map.drop;

import org.allbinary.util.BasicArrayList;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.NullUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;

public class AnyRandomDropCellPositionGenerator
extends BaseDropCellPositionGenerator
{
    protected final BasicArrayList list = new BasicArrayList();
    protected Object geographicMapInterface = NullUtil.getInstance().NULL_OBJECT;
    
    @Override
    public void update(final AllBinaryGameLayerManager allBinaryGameLayerManager,
            final BasicGeographicMap geographicMapInterface) throws Exception
    {
        this.geographicMapInterface = geographicMapInterface;
        
        final BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory =
                geographicMapInterface.getGeographicMapCellPositionFactory();
        
        final MyRandomFactory randomFactory = MyRandomFactory.getInstance();
        
        final AllBinaryTiledLayer tiledLayer = 
                geographicMapInterface.getAllBinaryTiledLayer();
        
        final int total = tiledLayer.getColumns() * tiledLayer.getRows();
        
        int randomColumn;
        int randomRow;
        GeographicMapCellPosition geographicMapCellPosition;

        //Create a list of random drops
        for(int index = total; --index >= 0;)
        {
            randomColumn = randomFactory.getAbsoluteNextInt(tiledLayer.getColumns());
            randomRow = randomFactory.getAbsoluteNextInt(tiledLayer.getRows());
            
            geographicMapCellPosition = 
                    basicGeographicMapCellPositionFactory.getInstance(
                            randomColumn, randomRow);

            //!this.list.contains(geographicMapCellPosition)
            if(this.isDropAllowedAt(geographicMapCellPosition))
            {
                this.list.add(geographicMapCellPosition);
            }
        }
    }

    @Override
    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        
    }
}
