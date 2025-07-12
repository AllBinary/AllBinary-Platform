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
package org.allbinary.input.motion.button;

import org.allbinary.game.input.InputFactory;

public class CancelTouchButtonInputFactory
{
    private static final CancelTouchButtonInputFactory instance = 
        new CancelTouchButtonInputFactory();
        
    public static CancelTouchButtonInputFactory getInstance()
    {
        return instance;
    }
    
    private final TouchButtonInput[] cancelInputArray = new TouchButtonInput[InputFactory.getInstance().MAX];
    
    private CancelTouchButtonInputFactory()
    {
        BasicTouchInputFactory basicTouchInputFactory = 
            BasicTouchInputFactory.getInstance();
        
        for(int index = cancelInputArray.length - 1; index >= 0; index--)
        {
            cancelInputArray[index] = basicTouchInputFactory.NONE; 
        }
        
        cancelInputArray[basicTouchInputFactory.UP.getId()] = basicTouchInputFactory.DOWN;
        cancelInputArray[basicTouchInputFactory.DOWN.getId()] = basicTouchInputFactory.UP;
        
        cancelInputArray[basicTouchInputFactory.LEFT.getId()] = basicTouchInputFactory.RIGHT;
        cancelInputArray[basicTouchInputFactory.RIGHT.getId()] = basicTouchInputFactory.LEFT;
    }
    
    public TouchButtonInput getCancel(TouchButtonInput touchButtonInput)
    {
        return cancelInputArray[touchButtonInput.getId()];
    }
}
