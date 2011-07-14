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

import allbinary.graphics.color.BasicColor;
import allbinary.graphics.paint.Paintable;

public class TouchButtonsPaintableFactory
{
    public static final Paintable getInstance(BasicColor basicColor)
    {
        return new TouchButtonsPaintable(basicColor);
    }
}
