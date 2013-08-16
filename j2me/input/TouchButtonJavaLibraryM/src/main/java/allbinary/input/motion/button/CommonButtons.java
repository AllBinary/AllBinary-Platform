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

public class CommonButtons
{
    private static final CommonButtons instance = new CommonButtons();

    public static CommonButtons getInstance()
    {
        return instance;
    }
    
    public Rectangle NORMAL_BUTTON = new Rectangle(PointFactory.getInstance().ZERO_ZERO,
            TouchButtonInput.STANDARD_BUTTON_SIZE, TouchButtonInput.STANDARD_BUTTON_SIZE);

    public Rectangle LARGE_BUTTON = new Rectangle(PointFactory.getInstance().ZERO_ZERO, 128, 128);
}
