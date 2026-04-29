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
        return CancelTouchButtonInputFactory.instance;
    }
    
    private final TouchButtonInput[] cancelInputArray = new TouchButtonInput[InputFactory.getInstance().MAX];
    
    private CancelTouchButtonInputFactory()
    {
        BasicTouchInputFactory basicTouchInputFactory = 
            BasicTouchInputFactory.getInstance();
        
        for(int index = this.cancelInputArray.length - 1; index >= 0; index--)
        {
            this.cancelInputArray[index] = basicTouchInputFactory.NONE; 
        }
        
        this.cancelInputArray[basicTouchInputFactory.UP.getId()] = basicTouchInputFactory.DOWN;
        this.cancelInputArray[basicTouchInputFactory.DOWN.getId()] = basicTouchInputFactory.UP;
        
        this.cancelInputArray[basicTouchInputFactory.LEFT.getId()] = basicTouchInputFactory.RIGHT;
        this.cancelInputArray[basicTouchInputFactory.RIGHT.getId()] = basicTouchInputFactory.LEFT;
    }
    
    public TouchButtonInput getCancel(TouchButtonInput touchButtonInput)
    {
        return cancelInputArray[touchButtonInput.getId()];
    }
}
