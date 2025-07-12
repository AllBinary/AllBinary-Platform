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
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.util.BasicArrayList;

public class BasicTouchInputFactory
        //extends BaseTouchInputFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BasicTouchInputFactory SINGLETON =
            new BasicTouchInputFactory();

    public final TouchButtonInput SPECIAL_BUTTON_SIX =
        new TouchButtonInput(InputFactory.getInstance().MAX - 30, "Button 6");
    
    public final TouchButtonInput SPECIAL_BUTTON_FIVE =
        new TouchButtonInput(InputFactory.getInstance().MAX - 31, "Button 5");

    public final TouchButtonInput SPECIAL_BUTTON_FOUR =
       new TouchButtonInput(InputFactory.getInstance().MAX - 32, "Button 4");
    public final TouchButtonInput SPECIAL_BUTTON_THREE =
       new TouchButtonInput(InputFactory.getInstance().MAX - 33, "Button 3");
    
    public final TouchButtonInput SPECIAL_BUTTON_TWO =
       new TouchButtonInput(InputFactory.getInstance().MAX - 34, "Button 2");
    public final TouchButtonInput SPECIAL_BUTTON_ONE =
       new TouchButtonInput(InputFactory.getInstance().MAX - 35, "Button 1");

    public final TouchButtonInput UP = new TouchButtonInput(InputFactory.getInstance().MAX - 36, "Up Button");
    public final TouchButtonInput LEFT = new TouchButtonInput(InputFactory.getInstance().MAX - 37, "Left Button");
    public final TouchButtonInput RIGHT = new TouchButtonInput(InputFactory.getInstance().MAX - 38, "Right Button");
    public final TouchButtonInput DOWN = new TouchButtonInput(InputFactory.getInstance().MAX - 39, "Down Button");

    public final TouchButtonInput NONE = new TouchButtonInput(InputFactory.getInstance().MAX - 40, "No Button");
    
    public static BasicTouchInputFactory getInstance()
    {
        return SINGLETON;
    }

    private boolean initialized = false;
    
    private final BasicArrayList list = new BasicArrayList();
    public synchronized void init(InputToGameKeyMapping inputToGameKeyMapping)
    {
        if (!initialized)
        {
            initialized = true;
            
            list.add(UP);
            list.add(LEFT);
            list.add(RIGHT);
            list.add(DOWN);
            list.add(SPECIAL_BUTTON_TWO);
            list.add(SPECIAL_BUTTON_FOUR);
            list.add(SPECIAL_BUTTON_THREE);
            list.add(SPECIAL_BUTTON_ONE);
            list.add(SPECIAL_BUTTON_FIVE);
            list.add(SPECIAL_BUTTON_SIX);
            this.updateAll(list, inputToGameKeyMapping);
            
            CancelTouchButtonInputFactory.getInstance();
        }
    }

    public void updateAll(InputToGameKeyMapping inputToGameKeyMapping)
    {
        this.updateAll(this.getList(), inputToGameKeyMapping);
    }
    
    public void updateAll(BasicArrayList list, InputToGameKeyMapping inputToGameKeyMapping)
    {
        logUtil.put(new StringMaker().append(CommonLabels.getInstance().START).append(list.size()).toString(), this, "updateAll");

        for (int index = list.size() - 1; index >= 0; index--)
        {
            TouchButtonInput touchButtonInput = 
                (TouchButtonInput) list.get(index);
            touchButtonInput.update(inputToGameKeyMapping);
        }
    }
    
    public BasicArrayList getList()
    {
        return list;
    }
}
