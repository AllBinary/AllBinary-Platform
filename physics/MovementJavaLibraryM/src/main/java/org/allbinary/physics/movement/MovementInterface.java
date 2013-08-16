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

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.logic.math.BasicDecimal;

/**
 *
 * @author user
 */
public interface MovementInterface
{
    void init(BasicDecimal speedBasicDecimal, short angle, short otherAngle);
    void process(AllBinaryGameLayer layer)throws Exception;
    void stop();
}
