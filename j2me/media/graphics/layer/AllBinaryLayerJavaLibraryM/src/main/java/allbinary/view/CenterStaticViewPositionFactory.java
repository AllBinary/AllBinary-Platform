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
package allbinary.view;

import allbinary.graphics.displayable.DisplayInfoSingleton;

public class CenterStaticViewPositionFactory
{
    /*
    private final int xOffset;
    private final int yOffset;
    
    public CenterStaticViewPositionFactory(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    */

    public CenterStaticViewPositionFactory()
    {
    }
    
    public ViewPosition getInstance(int z)
    {
        DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
        
        //return new StaticViewPosition((displayInfoSingleton.getLastWidth() >> 1) + this.xOffset, (displayInfoSingleton.getLastHeight() >> 1) + this.yOffset);
        return new StaticViewPosition((displayInfoSingleton.getLastWidth() >> 1), (displayInfoSingleton.getLastHeight() >> 1), z);
    }
}
