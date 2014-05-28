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
package allbinary.input.motion.button;

import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.graphics.displayable.DisplayInfoSingleton;

public class CommonButtons
{
    private static final CommonButtons instance = new CommonButtons();

    public static CommonButtons getInstance()
    {
        return instance;
    }
    
    public final int STANDARD_BUTTON_SIZE;

    public final Rectangle NORMAL_BUTTON;
    public final Rectangle LARGE_BUTTON;
    
    private CommonButtons()
    {
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        int commonButtonSize = 128;

        while(commonButtonSize >= 64)
        {
            int totalColumns = displayInfo.getLastWidth() / commonButtonSize;
            int totalRows = displayInfo.getLastHeight() / commonButtonSize;        

            int max = totalColumns;
            if(totalRows > max)
            {
                max = totalRows;
            }

            //PreLogUtil.put("width:" + displayInfo.getLastWidth() + " h:"  + displayInfo.getLastHeight()  + "max:" + max, this, "CommonButtons");

            if(max > 9)
            {
                break;
            }
            
            commonButtonSize = commonButtonSize >> 1;
        }

        STANDARD_BUTTON_SIZE = commonButtonSize;

        NORMAL_BUTTON = new Rectangle(PointFactory.getInstance().ZERO_ZERO, 
                STANDARD_BUTTON_SIZE, STANDARD_BUTTON_SIZE);

        LARGE_BUTTON = new Rectangle(PointFactory.getInstance().ZERO_ZERO, 
                STANDARD_BUTTON_SIZE << 1, STANDARD_BUTTON_SIZE << 1);
    }
        
}
