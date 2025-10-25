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

import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvasInterface;
import org.allbinary.layer.AllBinaryLayer;

public class GeographicMapCellPositionTracking
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private GeographicMapCellPosition currentGeographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
    private GeographicMapCellPosition previousGeographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
    private GeographicMapCellPosition newGeographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
    private GeographicMapCellType currentCellType = GeographicMapCellType.NULL_GEOGRAPHIC_MAP_CELL_TYPE;
    
    public GeographicMapCellPositionTracking()
    {
    }
        
    public GeographicMapCellType getCurrentOverCellType(final BasicGeographicMap geographicMap) 
    throws Exception
    {
        final GeographicMapCellPosition geographicMapCellPosition = 
            this.currentGeographicMapCellPosition;

        if (// geographicMapCellPosition != null &&
            geographicMapCellPosition != this.previousGeographicMapCellPosition)
        {
            // Get the location of this vehicle relative to the top righ corner of the tileLayer location
            this.currentCellType = geographicMap.getCellTypeAt(geographicMapCellPosition);
            // logUtil.put("currentCellType: " + currentCellType, this, "getCurrentCellType");
        }
        return this.currentCellType;
    }

    public boolean updateStart(
            final AllBinaryGameCanvasInterface gameCanvasInterface, 
            final AllBinaryLayer layer)
    throws Exception
    {
        //Check to see if initialized yet - only a problem because Layer calls it
        if (gameCanvasInterface != null)
        {
            final GeographicMapCompositeInterface geographicMapCompositeInterface = 
                (GeographicMapCompositeInterface) gameCanvasInterface.getLayerManager();

            final BasicGeographicMap geographicMapInterface = 
                geographicMapCompositeInterface.getGeographicMapInterface()[0];

            this.newGeographicMapCellPosition = 
                geographicMapInterface.getCellPositionAt(
                    layer.getXP() + layer.getHalfWidth(), 
                    layer.getYP() + layer.getHalfHeight());

            if (this.newGeographicMapCellPosition != this.currentGeographicMapCellPosition)
            {
                this.currentGeographicMapCellPosition = this.newGeographicMapCellPosition;
                
                return true;
            }
        }
        return false;
    }
        
    public boolean updateEnd()
    {
        if(this.currentGeographicMapCellPosition != this.previousGeographicMapCellPosition)
        {
            this.previousGeographicMapCellPosition = this.currentGeographicMapCellPosition;

            return true;
        }

        return false;
    }

    public GeographicMapCellPosition getPreviousGeographicMapCellPosition()
    {
        return previousGeographicMapCellPosition;
    }
    
    public GeographicMapCellPosition getCurrentGeographicMapCellPosition()
    {
        return currentGeographicMapCellPosition;
    }
}