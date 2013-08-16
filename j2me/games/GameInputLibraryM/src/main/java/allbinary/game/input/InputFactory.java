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
package allbinary.game.input;

import allbinary.logic.math.SmallIntegerSingletonFactory;

public class InputFactory
{
    private final static InputFactory instance = new InputFactory();

    public static InputFactory getInstance()
    {
        return instance;
    }

    //TWB - Should be seperated into an initializable class with specific size for a platform
    //public final int MAX = SmallIntegerSingletonFactory.MIN;
    //SmallIntegerSingletonFactory.init(0x101);
    //VK_CONTEXT_MENU
    public final int MAX = SmallIntegerSingletonFactory.getInstance().MIN;
    //protected final Input[] negativeInputIntegerArray = new Input[6];
    protected final Input[] inputIntegerArray = new Input[this.MAX];

    public void add(int id, Input input)
    {
        this.inputIntegerArray[id] = input;
    }
    
    public Input getInstance(int id)
    {
        if(id < 0)
        {
            id = -id;
        }

        return inputIntegerArray[id];
        /*
        if(id >= 0)
        {
            return Input.inputIntegerArray[id];
        }
        else
        {
            return Input.negativeInputIntegerArray[-id];
        }
        */
    }
}
