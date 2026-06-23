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

package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class MyFormUtil
{

    private static final MyFormUtil instance = new MyFormUtil();

    public static MyFormUtil getInstance()
    {
        return MyFormUtil.instance;
    }

    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    // Popup Menu Tab Init
    private Rectangle popupMenuRectangle = RectangleFactory.SINGLETON;

    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        
        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        final int width = MyFontProcessor.defaultStringWidth(font, 3);

        final PointFactory pointFactory = PointFactory.getInstance();
                
        // Popup Menu Tab Init
        if (displayInfo.getLastHeight() < 320)
        {
            // Make a smaller button for QVGA and move it to the top
            this.popupMenuRectangle = new Rectangle(
                    pointFactory.createXY(0, 25), width, (font.getHeight() * 4) + 2);
            
            //this.logUtil.putF("popupMenuRectangle: " + this.popupMenuRectangle.toString(), this, this.commonStrings.PROCESS);
        }
        else
        {
            this.popupMenuRectangle = new Rectangle(
                pointFactory.createXY(0, displayInfo.getLastHalfHeight() - 70), width, (font.getHeight() * 5));
            
            //this.logUtil.putF("large popupMenuRectangle: " + this.popupMenuRectangle.toString(), this, this.commonStrings.PROCESS);
        }

    }
    
    public Rectangle getPopupMenuRectangle()
    {
        //this.logUtil.putF("return popupMenuRectangle: " + this.popupMenuRectangle.toString(), this, this.commonStrings.PROCESS);
        return this.popupMenuRectangle;
    }

}
