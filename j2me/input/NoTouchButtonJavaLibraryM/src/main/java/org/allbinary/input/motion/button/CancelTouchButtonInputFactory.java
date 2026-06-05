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
import org.allbinary.logic.NullUtil;

public class CancelTouchButtonInputFactory
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
        
    public static CancelTouchButtonInputFactory getInstance()
    {
        if(CancelTouchButtonInputFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            CancelTouchButtonInputFactory.instance = new CancelTouchButtonInputFactory();
        }

        return (CancelTouchButtonInputFactory) CancelTouchButtonInputFactory.instance;
    }
    
    private final TouchButtonInput[] cancelInputArray = new TouchButtonInput[InputFactory.getInstance().MAX];
    
    private CancelTouchButtonInputFactory()
    {
        final BasicTouchInputFactory basicTouchInputFactory = 
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
        return this.cancelInputArray[touchButtonInput.getId()];
    }
}
