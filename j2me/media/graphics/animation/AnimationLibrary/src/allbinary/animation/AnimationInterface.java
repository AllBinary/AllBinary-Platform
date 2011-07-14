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
package allbinary.animation;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.paint.LocationPaintableInterface;

public interface AnimationInterface extends LocationPaintableInterface
{
    void nextFrame() throws Exception;
    void paintThreed(Graphics graphics, int x, int y, int z);
}
