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
package org.allbinary.animation;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.LocationPaintableInterface;
import jsinterop.annotations.JsMethod;


@JsType
public interface AnimationInterface extends LocationPaintableInterface
{
    @JsMethod
    void nextFrame() throws Exception;
    @JsMethod
    void paintThreedXYZ(Graphics graphics, int x, int y, int z);
}
