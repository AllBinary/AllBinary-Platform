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
package org.allbinary.game.layer.special;

import jsinterop.annotations.JsType;

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.layer.AllBinaryLayerManager;
import jsinterop.annotations.JsMethod;


@JsType
public interface SpecialGameInputInterface extends PaintableInterface {

    @JsMethod
    void up() throws Exception;
    
    @JsMethod
    void down() throws Exception;

    @JsMethod
    void right() throws Exception;

    @JsMethod
    void left() throws Exception;

    @JsMethod
    void strafeLeft() throws Exception;

    @JsMethod
    void strafeRight() throws Exception;
    
    @JsMethod
    void fire(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    @JsMethod
    void special1(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    @JsMethod
    void special2(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    @JsMethod
    void special3(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    @JsMethod
    void special4(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    @JsMethod
    void special5(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;
    
}
