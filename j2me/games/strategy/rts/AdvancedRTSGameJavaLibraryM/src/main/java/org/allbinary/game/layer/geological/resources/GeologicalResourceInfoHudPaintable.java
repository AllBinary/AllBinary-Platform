/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

package org.allbinary.game.layer.geological.resources;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.waypoint.WaypointInfoHudPaintable;
import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.draw.KeyValueDrawCharArray;
import org.allbinary.graphics.font.MyFont;

/**
 *
 * @author user
 */
public class GeologicalResourceInfoHudPaintable 
    extends WaypointInfoHudPaintable
{
    private final KeyValueDrawCharArray keyvalueDrawString;

    private static final String RESOURCES = "Resources: ";
    
    protected GeologicalResourceInfoHudPaintable()
    {
        keyvalueDrawString = new KeyValueDrawCharArray(RESOURCES, this.textX);
    }

    public void updateSelectionInfo()
    {
        super.updateSelectionInfo();
        
        BasicArrayList list = this.getRtsLayer().geographicMapCellPositionArea
                .getOccupyingGeographicMapCellPositionList();

        int total = 0;
        for (int index = list.size(); --index >= 0;)
        {
            GeologicalGeographicMapCellPosition geographicMapCellPosition = 
                (GeologicalGeographicMapCellPosition) list.get(index);

            total += geographicMapCellPosition.getGeologicalResource().getTotal();
        }

        this.keyvalueDrawString.update(this.getPrimitiveLongUtil().getCharArray(total), this
                .getPrimitiveLongUtil().getCurrentTotalDigits());
    }
    
    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        this.keyvalueDrawString.paint(graphics, (y + (2 * MyFont.getInstance().DEFAULT_CHAR_HEIGHT)));
    }
}
