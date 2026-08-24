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
package org.allbinary.physics.movement;

import jsinterop.annotations.JsType;

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.logic.math.BasicDecimal;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author user
 */

@JsType
public interface MovementInterface
{
    @JsMethod
    void init(BasicDecimal speedBasicDecimal, int angle, int otherAngle);
    @JsMethod
    void process(AllBinaryGameLayer layer) throws Exception;
    @JsMethod
    void stop();
}
