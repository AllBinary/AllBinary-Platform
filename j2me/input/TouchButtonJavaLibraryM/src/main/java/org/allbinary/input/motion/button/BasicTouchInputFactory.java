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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;

public class BasicTouchInputFactory
// extends BaseTouchInputFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BasicTouchInputFactory SINGLETON = new BasicTouchInputFactory();

    public static BasicTouchInputFactory getInstance()
    {
        return SINGLETON;
    }

    public final TouchButtonInput SPECIAL_BUTTON_SEVEN_TESTING_ONLY;

    public final TouchButtonInput SPECIAL_BUTTON_EIGHT_TESTING_ONLY;

    public final TouchButtonInput SPECIAL_BUTTON_SIX;

    public final TouchButtonInput SPECIAL_BUTTON_FIVE;

    public final TouchButtonInput SPECIAL_BUTTON_FOUR;
    public final TouchButtonInput SPECIAL_BUTTON_THREE;

    public final TouchButtonInput SPECIAL_BUTTON_TWO;
    public final TouchButtonInput SPECIAL_BUTTON_ONE;

    public final TouchButtonInput UP;
    public final TouchButtonInput LEFT;
    public final TouchButtonInput RIGHT;
    public final TouchButtonInput DOWN;

    public final TouchButtonInput NONE;

    private boolean initialized = false;

    private final BasicArrayList list = new BasicArrayList();

    private BasicTouchInputFactory()
    {
        int MAX = InputFactory.getInstance().MAX;

        SPECIAL_BUTTON_SEVEN_TESTING_ONLY = new TouchButtonInput(MAX - 41,
                "Button 7 - Testing Only May Cross Over Key Values");

        SPECIAL_BUTTON_EIGHT_TESTING_ONLY = new TouchButtonInput(MAX - 42,
                "Button 8 - Testing Only May Cross Over Key Values");

        SPECIAL_BUTTON_SIX = new TouchButtonInput(MAX - 30, "Button 6");

        SPECIAL_BUTTON_FIVE = new TouchButtonInput(MAX - 31, "Button 5");

        SPECIAL_BUTTON_FOUR = new TouchButtonInput(MAX - 32, "Button 4");
        SPECIAL_BUTTON_THREE = new TouchButtonInput(MAX - 33, "Button 3");

        SPECIAL_BUTTON_TWO = new TouchButtonInput(MAX - 34, "Button 2");
        SPECIAL_BUTTON_ONE = new TouchButtonInput(MAX - 35, "Button 1");

        UP = new TouchButtonInput(MAX - 36, "Up Button");
        LEFT = new TouchButtonInput(MAX - 37, "Left Button");
        RIGHT = new TouchButtonInput(MAX - 38, "Right Button");
        DOWN = new TouchButtonInput(MAX - 39, "Down Button");

        NONE = new TouchButtonInput(MAX - 40, "No Button");
    }

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
            list.add(SPECIAL_BUTTON_SIX);
            list.add(SPECIAL_BUTTON_SEVEN_TESTING_ONLY);
            list.add(SPECIAL_BUTTON_EIGHT_TESTING_ONLY);
            this.updateAll(list, inputToGameKeyMapping);

            CancelTouchButtonInputFactory.getInstance();
        }
    }

    public void updateAll(InputToGameKeyMapping inputToGameKeyMapping)
    {
        this.updateAll(this.getList(), inputToGameKeyMapping);
    }

    public void updateAll(BasicArrayList list,
            InputToGameKeyMapping inputToGameKeyMapping)
    {
        logUtil.put(
                new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(list.size()).toString(), this,
                "updateAll");

        TouchButtonInput touchButtonInput;

        for (int index = list.size() - 1; index >= 0; index--)
        {
            touchButtonInput = (TouchButtonInput) list.objectArray[index];
            touchButtonInput.update(inputToGameKeyMapping);
        }
    }

    public BasicArrayList getList()
    {
        return list;
    }
}
