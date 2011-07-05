/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.displayable.canvas;

import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;

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
        DisplayInfoSingleton displayInfo = 
            DisplayInfoSingleton.getInstance();
        
        // Popup Menu Tab Init
        Rectangle popupMenuRectangle = null;

        // Popup Menu Tab Init
        if (displayInfo.getLastHeight() < 320)
        {
            // Make a smaller button for QVGA and move it to the top
            popupMenuRectangle = new Rectangle(
                    PointFactory.getInstance().getInstance(0, 25), 22, (MyFont.getInstance().DEFAULT_CHAR_HEIGHT * 4) + 2);
        }
        else
        {
            popupMenuRectangle = new Rectangle(PointFactory.getInstance().getInstance(
                    0, displayInfo.getLastHalfHeight() - 70), 22, (MyFont.getInstance().DEFAULT_CHAR_HEIGHT * 5));
        }
        
        return popupMenuRectangle;
    }
}
