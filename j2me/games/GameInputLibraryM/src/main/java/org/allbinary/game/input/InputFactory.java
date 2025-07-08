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
package org.allbinary.game.input;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

public class InputFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final static InputFactory instance = new InputFactory();

    public static InputFactory getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final String KEY_CODE_LABEL = "KeyCode: ";
    public final String DEVICE_ID_LABEL = "DeviceId: ";
    
    //TWB - Should be seperated into an initializable class with specific size for a platform
    //public final int MAX = SmallIntegerSingletonFactory.MIN;
    //SmallIntegerSingletonFactory.init(0x101);
    //VK_CONTEXT_MENU
    public final int MAX = SmallIntegerSingletonFactory.getInstance().MIN;
    //protected final Input[] negativeInputIntegerArray = new Input[6];
    protected final Input[] inputIntegerArray = new Input[this.MAX];

    public final Input NO_INPUT = new Input(0, commonStrings.UNKNOWN);
    
    private InputFactory() {
        
        final int size = inputIntegerArray.length;
        //logUtil.put(new StringMaker().append("size: ").append(size).toString(), this, commonStrings.CONSTRUCTOR);
        for(int index = 0; index < size; index++) {
            inputIntegerArray[index] = NO_INPUT;
        }
    }

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

        if(id > inputIntegerArray.length) {
            //Using the Exception for HTML5 build causes the game to hang.
            //logUtil.put(new StringMaker().append("id: ").append(id).toString(), this, commonStrings.GET_INSTANCE, new Exception());
            logUtil.put(new StringMaker().append("Warning id: ").append(id).toString(), this, commonStrings.GET_INSTANCE);
            return NO_INPUT;
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
