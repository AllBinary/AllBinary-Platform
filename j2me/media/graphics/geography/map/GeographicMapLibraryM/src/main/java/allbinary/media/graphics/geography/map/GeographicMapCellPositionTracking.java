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

import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.layer.AllBinaryLayer;

public class GeographicMapCellPositionTracking
{
    private GeographicMapCellPosition currentGeographicMapCellPosition;
    private GeographicMapCellPosition previousGeographicMapCellPosition;
    private GeographicMapCellPosition newGeographicMapCellPosition;
    private GeographicMapCellType currentCellType;
    
    public GeographicMapCellPositionTracking()
    {
    }
        
    public GeographicMapCellType getCurrentOverCellType(
            BasicGeographicMap geographicMap) 
    throws Exception
    {
        GeographicMapCellPosition geographicMapCellPosition = 
            this.currentGeographicMapCellPosition;

        if (// geographicMapCellPosition != null &&
            geographicMapCellPosition != this.previousGeographicMapCellPosition)
        {
            // Get the location of this vehicle relative to the top righ corner of the tileLayer location
            this.currentCellType = geographicMap.getCellTypeAt(geographicMapCellPosition);
            // LogUtil.put(LogFactory.getInstance("currentCellType: " + currentCellType, this, "getCurrentCellType"));
        }
        return this.currentCellType;
    }

    public boolean updateStart(
            AllBinaryGameCanvas gameCanvasInterface, 
            AllBinaryLayer layer)
    throws Exception
    {
        //Check to see if initialized yet - only a problem because Layer calls it
        if (gameCanvasInterface != null)
        {
            GeographicMapCompositeInterface geographicMapCompositeInterface = 
                (GeographicMapCompositeInterface) gameCanvasInterface.getLayerManager();

            BasicGeographicMap geographicMapInterface = 
                geographicMapCompositeInterface.getGeographicMapInterface();

            this.newGeographicMapCellPosition = 
                geographicMapInterface.getCellPositionAt(
                    layer.getX() + layer.getHalfWidth(), 
                    layer.getY() + layer.getHalfHeight());

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