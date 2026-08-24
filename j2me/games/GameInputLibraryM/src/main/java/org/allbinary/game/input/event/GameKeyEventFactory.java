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
package org.allbinary.game.input.event;

import jsinterop.annotations.JsType;

import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameKeyEventFactory
{

    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    @JsMethod
    public static GameKeyEventFactory getInstance()
    {
        if(GameKeyEventFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            GameKeyEventFactory.instance = new GameKeyEventFactory();
        }

        return (GameKeyEventFactory) GameKeyEventFactory.instance;
    }

    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    public final int TOUCH_BUTTON_SOURCE_ID = 2;
    @JsProperty
    public final int MOTION_GESTURE_SOURCE_ID = 3;
    
    private final int MAX_SOURCES = 4;

    //private final String SOURCE_ID = "sourceId";    
    private GameKeyEvent[][] ARRAY = new GameKeyEvent[this.MAX_SOURCES][InputFactory.getInstance().MAX];
    //private GameKeyEvent[] ARRAY = new GameKeyEvent[MAX];

    @JsConstructor
    private GameKeyEventFactory()
    {
    }

    @JsMethod
    public void init()
    {
        //this.logUtil.putF(this.commonStrings.START, "GameKeyEventFactory", commonStrings.INIT);
        
        final NullUtil nullUtil = NullUtil.getInstance();
        
        /*
        for (int index2 = 0; index2 < MAX; index2++)
        {
            this.ARRAY[index2] = new GameKeyEvent(null, index2);
        }
        */
        
        final int size = InputFactory.getInstance().MAX;
        for (int index = this.MAX_SOURCES; --index >= 0;)
        {
            for (int index2 = size; --index2 >= 0;)
            {
                //this.logUtil.putF("TWB index2: " + index2 + " src: " + index, this, "getInstanceForKey");
                this.ARRAY[index][index2] = GameKeyEvent.createEvent(nullUtil.NULL_OBJECT, index, index2);
            }
        }
    }

    @JsMethod
    public GameKeyEvent getInstanceForKey(final GameKeyEventSourceInterface object, final int key)
    throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START, this, SOURCE_ID + object.getSourceId());

        final GameKeyEvent gameKeyEvent = this.ARRAY[object.getSourceId()][key];
        //GameKeyEvent gameKeyEvent = ARRAY[key];
        //gameKeyEvent.init(object);
        return gameKeyEvent;
    }

    @JsMethod
    public GameKeyEvent getInstanceForInput(final GameKeyEventSourceInterface object, final Input input)
    throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START_LABEL, this, SOURCE_ID + object.getSourceId());

        final GameKeyEvent gameKeyEvent = this.ARRAY[object.getSourceId()][input.getId()];
        //GameKeyEvent gameKeyEvent = ARRAY[gameKey.getKey().intValue()];
        //gameKeyEvent.init(object);
        return gameKeyEvent;
    }

    /*
    public GameKeyEvent getInstance(GameKeyEventSourceInterface object,
            int originalKey, int gameActionKey, int key) throws Exception
    {
        GameKeyEvent gameKeyEvent = getInstance(object, key);

        gameKeyEvent.setOriginalKey(originalKey);
        gameKeyEvent.setGameActionKey(gameActionKey);

        return gameKeyEvent;
    }
    */
}
