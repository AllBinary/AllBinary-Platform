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

import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;

public class FormUtil
{
    private static final FormUtil instance = new FormUtil();

    public static FormUtil getInstance()
    {
        return instance;
    }

    public Rectangle createFormRectangle()
    {
        DisplayInfoSingleton displayInfo = 
            DisplayInfoSingleton.getInstance();
        
        Rectangle rectangle = new Rectangle(
        // PointFactory.getInstance(0, 30),
                PointFactory.getInstance().getInstance(30, 10),
                // displayInfo.getLastWidth(),
                displayInfo.getLastWidth() - 30,
                displayInfo.getLastHeight() - 35);
        
        return rectangle;
    }

    public Rectangle createPopupMenuRectangle()
    {
        final DisplayInfoSingleton displayInfo = 
            DisplayInfoSingleton.getInstance();
        final MyFont myFont = MyFont.getInstance();
        
        // Popup Menu Tab Init
        Rectangle popupMenuRectangle = null;

        // Popup Menu Tab Init
        if (displayInfo.getLastHeight() < 320)
        {
            // Make a smaller button for QVGA and move it to the top
            popupMenuRectangle = new Rectangle(
                    PointFactory.getInstance().getInstance(0, 25), myFont.stringWidth(4), (myFont.DEFAULT_CHAR_HEIGHT * 4) + 2);
        }
        else
        {
            popupMenuRectangle = new Rectangle(
                    PointFactory.getInstance().getInstance(
                    0, displayInfo.getLastHalfHeight() - 70), myFont.stringWidth(4), (myFont.DEFAULT_CHAR_HEIGHT * 5));
        }
        
        return popupMenuRectangle;
    }
}
