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
package org.allbinary.game.view;

import org.allbinary.game.view.TileLayerPositionIntoViewPosition;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;

public class TileLayerPositionIntoViewPositionUtil
{
    public static void init(
            AllBinaryGameCanvas gameCanvasInterface,
            TileLayerPositionIntoViewPosition viewPosition)
    {
        GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) gameCanvasInterface.getLayerManager();

        BasicGeographicMap geographicMapInterface =
            geographicMapCompositeInterface.getGeographicMapInterface();
        
        AllBinaryTiledLayer tiledLayer = 
            geographicMapInterface.getAllBinaryTiledLayer();

        viewPosition.setTiledLayer(tiledLayer);
    }
}
