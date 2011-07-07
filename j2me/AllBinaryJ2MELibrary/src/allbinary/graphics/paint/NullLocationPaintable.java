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
package allbinary.graphics.paint;

public class NullLocationPaintable extends LocationPaintable
{
    private static final NullLocationPaintable instance = new NullLocationPaintable();

    public static NullLocationPaintable getInstance()
    {
        return instance;
    }
}
