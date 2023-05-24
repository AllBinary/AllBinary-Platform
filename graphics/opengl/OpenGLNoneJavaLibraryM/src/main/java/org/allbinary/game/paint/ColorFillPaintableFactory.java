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
package org.allbinary.game.paint;

import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.graphics.color.BasicColor;

public class ColorFillPaintableFactory
{
    public static ColorFillBasePaintable getInstance(final BasicColor basicColor, final boolean forThreedCanvas)
    {
        final OperatingSystemInterface operatingSystem = 
                OperatingSystemFactory.getInstance().getOperatingSystemInstance();
        
        if(operatingSystem.isOverScan())
        {
            return new OverScanColorFillPaintable(basicColor);
        }
        else
        {
            return new ColorFillPaintable(basicColor);
        }
    }
}
